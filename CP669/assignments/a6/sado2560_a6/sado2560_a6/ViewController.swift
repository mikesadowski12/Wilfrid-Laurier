//
//  ViewController.swift
//  sado2560_a6
//
//  Created by Mike Sadowski on 2021-03-28.
//

import UIKit

class ViewController: UIViewController {
    var selectedArticle: Article? = nil;
    
    @IBOutlet weak var articleImage: UIImageView!
    @IBOutlet weak var articleText: UITextView!

    override func viewDidLoad() {
        super.viewDidLoad();
        
        self.title = selectedArticle?.getTitle();
        self.articleText.text = selectedArticle?.getText();
        self.articleImage.image = selectedArticle?.getImage();
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated);
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        super.prepare(for: segue, sender: sender);
        
        switch(segue.identifier ?? "") {
            case "ShowFullArticleFromDetail":
                guard let webViewController = segue.destination as? WebViewController else {
                    fatalError("Unexpected destination: \(segue.destination)");
                }
                                
                webViewController.webPageURL = selectedArticle?.getUrl();
                
            default:
                fatalError("Unexpected Segue Identifier; \(segue.identifier as Optional)");
        }
    }
}

