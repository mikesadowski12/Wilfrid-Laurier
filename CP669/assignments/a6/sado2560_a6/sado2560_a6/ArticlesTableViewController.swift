//
//  ArticlesTableViewController.swift
//  sado2560_a6
//
//  Created by Mike Sadowski on 2021-03-28.
//

import UIKit;
import os.log;
import Foundation;

let ARTICLE_SOURCE = "http://rss.cbc.ca/lineup/topstories.xml";
let ELEMENT_NAME = "item";

class TableViewCell: UITableViewCell {
    @IBOutlet weak var articleImage: UIImageView!;
    @IBOutlet weak var articleTitle: UILabel!;
    @IBOutlet weak var articleButton: UIButton!
}

class ArticlesTableViewController: UITableViewController, URLSessionTaskDelegate, XMLParserDelegate {
    private var articles = [Article]();

    private var dataStore = NSData();
    private let urlPath: String = ARTICLE_SOURCE;
    
    var processingElement = false
    var currentElement = ""
    
    var currentElementTitle = ""
    var currentElementDescription = ""
    var currentElementUrl = ""
    
    override func viewDidLoad() {
        super.viewDidLoad();
        self.navigationItem.setHidesBackButton(true, animated: true);
        UIApplication.shared.isNetworkActivityIndicatorVisible = true; // from course notes, not sure how to get warnings to disappear
        getRequest();
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return articles.count;
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        var cell = tableView.dequeueReusableCell(withIdentifier: "ArticleCell", for: indexPath)
                as? TableViewCell;

        if (cell == nil) {
          cell = TableViewCell(
            style: UITableViewCell.CellStyle.default,
            reuseIdentifier: "ArticleCell");
        }
        
        cell?.articleImage.image = articles[indexPath.row].getImage();
        cell?.articleTitle.text = articles[indexPath.row].getTitle();
        cell?.articleButton.tag = indexPath.row
        cell?.articleButton.setTitle(articles[indexPath.row].getUrl(), for: .normal);
        
        return cell!;
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        super.prepare(for: segue, sender: sender);

        switch(segue.identifier ?? "") {
            case "ShowFullArticle":
                guard let webViewController = segue.destination as? WebViewController else {
                    fatalError("Unexpected destination: \(segue.destination)");
                }
                
                guard let selectedArticleCell = sender as? UIButton else {
                    fatalError("Unexpected sender: \(sender as Optional)");
                }

                webViewController.webPageURL = selectedArticleCell.currentTitle;
                
            case "ShowArticle":
                guard let articleViewController = segue.destination as? ViewController else {
                    fatalError("Unexpected destination: \(segue.destination)");
                }
                
                guard let selectedArticleCell = sender as? TableViewCell else {
                    fatalError("Unexpected sender: \(sender as Optional)");
                }
                
                guard let indexPath = tableView.indexPath(for: selectedArticleCell) else {
                    fatalError("The selected cell is not being displayed by the table");
                }
                
                articleViewController.selectedArticle = articles[indexPath.row];
                
            default:
                fatalError("Unexpected Segue Identifier; \(segue.identifier as Optional)");
        }
    }
    
    func parseArticleText(description: String) -> String {
        let scannerStart = Scanner(string: description);
        let scannerEnd = Scanner(string: description);
        
        scannerStart.scanUpTo("<p>", into: nil); // from course notes, not sure how to get warnings to disappear
        scannerStart.scanString("<p>", into: nil) // from course notes, not sure how to get warnings to disappear
        scannerEnd.scanUpTo("</p>", into: nil); // from course notes, not sure how to get warnings to disappear
        
        let start = String.Index(encodedOffset: scannerStart.scanLocation) // from course notes, not sure how to get warnings to disappear
        let end = String.Index(encodedOffset: scannerEnd.scanLocation) // from course notes, not sure how to get warnings to disappear
        let substring = String(description[start..<end])
    
        return substring;
    }

    func parseArticleImageUrl(description: String) -> String {
        let SRC = "src=\'";
        var imgUrl: NSString?
        let quoteSet =  NSCharacterSet(charactersIn: "\'")
        let theScanner = Scanner(string: description)
        
        theScanner.scanUpTo(SRC, into: nil) // from course notes, not sure how to get warnings to disappear
        theScanner.scanString(SRC, into: nil) // from course notes, not sure how to get warnings to disappear
        theScanner.scanUpToCharacters(from: quoteSet as CharacterSet, into: &imgUrl) // from course notes, not sure how to get warnings to disappear

        return String(imgUrl!);
    }
    
    func getRequest() {
        let url: NSURL = NSURL(string: urlPath)!
        let request: URLRequest = URLRequest(url: url as URL)
        let config = URLSessionConfiguration.default
        let session = URLSession(configuration: config)
        let task = session.dataTask(
              with: request,
              completionHandler:{ (data, response, error) in
                       self.dataStore = data! as NSData
                       let _ = NSString(
                                data: self.dataStore as Data,
                                encoding: String.Encoding.utf8.rawValue)

                self.parseXML()// we got the data, now we parse it
                
                for result in self.results {
                    let imageQueue = DispatchQueue(label: "Image Queue", attributes:  .concurrent);
                    imageQueue.async {  // put the image downloading operation in a dispatch queue
                        let text = self.parseArticleText(description: result["description"] ?? "");
                        let imgUrl = self.parseArticleImageUrl(description: result["description"] ?? "");
                        let article = Article(image: UIImage(), imageUrl: imgUrl, title: result["title"] ?? "", text: text, url: result["link"] ?? "");
                        let url = NSURL(string: imgUrl)
                        let imageData  = NSData(contentsOf: url! as URL)
                        let image = UIImage(data: imageData! as Data) // get our image
                        
                        article!.setImage(image: image!);
                        self.articles.append(article!);
                        DispatchQueue.main.async {
                            UIApplication.shared.isNetworkActivityIndicatorVisible = false; // from course notes, not sure how to get warnings to disappear
                            self.tableView.reloadData()
                        };
                    }
                }
              } // completionHandler
        )//dataTask

        task.resume()
    }
    
    func parseXML() {
      let parser = XMLParser(data: dataStore as Data)
      parser.delegate = self;      // don't forget to set the delegate for the parser
      parser.parse()
    }
    
//    https://stackoverflow.com/questions/31083348/parsing-xml-from-url-in-swift/48008897
    // a few constants that identify what element names we're looking for inside the XML
    let recordKey = "item"
    let dictionaryKeys = Set<String>(["title", "link", "description"])

    // a few variables to hold the results as we parse the XML
    var results: [[String: String]] = []        // the whole array of dictionaries
    var currentDictionary: [String: String]? // the current dictionary
    var currentValue: String?                // the current value for one of the keys in the dictionary
    
    // start element
    //
    // - If we're starting a "record" create the dictionary that will hold the results
    // - If we're starting one of our dictionary keys, initialize `currentValue` (otherwise leave `nil`)
    func parser(_ parser: XMLParser, didStartElement elementName: String, namespaceURI: String?, qualifiedName qName: String?, attributes attributeDict: [String : String]) {
        if elementName == recordKey {
            currentDictionary = [:]
        } else if dictionaryKeys.contains(elementName) {
            currentValue = ""
        }
    }
                 
    // found characters
    //
    // - If this is an element we care about, append those characters.
    // - If `currentValue` still `nil`, then do nothing.
    func parser(_ parser: XMLParser, foundCharacters string: String) {
        currentValue? += string
    }
                 
    // end element
    //
    // - If we're at the end of the whole dictionary, then save that dictionary in our array
    // - If we're at the end of an element that belongs in the dictionary, then save that value in the dictionary
    func parser(_ parser: XMLParser, didEndElement elementName: String, namespaceURI: String?, qualifiedName qName: String?) {
        if elementName == recordKey {
            results.append(currentDictionary!);
            currentDictionary = nil
        } else if dictionaryKeys.contains(elementName) {
            if (currentDictionary != nil) {
                currentDictionary![elementName] = currentValue!.replacingOccurrences(of: "^\\s*", with: "", options: .regularExpression)
                currentValue = nil
            }
        }
    }
       
    func parser(_ parser: XMLParser, parseErrorOccurred parseError: Error) {
      print("parser error " + String(describing: parseError))
    }
}
