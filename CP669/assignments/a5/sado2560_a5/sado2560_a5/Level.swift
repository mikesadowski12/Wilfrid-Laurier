//
//  Level.swift
//  sado2560_a5
//
//  Created by Mike Sadowski on 2021-03-20.
//

import UIKit;
import SpriteKit;

class Level: SKScene, SKPhysicsContactDelegate {
    private var defenderUtils : DefenderUtils?;
    private var controllerUtils : ControllerUtils?;
    private var invaderUtils : InvaderUtils?;
    
    private var leftButton : SKSpriteNode?;
    private var rightButton : SKSpriteNode?;
    private var shootButton : SKSpriteNode?;
    
    private var defender : SKSpriteNode?;
    
    private var enemyCount : Int?;
    private var enemies : [SKSpriteNode];
    
    private var levelNum : Int?;
    
    private func initEnemies() {
        invaderUtils?.createInvaders(enemies: &enemies);
        
        for enemy in enemies {
            self.addChild(enemy);
        }
    }
    
    private func initDefender() {
        defender = SKSpriteNode(imageNamed: "Spaceship.png");
        self.defenderUtils!.createDefender(defender: &defender!);
        self.addChild(defender!);
    }
    
    private func initController() {
        leftButton = SKSpriteNode(imageNamed: "left-arrow-unit.png");
        rightButton = SKSpriteNode(imageNamed: "right-arrow-unit.png");
        shootButton = SKSpriteNode(imageNamed: "bullet-unit.png");
        
        self.controllerUtils!.createControlBar(leftButton: &leftButton!, rightButton: &rightButton!, shootButton: &shootButton!);
        
        self.addChild(leftButton!);
        self.addChild(rightButton!);
        self.addChild(shootButton!);
    }
    
    private func shootDefenderBullet() {
        var bullet = SKSpriteNode(imageNamed: "defenderBullet2.png");
        
        defenderUtils!.shoot(defenderXPos: defender!.position.x, defenderYPos: defender!.position.y, bullet: &bullet);
        
        self.addChild(bullet);
    }
        
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        /* Called when a touch begins */
        for touch: AnyObject in touches {
            let location = touch.location(in:self);
            let theNode = self.atPoint(location);
            
            if theNode.name == leftButton!.name {
                self.defenderUtils!.moveDefenderLeft(defender: &defender!);
            } else if theNode.name == rightButton!.name {
                self.defenderUtils!.moveDefenderRight(defender: &defender!);
            } else if theNode.name == shootButton!.name {
                shootDefenderBullet();
            }
        }// for
    }
    
    // https://stackoverflow.com/questions/49041147/how-to-find-closest-spritenode-to-the-bottom-of-scene-in-swift
    private func willInvaderShoot() {
        let randomInt = Int.random(in: 1..<SHOOT_SPEED)

        if (randomInt == 2) {
            var distanceArray = [Int]();
            var closestNode : SKSpriteNode?;
            
            self.enumerateChildNodes(withName: "enemy") {node,_ in
                if let sprite = node as? SKSpriteNode {
                    let distance = Int(sprite.position.y)
                    distanceArray.append(distance)
                    if distance == distanceArray.min() {
                        closestNode = sprite
                    }
                }
            }
            
            var rock = SKSpriteNode(imageNamed: "rock.png");
            invaderUtils?.shoot(invader: &closestNode!, rock: &rock);
            self.addChild(rock);
        }
    }
    
    override func update(_ currentTime: TimeInterval) {
        if (self.enemyCount == 0) {
            let nextLevelScene = NextLevelScene(size: self.size, levelNum: self.levelNum!);
            self.view?.presentScene(nextLevelScene, transition: SKTransition.doorsCloseHorizontal(withDuration: 1.0));
        } else {
            invaderUtils?.moveInvaders(invaders: &enemies);
            willInvaderShoot();
        }
    } //update
    
    private func removeInvader(invader: SKSpriteNode, bullet: SKSpriteNode) {
        bullet.removeFromParent();
        invader.removeFromParent();
        
        invaderUtils?.destroyInvader();
        enemyCount! -= 1;
    }
    
    public func endGame() {
        let gameOverScene = GameOverScene(size: self.size);
        self.view?.presentScene(gameOverScene, transition: SKTransition.doorsCloseHorizontal(withDuration: 1.0));
    }
    
    public func removeDefender() {
        var scream = SKSpriteNode(imageNamed: "scream.png");
        defenderUtils?.destroyDefender(defender: &defender!, scream: &scream);
        
        self.addChild(scream);
        
        let seconds = 2.0
        DispatchQueue.main.asyncAfter(deadline: .now() + seconds) {
            self.endGame();
        }
    }
    
    func removeNode(node: SKSpriteNode) {
        node.removeFromParent();
    }
    
    func removeRockAndBullet(bullet: SKSpriteNode, rock: SKSpriteNode) {
        removeNode(node: bullet);
        removeNode(node: rock);
    }
    
    func didBegin(_ contact: SKPhysicsContact) {
        //  bodyA and bodyB collide, we have to sort them by their bitmasks
        var firstBody: SKPhysicsBody
        var secondBody: SKPhysicsBody

        if contact.bodyA.categoryBitMask < contact.bodyB.categoryBitMask {
            firstBody = contact.bodyA
            secondBody = contact.bodyB
        } else {
            firstBody = contact.bodyB
            secondBody = contact.bodyA
        }

        if ((firstBody.categoryBitMask & PhysicsCategory.Invader != 0) && (secondBody.categoryBitMask & PhysicsCategory.DefenderBullet != 0) && firstBody.node != nil && secondBody.node != nil) {
                removeInvader(invader: firstBody.node as! SKSpriteNode, bullet: secondBody.node as! SKSpriteNode);
        } else if ((firstBody.categoryBitMask & PhysicsCategory.Invader != 0) && (secondBody.categoryBitMask & PhysicsCategory.Defender != 0) && firstBody.node != nil && secondBody.node != nil) {
            removeDefender();
        } else if ((firstBody.categoryBitMask & PhysicsCategory.Defender != 0) && (secondBody.categoryBitMask & PhysicsCategory.Rock != 0) && firstBody.node != nil && secondBody.node != nil) {
            removeDefender();
        } else if ((firstBody.categoryBitMask & PhysicsCategory.DefenderBullet != 0) && (secondBody.categoryBitMask & PhysicsCategory.Rock != 0) && firstBody.node != nil && secondBody.node != nil) {
            removeRockAndBullet(bullet: firstBody.node as! SKSpriteNode, rock: secondBody.node as! SKSpriteNode);
        } else if ((firstBody.categoryBitMask & PhysicsCategory.Rock != 0) && (secondBody.categoryBitMask & PhysicsCategory.Shelter != 0) && firstBody.node != nil && secondBody.node != nil) {
            removeNode(node: firstBody.node as! SKSpriteNode);
        } else if ((firstBody.categoryBitMask & PhysicsCategory.DefenderBullet != 0) && (secondBody.categoryBitMask & PhysicsCategory.Shelter != 0) && firstBody.node != nil && secondBody.node != nil) {
            removeNode(node: firstBody.node as! SKSpriteNode);
        }
    } //didBeginContact
    
    init(size: CGSize, enemies: [SKSpriteNode], enemyCount: Int, levelNum: Int) {
        self.enemies = enemies;
        self.levelNum = levelNum;
        self.enemyCount = enemyCount;
        super.init(size: size);
        backgroundColor = SKColor.black;
        self.physicsWorld.gravity = CGVector(dx: 0, dy: 0) // no gravity
        self.physicsWorld.contactDelegate = self;
        
        defenderUtils = DefenderUtils(midX: self.frame.midX, minY: self.frame.minY, minX: self.frame.minX, maxX: self.frame.maxX, maxY: self.frame.maxY);
        controllerUtils = ControllerUtils(windowWidth: self.frame.width, midX: self.frame.midX, minX: self.frame.minX, maxX: self.frame.maxX, minY: self.frame.minY, maxY: self.frame.maxX);
        invaderUtils = InvaderUtils(maxX: self.frame.maxX, minX: self.frame.minX, minY: self.frame.minY, maxY: self.frame.maxY);
        
        initDefender();
        initController();
        initEnemies();
    }
    
    override func touchesMoved(_ touches: Set<UITouch>, with event: UIEvent?) {
        for touch in touches {
            let location = touch.location(in: self);
            let theNode = self.atPoint(location);
            
            if theNode.name == defender!.name {
                defender!.position = CGPoint(x: location.x, y: defender!.position.y);
            }
        }
    }
    
    required init(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented");
    }
}
