<?php
$servername = "localhost";
$username = "root";
$password = "";
$nomedb = "db_ingsw";
$argument1 = $_GET['argument1'];


// Create connection
$conn = new mysqli($servername, $username, $password, $nomedb);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 


$risultato = $conn->query("SELECT CodContatore FROM utenza WHERE CodContatore = $argument1");
    if ($risultato == NULL) {
        echo 0;
    }
    else{
        $rows = $risultato->num_rows;
        echo $rows;
    }
?>
