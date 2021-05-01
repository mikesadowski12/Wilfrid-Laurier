//
//  ViewController.swift
//  sado2560_e7
//
//  Created by Mike Sadowski on 2021-04-05.
//

import UIKit

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }
    
    //return true if and only if the num is odd
    public func isOdd(num: Int) -> Bool {
        if num % 2 == 0 {
            return false;
        } else {
            return true;
        }
    } //isOdd
}

