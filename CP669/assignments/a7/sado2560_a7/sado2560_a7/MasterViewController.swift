//
//  MasterViewController.swift
//  sado2560_a7
//
//  Created by Mike Sadowski on 2021-04-01.
//

import UIKit;
import os.log;

class TableViewCell: UITableViewCell {
    @IBOutlet weak var questionImage: UIImageView!
    @IBOutlet weak var questionLabel: UILabel!
}

class MasterViewController: UITableViewController {
    let sd = SharingDeck();
    var detailViewController: CardViewController? = nil

    
    override func viewDidLoad() {
        super.viewDidLoad();
        SharingDeck.sharedDeck.loadDeck();
        
        navigationItem.leftBarButtonItem = editButtonItem
        
        //let addButton = UIBarButtonItem(barButtonSystemItem: .add, target: self, action:  #selector(insertNewObject(_:)))
        //navigationItem.rightBarButtonItem  = addButton
        if let split = splitViewController {
           let controllers = split.viewControllers
           detailViewController = (controllers[controllers.count-1] as! UINavigationController).topViewController as? CardViewController
        }
    }
    
    @IBAction func unwindToCardList(sender: UIStoryboardSegue) {
        if let source = sender.source as? AddCardViewController,
           let card = source.getCard() {
            let newIndexPath = IndexPath(row: SharingDeck.sharedDeck.getDeck()?.getCards().count ?? 0, section: 0);
            SharingDeck.sharedDeck.getDeck()?.appendCard(card: card);
            tableView.insertRows(at: [newIndexPath], with: .automatic);
        }
    }
    
    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            SharingDeck.sharedDeck.getDeck()?.deleteCard(position: indexPath.row);
            tableView.deleteRows(at: [indexPath], with: .fade);
        }
    }
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if let deck = SharingDeck.sharedDeck.getDeck() {
            return deck.getCards().count;
        } else {
            return 0;
        }
    }

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        var cell = tableView.dequeueReusableCell(withIdentifier: "QuestionCellIdentifier", for: indexPath)
                as? TableViewCell;

        if (cell == nil) {
          cell = TableViewCell(
            style: UITableViewCell.CellStyle.default,
            reuseIdentifier: "QuestionCellIdentifier");
        }

        cell?.questionImage.image = SharingDeck.sharedDeck.getDeck()?.getCards()[indexPath.row].getImage();
        cell?.questionLabel.text = SharingDeck.sharedDeck.getDeck()?.getCards()[indexPath.row].getQuestion();

        return cell!;
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        super.prepare(for: segue, sender: sender);
        
        if segue.identifier == "ShowCard" {
           if let indexPath = tableView.indexPathForSelectedRow {
            let controller = (segue.destination as! UINavigationController).topViewController as! CardViewController;
                let selectedMeal = SharingDeck.sharedDeck.getDeck()?.getCards()[indexPath.row];
                controller.selectedCard = selectedMeal;
              controller.navigationItem.leftBarButtonItem = splitViewController?.displayModeButtonItem
              controller.navigationItem.leftItemsSupplementBackButton = true
           }
        }
//        switch(segue.identifier ?? "") {
//            case "AddCard":
//                os_log("Adding a new card.", log: OSLog.default, type: .debug);
//
//            case "ShowCard":
//                guard let cardViewController = segue.destination as? CardViewController else {
//                    fatalError("Unexpected destination: \(segue.destination)");
//                }
//
//                guard let selectedMealCell = sender as? TableViewCell else {
//                    fatalError("Unexpected sender: \(sender as Optional)");
//                }
//
//                guard let indexPath = tableView.indexPath(for: selectedMealCell) else {
//                    fatalError("The selected cell is not being displayed by the table");
//                }
//
//                let selectedMeal = SharingDeck.sharedDeck.getDeck()?.getCards()[indexPath.row];
//                cardViewController.selectedCard = selectedMeal;
//
//            default:
//                fatalError("Unexpected Segue Identifier; \(segue.identifier as Optional)");
//        }
    }
}
