//
//  AddCardViewController.swift
//  sado2560_a3
//
//  Created by Mike Sadowski on 2021-02-08.
//

import UIKit;
import os.log;

class AddCardViewController: UIViewController, UIImagePickerControllerDelegate & UINavigationControllerDelegate, UITextFieldDelegate {
    @IBOutlet weak var questionTextBox: UITextField!;
    @IBOutlet weak var answerTextBox: UITextField!
    @IBOutlet weak var questionImage: UIImageView!
    @IBOutlet weak var saveButton: UIBarButtonItem!
    private let imagePicker = UIImagePickerController();
    private var card: Card?;
    
    func getCard() -> Card? {
        return self.card;
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        super.prepare(for: segue, sender: sender);
        guard let button = sender as? UIBarButtonItem, button === saveButton else {
            os_log("The save button was not pressed, cancelling", log: OSLog.default, type: .debug);
            return;
        }
        
        let question = questionTextBox.text!;
        let answer = answerTextBox.text!;
        let image = questionImage.image!;
        
        card = Card(image: image, question: question, answer: answer);
    }
    
    @IBAction func cancelPressed(_ sender: Any) {
        dismiss(animated: true, completion: nil);
    }
    
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
        saveButton.isEnabled = false;
        
        self.questionTextBox.delegate = self;
        self.answerTextBox.delegate = self;
        
        // https://stackoverflow.com/questions/27880607/how-to-assign-an-action-for-uiimageview-object-in-swift
        let tapGestureRecognizer = UITapGestureRecognizer(target: self, action: #selector(imageTapped(tapGestureRecognizer:)));
        questionImage.isUserInteractionEnabled = true;
        questionImage.addGestureRecognizer(tapGestureRecognizer);
    }
    
    func updateSaveButtonState() {
        let question = questionTextBox.text ?? "";
        let answer = answerTextBox.text ?? "";
        saveButton.isEnabled = !question.isEmpty && !answer.isEmpty;
    }
    
    @IBAction func textFieldDoneEditing(sender: UITextField) {
        sender.resignFirstResponder();
        updateSaveButtonState();
    }
    
    @IBAction func backgroundTap(sender: UIControl) {
        questionTextBox.resignFirstResponder();
        answerTextBox.resignFirstResponder();
        updateSaveButtonState();
    }
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
    }
}
