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
    @IBOutlet weak var time: UILabel!
    let sd = SharingDeck();
    
    func displayTime() {
        if (SharingDeck.sharedDeck.deck?.time != nil) {
            time.text = SharingDeck.sharedDeck.deck?.time;
        } else {
            time.text = getCurrentDate();
        }
    }
    
    func reloadUI() {
        if (SharingDeck.sharedDeck.deck?.isDeckEmpty() == true) {
            image.image = UIImage(named: "question_mark.png");
            question.text = "Please add a question using the Add Card tab";
            answer.text = "...";
        } else {
            image.image = SharingDeck.sharedDeck.deck?.card().image;
            question.text = SharingDeck.sharedDeck.deck?.card().question;
            answer.text = "...";
        }
    }
        
    override func viewDidLoad() {
        super.viewDidLoad();
        SharingDeck.sharedDeck.loadDeck();
        reloadUI();
        displayTime();
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated);
        reloadUI();
    }
    
    @IBAction func showAnswerPressed(_ sender: Any) {
        if (SharingDeck.sharedDeck.deck?.isDeckEmpty() == false) {
            answer.text = SharingDeck.sharedDeck.deck?.card().answer;
        }
    }
    
    @IBAction func nextPressed(_ sender: Any) {
        if (SharingDeck.sharedDeck.deck?.isDeckEmpty() == false) {
            SharingDeck.sharedDeck.deck?.nextIndex();
            reloadUI();
        }
    }
    
    @IBAction func prevPressed(_ sender: Any) {
        if (SharingDeck.sharedDeck.deck?.isDeckEmpty() == false) {
            SharingDeck.sharedDeck.deck?.prevIndex();
            reloadUI();
        }
    }
}
