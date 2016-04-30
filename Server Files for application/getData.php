<?php
error_reporting(E_ALL);
ini_set('display_errors', 1);
include("dbconnect.php");
$details = '%'.$_POST['Search'].'%';
$test = "Dublin,Level 8,Mathematics,BA";
$keywordsSent =  explode(",", $test);//explode(",",$_POST['Search']);
if($details === "%%"){
    echo "nothing specified";
}

            
         
            if($connect->connect_errno){
                printf("connection failed, please try again and if this error occurs again please submit this bug through the contact us page", $connect->connect_error);
                exit();
            }
            
            //"select * from(select cd.id,cd.lattitude,cd.longitude,cd.type, cd.college_name,cd.college_address,cd.college_email,cc.course_name,cc.course_type from college_detail cd inner join course_detail cc where cd.id=cc.college_id) t1 inner JOIN  (SELECT avg(ratings) as average_ratings,id as college_id from college_ratings group by college_id) t2 where t2.college_id=t1.id AND WHERE college_detail.college_name LIKE ? OR college_detail.college_address LIKE ? OR college_detail.type LIKE ? OR course_detail.course_name LIKE ? OR course_detail.level LIKE ? OR course_detail.course_type LIKE ? OR course_detail.Course_year LIKE ?"
            //$SearchDetails = $connect->prepare("SELECT college_detail.id, college_detail.college_name, college_detail.college_address, college_detail.college_email, college_detail.lattitude, college_detail.longitude, college_detail.contact, college_detail.type, course_detail.course_name, course_detail.description, course_detail.Course_level, course_detail.course_type, course_detail.Course_year FROM college_detail JOIN course_detail ON college_detail.id=course_detail.college_id WHERE college_detail.college_name LIKE ? OR college_detail.college_address LIKE ? OR college_detail.type LIKE ? OR course_detail.course_name LIKE ? OR course_detail.level LIKE ? OR course_detail.course_type LIKE ? OR course_detail.Course_year LIKE ?"); //going to put an and at the end to get average rating when i know it works
            //not sure if this will work need to confim with sunny
            //N.B need to add course id to remove duplicates
            $SearchDetails = $connect->prepare("select DISTINCT * from(select cc.id_Course,cd.id,cd.lattitude,cd.longitude,cd.type, cd.college_name,cd.college_address,cd.college_email, cd.contact,cc.course_name,cc.course_type, cc.description, cc.Course_year, cc.Course_level from college_detail cd inner join course_detail cc where cd.id=cc.college_id) t1 inner JOIN  (SELECT avg(ratings) as average_ratings,id as college_id from college_ratings group by college_id) t2 where t2.college_id=t1.id AND t1.college_name LIKE ? OR t1.college_address LIKE ? OR t1.type LIKE ? OR t1.course_name LIKE ? OR t1.Course_level LIKE ? OR t1.course_type LIKE ? OR t1.Course_year LIKE ?");
            //$CollegeId, $latitude, $longitude, $CollegeType, $CollegeName, $CollegeAddress,$CollegeEmail, $CollegeContact, $CourseName, $CourseType, $CourseDescription, $CourseYear, $CourseLevel, $CollegeRating
            //$SearchDetails = $connect->prepare("SELECT * FROM college_detail WHERE college_name= ?");
            //check if connection succeeded 
            if ( false===$SearchDetails ) {
                die('failed error 1: There seems to be an issue with your query ' . htmlspecialchars($connect->error));
            }
            //bind the month to the prepared statement
            $SearchDetails->bind_param("sssssss", $details, $details, $details, $details, $details, $details, $details);
            //check if failed
            if ( false===$SearchDetails ) {
                die('failed error 2: Failed to bind paramiters to query ' . htmlspecialchars($SearchDetails->error));
            }
            //execute query
            $SearchDetails->execute();
            //check if failed
            if ( false===$SearchDetails ) {
                die('failed error 3: failed to execute query' . htmlspecialchars($SearchDetails->error));
            }
            //store results
            $SearchDetails->store_result();
            if ( false===$SearchDetails ) {
                die('failed error 4: Failed to store results' );
            }
            //bind results to separate variables
            $SearchDetails->bind_result($CourseID,$CollegeId, $latitude, $longitude, $CollegeType, $CollegeName, $CollegeAddress,$CollegeEmail, $CollegeContact, $CourseName, $CourseType, $CourseDescription, $CourseYear, $CourseLevel, $CollegeRating, $RatingID);
            //$SearchDetails->bind_result($CollegeId, $CollegeName, $CollegeAddress, $CollegeEmail, $CollegeLatitude, $CollegeLongitude, $CollegeContact, $CollegeType, $CourseName, $CourseDescription, $CourseLevel, $CourseType, $CourseYear);
            
            
				//create an array to store results and send to app 
                //$Allevents = array();
                $i = 0;
                $SearchArr = array();
                while($SearchDetails->fetch()){
                //this will combine all results into one string and will be split into an array in java
                //echo $CollegeId ."|". $latitude ."|". $longitude ."|". $CollegeType ."|". $CollegeName ."|". $CollegeAddress ."|". $CollegeEmail ."|". $CollegeContact ."|". $CourseName ."|". $CourseType ."|". $CourseDescription ."|". $CourseYear ."|". $CourseLevel ."|". $CollegeRating."|". $RatingID;
                
				$SearchArr[$i] = utf8_encode ($CourseID ."|". $CollegeId ."|". $latitude ."|". $longitude ."|". $CollegeType ."|". $CollegeName ."|". $CollegeAddress ."|". $CollegeEmail ."|". $CollegeContact ."|". $CourseName ."|". $CourseType ."|". $CourseDescription ."|". $CourseYear ."|". $CourseLevel ."|". $CollegeRating);
                //echo $SearchArr[$i];
                $i++;
                }
                $j = 0;
			//create loop to search for keywords
			foreach($keywordsSent as $value){
				//$SearchDetails = $connect->prepare("SELECT college_detail.id, college_detail.college_name, college_detail.college_address, college_detail.college_email, college_detail.latitude, college_detail.longitude, college_detail.contact, college_detail.type, course_detail.course_name, course_detail.description, course_detail.Course_level, course_detail.course_type, course_detail.Course_year FROM college_detail JOIN course_detail ON college_detail.id=course_detail.college_id WHERE course_detail.Keywords LIKE ?");
                $SearchDetails2 = $connect->prepare("select DISTINCT * from(select cc.id_Course,cd.id,cd.lattitude,cd.longitude,cd.type, cd.college_name,cd.college_address,cd.college_email, cd.contact,cc.course_name,cc.course_type, cc.description, cc.Course_year, cc.Course_level, cc.Keywords from college_detail cd inner join course_detail cc where cd.id=cc.college_id) t1 inner JOIN  (SELECT avg(ratings) as average_ratings,id as college_id from college_ratings group by college_id) t2 where t2.college_id=t1.id AND t1.Keywords LIKE ?");
            //check if connection succeeded 
			$oneKeyword = '%'.$value.'%';
			$j++;
			//echo $j . $value;
            if ( false===$SearchDetails2 ) {
                die('failed error 5: There seems to be an issue with your query ' . htmlspecialchars($connect->error));
            }
            //bind the month to the prepared statement

            $SearchDetails2->bind_param("s", $oneKeyword);
            //check if failed
            if ( false===$SearchDetails2 ) {
                die('failed error 6: Failed to bind parameters to query ' . htmlspecialchars($SearchDetails->error));
            }
            //execute query
            $SearchDetails2->execute();
            //check if failed
            if ( false===$SearchDetails2 ) {
                die('failed error 7: failed to execute query' . htmlspecialchars($SearchDetails->error));
            }
            //store results
            $SearchDetails2->store_result();
            if ( false===$SearchDetails2 ) {
                die('failed error 8: Failed to store results' );
            }
            //bind results to separate variables
            $SearchDetails2->bind_result($CourseID,$CollegeId, $latitude, $longitude, $CollegeType, $CollegeName, $CollegeAddress,$CollegeEmail, $CollegeContact, $CourseName, $CourseType, $CourseDescription, $CourseYear, $CourseLevel,$Key, $CollegeRating, $RatingID);
            
            
				//create an array to store results and send to app 
                //$Allevents = array();
               
               
                while($SearchDetails2->fetch()){
                //echo "work" .$CollegeId;
                //this will combine all results into one string and will be split into an array in java
				$SearchArr[$i] = utf8_encode ($CourseID ."|". $CollegeId ."|". $latitude ."|". $longitude ."|". $CollegeType ."|". $CollegeName ."|". $CollegeAddress ."|". $CollegeEmail ."|". $CollegeContact ."|". $CourseName ."|". $CourseType ."|". $CourseDescription ."|". $CourseYear ."|". $CourseLevel ."|". $CollegeRating);
               
                $i++;
                }
}
			
			if($SearchArr != null){
			   
            if($details != null){
                $temp = json_encode($SearchArr);
                echo $temp;
                $data = json_decode($temp);
                if($data === null){
                    echo "Json fail to encode";
                }
                mysqli_stmt_close($SearchDetails);    
            }else{
                echo "month not sent from app"; 
            }
            }else{
                echo "no events this month";
            }
            
  

?>