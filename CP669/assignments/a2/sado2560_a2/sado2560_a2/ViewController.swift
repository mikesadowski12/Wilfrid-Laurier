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
    @IBOutlet weak var question: UILabel!;
    let deck = Deck();
    
    func reloadUI() -> () {
        image.image = Deck.card().image;
        question.text = Deck.card().question;
        answer.text = "...";
    }
    
    override func viewDidLoad() {
        super.viewDidLoad();
        
        Deck.setCurrentIndex(to: UserDefaults.standard.integer(forKey: "currentIndex") as Int? ?? 0);
        reloadUI();
    }
    
    @IBAction func showAnswerPressed(_ sender: Any) {
        answer.text = Deck.card().answer;
    }
    
    @IBAction func nextPressed(_ sender: Any) {
        Deck.nextIndex();
        reloadUI();
    }
    
    @IBAction func prevPressed(_ sender: Any) {
        Deck.prevIndex();
        reloadUI();
    }
}
