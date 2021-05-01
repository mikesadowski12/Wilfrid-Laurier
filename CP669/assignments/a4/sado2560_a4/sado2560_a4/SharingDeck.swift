//
//  SharingDeck.swift
//  sado2560_a3
//
//  Created by Mike Sadowski on 2021-02-08.
//

import UIKit;
import Foundation;

class SharingDeck {
    static let sharedDeck = SharingDeck();
    private let fileName = "cards.archive";
    private let rootKey = "rootKey";
    private var deck : Deck?;
    
    func getDeck() -> Deck?{
        return self.deck;
    }

    // Define the path to the archive
    func dataFilePath() -> String {
        let paths = NSSearchPathForDirectoriesInDomains(
            FileManager.SearchPathDirectory.documentDirectory,
            FileManager.SearchPathDomainMask.userDomainMask, true
        );
        let documentsDirectory = paths[0] as NSString;
        
        return documentsDirectory.appendingPathComponent(fileName) as String;
    }
    
    // un-archive the data, load it into the Deck
    func loadDeck() {
        let filePath = self.dataFilePath();
        
        if (FileManager.default.fileExists(atPath: filePath)) {
            let data = NSMutableData(contentsOfFile: filePath)!;
            let unarchiver = NSKeyedUnarchiver(forReadingWith: data as Data);

            deck = unarchiver.decodeObject(forKey: rootKey) as? Deck;
            unarchiver.finishDecoding();
        } else {
            deck = Deck();
        }
    }

    // archive the Deck into the file, you may need to save the “last used time” in this method
    func saveDeck() {
        let filePath = self.dataFilePath()
        let data = NSMutableData();
        let archiver = NSKeyedArchiver(forWritingWith: data);

        archiver.encode(deck, forKey: rootKey);
        archiver.finishEncoding();
        data.write(toFile: filePath, atomically: true);
    }
}
