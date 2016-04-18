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
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Calendar_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //creating objects for navigation drawer
    DrawerLayout mdrawer;
    NavigationView mNavigationView;
    Toolbar toolbar;


    CalendarView calendarView;
    TextView displayEvents;
    storeDbresults storeParam = new storeDbresults();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_);
        Calendar currentDate = Calendar.getInstance();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //declaring date format
        SimpleDateFormat DMY = new SimpleDateFormat("dd-MM-yyyy");
        //getting current date in perfered format
        String date = DMY.format(currentDate.getTime());
        //testing date
        Log.d("current date is", date);
        calendarView = (CalendarView) findViewById(R.id.Cal2);
        displayEvents = (TextView) findViewById(R.id.txtDisplayEvents);
        displayEvents.setText("Events today: ");

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {

                displayEvents.setText("Insert database");
                Toast.makeText(getApplicationContext(), "Selected Date:\n" + "Day = " + i2 + "\n" + "Month = " + i1 + "\n" + "Year = " + i, Toast.LENGTH_LONG).show();


            }
        });

        if (date.equals(null)) {
            Log.d("you have not ", "entered any details");
        } else {
            storeParam.setDate(date);
            CheckDetails(storeParam);
        }

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


    private void CheckDetails(storeDbresults newSearchParam) {
        //send data to database request
        String Type = "Events";
        DbRequest newRequest = new DbRequest(this);
        newRequest.DbRetrieveDetails(Type, newSearchParam, new CallBackInter() {
            @Override
            public void complete(storeDbresults newObject) {
                ArrayList<Events> allEvents = new ArrayList<>();
                storeDbresults searchPerams = newObject;
                allEvents = searchPerams.getMultiResult();
                //check if correct
                if (allEvents == null /*userLogin.checkEmpty() == true*/) {
                    Log.d(null, "something has gone wrong");
                    //Warninglbl.setText("user details were incorrect");
                } else if (allEvents.isEmpty()) {
                    Log.d(null, "something has gone wrong");
                } else {

                    Events newEvents = allEvents.get(0);
                    Log.d("testing object", newEvents.eventDate);

                }
            }
        });
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
