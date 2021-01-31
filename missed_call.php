<?php
$servername = "localhost";
$username = "id7723443_anand";
$password = "123456";
$dbname = "id7723443_missed_call";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);


// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 

$mobile_number=$_POST["mobile_number"];

$sql= " INSERT INTO save_number(missed_number) VALUES ('$mobile_number')";



if ($conn->query($sql) === TRUE) {
    echo "New record add successfully";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}



$conn->close();
?>
