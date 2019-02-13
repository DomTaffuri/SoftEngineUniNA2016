<?php
$servername = "localhost";
$username = "root";
$password = "";
$nomedb = "db_ingsw";
$argument1 = $_GET['argument1'];
$argument2 = $_GET['argument2'];
$argument3 = $_GET['argument3'];

// Create connection
$conn = new mysqli($servername, $username, $password, $nomedb);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 
   
    
    $res = $conn->query("SELECT idUtenza FROM utenza WHERE codContatore = $argument1");
    $row=mysqli_fetch_object($res);

    $sql = "INSERT INTO lettura (mc,statoBolletta,idUtenza,dataLettura) VALUES ($argument2,'NP',$row->idUtenza,'$argument3')";
    
    if ($conn->query($sql) == TRUE) {
        echo "true";
    } else {
        echo "false";
    }


?>
