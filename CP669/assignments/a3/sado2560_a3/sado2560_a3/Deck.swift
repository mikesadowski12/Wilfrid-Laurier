//
//  Deck.swift
//  sado2560_a2
//
//  Created by Mike Sadowski on 2021-02-06.
//

import Foundation;
import UIKit;

class Deck: NSObject, NSCoding {
    var cards = [Card]();
    var current : Int = 0; // index to the current card
    var time: String?;
    let deckKey = "deckKey";
    let indexKey = "indexKey";
    let timeKey = "timeKey";
    
    func card()->Card {
        return self.cards[current];
    }
    
    func setCurrentIndex(to index: Int) {
        self.current = index;
    }

    func nextIndex() {
        if (self.current == self.cards.count - 1) {
            self.current = 0;
        } else {
            self.current += 1;
        }
    }

    func prevIndex() {
        if current == 0 {
            self.current = self.cards.count - 1;
        } else {
            self.current -= 1;
        }
    }

    func getCurrentIndex() -> Int {
        return self.current;
    }
    
    func addCard(question: String, answer: String, image: UIImage) {
        if (question != "" && answer != "") {
            self.cards.append(Card(image: image, question: question, answer: answer)!);
        }
    }
    
    func deleteCard() {
        if (self.cards.count > 0) {
            self.cards.remove(at: self.current);
        }
        
        if (cards.count > 0 && current > cards.count - 1) {
            self.current = self.cards.count - 1;
        }
    }
    
    func isDeckEmpty() -> Bool {
        return !(self.cards.count > 0);
    }
    
    // https://stackoverflow.com/questions/24070450/how-to-get-the-current-time-as-datetime
    func setDateTime() {
        self.time = getCurrentDate();
    }

    // MARK: - NSCoding methods
    override init() {
        super.init();
    }

    required convenience init?(coder decoder: NSCoder) {
        self.init();
        self.cards = (decoder.decodeObject(forKey: deckKey) as? [Card])!;
        self.current = (decoder.decodeInteger(forKey: indexKey));
        self.time = (decoder.decodeObject(forKey: timeKey) as? String);
    }

    func encode(with acoder: NSCoder) {
        acoder.encode(cards, forKey: deckKey);
        acoder.encode(current, forKey: indexKey);
        acoder.encode(time, forKey: timeKey);
    } // You will need to implement other helper methods such as initDeck
}
