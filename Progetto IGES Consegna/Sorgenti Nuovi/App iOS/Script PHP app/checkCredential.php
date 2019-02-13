<?php
$servername = "localhost";
$nomedb = "db_ingsw";
$argument1 = $_GET['argument1'];
$argument2 = $_GET['argument2'];

    

// Create connection
$conn = new mysqli($servername, $argument1, $argument2, $nomedb);

// Check connection
if ($conn->connect_error) {
    echo 0;
}
else
    echo 1;



?>
