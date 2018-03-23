//
//  GradiantView.swift
//  CostFigure
//
//  Created by Josh Renelli on 2017-08-01.
//  Copyright © 2017 Joshua Renelli. All rights reserved.
//

import UIKit

@IBDesignable
class GradiantView: UIView {

    @IBInspectable var firstColor: UIColor = UIColor.clear{
        didSet{
            updateView()
        }
    }
    
    @IBInspectable var secondColor: UIColor = UIColor.clear{
        didSet{
            updateView()
        }
    }
    
    override class var layerClass: AnyClass{
        get {
            return CAGradientLayer.self
        }
    }
    
    func updateView(){
        let layer = self.layer as! CAGradientLayer
        layer.colors = [firstColor.cgColor,secondColor.cgColor]
    }
}
