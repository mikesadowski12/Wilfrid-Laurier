//
//  AddCardViewController.swift
//  sado2560_a3
//
//  Created by Mike Sadowski on 2021-02-08.
//

import UIKit;

class AddCardViewController: UIViewController, UIImagePickerControllerDelegate & UINavigationControllerDelegate {
    @IBOutlet weak var questionTextBox: UITextField!;
    @IBOutlet weak var answerTextBox: UITextField!
    @IBOutlet weak var questionImage: UIImageView!
    private let imagePicker = UIImagePickerController();
    
    // https://stackoverflow.com/questions/27880607/how-to-assign-an-action-for-uiimageview-object-in-swift
    // https://stackoverflow.com/questions/24625687/swift-uiimagepickercontroller-how-to-use-it
    @objc private func imageTapped(tapGestureRecognizer: UITapGestureRecognizer) {
        let imagePickerController = UIImagePickerController();
        imagePickerController.allowsEditing = false;
        imagePickerController.sourceType = .photoLibrary;
        imagePickerController.delegate = self;
        present(imagePickerController, animated: true, completion: nil);
    }
    
    // https://stackoverflow.com/questions/24625687/swift-uiimagepickercontroller-how-to-use-it
    func imagePickerController(_ picker: UIImagePickerController, didFinishPickingMediaWithInfo info: [UIImagePickerController.InfoKey : Any]) {
        let tempImage = info[UIImagePickerController.InfoKey.originalImage] as! UIImage;
        questionImage.image  = tempImage;
        self.dismiss(animated: true, completion: nil);
    }

    // https://stackoverflow.com/questions/24625687/swift-uiimagepickercontroller-how-to-use-it
    func imagePickerControllerDidCancel(_ picker: UIImagePickerController) {
        dismiss(animated: true, completion: nil);
    }
    
    override func viewDidLoad() {
        super.viewDidLoad();
        
        // https://stackoverflow.com/questions/27880607/how-to-assign-an-action-for-uiimageview-object-in-swift
        let tapGestureRecognizer = UITapGestureRecognizer(target: self, action: #selector(imageTapped(tapGestureRecognizer:)));
        questionImage.isUserInteractionEnabled = true;
        questionImage.addGestureRecognizer(tapGestureRecognizer);
    }
    
    private func showAlert(message: String) {
        let title = "Add a Card";
        let alertController = UIAlertController(title: title, message: message, preferredStyle: UIAlertController.Style.alert);
        
        alertController.addAction(UIAlertAction(title: "OK", style: .default, handler: nil))
        self.present(alertController, animated: true, completion: nil);
    }
    
    private func showAddCardSuccessAlert() {
        let message = "The question \"\(questionTextBox.text!)\" is added";
        showAlert(message: message);
    }

    private func showAddCardErrorAlert() {
        let message = "You must fill out both the Question and Answer fields in order to add a question";
        showAlert(message: message);
    }
    
    private func clearForm() {
        questionTextBox.text = "";
        answerTextBox.text = "";
        questionImage.image  = UIImage(named: "question_mark.png");
    }
    
    @IBAction func addCardPressed(_ sender: Any) {
        let question = questionTextBox.text!;
        let answer = answerTextBox.text!;
        let image = questionImage.image!;
        
        if (question != "" && answer != "") {
            SharingDeck.sharedDeck.deck?.addCard(question: question, answer: answer, image: image);
            showAddCardSuccessAlert();
            clearForm();
        } else {
            showAddCardErrorAlert();
        }
    }
    
    @IBAction func textFieldDoneEditing(sender: UITextField) {
        sender.resignFirstResponder();
    }
    
    @IBAction func backgroundTap(sender: UIControl) {
        questionTextBox.resignFirstResponder();
        answerTextBox.resignFirstResponder();
    }
}
