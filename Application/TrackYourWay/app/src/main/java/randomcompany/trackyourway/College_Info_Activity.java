package randomcompany.trackyourway;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;

public class College_Info_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    Button ratingsBtn,displayMapBtn;
    TextView colName,colAddress,colEmail,colPhone,colCourse,colCourseDesc,colCourseDuration;

    //creating objects for navigation drawer
    DrawerLayout mdrawer;
    NavigationView mNavigationView;
    Toolbar toolbar;
    CollegeDetails CollegeInfo = new CollegeDetails();
    CollegeDetails temp2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college__info_);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ratingsBtn = (Button)findViewById(R.id.ratingsBtn);
        displayMapBtn = (Button)findViewById(R.id.display_map_btn);
        colName = (TextView)findViewById(R.id.displayColNameTxt);
        colAddress = (TextView)findViewById(R.id.displayColAddressTxt);
        colEmail = (TextView)findViewById(R.id.displayColEmailTxt);
        colPhone = (TextView)findViewById(R.id.displayColNumTxt);
        colCourse = (TextView)findViewById(R.id.displayColCoursesTxt);
        colCourseDesc = (TextView)findViewById(R.id.displayColCourseDescTxt);
        CourseDetails temp = (CourseDetails) getIntent().getSerializableExtra("Course");
         temp2 = (CollegeDetails) getIntent().getSerializableExtra("College");
        colCourse.setText(temp.courseName);
        colCourseDesc.setText(temp.courseName);
        //colCourseDuration.setText(temp.duration);
        colName.setText(temp2.CollegeName);
        colAddress.setText(temp2.CollegeAddress);
        colEmail.setText(temp2.CollegeEmail);
        colPhone.setText(temp2.CollegeContactDetails);
        ratingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i;
                i = new Intent(getApplicationContext(),Comments_Rating_Activity.class);
                i.putExtra("CollegeID", (Serializable) CollegeInfo.CollegeID);
                startActivity(i);
            }});

        displayMapBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                Intent i;
                i = new Intent(getApplicationContext(),MapsActivity.class);
                i.putExtra("College", (Serializable) temp2);
                String Page = "CollegeInfo";
                i.putExtra("Page",Page );
                startActivity(i);
            }});



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

        } else if(id == R.id.nav_user_logout){
            LocalUserDetails UserLogout = new LocalUserDetails();
            UserLogout.removedetails();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);

        }

        mdrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mdrawer.closeDrawer(GravityCompat.START);
        return true;


    }



}
