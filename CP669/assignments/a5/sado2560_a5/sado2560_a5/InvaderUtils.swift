//
//  InvaderUtils.swift
//  sado2560_a5
//
//  Created by Mike Sadowski on 2021-03-18.
//

import UIKit;
import SpriteKit;

class InvaderUtils: NSObject {
    private var maxX: CGFloat?;
    private var minX: CGFloat?;
    private var minY: CGFloat?;
    private var maxY: CGFloat?;
    
    private var moveRight = true;
    
    init(maxX: CGFloat, minX: CGFloat, minY: CGFloat, maxY: CGFloat) {
        self.maxX = maxX;
        self.minX = minX;
        self.minY = minY;
        self.maxY = maxY;
    }
    
    public func createInvaders(enemies: inout [SKSpriteNode]) {
        var y_offset = 0.0;
        var x_offset = 0.0;
        
        for (index, enemy) in enemies.enumerated() {
            enemy.name = "enemy";
            enemy.yScale = 1.5;
            
            if index % 8 == 0 {
                y_offset += 60.0;
                x_offset = 0.0;
            }

            enemy.position = CGPoint(x: self.minX! + INVDR_X_PADDING + (CGFloat(x_offset * 40.0)), y: self.maxY! - INVDR_Y_PADDING - CGFloat(y_offset));
            x_offset += 1.0;
            
            enemy.physicsBody = SKPhysicsBody(rectangleOf: enemy.size);
            enemy.physicsBody?.categoryBitMask = PhysicsCategory.Invader;
            enemy.physicsBody?.contactTestBitMask = PhysicsCategory.DefenderBullet;
            enemy.physicsBody?.contactTestBitMask = PhysicsCategory.Defender;
            enemy.physicsBody!.collisionBitMask = 0;
        }
    }
    
    private func moveInvadersDown(invaders: inout [SKSpriteNode]) {
        for invader in invaders {
            invader.position.y = invader.position.y - INVDR_SPEED_DOWN;
        }
    }
        
    private func moveInvadersLeft(invaders: inout [SKSpriteNode]) {
        for invader in invaders {
            invader.position.x = invader.position.x - INVDR_SPEED;
            
            if (invader.position.x <= self.minX! + INVDR_X_PADDING) {
                moveRight = true;
                moveInvadersDown(invaders: &invaders);
            }
        }
    }
    
    private func moveInvadersRight(invaders: inout [SKSpriteNode]) {
        for invader in invaders {
            invader.position.x = invader.position.x + INVDR_SPEED;

            if (invader.position.x >= self.maxX! - INVDR_X_PADDING) {
                moveRight = false;
                moveInvadersDown(invaders: &invaders);
            }
        }
    }
    
    public func moveInvaders(invaders: inout [SKSpriteNode]) {
        if (self.moveRight) {
            moveInvadersRight(invaders: &invaders);
        } else {
            moveInvadersLeft(invaders: &invaders);
        }
    }
    
    public func destroyInvader() {
        AudioPlayer.audioPlayer.playSoundEffect(filename: "crash", fileExtension: "caf");
    }
    
    public func shoot(invader: inout SKSpriteNode, rock: inout SKSpriteNode) {
        rock.name = "rock";
        rock.yScale = 1.5;
        rock.position = CGPoint(x: invader.position.x, y: invader.position.y - 30.0);
        rock.setScale(0.5);
        
        rock.physicsBody = SKPhysicsBody(rectangleOf: rock.size);
        rock.physicsBody?.categoryBitMask = PhysicsCategory.Rock;
        rock.physicsBody?.contactTestBitMask = PhysicsCategory.DefenderBullet;
        rock.physicsBody?.contactTestBitMask = PhysicsCategory.Shelter;

        let destination = CGPoint(x: invader.position.x, y: self.minY!);
        let moveAction = SKAction.move(to: destination, duration: 2.0);
        let removeRock = SKAction.removeFromParent();
        
        rock.run(SKAction.sequence([moveAction, removeRock]));
    }
}
