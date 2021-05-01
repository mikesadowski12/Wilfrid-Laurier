//
//  WebViewController.swift
//  sado2560_a6
//
//  Created by Mike Sadowski on 2021-03-29.
//

import UIKit;
import WebKit;

// This code is from Appendix #2 from course notes, slightly modified for this assignment.
class WebViewController: UIViewController, WKNavigationDelegate {
   let kHEADERHEIGHT : CGFloat = 0.0 // set it to 44.0 if you want a header
   let kFOOTERHEIGHT : CGFloat = 44.0

   var webView : WKWebView = WKWebView()
   var headerView : UIView = UIView()
   var footerView : UIView = UIView()
             
   var leftArrowButton = UIButton(type: UIButton.ButtonType.custom)
   var rightArrowButton = UIButton(type: UIButton.ButtonType.custom)
   var listButton = UIButton(type: UIButton.ButtonType.custom)
    
    var webPageURL: String? = nil;
             
    @IBAction func unwindNewsFeed(_ sender: Any) {
        let articleTableView  = self.storyboard?.instantiateViewController(withIdentifier: "ArticlesTableViewController") as! ArticlesTableViewController
        self.navigationController?.pushViewController(articleTableView, animated: true)
    }
    
    override func viewDidLoad()  {
      super.viewDidLoad()
    
        self.navigationItem.setHidesBackButton(true, animated: true)

             
      webView.allowsBackForwardNavigationGestures = true
      self.view.backgroundColor = UIColor.white
      headerView.backgroundColor = UIColor.gray
      footerView.backgroundColor = UIColor.gray
             
      // Image set
      leftArrowButton.setImage(UIImage(named: "left-arrow"), for: [])
      // when user presses on the left arrow button, the method back is executed
      leftArrowButton.addTarget(self, action:#selector(back(_ : )), for: .touchUpInside)
             
      rightArrowButton.setImage(UIImage(named: "right-arrow"), for: [])
      rightArrowButton.addTarget(self, action:#selector(forward(_ : )), for: .touchUpInside)
             
      listButton.setTitle("History", for: [])
      listButton.setTitleColor(UIColor.blue, for: [])
             
      listButton.addTarget(self, action: #selector(history(_:)), for: .touchUpInside)
             
      //self.view.addSubview(self.headerView) // we do not need the header for this example
      self.view.addSubview(self.webView)
      self.view.addSubview(self.footerView)
      self.view.addSubview(self.leftArrowButton)
      self.view.addSubview(self.rightArrowButton)
//      self.view.addSubview(self.listButton)
             
      webView.navigationDelegate = self
      webView.load(URLRequest(url: NSURL(string: webPageURL!)! as URL))
   }
             
   override func viewWillLayoutSubviews() {
        let statusBarHeight = view.window?.windowScene?.statusBarManager?.statusBarFrame.height ?? 0

             
      headerView.frame = CGRect(origin: CGPoint(x:0, y:statusBarHeight), size: CGSize(width: self.view.frame.size.width, height: kHEADERHEIGHT))
             
      webView.frame = CGRect(origin: CGPoint(x:0, y:statusBarHeight+kHEADERHEIGHT), size: CGSize(width: self.view.frame.size.width, height: self.view.frame.size.height -  (statusBarHeight+kHEADERHEIGHT) - kFOOTERHEIGHT))
             
      footerView.frame = CGRect(origin: CGPoint(x:0, y:self.view.frame.size.height - kFOOTERHEIGHT), size: CGSize(width: self.view.frame.size.width, height: kFOOTERHEIGHT))
             
      leftArrowButton.frame = CGRect(origin: CGPoint(x:5, y:self.view.frame.size.height - kFOOTERHEIGHT), size: CGSize(width: kFOOTERHEIGHT, height: kFOOTERHEIGHT))
             
      // code for the other two buttons
      rightArrowButton.frame = CGRect(origin: CGPoint(x:10 + kFOOTERHEIGHT, y:self.view.frame.size.height - kFOOTERHEIGHT), size: CGSize(width: kFOOTERHEIGHT, height: kFOOTERHEIGHT))
             
      listButton.frame = CGRect(origin: CGPoint(x:self.view.frame.size.width - 85, y:self.view.frame.size.height - kFOOTERHEIGHT), size: CGSize(width: 80, height: kFOOTERHEIGHT))
   }
             
   // MARK: WKNavigationDelegate
   func webView(_ webView: WKWebView, didStartProvisionalNavigation navigation: WKNavigation!) {
      NSLog("Start")
   }
             
   func webView(_ webView: WKWebView, didFail navigation: WKNavigation!,  withError error: Error) {
      NSLog("Failed Navigation: \(error.localizedDescription)" )
   }
             
   func webView(_ webView: WKWebView, didFinish navigation: WKNavigation!) {
      // Finish navigation
      print("Finish Navigation")
      print("Title: \(webView.title!) ; URL: \(webView.url!)")
   }
             
   // MARK: UI Event
   @objc func history(_ sender: Any) {
      let list : WKBackForwardList = webView.backForwardList
             
      if (list.backItem != nil) {
         print("Back \(list.backItem!.url)" )
      }
      if (list.forwardItem != nil) {
         print("Forward \(list.forwardItem!.url)" )
      }
   } //history

   @objc func back(_ sender: Any) {
      if (webView.canGoBack) {
         webView.goBack()
      }
   } // back

   @objc func forward(_ sender: Any) {
      if (webView.canGoForward) {
         webView.goForward()
      }
   } // forward
}

