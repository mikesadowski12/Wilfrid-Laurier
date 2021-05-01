//
//  DefenderUtils.swift
//  sado2560_a5
//
//  Created by Mike Sadowski on 2021-03-17.
//

import UIKit;
import SpriteKit;

class DefenderUtils: NSObject {    
    private var midX: CGFloat?;
    private var minY: CGFloat?;
    private var minX: CGFloat?;
    private var maxX: CGFloat?;
    private var maxY: CGFloat?;
    
    init(midX: CGFloat, minY: CGFloat, minX: CGFloat, maxX: CGFloat, maxY: CGFloat) {
        self.midX = midX;
        self.minY = minY;
        self.minX = minX;
        self.maxX = maxX;
        self.maxY = maxY;
    }
    
    public func createDefender(defender: inout SKSpriteNode) {
        defender.name = "defender";
        defender.yScale = 1.5;
        defender.position = CGPoint(x: self.midX!, y: self.minY! + DFNDR_Y_PADDING);
        defender.setScale(0.25);
        
        defender.physicsBody = SKPhysicsBody(rectangleOf: defender.size);
        defender.physicsBody?.categoryBitMask = PhysicsCategory.Defender;
        defender.physicsBody?.contactTestBitMask = PhysicsCategory.Invader;
        defender.physicsBody?.contactTestBitMask = PhysicsCategory.Rock;
    }

    public func moveDefenderLeft(defender: inout SKSpriteNode) {
        if (defender.position.x - DFNDR_SPEED >= self.minX! + DFNDR_PADDING) {
            defender.position.x = defender.position.x - DFNDR_SPEED;
        }
    }

    public func moveDefenderRight(defender: inout SKSpriteNode) {
        if (defender.position.x + DFNDR_SPEED <= self.maxX! - DFNDR_PADDING) {
            defender.position.x = defender.position.x + DFNDR_SPEED;
        }
    }
    
    public func shoot(defenderXPos: CGFloat, defenderYPos: CGFloat, bullet: inout SKSpriteNode) {
        bullet.name = "bullet";
        bullet.yScale = 1.5;
        bullet.position = CGPoint(x: defenderXPos, y: defenderYPos + BULLET_PADDING);
        bullet.setScale(0.5);
        
        bullet.physicsBody = SKPhysicsBody(rectangleOf: bullet.size);
        bullet.physicsBody?.categoryBitMask = PhysicsCategory.DefenderBullet;
        bullet.physicsBody?.contactTestBitMask = PhysicsCategory.Rock;
        bullet.physicsBody?.contactTestBitMask = PhysicsCategory.Shelter;
        bullet.physicsBody?.contactTestBitMask = PhysicsCategory.Invader;
        
        let destination = CGPoint(x: defenderXPos, y: self.maxY!);
        let moveAction = SKAction.move(to: destination, duration: 2.0);
        let removeBullet = SKAction.removeFromParent();
        
        AudioPlayer.audioPlayer.playSoundEffect(filename: "artillery2", fileExtension: "m4a");
        bullet.run(SKAction.sequence([moveAction, removeBullet]));
    }
    
    public func destroyDefender(defender: inout SKSpriteNode, scream: inout SKSpriteNode) {
        defender.removeFromParent();
        
        scream.name = "scream";
        scream.yScale = 1.5;
        scream.position = CGPoint(x: defender.position.x, y: defender.position.y);
        scream.setScale(1.5);
        
        AudioPlayer.audioPlayer.playSoundEffect(filename: "scream", fileExtension: "mp3");
    }
}
