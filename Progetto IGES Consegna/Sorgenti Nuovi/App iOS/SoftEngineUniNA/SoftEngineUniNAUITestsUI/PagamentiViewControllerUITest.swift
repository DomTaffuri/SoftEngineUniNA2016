//
//  PagamentiViewControllerUITest.swift
//  SoftEngineUniNAUITestsUI
//
//  Created by Domenico Taffuri on 07/02/2019.
//  Copyright © 2019 Domenico Taffuri. All rights reserved.
//

import XCTest

class PagamentiViewControllerUITest: XCTestCase {
    var app:XCUIApplication!
    override func setUp() {
        // Put setup code here. This method is called before the invocation of each test method in the class.
        app = XCUIApplication()
        // In UI tests it is usually best to stop immediately when a failure occurs.
        continueAfterFailure = false

        // UI tests must launch the application that they test. Doing this in setup will make sure it happens for each test method.
        

        // In UI tests it’s important to set the initial state - such as interface orientation - required for your tests before they run. The setUp method is a good place to do this.
    }

    override func tearDown() {
        // Put teardown code here. This method is called after the invocation of each test method in the class.
    }

    func testVisualizzaButtonDidPressed() {
            app.launch()
            app.staticTexts["Accedi come : Tecnico"].tap()
            app.pickerWheels.element.adjust(toPickerWheelValue: "Cliente")
            app.buttons["Log In"].tap()
            let dateFormatter = DateFormatter()
            dateFormatter.dateFormat = "YYYY-MM-dd"
            let todayDate = dateFormatter.string(from: Date())
            app.staticTexts["Da: \(todayDate)"].tap()
            app.pickerWheels.element(boundBy: 0).adjust(toPickerWheelValue: "January")
            app.pickerWheels.element(boundBy: 1).adjust(toPickerWheelValue: "1")
            app.pickerWheels.element(boundBy: 2).adjust(toPickerWheelValue: "2017")
            app.staticTexts["RELATIVA AI PAGAMENTI"].tap()
            app.staticTexts["A: \(todayDate)"].tap()
            app.pickerWheels.element(boundBy: 0).adjust(toPickerWheelValue: "December")
            app.pickerWheels.element(boundBy: 1).adjust(toPickerWheelValue: "31")
            app.pickerWheels.element(boundBy: 2).adjust(toPickerWheelValue: "2017")
            app.staticTexts["RELATIVA AI PAGAMENTI"].tap()
            app.buttons["Visualizza"].tap()
            
    }
    
    func testNuovaLetturaButtonDidPressed() {
        app.launch()
        app.staticTexts["Accedi come : Tecnico"].tap()
        app.pickerWheels.element.adjust(toPickerWheelValue: "Cliente")
        app.buttons["Log In"].tap()
        app.buttons["Nuova Lettura"].tap()
        
    }
    
    func testShowComeBackLoginAlertMessage(){
        app.launch()
        app.staticTexts["Accedi come : Tecnico"].tap()
        app.pickerWheels.element.adjust(toPickerWheelValue: "Cliente")
        app.buttons["Log In"].tap()
        app.buttons["Log Out"].tap()
        app.buttons["Si"].tap()
    }
    
    func testDatePickerIsHidden() {
        app.launch()
        app.staticTexts["Accedi come : Tecnico"].tap()
        app.pickerWheels.element.adjust(toPickerWheelValue: "Cliente")
        app.buttons["Log In"].tap()
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "YYYY-MM-dd"
        let todayDate = dateFormatter.string(from: Date())
        app.staticTexts["Da: \(todayDate)"].tap()
        // metodo chiamato con valore false
        XCTAssertTrue(app.datePickers.element.isHittable)
        XCTAssertEqual(app.buttons.containing(.button, identifier: "Visualizza").count, 0)
        XCTAssertEqual(app.staticTexts.containing(.button, identifier: "Da: \(todayDate)").count, 0)
        XCTAssertEqual(app.staticTexts.containing(.button, identifier: "A: \(todayDate)").count, 0)
        app.staticTexts["RELATIVA AI PAGAMENTI"].tap()
        //metodo chiamato con valore true
        XCTAssertEqual(app.datePickers.count,0)
        XCTAssertTrue(app.buttons["Visualizza"].isHittable)
        XCTAssertTrue(app.staticTexts["Da: \(todayDate)"].isHittable)
        XCTAssertTrue(app.staticTexts["A: \(todayDate)"].isHittable)
    }
    
    func testAddGestureRecognizerToView(){
        app.launch()
        app.staticTexts["Accedi come : Tecnico"].tap()
        app.pickerWheels.element.adjust(toPickerWheelValue: "Cliente")
        app.buttons["Log In"].tap()
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "YYYY-MM-dd"
        var todayDate = dateFormatter.string(from: Date())
        let inputValues = [["May","4","2017"],["June","2","2017"],["January","14","2018"],["December","12","2018"],["January","15","2018"]]
        let results = ["2017-05-04","2017-06-02","2018-01-14","2018-12-12","2018-01-15"]
        for index in 0...inputValues.count - 1{
            app.staticTexts["Da: \(todayDate)"].tap()
            app.pickerWheels.element(boundBy: 2).adjust(toPickerWheelValue: inputValues[index][2])
            app.pickerWheels.element(boundBy: 1).adjust(toPickerWheelValue: inputValues[index][1])
            app.pickerWheels.element(boundBy: 0).adjust(toPickerWheelValue: inputValues[index][0])
            app.staticTexts["RELATIVA AI PAGAMENTI"].tap()
            app.staticTexts["Da: \(results[index])"].isHittable
            todayDate = results[index]
        }
        
    }
    

}
