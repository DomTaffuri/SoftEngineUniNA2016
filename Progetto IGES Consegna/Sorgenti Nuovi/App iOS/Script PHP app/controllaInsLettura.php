<?php
$servername = "localhost";
$username = "root";
$password = "";
$nomedb = "db_ingsw";
$from = $_GET['argument1'];


// Create connection
$conn = new mysqli($servername, $username, $password, $nomedb);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 


$risultato = $conn->query("SELECT CodContatore FROM Utenza WHERE CodContatore = $argument1");
    
    if ($risultato == NULL) {
        echo 0;
    }
    else{
        $rows = $risultato->num_rows;
        echo $rows;
    }
?>
