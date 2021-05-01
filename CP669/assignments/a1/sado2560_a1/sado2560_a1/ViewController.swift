//
//  ViewController.swift
//  sado2560_a1
//
//  Created by Mike Sadowski on 2021-01-18.
//

import UIKit;

class ViewController: UIViewController {
    
    let myQuestions: Questions? = Questions();
    var questionIndex: Int? = 0;
    
    @IBOutlet weak var questionImage: UIImageView!;
    @IBOutlet weak var question: UILabel!;
    @IBOutlet weak var answer: UILabel!;
        
    func reloadUI() -> () {
        questionImage.image = myQuestions!.list[questionIndex!].image
        question.text = myQuestions!.list[questionIndex!].question
        answer.text = "...";
    }
    
    override func viewDidLoad() {
        super.viewDidLoad();
        
        questionIndex = 0;
        reloadUI();
    }
    
    @IBAction func showAnswerPressed(_ sender: Any) {
        answer.text = myQuestions!.list[questionIndex!].answer
    }
    
    @IBAction func nextPressed(_ sender: Any) {
        if questionIndex == myQuestions!.count - 1 {
            questionIndex! = 0;
        } else {
            questionIndex! += 1;
        }
        
        reloadUI();
    }
    
    @IBAction func prevPressed(_ sender: Any) {
        if questionIndex == 0 {
            questionIndex! = myQuestions!.count - 1;
        } else {
            questionIndex! -= 1;
        }
        
        reloadUI();
    }
        
}

