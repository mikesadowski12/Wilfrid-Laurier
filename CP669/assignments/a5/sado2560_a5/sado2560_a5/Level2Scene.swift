//
//  Level2Scene.swift
//  sado2560_a5
//
//  Created by Mike Sadowski on 2021-03-20.
//

import UIKit;
import SpriteKit;

class Level2Scene: Level {
    private let levelNum = 2;
    
    private var enemyCount : Int?;
    private var enemies : [SKSpriteNode];
    
    init(size: CGSize) {
        self.enemies = [SKSpriteNode]();
        
        let enemy1 = SKSpriteNode(imageNamed: "space-invader-small.png");
        let enemy2 = SKSpriteNode(imageNamed: "space-invader-small.png");
        let enemy3 = SKSpriteNode(imageNamed: "space-invader-small.png");
        let enemy4 = SKSpriteNode(imageNamed: "space-invader-small.png");
        
        enemies.append(enemy1);
        enemies.append(enemy2);
        enemies.append(enemy3);
        enemies.append(enemy4);
        
        enemyCount = enemies.count;
        
        super.init(size: size, enemies: self.enemies, enemyCount: self.enemyCount!, levelNum: self.levelNum);
    }
    
    required init(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented");
    }
}
