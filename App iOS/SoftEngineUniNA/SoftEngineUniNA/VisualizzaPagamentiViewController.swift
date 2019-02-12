//
//  VisualizzaPagamentiViewController.swift
//  SoftEngineUniNA
//
//  Created by Domenico Taffuri on 06/02/2019.
//  Copyright © 2019 Domenico Taffuri. All rights reserved.
//

import UIKit

class VisualizzaPagamentiViewController: UIViewController,UITableViewDataSource,UITableViewDelegate,TouchableLabelDelegate {
    
    
    
    @IBOutlet weak var dataPicker: UIDatePicker!
    
    @IBOutlet weak var daLabel: TouchableLabel!
    
    @IBOutlet weak var aLabel: TouchableLabel!
    
    @IBOutlet weak var visualizzaButton: BlueRoundedButton!
    
    @IBOutlet weak var pagamentiTableView: UITableView!
    
    var username : String!
    var letture = [[String:String]]()
    private var lastDataLabelTapped : TouchableLabel?

    override func viewDidLoad() {
        super.viewDidLoad()
        daLabel.delegate = self
        aLabel.delegate = self
        addGestureRecognizerToView()
        initDateLabels()
        // Do any additional setup after loading the view.
    }
    
    override func viewWillAppear(_ animated: Bool) {
        self.navigationItem.leftBarButtonItem = UIBarButtonItem.init(title: "Log Out", style: .plain, target: self, action: #selector(self.showComeBackLoginAlertMessage))
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let element = letture[indexPath.row]
        if element["effettuataDa"] != nil{ //lettura
            let cell = tableView.dequeueReusableCell(withIdentifier: "letturaCell", for: indexPath) as! LetturaTableViewCell
            cell.tipoLabel.text = "Tipo:Lettura"
            cell.dataLabel.text = "Data:\(unwrapValue(value: element["dataLettura"]))"
            cell.effettuataLabel.text = "Effettuata da:\(unwrapValue(value: element["effettuataDa"]))"
            cell.idLabel.text = "ID:\(unwrapValue(value: element["idLettura"]))"
            cell.mcLabel.text = "mc:\(unwrapValue(value: element["mc"]))"
            cell.statoLabel.text = "Stato:\(unwrapValue(value: element["statoBolletta"]))"
            return cell
        }
        else{ //ingiunzione
            let cell = tableView.dequeueReusableCell(withIdentifier: "ingiunzioneCell", for: indexPath) as! IngiunzioneTableViewCell
            cell.idLabel.text = "ID:\(unwrapValue(value: element["idIngiunzione"]))"
            cell.moraLabel.text = "Mora:\(unwrapValue(value: element["mora"]))"
            cell.nProtocolloLabel.text = "nProtocllo:\(unwrapValue(value: element["nProtocollo"]))"
            cell.tipoLabel.text = "Tipo:Ingiunzione"
            cell.totaleLabel.text = "Totale da pagare:\(unwrapValue(value: element["importo"]))"
            return cell
            
        }
        
    }

    //MARK : - TableView Source and delegate
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return letture.count
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return pagamentiTableView.frame.height * 0.2
    }
    
    
    @IBAction func visualizzaButtonDidPressed(_ sender: Any) {
        if checkIfDateAreCorrect(){
            let inizio = String(daLabel.text!.components(separatedBy: ":")[1].dropFirst())
            let fine = String(aLabel.text!.components(separatedBy: ":")[1].dropFirst())
            NetworkManager.sharedInstance.getStatoPagamenti(username: username, dataInizio: inizio, dataFine: fine) { (res) in
                let controller = UIAlertController(title: "Attenzione", message: "Errore di comunicazione con il database", preferredStyle: .alert)
                let ok = UIAlertAction(title: "Ok", style: .default, handler: nil)
                controller.addAction(ok)
                guard res != nil else{
                    self.present(controller, animated: true, completion: nil)
                    return
                }
                guard res!.count > 0 else{
                    controller.message = "Nessun risultato trovato per l'intervallo inserito"
                    self.present(controller, animated: true, completion: nil)
                    return
                }
                self.letture = res!
                DispatchQueue.main.async {
                   self.pagamentiTableView.reloadData()
                }
                
            }
        }
        else{
            let controller = UIAlertController(title: "Attenzione", message: "Errore nelle date. Controlla che i campi non siano vuoti e che l'intervallo di tempo sia valido", preferredStyle: .alert)
            let ok = UIAlertAction(title: "Ok", style: .default, handler: nil)
            controller.addAction(ok)
            self.present(controller, animated: true, completion: nil)
        }
    }
    
    
    @IBAction func nuovaLetturaButtonDidPressed(_ sender: Any) {
        performSegue(withIdentifier: "fromVisualizzaToInvia", sender: nil)
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
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "fromVisualizzaToInvia"{
            let destinationVC = segue.destination as! SendDataViewController
            destinationVC.loginAs = "C"
        }
    }
    
     func datePickerIsHidden(value : Bool){
        dataPicker.isHidden = value
        visualizzaButton.isHidden = !value
        aLabel.isHidden = !value
        daLabel.isHidden = !value
        pagamentiTableView.isHidden = !value
    }
    
    func labelTapped(sender:TouchableLabel) {
        lastDataLabelTapped = sender
        datePickerIsHidden(value: false)
    }
    
    private func addGestureRecognizerToView(){
        let tapGesture = UITapGestureRecognizer(target: self, action: #selector(self.viewTapped(sender:)))
        self.view.addGestureRecognizer(tapGesture)
        self.view.isUserInteractionEnabled = true
    }
    
    private func initDateLabels(){
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "yyyy-MM-dd"
        let date = dateFormatter.string(from: Date())
        daLabel.text = "Da: \(date)"
        aLabel.text = "A: \(date)"
        dataPicker.maximumDate = Date()
    }

    @objc func viewTapped(sender: UITapGestureRecognizer){
        let date = dataPicker.date
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "yyyy-MM-dd"
        let stringDate = dateFormatter.string(from: date)
        switch lastDataLabelTapped {
        case daLabel:
            lastDataLabelTapped?.text = "Da: \(stringDate)"
        case aLabel:
            lastDataLabelTapped?.text = "A: \(stringDate)"
        default:
            return
        }
        datePickerIsHidden(value: true)
    }
    
   
        func checkIfDateAreCorrect() -> Bool{
        let from = daLabel.text
        let to = aLabel.text
        guard from != nil && to != nil else {return false}
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "yyyy-MM-dd"
        let from2 = String(from!.components(separatedBy: ":")[1].dropFirst())
        let to2 = String(to!.components(separatedBy: ":")[1].dropFirst())
        let fromDate = dateFormatter.date(from: from2)
        let toDate = dateFormatter.date(from: to2)
        guard fromDate! <= toDate! else {return false}
        return true
    }
    
    private func unwrapValue(value : String?) -> String{
        if let v = value{return v}
        return ""
    }
    
}
