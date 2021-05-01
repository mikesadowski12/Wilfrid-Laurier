//
//  ViewController.swift
//  sado2560_a2
//
//  Created by Mike Sadowski on 2021-02-06.
//

import UIKit;

class ViewController: UIViewController {
    @IBOutlet weak var image: UIImageView!;
    @IBOutlet weak var answer: UILabel!;
//    @IBOutlet weak var question: UILabel!;
    @IBOutlet weak var time: UILabel!
    var selectedCard: Card? = nil;
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder);
    }
    
    override func viewDidLoad() {
        super.viewDidLoad();
        
        if let selectedCard = selectedCard {
            navigationItem.title = selectedCard.getQuestion();
            self.answer.text = selectedCard.getAnswer();
            self.image.image = selectedCard.getImage();
        }
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated);
    }
}
