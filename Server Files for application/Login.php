<?php
	include("dbconnect.php");
	//echo "login file accessed";
	
	//get user input and convert to sha1 to match database versions
	//This is done because username and password when added to the database are converted to sha1 in order to provide a small amount of protection
	//these vars will be re-introduced once issues with app are fixed
//	$username = sha1($_POST['UserName']);
//	$password = sha1($_POST['Password']);
	$username = $_POST['UserName'];
	$password = $_POST['Password'];
	
				//connect to database and prepare mysqli statement to get password and user name if user entered correct details
				//will be adding more details for this statement to get once it is fully tested
				$loginDetails = $connect->prepare("SELECT UserName, Password, UserEmail, Name, age, CertificatesAchieved, Interests, PrevCollege, PrevCourse FROM UserDetails WHERE UserName= ? AND Password= ? ");
				//check if connection succeeded 
				if ( false===$loginDetails ) {
					die('failed error 1: There seems to be an issue with your query ' . htmlspecialchars($connect->error));
				}
				//bind the user name and password to the prepared statement
				$loginDetails->bind_param("ss", $username, $password);
				//check if failed
				if ( false===$loginDetails ) {
					die('failed error 2: Failed to bind paramiters to query ' . htmlspecialchars($loginDetails->error));
				}
				//execute query
				$loginDetails->execute();
				//check if failed
				if ( false===$loginDetails ) {
					die('failed error 3: failed to execute query' . htmlspecialchars($loginDetails->error));
				}
				//store results
				$loginDetails->store_result();
				if ( false===$loginDetails ) {
					die('failed error 4: Failed to store results');
				}
				//bind results to separate variables
				$loginDetails->bind_result($DBusername, $DBuserpassword, $DBuseremail, $DBname, $DBage, $DBcert, $DBinterests, $DBprevcollege, $DBprevcourse);
				//get results
				$loginDetails->fetch();
				//check password. this may be unnecessary as sql statement will only get exact matches only used to block off code
				//most of these if statements must be removed when testing has been completed
				if($username != null){
				if($password != null){
				if($DBusername != null){
					//this is is redundant as query check this for use but left in 
				if($username===$DBusername && $password===$DBuserpassword){
					//create an array to store results and send to app more results will be added later on 
					$user = array();
					$user['UserName'] = $DBusername;
					$user['Email'] = $DBuseremail;
					$user['Name'] = $DBname;
					$user['Age'] = $DBage;
					$user['Cert'] = $DBcert;
					$user['Interests'] = $DBinterests;
					$user['College'] = $DBprevcollege;
					$user['Course'] = $DBprevcourse;
					echo json_encode($user);
					mysqli_stmt_close($loginDetails);
				}else{
					echo "user name or password incorrect";
				//header("Location:../index.html");
				}
				
				}else{
					echo "user credentials incorrect";
					//echo $username, $password;
				}
				}else{
					echo "Password not sent from app";	
				}
				}else{
					echo "username not sent from app";
				}
?>