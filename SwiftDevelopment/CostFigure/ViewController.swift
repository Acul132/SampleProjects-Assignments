//
//  ViewController.swift
//  CostFigure
//
//  Created by Josh Renelli on 2017-08-01.
//  Copyright © 2017 Joshua Renelli. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UITextFieldDelegate {
    
    //Final price variable
    var price = ""
    
    //Creating dictionary to easily convert letters to numbers
    let costFigure: [Character:Character] = ["c":"0","o":"1","s":"2","t":"3","f":"4",
                                            "i":"5","g":"6","u":"7","r":"8","e":"9"]
    
    @IBOutlet weak var cost: UITextField!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.cost.delegate = self
    }
    
    //Call calculate when the calculateCost button is clicked
    @IBAction func calculateCost(_ sender: UIButton) {
        calculate()
    }
    
    //Calculate the price by parsing the values in the text field, and setting "price" accordingly
    func calculate(){
        price = ""
        if let enteredCost = cost.text{
            if isCost(cost: enteredCost){       //Ensures only valid letters have been entered
                for value in enteredCost.lowercased().characters{
                    price.append(costFigure[value]!)
                }
                price = "$\(String(Double(price)!/100))"
                displayPrice()
                cost.text = ""
                cost.placeholder = "Enter another cost"
            }
            else{
                cost.text = ""
                cost.placeholder = "Illegal Value Entered"
            }
        }
    }
    
    //Determines if an invalid letter has been entered
    func isCost(cost: String)->Bool{
        var goodChar = false
        for char in cost.lowercased().characters{
            switch char{
            case "c":   goodChar = true
            case "o":   goodChar = true
            case "s":   goodChar = true
            case "t":   goodChar = true
            case "f":   goodChar = true
            case "i":   goodChar = true
            case "g":   goodChar = true
            case "u":   goodChar = true
            case "r":   goodChar = true
            case "e":   goodChar = true
            default:
                goodChar = false
            }
            if !goodChar{
                break
            }
        }
        return goodChar
    }
    
    //Display the price back to the user
    func displayPrice(){
        let alertController = UIAlertController(title: "The item's cost", message: "\(price)", preferredStyle: .alert)
        let actionItem = UIAlertAction(title:"OK", style: .default,handler:
            nil)
        alertController.addAction(actionItem)
        present(alertController, animated: true, completion: nil)
    }
    
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        self.view.endEditing(true)
    }
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        cost.resignFirstResponder()
        return(true)
    }
}

