//
//  DeleteCardViewController.swift
//  sado2560_a3
//
//  Created by Mike Sadowski on 2021-02-08.
//

import UIKit

class DeleteCardViewController: UIViewController {
    @IBOutlet weak var questionLabel: UILabel!;
    @IBOutlet weak var answerLabel: UILabel!;
    @IBOutlet weak var questionImage: UIImageView!
    
    func reloadUI() {
        if (SharingDeck.sharedDeck.deck?.isDeckEmpty() == true) {
            questionImage.image = UIImage(named: "question_mark.png");
            questionLabel.text = "Please add a question using the Add Card tab";
            answerLabel.text = "Please add a question using the Add Card tab";
        } else {
            questionImage.image = SharingDeck.sharedDeck.deck?.card().image;
            questionLabel.text = SharingDeck.sharedDeck.deck?.card().question;
            answerLabel.text = SharingDeck.sharedDeck.deck?.card().answer;
        }
    }
    
    private func showAlert(title: String, message: String) {
        let alertController = UIAlertController(title: title, message: message, preferredStyle: UIAlertController.Style.alert);
        
        alertController.addAction(UIAlertAction(title: "OK", style: .default, handler: nil))
        self.present(alertController, animated: true, completion: nil);
    }
    
    private func showDeleteCardSuccessAlert() {
        let title = "Delete a Card";
        let message = "The question \"\(questionLabel.text!)\" is deleted";
        showAlert(title: title, message: message);
    }
    
    private func showDeleteCardErrorAlert() {
        let title = "Empty Deck";
        let message = "The deck is empty";
        showAlert(title: title, message: message);
    }
    
    override func viewDidLoad() {
        super.viewDidLoad();
        reloadUI();
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated);
        reloadUI();
    }
    
    @IBAction func deletePressed(_ sender: Any) {
        if (SharingDeck.sharedDeck.deck?.isDeckEmpty() == false) {
            SharingDeck.sharedDeck.deck?.deleteCard();
            showDeleteCardSuccessAlert();
            reloadUI();
        } else {
            showDeleteCardErrorAlert();
        }
    }
    
    @IBAction func nextCardPressed(_ sender: Any) {
        if (SharingDeck.sharedDeck.deck?.isDeckEmpty() == false) {
            SharingDeck.sharedDeck.deck?.nextIndex();
            reloadUI();
        } else {
            showDeleteCardErrorAlert();
        }
    }
}
