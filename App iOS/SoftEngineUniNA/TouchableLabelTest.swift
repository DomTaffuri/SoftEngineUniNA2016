//
//  TouchableLabelTest.swift
//  SoftEngineUniNATests
//
//  Created by Domenico Taffuri on 04/02/2019.
//  Copyright © 2019 Domenico Taffuri. All rights reserved.
//

import XCTest
@testable import SoftEngineUniNA

class TouchableLabelTest: XCTestCase {
    class MockTouchableLabelDelegate : TouchableLabelDelegate{
        var called = false
        func labelTapped() {
            called = true
        }
    }
    
    override func setUp() {
        // Put setup code here. This method is called before the invocation of each test method in the class.
    }

    override func tearDown() {
        // Put teardown code here. This method is called after the invocation of each test method in the class.
    }

    func testExample() {
        let testDelegate = MockTouchableLabelDelegate()
        let testObject = TouchableLabel()
        testObject.delegate = testDelegate
        XCTAssertEqual(testDelegate.called, true)
    }

    func testPerformanceExample() {
        // This is an example of a performance test case.
        self.measure {
            // Put the code you want to measure the time of here.
        }
    }

}
