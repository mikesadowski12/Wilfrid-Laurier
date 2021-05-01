//
//  InstructionScene.swift
//  sado2560_a5
//
//  Created by Mike Sadowski on 2021-03-17.
//

import UIKit;
import SpriteKit;

class InstructionScene: SKScene {
    private var backButton : SKSpriteNode?;
    private var instructions : SKSpriteNode?;
    
    required init(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented");
    }
    
    override init(size: CGSize) {
        super.init(size: size);
        backgroundColor = SKColor.white;
        
        instructions = SKSpriteNode(imageNamed: "instructions.png");
        instructions?.name = "instructions";
        instructions?.yScale = 1.5;
        instructions?.position = CGPoint(x: self.frame.midX, y: self.frame.midY + INSTRUCTIONS_Y_PADDING);

        backButton = SKSpriteNode(imageNamed: "goBack.png");
        backButton?.name = "backButton";
        backButton?.yScale = 1.5;
        backButton?.position = CGPoint(x: self.frame.midX, y: self.frame.midY);
        
        self.addChild(instructions!);
        self.addChild(backButton!);
    }
    
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        /* Called when a touch begins */
        for touch: AnyObject in touches {
            let location = touch.location(in:self);
            let theNode = self.atPoint(location);
            
            if theNode.name == backButton!.name {
                let gameScene = GameScene(size: self.size);
                let transition = SKTransition.doorsOpenHorizontal(withDuration: 1.0);
                self.view?.presentScene(gameScene, transition: transition);
            }
        }
    } //touchesBegan
}
