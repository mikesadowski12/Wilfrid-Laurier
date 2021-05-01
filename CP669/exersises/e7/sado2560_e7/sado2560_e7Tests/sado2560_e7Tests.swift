//
//  sado2560_e7Tests.swift
//  sado2560_e7Tests
//
//  Created by Mike Sadowski on 2021-04-05.
//

import XCTest
@testable import sado2560_e7

class sado2560_e7Tests: XCTestCase {
    
    func testIsOdd() {
        let viewController = ViewController();
        
        let evenNumber = 8;
        let oddNumber = 9;
        
        XCTAssertFalse(viewController.isOdd(num: evenNumber)); // should return FALSE
        XCTAssertTrue(viewController.isOdd(num: oddNumber)); // should return TRUE
    }
}
