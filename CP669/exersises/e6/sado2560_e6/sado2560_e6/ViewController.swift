//
//  ViewController.swift
//  sado2560_e6
//
//  Created by Mike Sadowski on 2021-03-30.
//

import UIKit

class ViewController: UIViewController, URLSessionTaskDelegate {
    var dataStore = NSData();
    var holidayItem: HolidayItem?;
    
    let urlPath: String = "https://holidayapi.com/v1/holidays?key=d2ebddab-9dfd-4cae-aba9-a58dd98fa34b&country=CA&year=2020&month=10";
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        let url: NSURL = NSURL(string: urlPath)!
        let request: URLRequest = URLRequest(url: url as URL)
        let config = URLSessionConfiguration.default
        let session = URLSession(configuration: config)
        let task = session.dataTask(
            with: request,
            completionHandler:{ (data, response, error) in
                self.dataStore = data! as NSData
                let results = NSString(data: self.dataStore as Data, encoding: String.Encoding.utf8.rawValue)
                print(" the result file content is ")
                print(results!)
                print("The server's response is")
//                print(response!)
                print("--done--")
                if let r = error {
                    print(r)
                }
               
                do {
                    print("start json decoder")
                    self.holidayItem = try JSONDecoder().decode(HolidayItem.self, from: self.dataStore as Data)
                    print("finish json decoder")
                    DispatchQueue.main.async { // must handle UI methods in the main thread
//                        self.holidayItem?.printHolidays();
                    }
                } catch {
                    return
                } // catch - do
            }) //dataTask""
        
        task.resume()
    }
}

