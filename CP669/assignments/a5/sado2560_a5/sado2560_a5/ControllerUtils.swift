//
//  ControllerUtils.swift
//  sado2560_a5
//
//  Created by Mike Sadowski on 2021-03-17.
//

import UIKit;
import SpriteKit;

class ControllerUtils: NSObject {
    private var windowWidth : CGFloat?;
    private var midX : CGFloat?;
    private var minX : CGFloat?;
    private var maxX : CGFloat?;
    private var minY : CGFloat?;
    private var maxY : CGFloat?;
    
    init(windowWidth : CGFloat, midX: CGFloat, minX: CGFloat, maxX: CGFloat, minY: CGFloat, maxY: CGFloat) {
        self.windowWidth = windowWidth;
        self.midX = midX;
        self.minX = minX;
        self.maxX = maxX;
        self.minY = minY;
        self.maxY = maxY;
    }
    
    public func createControlBar(leftButton: inout SKSpriteNode, rightButton: inout SKSpriteNode, shootButton: inout SKSpriteNode) {
        leftButton.name = "leftButton";
        leftButton.yScale = 1.5;
        leftButton.position = CGPoint(x: self.minX! + BTN_X_PADDING, y: self.minY! + BTN_Y_PADDING);
        leftButton.setScale(0.50);
        leftButton.size.width = self.windowWidth! / NUM_BTNS + LEFT_RIGHT_BTN_OVERFLOW;
        
        rightButton.name = "rightButton";
        rightButton.yScale = 1.5;
        rightButton.position = CGPoint(x: self.maxX! - BTN_X_PADDING, y: self.minY! + BTN_Y_PADDING);
        rightButton.setScale(0.50);
        rightButton.size.width = self.windowWidth! / NUM_BTNS + LEFT_RIGHT_BTN_OVERFLOW;
        
        shootButton.name = "shootButton";
        shootButton.yScale = 1.5;
        shootButton.position = CGPoint(x: self.midX!, y: self.minY! + BTN_Y_PADDING);
        shootButton.setScale(0.50);
        shootButton.size.width = self.windowWidth! / NUM_BTNS;
    }
}
