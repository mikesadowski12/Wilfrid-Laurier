//
//  Card.swift
//  sado2560_a2
//
//  Created by Mike Sadowski on 2021-02-06.
//

import Foundation;
import UIKit;
import os;

class Card: NSObject, NSCoding {
    let image: UIImage?;
    let question:String;
    let answer:String;

    init?(image: UIImage?, question: String, answer: String) {
        guard !question.isEmpty else {
            return nil;
        }
        
        guard !answer.isEmpty else {
            return nil;
        }

        self.image = image;
        self.question = question;
        self.answer = answer;
    } //init?

    func encode(with aCoder: NSCoder) {
        aCoder.encode(image, forKey: PropertyKey.image);
        aCoder.encode(question, forKey: PropertyKey.question);
        aCoder.encode(answer, forKey: PropertyKey.answer);
    } //encode

    required convenience init?(coder aDecoder: NSCoder) {
        // The question is required. If we cannot decode a question string, the initializer should fail.
        guard let question = aDecoder.decodeObject(forKey: PropertyKey.question) as? String else {
            os_log("Unable to decode the question for a Card object.", log: OSLog.default, type: .debug);
            return nil;
        }

        // Because image is an optional property of Card, just use conditional cast.
        let image = aDecoder.decodeObject(forKey: PropertyKey.image) as? UIImage;
        let answer = aDecoder.decodeObject(forKey: PropertyKey.answer);

        // Must call designated initializer.
        self.init(image: image, question: question, answer: answer as! String);
    } // decode
}// Card

struct PropertyKey {
    static let question = "question";
    static let image = "image";
    static let answer = "answer";
}
