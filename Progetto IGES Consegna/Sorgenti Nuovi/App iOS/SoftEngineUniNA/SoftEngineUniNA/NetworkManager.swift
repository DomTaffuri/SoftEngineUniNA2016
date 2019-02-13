import Foundation

class NetworkManager{
    static let sharedInstance = NetworkManager()
    //private let ipAddress : String = "localhost"
    private let ipAddress : String = "192.168.64.2"
    
    private init(){}
    
    
    func checkIfCredentialExists(username:String,passw:String,completion:@escaping (Bool?) -> Void){
        let url = URL(string: "http://\(ipAddress)/checkCredential.php?argument1=\(username)&argument2=\(passw)")
        do{
            let res = try String(contentsOf: url!)
            
            if let intRes = Int(res){
                if intRes > 0{
                    completion(true)
                }
                else{
                    completion(false)
                }
            }
            else{
                completion(false)
            }
        }
        catch{
            print(error)
            completion(nil)
        }
    }
    
    func checkIfCodContatoreExists(cod:String,completition:@escaping (Bool?) -> Void){
        let url = URL(string: "http://\(ipAddress)/checkCodContatore.php?argument1=\(cod)")
        do{
            let res = try String(contentsOf: url!)
            let intRes = Int(res)
            if intRes! > 0{
                completition(true)
            }
            else{
                completition(false)
            }
        }
        catch{
            completition(nil)
        }
    }
    
    func sendLetturaToDatabase(codContatore:String,mc:String,da:String,completition:@escaping (Bool?) -> ()){
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "yyyy-MM-dd"
        let date = dateFormatter.string(from: Date())
        let url = URL(string: "http://\(ipAddress)/sendData.php?argument1=\(codContatore)&argument2=\(mc)&argument3=\(date)&argument4=\(da)")
        do{
            let res = try String(contentsOf: url!)
            if res == "true"{
                let checkLetUrl = URL(string: "http://\(ipAddress)/checkLetture.php?argument1=\(codContatore)&argument2=\(date)&argument3=\(da)&argument4=\(mc)")
                let _ = try? String(contentsOf: checkLetUrl!)
                completition(true)
            }
            else{
                completition(false)
            }
        }
        catch{
            completition(nil)
        }
    }
    
    func getStatoPagamenti(username : String, dataInizio : String, dataFine : String,completion:@escaping ([[String:String]]?) -> ()){
        getLetture(username: username, dataInizio: dataInizio, dataFine: dataFine) { (res) in
            guard res != nil else {completion(nil);return}
            self.getIngiunzioni(username: username, dataInizio: dataInizio, dataFine: dataFine, completion: { (result) in
                guard result != nil else {completion(nil);return}
                var totalResult = [[String:String]]()
                for elem in res!{
                    totalResult.append(elem)
                }
                for elem in result!{
                    totalResult.append(elem)
                }
                completion(totalResult)
                return
            })
        }
        
    }
    
    private func getIngiunzioni(username : String,dataInizio:String,dataFine:String,completion:@escaping ([[String:String]]?) -> ()){
        let url = URL(string: "http://\(ipAddress)/getIngiunzioni.php?argument1=\(username)&argument2=\(dataInizio)&argument3=\(dataFine)")
        guard url != nil else {completion(nil);return}
        URLSession.shared.dataTask(with: url!) { (data, res, err) in
            if err != nil{
                completion(nil)
                return
            }
            guard let d = data else {completion(nil);return}
            do{
                let result = try JSONDecoder().decode([[String:String]].self, from: d)
                completion(result)
                return
            }
            catch{
                print(error.localizedDescription)
                completion(nil)
                return
            }
        }.resume()
        
    }
    
    private func getLetture(username : String,dataInizio:String,dataFine:String,completion:@escaping ([[String:String]]?) -> ()){
        let url = URL(string: "http://\(ipAddress)/getLetture.php?argument1=\(username)&argument2=\(dataInizio)&argument3=\(dataFine)")
        guard url != nil else {completion(nil);return}
        URLSession.shared.dataTask(with: url!) { (data, res, err) in
            if err != nil{
                completion(nil)
                return
            }
            guard let d = data else {completion(nil);return}
            do{
                let result = try JSONDecoder().decode([[String:String]].self, from: d)
                completion(result)
                return
            }
            catch{
                print(error.localizedDescription)
                completion(nil)
                return
            }
        }.resume()
        
    }
    
   
    
    
}
