<?php
$servername = "localhost";
$username = "root";
$password = "";
$nomedb = "db_ingsw";
$argument1 = $_GET['argument1']; //codContatore
$argument2 = $_GET['argument2']; // data
$argument3 = $_GET['argument3']; // effettuatoDa
$argument4 = $_GET['argument4']; // mc

// Create connection
$conn = new mysqli($servername, $username, $password, $nomedb);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 
    $split = strtok($argument2,"-");
    $year = "$split<br>";
    $split = strtok("-");
    $month = "$split<br>";
    $da;
    if ($argument3 =='T'){
        $da = 'C';
    }
    else{
        $da = 'T';
    }
      
    $utente = $conn->query("SELECT idUtenza FROM utenza WHERE codContatore = $argument1");
    $row=mysqli_fetch_object($utente);
    $letturaRes = $conn->query("SELECT * FROM lettura WHERE idUtenza = $row->idUtenza AND effettuataDa = '$da' AND statoBolletta != 'NV' AND YEAR(dataLettura) = '$year' AND MONTH(dataLettura) = '$month'" );
    if ($letturaRes->num_rows > 0){
        $letturaRow = mysqli_fetch_object($letturaRes);
        if($letturaRow->mc != $argument4){//letture diverse , passare in uno stato NV
            if($conn->query("UPDATE lettura SET statoBolletta = 'NV' WHERE idLettura = $letturaRow->idLettura") == FALSE){
                echo "Errore update lettura 1";
            }
            else{
                if($conn->query("UPDATE lettura SET statoBolletta = 'NV' WHERE idUtenza = $row->idUtenza AND dataLettura = '$argument2' AND mc = $argument4 AND effettuataDa = '$argument3'") == FALSE){
                    echo "Errore update lettura 2";
                }
            }
        }
        else{ //letture uguali eliminare quella del cliente
            if($conn->query("DELETE from lettura WHERE statoBolletta = 'NP' AND effettuataDa = 'C' AND idUtenza = $row->idUtenza") == FALSE){
                echo "Errore delete";
            }
            
        }
    }
    else{
        echo "0 risultati";
    }
?>
