//
//  Deck.swift
//  sado2560_a2
//
//  Created by Mike Sadowski on 2021-02-06.
//

import Foundation;
import UIKit;

struct Deck {
    static var deck = [Card]();
    static var current:Int = 0;

    init() {
        Deck.deck.append(Card(image: UIImage(named: "flag.png"), question: "What country is my family from?", answer: "Poland"));
        Deck.deck.append(Card(image: UIImage(named: "car.png"), question: "What is my favourite type of car?", answer: "Honda Civic Type R"));
        Deck.deck.append(Card(image: UIImage(named: "sport.png"), question: "What is my favourite sport?", answer: "Soccer"));
        Deck.deck.append(Card(image: UIImage(named: "rolemodel.png"), question: "Who is my role model?", answer: "Arnold Schwarzenegger"));
        Deck.deck.append(Card(image: UIImage(named: "game.png"), question: "What is my favourite video game?", answer: "Counter Strike"));
    }

    static func card()->Card {
        return deck[current];
    }

    static func setCurrentIndex(to index: Int) {
        current = index;
    }

    static func nextIndex() {
        if current == Deck.deck.count - 1 {
            current = 0;
        } else {
            current += 1;
        }
    }
    
    static func prevIndex() {
        if current == 0 {
            current = Deck.deck.count - 1;
        } else {
            current -= 1;
        }
    }

    static func getCurrentIndex() -> Int {
        return current;
    }
}
