package randomcompany.trackyourway;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private EditText userName,password;
    private TextView Warninglbl;
    LocalUserDetails details;
    private RelativeLayout loginLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginuser);
        Button Loginbtn = (Button)findViewById(R.id.loginBtn);
        //Registerbtn = (Button) findViewById(R.id.registerBtn);
        userName = (EditText) findViewById(R.id.userNameTxt);
        password = (EditText) findViewById(R.id.passwordTxt);
        Warninglbl = (TextView) findViewById(R.id.Warninglbl);
        loginLayout = (RelativeLayout)findViewById(R.id.loginLayout);
        details = new LocalUserDetails(this);
        //move outside of oncreate when it is working

//        Loginbtn,Registerbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        })
//            public void onClick(View v) {
//                String lUserName = userName.getText().toString();
//                String lPassword = password.getText().toString();
//                UserAccount userLogin = new UserAccount(lUserName, lPassword);
//                CheckDetails(userLogin);
//            }
//
//        });

    }

    //if a button has been clicked start this method
    public void onClick(View v) {
        //get selected button id and do something based on what is contained within the id's case
        switch(v.getId()) {
            //user login
            case R.id.loginBtn:
                //check if user has entered username and password
                if (userName.getText().toString().trim().equals("") && userName.getText().toString().trim().equals(null)||password.getText().toString().equals("") && password.getText().toString().equals(null)) {
                    //Warninglbl.setText("please enter your username");
                    //this is a reminder to myself
                    //Think i found solution to snackbar issue will create relative layout variable
                    //also 1 if else my work userName.getText().toString().trim().equals("") && userName.getText().toString().trim().equals("")) & remove if else
                    Snackbar.make(loginLayout, "Please Enter Valid Username", Snackbar.LENGTH_LONG)
                            .setAction("Dismiss", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                }
                            }).show();

                    //note to self :commented out else if statement going to test snackbar needs putting back to vlidate password

                /*}else if(password.getText().toString().equals("") && password.getText().toString().equals(null)){
                    Snackbar.make(loginLayout, "Please Enter Correct Password", Snackbar.LENGTH_LONG)
                            .setAction("Dismiss", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                }
                            }).show();
                    //Warninglbl.setText("please enter your Password");*/
                }else{
                    //get details from textbox
                    String lUserName = userName.getText().toString();
                    String lPassword = password.getText().toString();
                    //store user login details in an object
                    UserAccount tempuserLogin = new UserAccount(lUserName, lPassword);
                    //store userlogin object into a wrapper object
                    storeDbresults userLogin = new storeDbresults();
                    userLogin.setTempUser(tempuserLogin);//
                    //send details off to database request and check the details recieved
                    CheckDetails(userLogin);
                }
                break;
            //user register
            case R.id.registerBtn:
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
                break;
        }
    }

    private void CheckDetails(storeDbresults newUserLogin){

        //send data to database request
        String Type = "Login";
        DbRequest newRequest = new DbRequest(this);
        newRequest.DbRetrieveDetails(Type, newUserLogin, new CallBackInter() {
            @Override
            public void complete(storeDbresults newObject) {
            //public void complete(UserAccount userLogin) {
                //get variables
                UserAccount userLogin = newObject.getTempUser();
                //test variables

                //check if correct
                if(userLogin == null/*userLogin.checkEmpty() == true*/){
                    Log.d(null,"something has gone wrong");
                    Snackbar.make(loginLayout, "User details incorrect/Check Wifi Connection", Snackbar.LENGTH_LONG)
                            .setAction("Dismiss", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                }
                            })
                            .show();
                    //Warninglbl.setText("user details were incorrect");
                }else{
                    Log.d("testing object", userLogin.UserName);
                    //store details locally
                    details.storeDetails(userLogin);
                    //set user as logged in until log out button is selected.
                    details.setIsLoggedIn(true);
                    //move to main content
                    storeDbresults results = new storeDbresults();
                    results.setTempUser(userLogin);
                    //open new page
                    Intent i = new Intent(getApplicationContext(), MainHub_Activity.class);
                    startActivity(i);
                }
            }
        });
    }


//    @Override
//    public void onStart(){
//        super.onStart();
//        //this is a test if this works remove the if authenticate and others related
//        if(details.userDetails.getBoolean("isLoggedIn",false)== true){
//            getUserDetails();
//            Intent i = new Intent(getApplicationContext(), MapsActivity.class);
//            startActivity(i);
//        }
////        if(authenticate() == true){
////            Intent i = new Intent(getApplicationContext(), MapsActivity.class);
////            startActivity(i);
////        }
//
//    }

//    private boolean authenticate(){
//       return details.checkLoggedInstatus();
//    }

//    public void getUserDetails(){
//        UserAccount userDetails = details.getDetails();
//
//    }

}
