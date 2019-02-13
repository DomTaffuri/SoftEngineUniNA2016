//
//  TouchableLabel.swift
//  SoftEngineUniNA
//
//  Created by Domenico Taffuri on 28/01/2019.
//  Copyright © 2019 Domenico Taffuri. All rights reserved.
//

import UIKit


class TouchableLabel: UILabel {
    var delegate : TouchableLabelDelegate?
    
    override func awakeFromNib() {
        super.awakeFromNib()
        let tapGesture = UITapGestureRecognizer(target: self, action: #selector(self.tapped(sender:)))
        addGestureRecognizer(tapGesture)
        isUserInteractionEnabled = true
    }
    
    @objc func tapped(sender : UITapGestureRecognizer){
        if let del = delegate{
            del.labelTapped(sender: self)
        }
    }
    
}
