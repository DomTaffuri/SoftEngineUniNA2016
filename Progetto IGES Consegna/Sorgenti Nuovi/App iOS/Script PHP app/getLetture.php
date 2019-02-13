<?php
$servername = "localhost";
$username = "root";
$password = "";
$nomedb = "db_ingsw";
$argument1 = $_GET['argument1']; //username
$argument2 = $_GET['argument2']; //data inizio
$argument3 = $_GET['argument3']; //data fine

// Create connection
$conn = new mysqli($servername, $username, $password, $nomedb);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 
   
    
    $res = $conn->query("SELECT idUtente FROM credenziali WHERE nickname = '$argument1'");
    $row=mysqli_fetch_object($res);
    
    
    $res = $conn->query("SELECT idLettura,mc,dataLettura,statoBolletta,effettuataDa FROM lettura WHERE dataLettura >= '$argument2' AND dataLettura <= '$argument3' AND idUtenza = $row->idUtente AND idIngiunzione IS NULL");
    $arr = array();
    while($row = $res->fetch_assoc()){
        $arr[] = $row;
    }
    echo json_encode($arr);
    
    /*$sql = "INSERT INTO lettura (mc,statoBolletta,idUtenza,dataLettura,effettuataDa) VALUES ($argument2,'NP',$row->idUtenza,'$argument3','$argument4')";
    
    if ($conn->query($sql) == TRUE) {
        echo "true";
    } else {
        echo "false";
    }*/


?>
