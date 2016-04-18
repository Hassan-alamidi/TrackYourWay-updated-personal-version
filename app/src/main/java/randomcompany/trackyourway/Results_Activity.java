package randomcompany.trackyourway;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Results_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    //ListView courseDetails;
    private ListView lvCollege;
    private SearchResListAdapter adapter;
    private List<SearchResListView> mSearchList;
    private TextView collegeNameLabel;

    //creating objects for navigation drawer
    DrawerLayout mdrawer;
    NavigationView mNavigationView;
    Toolbar toolbar;
    ArrayList<CollegeDetails> allResults = new ArrayList<>();
    storeDbresults searchPerams = new storeDbresults();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_);

        collegeNameLabel = (TextView)findViewById(R.id.collegeNameLabel);

        lvCollege = (ListView)findViewById(R.id.searchResListView);

        mSearchList = new ArrayList<>();
        //add sample data here
        //we can get data from DB here

        populateList();

        //Init Adapter
        adapter = new SearchResListAdapter(getApplicationContext(),mSearchList);
        lvCollege.setAdapter(adapter);
        //here is how to get details


        allResults = searchPerams.getMultiResult();
        CollegeDetails temp = allResults.get(0);
        Log.d("testing object", temp.CollegeName);
        //allResults.get().
        //here is ending of get details
        lvCollege.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //do something
                //
                Toast.makeText(getApplicationContext(), "Id & Name of clicked college " + view.getTag(), Toast.LENGTH_SHORT).show();
                Intent i;
                i = new Intent(getApplicationContext(),College_Info_Activity.class);
                startActivity(i);
                //collegeNameLabel.setText(mSearchList.get(position).getCollegeName().toString());

            }
        });


        //courseDetails = (ListView)findViewById(R.id.listView);
        //String[] items = {"temp", "temp 2"};
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,items);
        //courseDetails.setAdapter(adapter);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //this is just a test

        //end of test
        /*courseDetails.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Intent i = new Intent(getApplicationContext(), College_Info_Activity.class);
                        startActivity(i);

                        break;
                }
            }
        });*/

        //hamburger icon to open navigation drawer
        mdrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mdrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mdrawer.addDrawerListener(toggle);
        toggle.syncState();

        //creating nav view with items in it
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);

    }

    public void populateList(){
        mSearchList.add(new SearchResListView(1,"NCI","Computing"));
        allResults = searchPerams.getMultiResult();
        for(int i = 0; i < allResults.size();i++){
            CollegeDetails oneCollege = allResults.get(i);
            ArrayList<CourseDetails> allCourses = oneCollege.Courses;
            for(int j = 0; j < allCourses.size();j++){
                CourseDetails oneCourse = allCourses.get(j);
                mSearchList.add(new SearchResListView(j,oneCollege.CollegeName, oneCourse.courseName));

            }

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //functionality to open activities in nav drawer
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handles the navigation drawer items stored
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            //Handles the click event for the home activity
            Intent intent = new Intent(this, MainHub_Activity.class);
            startActivity(intent);
            return true;

        } else if (id == R.id.nav_new_search) {
            //Handles the click event for the search activity
            Intent intent = new Intent(this, SearchFragment.class);
            startActivity(intent);
            return true;

            //handles the click even for the saved search activity
        } else if (id == R.id.nav_saved_searches) {
            Intent intent = new Intent(this, Saved_Results_Activity.class);
            startActivity(intent);
            return true;

            //handles the click even for the forum activity
        } else if (id == R.id.nav_forum) {
            Intent intent = new Intent(this, Comments_Rating_Activity.class);
            startActivity(intent);
            return true;

            //handles the click even for the user etails activity
        } else if (id == R.id.nav_user_details) {
            Intent intent = new Intent(this, MainHub_Activity.class);
            startActivity(intent);
            return true;

        } else if (id == R.id.nav_calendar) {
            Intent intent = new Intent(this, Calendar_Activity.class);
            startActivity(intent);
            return true;

        }

        mdrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mdrawer.closeDrawer(GravityCompat.START);
        return true;


    }


}
