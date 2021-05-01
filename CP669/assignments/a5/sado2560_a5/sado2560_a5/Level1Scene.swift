//
//  Level1Scene.swift
//  sado2560_a5
//
//  Created by Mike Sadowski on 2021-03-17.
//

import UIKit;
import SpriteKit;

class Level1Scene: Level {
    private let levelNum = 1;
    
    private var enemyCount : Int?;
    private var enemies : [SKSpriteNode];
    
    init(size: CGSize) {
        self.enemies = [SKSpriteNode]();
        
        let enemy1 = SKSpriteNode(imageNamed: "space-invader-small.png");
        
        enemies.append(enemy1);
        
        enemyCount = enemies.count;
        
        super.init(size: size, enemies: self.enemies, enemyCount: self.enemyCount!, levelNum: self.levelNum);
    }
    
    required init(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented");
    }
}
