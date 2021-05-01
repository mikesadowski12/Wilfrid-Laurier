//
//  ViewController.swift
//  sado2560_e1
//
//  Created by Mike Sadowski on 2021-01-14.
//

import UIKit

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }

    @IBOutlet weak var numClicks: UILabel!
    var count: Int = 0;
    
    @IBAction func pressMeTouched(_ sender: Any) {
        count += 1;
        numClicks.text = String(count);
    }
    
}
