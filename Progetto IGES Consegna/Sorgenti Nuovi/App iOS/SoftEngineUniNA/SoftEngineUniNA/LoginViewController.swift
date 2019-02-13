import UIKit

class LoginViewController: UIViewController,UITextFieldDelegate,TouchableLabelDelegate,UIPickerViewDataSource,UIPickerViewDelegate{

    //MARK: - Variables Outlets
    
    
    @IBOutlet weak var ricordamiLabel: UILabel!
    
    @IBOutlet weak var userTextField: UITextField!
    
    @IBOutlet weak var passwordTextField: UITextField!
    
    @IBOutlet weak var loginButton: BlueRoundedButton!
    
    @IBOutlet weak var rememberSwitch: UISwitch!
    
    @IBOutlet weak var accediComeLabel: TouchableLabel!
    
    @IBOutlet weak var loginPickerView: UIPickerView!
    
    //MARK: - Private variables
    
    private var oldCenterOfView : CGPoint?
    private var isKeyboardOnScreen = false
    private var isLocalDBEmpty = false
    private var loginAs = "T"
    
    //MARK: - System Methods
    
    override func viewDidLoad() {
        super.viewDidLoad()
        putImagesInLoginTextFields()
        setLoginButtonTitle()
        initRememberSwitch()
        self.hideKeyboardWhenTappedAround()
        setLoginTextFieldsDelegate()
        addKeyboardObservers()
        checkAndSaveLocalDBIfPossible()
        accediComeLabel.delegate = self
        
    }
    
    override func viewWillAppear(_ animated: Bool) {
        loadLoginDataIfRememberIsActive()
    }
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    //MARK: - PickerViewDataSource
    
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 1
    }
    
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        return 2
    }
    
    //MARK: - PickerViewDelegate
    
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        switch row {
        case 0:
            return "Tecnico"
        case 1:
            return "Cliente"
        default:
            return "Error"
        }
    }
    
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        switch row {
        case 0:
            accediComeLabel.text = "Accedi come : Tecnico"
            loginAs = "T"
        case 1:
            accediComeLabel.text = "Accedi come : Cliente"
            loginAs = "C"
        default:
            return
        }
        pickerViewIsHidden(value: true)
    }
    
    //MARK: - Actions
    
    @IBAction func loginButtonDidPressed(_ sender: Any) {
        do{
            try checkIfLoginIsValid()
            let username = userTextField.text!
            let password = passwordTextField.text!
            NetworkManager.sharedInstance.checkIfCredentialExists(username: username, passw: password) { (res) in
                if let exists = res{
                    if exists{
                        self.saveLoginDataIfRememberIsActive(user: username, pass: password)
                        if(self.loginAs == "T"){
                            self.performSegue(withIdentifier: "fromLoginToInvia", sender: nil)
                        }
                        else{
                            self.performSegue(withIdentifier: "fromLoginToVisualizza", sender: nil)
                        }
                        
                    }
                    else{
                        self.showAlertMessage(title: "Attenzione", message: "Username o password errati")
                    }
                }
                else{
                    self.showAlertMessage(title: "Attenzione", message: "Errore di comunicazione con il database")
                }
            }
        }
        catch{
            switch error {
            case LoginExceptions.textFieldsEmpty:
                self.showAlertMessage(title: "Attenzione", message: "Riempi tutti i campi")
            case LoginExceptions.spaceInText:
                self.showAlertMessage(title: "Attenzione", message: "Username o password contengono spazi")
            default:
                break
            }
        }
    }
    
    //MARK: - TextField Delegate functions
    
    func textFieldDidBeginEditing(_ textField: UITextField) {
        textField.placeholder = ""
        
    }
    
    func textFieldDidEndEditing(_ textField: UITextField) {
        setLoginTextFieldsPlaceholdersIfCan()
    }
    
    //MARK: - Private functions
    
    private func checkAndSaveLocalDBIfPossible(){
        checkLocalDB()
        if !self.isLocalDBEmpty && isInternetAvailable(){
            DispatchQueue.init(label: "q").async {
                self.writeLocalDBOnServer()
            }
        }
    }
    
    private func checkLocalDB(){
        let db = UserDefaults.standard.value(forKey: "dbLetture") as! [[String:String]]
        if db.count == 0 {
            self.isLocalDBEmpty = true
        }
    }
    
    private func writeLocalDBOnServer(){
        var db = UserDefaults.standard.value(forKey: "dbLetture") as! [[String:String]]
        for lect in db{
            let codContatore = lect["contatore"]
            let lettura = lect["lettura"]
            NetworkManager.sharedInstance.checkIfCodContatoreExists(cod: codContatore!, completition: { (exists) in
                if let esiste = exists{
                    if esiste{
                        NetworkManager.sharedInstance.sendLetturaToDatabase(codContatore: codContatore!, mc: lettura!,da:self.loginAs, completition: { (result) in
                            
                        })
                    }
                }
                else{ // PROBLEMA DEL SERVER
                    
                }
            })
        }
        db = [[String:String]]()
        UserDefaults.standard.set(db, forKey: "dbLetture")
    }
    
    private func putImagesInLoginTextFields(){
        addImageToTextField(image: #imageLiteral(resourceName: "user"), textField: userTextField)
        addImageToTextField(image: #imageLiteral(resourceName: "pass"), textField: passwordTextField)
    }
    
    private func addImageToTextField(image:UIImage,textField:UITextField){
        let imageView = UIImageView()
        imageView.image = image
        let size = textField.frame.height/2
        imageView.frame = CGRect(x:10, y: textField.frame.height/5, width: size, height: size)
        textField.leftViewMode = .always
        textField.addSubview(imageView)
    }
    
    private func setLoginButtonTitle(){
        loginButton.setTitle("Log In", for: .normal)
    }
    
    private func initRememberSwitch(){
        rememberSwitch.onTintColor = UIColor.init(red: 0, green: 186/255, blue: 227/255, alpha: 1)
        rememberSwitch.transform = CGAffineTransform(scaleX: 0.50, y: 0.50)
    }
    
    private func setLoginTextFieldsPlaceholdersIfCan(){
        if userTextField.text == ""{
            userTextField.placeholder = "username"
        }
        if passwordTextField.text == ""{
            passwordTextField.placeholder = "password"
        }
    }
    
    private func setLoginTextFieldsDelegate(){
        userTextField.delegate = self
        passwordTextField.delegate = self
    }
    
    private func checkIfLoginIsValid() throws{
        if userTextField.text == "" || passwordTextField.text == "" {throw LoginExceptions.textFieldsEmpty}
        if (userTextField.text?.contains(" "))! || (passwordTextField.text?.contains(" "))!{
            throw LoginExceptions.spaceInText
        }
    }
    
    private func addKeyboardObservers(){
        NotificationCenter.default.addObserver(self, selector: #selector(keyboardWillShow(_:)), name: NSNotification.Name.UIKeyboardWillShow, object: nil)
        NotificationCenter.default.addObserver(self, selector: #selector(self.keyboardWillDisappear), name: NSNotification.Name.UIKeyboardWillHide, object: nil)
    }
    
    @objc private func keyboardWillShow(_ notification: Notification) {
        if !isKeyboardOnScreen{
            if let keyboardFrame: NSValue = notification.userInfo?[UIKeyboardFrameEndUserInfoKey] as? NSValue {
                let keyboardRectangle = keyboardFrame.cgRectValue
                let minY = keyboardRectangle.minY
                if minY < ricordamiLabel.frame.maxY{
                    let offset = ricordamiLabel.frame.maxY - minY
                    self.oldCenterOfView = self.view.center
                    self.view.center = CGPoint(x: self.view.center.x, y: self.view.center.y - offset)
                }
            }
            isKeyboardOnScreen = true
        }
    }
    
    @objc private func keyboardWillDisappear(){
        if let center = self.oldCenterOfView{
            self.view.center = center
            self.oldCenterOfView = nil
            isKeyboardOnScreen = false
        }
    }
    
    private func loadLoginDataIfRememberIsActive(){
        let switchState = UserDefaults.standard.value(forKey: "rememberSwitch") as! Bool
        rememberSwitch.setOn(switchState, animated: false)
        if switchState{
            let user = UserDefaults.standard.value(forKey: "user") as! String
            let pass = UserDefaults.standard.value(forKey: "pass") as! String
            userTextField.text = user
            passwordTextField.text = pass
        }
        else{
            userTextField.text = ""
            passwordTextField.text = ""
            setLoginTextFieldsPlaceholdersIfCan()
        }
    }
    
    private func saveLoginDataIfRememberIsActive(user:String,pass:String){
        UserDefaults.standard.set(rememberSwitch.isOn, forKey: "rememberSwitch")
        if rememberSwitch.isOn{
            UserDefaults.standard.set(userTextField.text, forKey: "user")
            UserDefaults.standard.set(passwordTextField.text, forKey: "pass")
        }
        else{
            UserDefaults.standard.set("", forKey: "user")
            UserDefaults.standard.set("", forKey: "pass")
        }
    }
    
    func pickerViewIsHidden(value : Bool){
        loginPickerView.isHidden = value
        userTextField.isHidden = !value
        passwordTextField.isHidden = !value
        accediComeLabel.isHidden = !value
        ricordamiLabel.isHidden = !value
        rememberSwitch.isHidden = !value
        loginButton.isHidden = !value
    }
    
    func labelTapped(sender:TouchableLabel) {
        pickerViewIsHidden(value: false)
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "fromLoginToInvia"{
            let destinationVC = segue.destination as! SendDataViewController
            destinationVC.loginAs = self.loginAs
        }
        
        if segue.identifier == "fromLoginToVisualizza"{
            let destinationVC = segue.destination as! VisualizzaPagamentiViewController
            destinationVC.username = userTextField.text!
        }
    }
    
    
}
