import UIKit

class SendDataViewController: UIViewController,UITextFieldDelegate {

    // MARK: - Variables Outlet
    
    @IBOutlet weak var contatoreTextField: UITextField!
    
    @IBOutlet weak var letturaTextField: UITextField!
    
    @IBOutlet weak var inviaDatiButton: BlueRoundedButton!
    
    @IBOutlet weak var loadingIndicator: UIActivityIndicatorView!
    
    @IBOutlet weak var textFieldImageView: UIImageView!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.hideKeyboardWhenTappedAround()
        setNameOfButton()
        hideLoadingIndicator()
        disableInviaDatiButton()
        hideTextFieldImageView()
        setTextFieldsDelegate()
        
    }
    
    override func viewWillAppear(_ animated: Bool) {
        self.navigationItem.leftBarButtonItem = UIBarButtonItem.init(title: "Log Out", style: .plain, target: self, action: #selector(self.showComeBackLoginAlertMessage))
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    
    //MARK: - Actions
    
    @IBAction func inviaDatiButtonDidPressed(_ sender: Any) {
        let isInternetOk = self.isInternetAvailable()
        do{
            try checkIfLetturaIsValid()
            if isInternetOk{
                sendLetturaToDatabase()
            }
            else{
                let db = UserDefaults.standard.value(forKey: "dbLetture") as! [[String:String]]
                if db.count >= 100{
                    self.showAlertMessage(title: "Attenzione", message: "Limite massimo di letture salvate localmente raggiunto")
                }
                else{
                    self.saveLetturaInLocale()
                }
            }
        }
        catch{
            switch error {
            case InviaDatiExceptions.letturaTextFieldEmpty:
                self.showAlertMessage(title: "Attenzione", message: "Riempi tutti i campi")
            case InviaDatiExceptions.letturaWithChar:
                self.showAlertMessage(title: "Attenzione", message: "Il campo lettura può contenere solo numeri")
            case InviaDatiExceptions.spaceInTextField:
                self.showAlertMessage(title: "Attenzione", message: "Username o password contengono spazi")
            default:
                break
            }
        }
    }
    
    //MARK: - TextField Delegate Methods
    
    func textFieldDidBeginEditing(_ textField: UITextField) {
        textField.placeholder = ""
    }
    
    func textFieldDidEndEditing(_ textField: UITextField) {
        setInviaDatiTextFieldsPlaceholdersIfCan()
        if textField == contatoreTextField{
            checkIfCodContatoreIsValid()
        }
    }
    
    //MARK: - Private functions
    
    private func saveLetturaInLocale(){
        let contatore = contatoreTextField.text
        let lettura = letturaTextField.text
        if contatore != "" && lettura != ""{
            self.showAlertMessage(title: "Connessione assente", message: "La lettura sarà salvata in locale e verrà inviata non appena la connessione internet sarà disponibile")
            var db = UserDefaults.standard.value(forKey: "dbLetture") as! [[String:String]]
            let nuovaLettura = ["contatore":contatore!,"lettura":lettura!]
            db.append(nuovaLettura)
            UserDefaults.standard.set(db, forKey: "dbLetture")
        }
        else{
            self.showAlertMessage(title: "Attenzione", message: "Riempi tutti i campi")
        }    }
    
    private func setTextFieldsDelegate(){
        contatoreTextField.delegate = self
        letturaTextField.delegate = self
    }
    
    private func setNameOfButton(){
        self.inviaDatiButton.setTitle("Invia Dati", for: .normal)
    }
    
    private func setInviaDatiTextFieldsPlaceholdersIfCan(){
        if contatoreTextField.text == ""{
            contatoreTextField.placeholder = "contatore"
        }
        if letturaTextField.text == ""{
            letturaTextField.placeholder = "lettura"
        }
    }
    
    private func hideLoadingIndicator(){
        self.loadingIndicator.stopAnimating()
        self.loadingIndicator.isHidden = true
    }
    
    private func showLoadingIndicator(){
        self.loadingIndicator.startAnimating()
        self.loadingIndicator.isHidden = false
    }
    
    private func disableInviaDatiButton(){
        self.inviaDatiButton.isEnabled = false
        self.inviaDatiButton.alpha = 0.5
    }
    
    private func enableInviaDatiButton(){
        self.inviaDatiButton.isEnabled = true
        self.inviaDatiButton.alpha = 1
    }
    
    private func hideTextFieldImageView(){
        self.textFieldImageView.isHidden = true
    }
    
    private func showTextFieldImageView(image:UIImage){
        hideLoadingIndicator()
        self.textFieldImageView.image = image
        self.textFieldImageView.isHidden = false
    }
    
    private func checkIfCodContatoreIsValid(){
            let cod = contatoreTextField.text
            if cod != ""{
                if (cod?.contains(" "))! == false{
                    showLoadingIndicator()
                    let isInternetOk = self.isInternetAvailable()
                    if isInternetOk{
                    NetworkManager.sharedInstance.checkIfCodContatoreExists(cod: cod!, completition: { (result) in
                        if let res = result{
                                if res{
                                    self.showTextFieldImageView(image: #imageLiteral(resourceName: "ok"))
                                    self.enableInviaDatiButton()
                                }
                                else{
                                    self.showTextFieldImageView(image: #imageLiteral(resourceName: "error"))
                                    self.disableInviaDatiButton()
                                    self.showAlertMessage(title: "Attenzione", message: "Codice contatore non presente nel database")
                                }
                            }
                            else{
                                self.showAlertMessage(title: "Attenzione", message: "Errore di comunicazione con il database")
                            }
                        })
                    }
                    else{ //CONNESSIONE NON PRESENTE
                        self.showTextFieldImageView(image: #imageLiteral(resourceName: "offline"))
                        self.enableInviaDatiButton()
                    }
                }
                else{
                    self.showAlertMessage(title: "Attenzione", message: "Username o password contengono spazi")
                }
            }
            else{
                self.textFieldImageView.isHidden = true
            }
    }
    
    private func checkIfLetturaIsValid() throws{
        if letturaTextField.text == ""{throw InviaDatiExceptions.letturaTextFieldEmpty}
        let nums: Set<Character> = ["0", "1", "2", "3", "4", "5", "6", "7", "8", "9"]
        if Set(letturaTextField.text!.characters).isSubset(of: nums) == false{
            throw InviaDatiExceptions.letturaWithChar
        }
        if (contatoreTextField.text?.contains(" "))! || (letturaTextField.text?.contains(" "))!{
            throw InviaDatiExceptions.spaceInTextField
        }
    }
    
    private func sendLetturaToDatabase(){
        NetworkManager.sharedInstance.sendLetturaToDatabase(codContatore: contatoreTextField.text!, mc: letturaTextField.text!) { (res) in
            if let ris = res{
                if ris == true{
                    self.showAlertMessage(title: "Attenzione", message: "Lettura inserita correttamente")
                }
                else{
                    self.showAlertMessage(title: "Attenzione", message: "Errore sconosciuto")
                }
            }
            else{
                self.showAlertMessage(title: "Attenzione", message: "Errore di comunicazione con il database")
            }
        }
    }
    
    @objc private func showComeBackLoginAlertMessage(){
        let alert = UIAlertController(title: "Attenzione", message: "Vuoi davvero effettura il log out?", preferredStyle: .alert)
        let yes = UIAlertAction(title: "Si", style: .destructive) { (action) in
            self.navigationController?.popViewController(animated: true)
        }
        let no = UIAlertAction(title: "No", style: .default, handler: nil)
        alert.addAction(no)
        alert.addAction(yes)
        self.present(alert, animated: true, completion: nil)
    }
}
