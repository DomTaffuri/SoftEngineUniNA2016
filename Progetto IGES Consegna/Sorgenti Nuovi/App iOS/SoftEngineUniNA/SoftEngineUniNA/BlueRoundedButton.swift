import UIKit

class BlueRoundedButton: UIButton {

    override func awakeFromNib() {
        super.awakeFromNib()
        self.clipsToBounds = true
        self.layer.cornerRadius = 5
        self.tintColor = UIColor.black
        self.backgroundColor = UIColor.init(red: 0, green: 186/255, blue: 227/255, alpha: 1)
        self.layer.shadowColor = UIColor.black.cgColor
        self.layer.shadowOffset = CGSize(width: 10, height: 10)
        self.layer.shadowOpacity = 100
        self.layer.shadowRadius = 10
    }

}
