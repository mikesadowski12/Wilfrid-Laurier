//
//  CardViewController.swift
//  sado2560_a7
//
//  Created by Mike Sadowski on 2021-04-01.
//

import UIKit

class CardViewController: UIViewController {
    @IBOutlet weak var image: UIImageView!
    @IBOutlet weak var answer: UILabel!
    var selectedCard: Card? = nil;
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder);
    }
    
    override func viewDidLoad() {
        super.viewDidLoad();
        
        self.answer.text = "Please select a question from the Table"
        
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
