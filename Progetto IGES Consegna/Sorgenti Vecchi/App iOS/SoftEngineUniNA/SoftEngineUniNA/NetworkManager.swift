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
                completion(nil)
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
    
    func sendLetturaToDatabase(codContatore:String,mc:String,completition:@escaping (Bool?) -> ()){
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "yyyy-MM-dd"
        let date = dateFormatter.string(from: Date())
        let url = URL(string: "http://\(ipAddress)/sendData.php?argument1=\(codContatore)&argument2=\(mc)&argument3=\(date)")
        do{
            let res = try String(contentsOf: url!)
            if res == "true"{
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
    
    
}
