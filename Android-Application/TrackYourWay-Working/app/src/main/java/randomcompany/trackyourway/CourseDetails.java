package randomcompany.trackyourway;

import java.io.Serializable;

/**
 * Created by Hassan on 10/03/2016.
 */
public class CourseDetails extends CollegeDetails implements Serializable{

    String courseName, description, type, level;
    int duration , courseID, collegeID;

    public CourseDetails(){

    }

    public CourseDetails(int newCourseID,int newCollegeID, String newCourseName, String newDescription, String newLevel, String newType, int newDuration){
        courseID = newCourseID;
        collegeID = newCollegeID;
        courseName = newCourseName;
        description = newDescription;
        type = newType;
        //level is a string because we want to also specify things like BA or BSc in this
        level = newLevel;
        duration = newDuration;
    }


}
