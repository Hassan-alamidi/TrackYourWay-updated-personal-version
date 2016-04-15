package randomcompany.trackyourway;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patrick on 05/04/2016.
 */
public class UserDetails extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    //creating objects for navigation drawer
    DrawerLayout mdrawer;
    NavigationView mNavigationView;
    Toolbar toolbar;

    UserAccount userDetails;
    List<User> user = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_details);
        storeDbresults temp = new storeDbresults();
        userDetails = temp.getTempUser();
        populateListDetails();
        populateList();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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



    private void populateListDetails() {
        user.add(new User(userDetails.getName()));
        user.add(new User(Integer.toString(userDetails.getAge())));
        user.add(new User(userDetails.getEmail()));
        user.add(new User(userDetails.getCertificate()));
        user.add(new User(userDetails.getInterests()));
        user.add(new User(userDetails.getPrevCollege()));
        user.add(new User(userDetails.getPrevCourse()));
    }

    private void populateList(){
        ListView listUser = (ListView) findViewById(R.id.listUser);
        ArrayAdapter<User> Adapter = new UserArrayAdapter();
        listUser.setAdapter(Adapter);
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
            Intent intent = new Intent(this, UserDetails.class);
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


    private class UserArrayAdapter extends ArrayAdapter<User>{

        public UserArrayAdapter(){
            super(UserDetails.this, R.layout.list_item, user);

        }
        


        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            //Make sure our view works may have passed null
            View itemView = convertView;
            if(itemView == null){
                itemView = getLayoutInflater().inflate(R.layout.list_item, parent, false);
            }


            User currentUser = user.get(position);

            //Comment
            TextView detailsText = (TextView) itemView.findViewById(R.id.item_comment_txt);
            detailsText.setText(currentUser.getDetails());

            return itemView;
        }

    }

}



