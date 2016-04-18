package randomcompany.trackyourway;

/**
 * Created by Evan on 18/04/2016.
 */
public class SearchResListView {
    private int id;
    private String collegeName;


    //Constructor
    public SearchResListView(int id, String collegeName) {
        this.id = id;
        this.collegeName = collegeName;
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
}
