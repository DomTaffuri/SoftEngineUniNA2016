//
//  IngiunzioneTableViewCell.swift
//  SoftEngineUniNA
//
//  Created by Domenico Taffuri on 06/02/2019.
//  Copyright © 2019 Domenico Taffuri. All rights reserved.
//

import UIKit

class IngiunzioneTableViewCell: UITableViewCell {
    
    @IBOutlet weak var tipoLabel: UILabel!
    
    @IBOutlet weak var idLabel: UILabel!
    
    @IBOutlet weak var nProtocolloLabel: UILabel!
    
    @IBOutlet weak var moraLabel: UILabel!
    
    @IBOutlet weak var totaleLabel: UILabel!
    
    @IBOutlet weak var statoLabel: UILabel!
    
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
