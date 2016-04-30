<?php
error_reporting(E_ALL);
ini_set('display_errors', 1);
include("dbconnect.php");
//get first 3 months

$month = '%-'.'04'.'-%';//'%'.'-'.$_POST['month'].'-'.'%';
//month 1 and 2 are currently not in use untill issue is fixed
//$month2 = '%'.'-'.$_POST['month'] + 1 .'-'.'%';
//$month3 = '%'.'-'.$_POST['month'] + 2 .'-'.'%';
//echo $month;

            //connect to database and prepare mysqli statement to get day, title, details, time and location if month has any details
            //will be adding more details for this statement to get once it is fully tested
            if($connect->connect_errno){
                printf("connection failed, please try again and if this error occurs again please submit this bug through the contact us page", $connect->connect_error);
                exit();
            }
            $CalendarDetails = $connect->prepare("SELECT `date`, `time`, `title`, `details`, `eventLocation`, `longitude`, `latitude` FROM `calender` WHERE date LIKE ?");
            //$CalendarDetails = $connect->prepare("SELECT date, time, title, details, eventLocation, longitude, latitude FROM calendar WHERE date LIKE ?");
            //check if connection succeeded 
            if ( false===$CalendarDetails ) {
                die('failed error 1: There seems to be an issue with your query ' . htmlspecialchars($connect->error));
            }
            //bind the month to the prepared statement

            $CalendarDetails->bind_param("s", $month);
            //check if failed
            if ( false===$CalendarDetails ) {
                die('failed error 2: Failed to bind paramiters to query ' . htmlspecialchars($CalendarDetails->error));
            }
            //execute query
            $CalendarDetails->execute();
            //check if failed
            if ( false===$CalendarDetails ) {
                die('failed error 3: failed to execute query' . htmlspecialchars($CalendarDetails->error));
            }
            //store results
            $CalendarDetails->store_result();
            if ( false===$CalendarDetails ) {
                die('failed error 4: Failed to store results' );
            }
            //bind results to separate variables
            $CalendarDetails->bind_result($date, $time, $title, $details, $location, $longitude, $latitude);
            
			
			
            
                //create an array to store results and send to app 
                //$Allevents = array();
                $i = 0;
                $event = array();
                while($CalendarDetails->fetch()){
                
                //this will combine all results into one string and will be split into an array in java
				$event[$i] = $date. "|". $title. "|". $details. "|". $time. "|". $location. "|". $longitude. "|". $latitude;
               
                $i++;
                }
			if($event != null){
            if($month != null){
                echo json_encode($event);
                
                //echo json_encode($event);
                mysqli_stmt_close($CalendarDetails);    
            }else{
                echo "month not sent from app"; 
            }
            }else{
                echo "no events this month";
            }

?>