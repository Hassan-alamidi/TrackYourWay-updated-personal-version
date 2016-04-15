package randomcompany.trackyourway;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Hassan on 06/04/2016.
 */
public class CollegeDetails {

    String CollegeName, CollegeAddress, CollegeEmail, CollegeContactDetails;
    double longitude,latitude;
    int CollegeID;
    float CollegeRatings;
    ArrayList<CourseDetails> Courses = new ArrayList<>();
    public CollegeDetails() {

    }

    public CollegeDetails(int newCollegeID, String newCollegeName, String newCollegeAddress, String newCollegeEmail, String newCollegeContactDetails, double newLongitude, double newLatitude, float newCollegeRatings){
        CollegeID = newCollegeID;
        CollegeName = newCollegeName;
        CollegeAddress = newCollegeAddress;
        CollegeEmail = newCollegeEmail;
        CollegeContactDetails = newCollegeContactDetails;
        longitude = newLongitude;
        latitude = newLatitude;
        CollegeRatings = newCollegeRatings;
    }

    public void addCourse(CourseDetails C){
        Courses.add(C);
    }

    public Boolean CheckCourseExsistance(int i){
        Iterator IT = Courses.iterator();
        while(IT.hasNext()){
            CourseDetails tempCourse = (CourseDetails) IT.next();
            if(tempCourse.collegeID == i){
                return true;
            }
        }
        return false;
    }


}
