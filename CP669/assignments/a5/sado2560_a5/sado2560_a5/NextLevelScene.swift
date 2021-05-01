//
//  NextLevelScene.swift
//  sado2560_a5
//
//  Created by Mike Sadowski on 2021-03-20.
//

import UIKit;
import SpriteKit;

class NextLevelScene: SKScene {
    private var youWin: SKSpriteNode?;
    private var nextLevelSecond: SKSpriteNode?;
    private var nextLevelThird: SKSpriteNode?;
    private var nextLevelMenu: SKSpriteNode?;
    private var levelNum : Int?;
    
    init(size: CGSize, levelNum: Int) {
        self.levelNum = levelNum;
        super.init(size: size);
    }
    
    override func didMove(to view: SKView) {
        backgroundColor = SKColor.white;
        
        youWin = SKSpriteNode(imageNamed: "youwin.png");
        youWin!.name="youWin";
        youWin?.yScale = 1.5;
        youWin!.position = CGPoint(x: self.frame.midX, y: self.frame.midY + 150.0);
        
        nextLevelSecond = SKSpriteNode(imageNamed: "nextlevel-second.png");
        nextLevelSecond!.name="nextLevelSecond";
        nextLevelSecond?.yScale = 1.5;
        nextLevelSecond!.position = CGPoint(x: self.frame.midX, y: self.frame.midY - 150.0);
        
        nextLevelThird = SKSpriteNode(imageNamed: "nextlevel-third.png");
        nextLevelThird!.name="nextLevelThird";
        nextLevelThird?.yScale = 1.5;
        nextLevelThird!.position = CGPoint(x: self.frame.midX, y: self.frame.midY - 150.0);
        
        nextLevelMenu = SKSpriteNode(imageNamed: "nextlevel-menu.png");
        nextLevelMenu!.name="nextLevelMenu";
        nextLevelMenu?.yScale = 1.5;
        nextLevelMenu!.position = CGPoint(x: self.frame.midX, y: self.frame.midY - 150.0);
        
        self.addChild(youWin!);
        
        if (levelNum == 1) {
            self.addChild(nextLevelSecond!);
        } else if (levelNum == 2) {
            self.addChild(nextLevelThird!);
        } else if (levelNum == 3) {
            self.addChild(nextLevelMenu!);
        }
        
        let seconds = 3.0
        DispatchQueue.main.asyncAfter(deadline: .now() + seconds) {
            switch self.levelNum {
            case 1:
                let nextLevel = Level2Scene(size: self.size);
                self.view?.presentScene(nextLevel, transition: SKTransition.doorsCloseHorizontal(withDuration: 2.0));
            case 2:
                let nextLevel = Level3Scene(size: self.size);
                self.view?.presentScene(nextLevel, transition: SKTransition.doorsCloseHorizontal(withDuration: 2.0));

            default:
                let mainMenu = GameScene(size: self.size);
                self.view?.presentScene(mainMenu, transition: SKTransition.doorsCloseHorizontal(withDuration: 2.0));
            }
        }

    }
    
    required init(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented");
    }
}
