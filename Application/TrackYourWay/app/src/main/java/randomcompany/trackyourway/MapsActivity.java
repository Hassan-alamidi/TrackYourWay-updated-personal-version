package randomcompany.trackyourway;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.Serializable;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LocationManager locationMgr;
    private LocationListener locationlist;
    private double userLongitude;
    private double userLatitude;
    private Button btnFindMe, PrevPage;
    private Events newEvent;
    private String page;
    CollegeDetails College;
    // permissionsrequest not working right now
    //permissionsRequest request = new permissionsRequest();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setview
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        btnFindMe = (Button) findViewById(R.id.GetUserLocation);
        PrevPage = (Button) findViewById(R.id.prevPage);
        locationMgr = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationlist = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                userLatitude = location.getLatitude();
                userLongitude = location.getLongitude();

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
                Intent gpsSettings = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(gpsSettings);
            }
        };

       checkPermission();
       findme();
        //time and distance updates are long because updates are not really required
        page = getIntent().getExtras().getString("Page");
        if (page.equals("Event")) {

            newEvent = new Events();
            newEvent = (Events) getIntent().getSerializableExtra("Event");
        }else if(page.equals("CollegeInfo")){
            College = new CollegeDetails();
            College = (CollegeDetails) getIntent().getSerializableExtra("College");
        }
        PrevPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(page.equals("Event")){
                    Intent i;
                    i = new Intent(getApplicationContext(), Events_Info_Activity.class);
                    i.putExtra("Event", (Serializable) newEvent);
                startActivity(i);
                }else if(page.equals("CollegeInfo")){
                        Intent i;
                        i = new Intent(getApplicationContext(), SearchFragment.class);
                        startActivity(i);
                }
            }
        });
    }

    public void checkPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
                }, 1);
                return;
            }
        } else {
            findme();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                findme();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


    public void findme() {
        btnFindMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    locationMgr.requestLocationUpdates("gps", 6000, 0, locationlist);
                    LatLng user = new LatLng(userLatitude, userLongitude);
                    mMap.addMarker(new MarkerOptions().position(user).title("user"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(user));
                }else{
                   checkPermission();
                }


            }
        });

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        LatLng nci = new LatLng(53.348889,-6.243199);
        if(page.equals("Event")) {
            LatLng EventLocation = new LatLng(newEvent.latitude, newEvent.longitude);
            mMap.addMarker(new MarkerOptions().position(EventLocation).title("Marker for event"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(EventLocation));
        }else if(page.equals("CollegeInfo")){
            LatLng CollegeLocation = new LatLng(College.latitude, College.longitude);
            mMap.addMarker(new MarkerOptions().position(CollegeLocation).title("Marker for College"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(CollegeLocation));
        }
       // mMap.addMarker(new MarkerOptions().position(user).title("user"));
        //need to figure out how to ask and check for permissions
        //if(checkPermission() = true) {
            //mMap.setMyLocationEnabled(true);
        //}
    }

}
