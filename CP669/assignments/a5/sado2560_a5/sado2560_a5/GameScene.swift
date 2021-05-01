//
//  GameScene.swift
//  sado2560_a5
//
//  Created by Mike Sadowski on 2021-03-16.
//

import SpriteKit;
import GameplayKit;

class GameScene: SKScene {
    private var label : SKLabelNode?
    private var spinnyNode : SKShapeNode?
    
    
    
    private var playButton: SKSpriteNode?;
    private var instructionButton: SKSpriteNode?;
    private var playMusicButton: SKSpriteNode?;
    private var stopMusicButton: SKSpriteNode?;
    
    private let ap = AudioPlayer();
        
    override func didMove(to view: SKView) {
        backgroundColor = SKColor.white;
        
        playButton = SKSpriteNode(imageNamed: "PlayButton.png");
        playButton!.name="playButton";
        playButton?.yScale = 1.5;
        playButton!.position = CGPoint(x: self.frame.midX, y: self.frame.midY + MAIN_MENU_OFFSET*3);
        
        instructionButton = SKSpriteNode(imageNamed: "InstructionButton.png");
        instructionButton!.name = "instructionButton";
        instructionButton?.yScale = 1.5;
        instructionButton!.position = CGPoint(x: self.frame.midX, y: self.frame.midY  + MAIN_MENU_OFFSET*2);
        
        playMusicButton = SKSpriteNode(imageNamed: "PlayMusic.png");
        playMusicButton!.name = "playMusicButton";
        playMusicButton?.yScale = 1.5;
        playMusicButton!.position = CGPoint(x: self.frame.midX, y: self.frame.midY  + MAIN_MENU_OFFSET);
        
        stopMusicButton = SKSpriteNode(imageNamed: "StopMusic.png");
        stopMusicButton!.name = "stopMusicButton";
        stopMusicButton?.yScale = 1.5;
        stopMusicButton!.position = CGPoint(x: self.frame.midX, y: self.frame.midY);
        
        self.addChild(playButton!);
        self.addChild(instructionButton!);
        self.addChild(playMusicButton!);
        self.addChild(stopMusicButton!);
                
//        // Get label node from scene and store it for use later
//        self.label = self.childNode(withName: "//helloLabel") as? SKLabelNode
//        if let label = self.label {
//            label.alpha = 0.0
//            label.run(SKAction.fadeIn(withDuration: 2.0))
//        }
//
//        // Create shape node to use during mouse interaction
//        let w = (self.size.width + self.size.height) * 0.05
//        self.spinnyNode = SKShapeNode.init(rectOf: CGSize.init(width: w, height: w), cornerRadius: w * 0.3)
//
//        if let spinnyNode = self.spinnyNode {
//            spinnyNode.lineWidth = 2.5
//
//            spinnyNode.run(SKAction.repeatForever(SKAction.rotate(byAngle: CGFloat(Double.pi), duration: 1)))
//            spinnyNode.run(SKAction.sequence([SKAction.wait(forDuration: 0.5),
//                                              SKAction.fadeOut(withDuration: 0.5),
//                                              SKAction.removeFromParent()]))
//        }
    }
    
    
//    func touchDown(atPoint pos : CGPoint) {
//        if let n = self.spinnyNode?.copy() as! SKShapeNode? {
//            n.position = pos
//            n.strokeColor = SKColor.green
//            self.addChild(n)
//        }
//    }
//
//    func touchMoved(toPoint pos : CGPoint) {
//        if let n = self.spinnyNode?.copy() as! SKShapeNode? {
//            n.position = pos
//            n.strokeColor = SKColor.blue
//            self.addChild(n)
//        }
//    }
//
//    func touchUp(atPoint pos : CGPoint) {
//        if let n = self.spinnyNode?.copy() as! SKShapeNode? {
//            n.position = pos
//            n.strokeColor = SKColor.red
//            self.addChild(n)
//        }
//    }

    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        /* Called when a touch begins */
        for touch: AnyObject in touches {
            let location = touch.location(in:self);
            let theNode = self.atPoint(location);
            
            if theNode.name == playButton!.name {
                let level1Scene = Level1Scene(size: self.size);
                let transition = SKTransition.doorway(withDuration: 1.0);
                
                self.view?.presentScene(level1Scene, transition: transition);
            } else if theNode.name == instructionButton!.name {
                let instructionScene = InstructionScene(size: self.size);
                let transition = SKTransition.doorway(withDuration: 1.0);
                
                self.view?.presentScene(instructionScene, transition: transition);
            } else if theNode.name == playMusicButton!.name {
                AudioPlayer.audioPlayer.playBackgroundMusic();
            } else if theNode.name == stopMusicButton!.name {
                AudioPlayer.audioPlayer.stopBackgroundMusic();
            }
        }// for
    }
    
//
//    override func touchesMoved(_ touches: Set<UITouch>, with event: UIEvent?) {
//        for t in touches { self.touchMoved(toPoint: t.location(in: self)) }
//    }
//
//    override func touchesEnded(_ touches: Set<UITouch>, with event: UIEvent?) {
//        for t in touches { self.touchUp(atPoint: t.location(in: self)) }
//    }
//
//    override func touchesCancelled(_ touches: Set<UITouch>, with event: UIEvent?) {
//        for t in touches { self.touchUp(atPoint: t.location(in: self)) }
//    }
//
//
//    override func update(_ currentTime: TimeInterval) {
//        // Called before each frame is rendered
//    }
}
