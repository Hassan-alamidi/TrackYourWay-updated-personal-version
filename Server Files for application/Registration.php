<?php 
	echo "it made it to the file";
	include("dbconnect.php");
	$Username = $_POST['UserName'];
	$Password = $_POST['Password'];
	$Name = $_POST['Name'];
	$Age = $_POST['Age'];
	$Email = $_POST['Email'];
	$PrevCollege = $_POST['PrevCollege'];
	$PrevCourse = $_POST['PrevCourse'];
	$Cert = $_POST['Certificate'];
	$Interests = $_POST['Interests'];
	echo "The name given was ",$Name, " Password ", $Password, " username ", $Username, " Email ", $Email;
	/*this if statement is used to avoid null items going into database because retrieving null items from the database when logging in causes and error*/
	if(empty($_POST['PrevCollege']) || !isset($_POST['PrevCollege'])){
		$PrevCollege = "Notspecified";
		echo "prevCollege is empty";
	}
	if(empty($_POST['PrevCourse'])  || !isset($_POST['PrevCourse'])){
		$PrevCourse = "Notspecified";
	}
	if(empty($_POST['Certificate'])  || !isset($_POST['Certificate'])){
		$Certificate = "Notspecified";
	}
	if(empty($_POST['Interests'])  || !isset($_POST['Interests'])){
		$Interests = "Notspecified";
	}
	echo "about to insert";
	//$sql= $connect->prepare("INSERT INTO UserDetails (UserName, Password, UserEmail, Name, age, CertificatesAchieved, Interests, PrevCollege, PrevCourse) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"); 
	//testing
	$sql= $connect->prepare("INSERT INTO UserDetails (UserName, Password, UserEmail, Name, age, CertificatesAchieved, Interests, PrevCollege, PrevCourse) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"); 
	if ( false===$sql) {
		echo "failed to prepare";
		die('failed error 1: There seems to be an issue with your query ' . htmlspecialchars($connect->error));	
	}
	$sql->bind_param("sssssssss", $Username, $Password, $Email, $Name, $Age, $Cert, $Interests, $PrevCollege, $PrevCourse);
		if ( false===$sql) {
		echo "failed to bind";
		die('failed error 2: Failed to bind paramiters to query ' . htmlspecialchars($sql->error));	
	}
	$sql->execute();
	if ( false===$sql) {
		echo "failed to execute";
		die('failed error 3: failed to execute query' . htmlspecialchars($sql->error));	
	}else{
		//$sql->close();
		mysqli_stmt_close($sql);	
		echo "worked";
							
	}
	
?>