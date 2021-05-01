//
//  Deck.swift
//  sado2560_a2
//
//  Created by Mike Sadowski on 2021-02-06.
//

import Foundation;
import UIKit;

class Deck: NSObject, NSCoding {
    private var cards = [Card]();
    private let deckKey = "deckKey";
    private let indexKey = "indexKey";
    private let timeKey = "timeKey";
    
    func getCards() -> [Card] {
        return self.cards;
    }
    
    func appendCard(card: Card) {
        self.cards.append(card);
    }
    
    func addCard(question: String, answer: String, image: UIImage) {
        if (question != "" && answer != "") {
            self.cards.append(Card(image: image, question: question, answer: answer)!);
        }
    }
    
    func deleteCard(position: Int) {
        if (self.cards.count > 0) {
            self.cards.remove(at: position);
        }
    }

    // MARK: - NSCoding methods
    override init() {
        super.init();
    }

    required convenience init?(coder decoder: NSCoder) {
        self.init();
        self.cards = (decoder.decodeObject(forKey: deckKey) as? [Card])!;
    }

    func encode(with acoder: NSCoder) {
        acoder.encode(cards, forKey: deckKey);
    } // You will need to implement other helper methods such as initDeck
}
