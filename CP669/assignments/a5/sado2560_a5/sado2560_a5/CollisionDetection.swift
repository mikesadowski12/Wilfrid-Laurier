//
//  CollisionDetection.swift
//  sado2560_a5
//
//  Created by Mike Sadowski on 2021-03-18.
//

import Foundation;

struct PhysicsCategory {
    static let None             : UInt32 = 0
    static let All              : UInt32 = UInt32.max
    static let Invader          : UInt32 = 0b1
    static let DefenderBullet   : UInt32 = 0b10
    static let Defender         : UInt32 = 0b100
    static let Rock             : UInt32 = 0b1000
    static let Shelter          : UInt32 = 0b10000
}
