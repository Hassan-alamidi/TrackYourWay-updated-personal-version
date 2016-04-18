package randomcompany.trackyourway;

/**
 * Created by Evan on 18/04/2016.
 */
public class SearchResListView {
    private int id;
    private String collegeName;
    private String courseType;


    //Constructor
    public SearchResListView(int id, String collegeName, String courseType) {
        this.id = id;
        this.collegeName = collegeName;
        this.courseType = courseType;
    }
    //set & gets

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }
}
