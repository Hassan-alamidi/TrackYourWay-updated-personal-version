package randomcompany.trackyourway;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Patrick on 04/04/2016.
 */
public class UserDetails extends AppCompatActivity{
    UserAccount userDetails;
    ListView listUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_details);
        storeDbresults temp = new storeDbresults();
        userDetails = temp.getTempUser();
        String[] details = new String[]{
                userDetails.getName(),
                Integer.toString(userDetails.getAge()),
                userDetails.getEmail(),
                userDetails.getCertificate(),
                userDetails.getInterests(),
                userDetails.getPrevCollege(),
                userDetails.getPrevCourse()
        };

        ArrayList<String> user = new ArrayList<String>();
        for(int i = 0; i < details.length; i ++){
            user.add(details[i]);
        }



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.user_details);




    }


    public class adapter extends ArrayAdapter<String>{

        public adapter(){
            super(UserDetails.this, R.layout.item_view, details);



        }





    }




}
