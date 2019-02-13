//
//  LoginViewTest.swift
//  SoftEngineUniNAUITestsUI
//
//  Created by Domenico Taffuri on 05/02/2019.
//  Copyright © 2019 Domenico Taffuri. All rights reserved.
//

import XCTest
@testable import SoftEngineUniNA

class LoginViewTest: XCTestCase {
    var app : XCUIApplication!
    override func setUp() {
        app = XCUIApplication()
        continueAfterFailure = false

        // UI tests must launch the application that they test. Doing this in setup will make sure it happens for each test method.
        app.launchArguments.append("--uitesting")

        // In UI tests it’s important to set the initial state - such as interface orientation - required for your tests before they run. The setUp method is a good place to do this.
    }

    override func tearDown() {
        // Put teardown code here. This method is called after the invocation of each test method in the class.
    }

    func testLabelAndPicker() {
        app.launch()
        app.staticTexts["Accedi come : Tecnico"].tap()
        app.pickerWheels.element.adjust(toPickerWheelValue: "Cliente")
    }

}
