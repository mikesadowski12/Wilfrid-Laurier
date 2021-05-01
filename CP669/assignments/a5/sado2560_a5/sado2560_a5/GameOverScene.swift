//
//  GameOverScene.swift
//  sado2560_a5
//
//  Created by Mike Sadowski on 2021-03-20.
//

import UIKit;
import SpriteKit;

class GameOverScene: SKScene {
    private var youLose: SKSpriteNode?;
    
    override func didMove(to view: SKView) {
        backgroundColor = SKColor.white;
        
        youLose = SKSpriteNode(imageNamed: "gameover.png");
        youLose!.name="youLose";
        youLose?.yScale = 1.5;
        youLose!.position = CGPoint(x: self.frame.midX, y: self.frame.midY);
        
        self.addChild(youLose!);
        
        let seconds = 3.0
        DispatchQueue.main.asyncAfter(deadline: .now() + seconds) {
            let mainMenu = GameScene(size: self.size);
            self.view?.presentScene(mainMenu, transition: SKTransition.doorsCloseHorizontal(withDuration: 2.0));
        }

    }
}
