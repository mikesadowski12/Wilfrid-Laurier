//
//  Level3Scene.swift
//  sado2560_a5
//
//  Created by Mike Sadowski on 2021-03-21.
//

import UIKit;
import SpriteKit;

class Level3Scene: Level {
    private let levelNum = 3;
    
    private var enemyCount : Int?;
    private var enemies : [SKSpriteNode];
    
    private var shelterLeft : SKSpriteNode?;
    private var shelterRight : SKSpriteNode?;
    
    private func spawnShelters() {
        shelterLeft = SKSpriteNode(imageNamed: "shelter2.png");
        shelterLeft!.name = "shelterLeft";
        shelterLeft?.yScale = 1.5;
        shelterLeft!.position = CGPoint(x: self.frame.midX + 100.0, y: self.frame.midY);
        
        shelterLeft!.physicsBody = SKPhysicsBody(rectangleOf: shelterLeft!.size);
        shelterLeft!.physicsBody?.categoryBitMask = PhysicsCategory.Shelter;
        shelterLeft!.physicsBody?.contactTestBitMask = PhysicsCategory.DefenderBullet;
        shelterLeft!.physicsBody?.contactTestBitMask = PhysicsCategory.Rock;
        shelterLeft!.physicsBody?.collisionBitMask = 0;

        
        shelterRight = SKSpriteNode(imageNamed: "shelter2.png");
        shelterRight!.name = "shelterRight";
        shelterRight?.yScale = 1.5;
        shelterRight!.position = CGPoint(x: self.frame.midX - 100.0, y: self.frame.midY);
        
        shelterRight!.physicsBody = SKPhysicsBody(rectangleOf: shelterRight!.size);
        shelterRight!.physicsBody?.categoryBitMask = PhysicsCategory.Shelter;
        shelterRight!.physicsBody?.contactTestBitMask = PhysicsCategory.DefenderBullet;
        shelterRight!.physicsBody?.contactTestBitMask = PhysicsCategory.Rock;
        shelterRight!.physicsBody?.collisionBitMask = 0;
        
        self.addChild(shelterLeft!);
        self.addChild(shelterRight!);
    }
    
    init(size: CGSize) {
        self.enemies = [SKSpriteNode]();
        
        let enemy1 = SKSpriteNode(imageNamed: "space-invader-small.png");
        let enemy2 = SKSpriteNode(imageNamed: "space-invader-small.png");
        let enemy3 = SKSpriteNode(imageNamed: "space-invader-small.png");
        let enemy4 = SKSpriteNode(imageNamed: "space-invader-small.png");
        let enemy5 = SKSpriteNode(imageNamed: "space-invader-small.png");
        let enemy6 = SKSpriteNode(imageNamed: "space-invader-small.png");
        let enemy7 = SKSpriteNode(imageNamed: "space-invader-small.png");
        let enemy8 = SKSpriteNode(imageNamed: "space-invader-small.png");
        let enemy9 = SKSpriteNode(imageNamed: "space-invader-small.png");
        let enemy10 = SKSpriteNode(imageNamed: "space-invader-small.png");
        let enemy11 = SKSpriteNode(imageNamed: "space-invader-small.png");
        let enemy12 = SKSpriteNode(imageNamed: "space-invader-small.png");
        let enemy13 = SKSpriteNode(imageNamed: "space-invader-small.png");
        let enemy14 = SKSpriteNode(imageNamed: "space-invader-small.png");
        let enemy15 = SKSpriteNode(imageNamed: "space-invader-small.png");
        let enemy16 = SKSpriteNode(imageNamed: "space-invader-small.png");
        let enemy17 = SKSpriteNode(imageNamed: "space-invader-small.png");
        let enemy18 = SKSpriteNode(imageNamed: "space-invader-small.png");
        let enemy19 = SKSpriteNode(imageNamed: "space-invader-small.png");
        let enemy20 = SKSpriteNode(imageNamed: "space-invader-small.png");
        let enemy21 = SKSpriteNode(imageNamed: "space-invader-small.png");
        let enemy22 = SKSpriteNode(imageNamed: "space-invader-small.png");
        let enemy23 = SKSpriteNode(imageNamed: "space-invader-small.png");
        let enemy24 = SKSpriteNode(imageNamed: "space-invader-small.png");
        
        enemies.append(enemy1);
        enemies.append(enemy2);
        enemies.append(enemy3);
        enemies.append(enemy4);
        enemies.append(enemy5);
        enemies.append(enemy6);
        enemies.append(enemy7);
        enemies.append(enemy8);
        enemies.append(enemy9);
        enemies.append(enemy10);
        enemies.append(enemy11);
        enemies.append(enemy12);
        enemies.append(enemy13);
        enemies.append(enemy14);
        enemies.append(enemy15);
        enemies.append(enemy16);
        enemies.append(enemy17);
        enemies.append(enemy18);
        enemies.append(enemy19);
        enemies.append(enemy20);
        enemies.append(enemy21);
        enemies.append(enemy22);
        enemies.append(enemy23);
        enemies.append(enemy24);
        
        enemyCount = enemies.count;
        
        super.init(size: size, enemies: self.enemies, enemyCount: self.enemyCount!, levelNum: self.levelNum);
        spawnShelters();
    }
    
    required init(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented");
    }
}
