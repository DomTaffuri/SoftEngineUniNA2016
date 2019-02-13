//
//  TouchableLabelTest.swift
//  SoftEngineUniNATests2
//
//  Created by Domenico Taffuri on 04/02/2019.
//  Copyright © 2019 Domenico Taffuri. All rights reserved.
//

import XCTest
@testable import SoftEngineUniNA

class TouchableLabelTest: XCTestCase {
    
    override func setUp() {
        // Put setup code here. This method is called before the invocation of each test method in the class.
    }

    override func tearDown() {
        // Put teardown code here. This method is called after the invocation of each test method in the class.
    }

    func testTouchableLabelWithNilDelegate() {
        class MockTouchableLabelDelegate : TouchableLabelDelegate{
            func labelTapped(sender:TouchableLabel) {
                XCTFail()
            }
        }
        let testDelegate : MockTouchableLabelDelegate? = nil
        let labelUnderTest = TouchableLabel()
        labelUnderTest.delegate = testDelegate
        labelUnderTest.tapped(sender: UITapGestureRecognizer())
        
    }
    
    func testTouchableLabelWithNotNilDelegate() {
        class MockTouchableLabelDelegate : TouchableLabelDelegate{
            var called : Bool = false
            func labelTapped(sender:TouchableLabel) {
                called = true
            }
        }
        let testDelegate : MockTouchableLabelDelegate = MockTouchableLabelDelegate()
        let labelUnderTest = TouchableLabel()
        labelUnderTest.delegate = testDelegate
        labelUnderTest.tapped(sender: UITapGestureRecognizer())
        XCTAssertTrue(testDelegate.called)
    }

    func testPerformanceExample() {
        // This is an example of a performance test case.
        self.measure {
            // Put the code you want to measure the time of here.
        }
    }

}
