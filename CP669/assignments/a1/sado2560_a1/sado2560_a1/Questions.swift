//
//  Questions.swift
//  sado2560_a1
//
//  Created by Mike Sadowski on 2021-01-18.
//

import Foundation
import UIKit

let question1 = QuestionModel(image: UIImage(named: "flag.png"), question: "What country is my family from?", answer: "Poland");
let question2 = QuestionModel(image: UIImage(named: "car.png"), question: "What is my favourite type of car?", answer: "Honda Civic Type R");
let question3 = QuestionModel(image: UIImage(named: "sport.png"), question: "What is my favourite sport?", answer: "Soccer");
let question4 = QuestionModel(image: UIImage(named: "rolemodel.png"), question: "Who is my role model?", answer: "Arnold Schwarzenegger");
let question5 = QuestionModel(image: UIImage(named: "game.png"), question: "What is my favourite video game of all time?", answer: "Counter Strike");

let questionList: [QuestionModel] = [question1, question2, question3, question4, question5];

struct Questions {
    let list: [QuestionModel] = questionList;
    let count: Int = questionList.count;
}
