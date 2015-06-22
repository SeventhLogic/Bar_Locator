package com.example.jen.bar_locator;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.SupportMapFragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.GoogleMap;


public class MainActivity extends Activity {

    ErrorCheck eCheck = new ErrorCheck();
    DatabaseHelper myDb;
    EditText userNameText, passwordText;
    Button loginBtn;

    String userID, passWord;

    //int tempVal = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);


        loginBtn = (Button)findViewById(R.id.loginBtn);
        //passwordText.setTransformationMethod(new AsteriskPasswordTransformationMetod());

        BarInfo frag =  new BarInfo();
        FragmentManager manager = getFragmentManager();
        android.app.FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.map, frag, "markerFragment");
    }

    public void registerMethod(View view)
    {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
        this.isDestroyed();
    }


    //    public void displayMain_Activity(FragmentActivity fragmentActivity){
//        //BlankFragment fragment = new
//    }
    public void goToHomePage (View view){

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View e) {
                userNameText = (EditText) findViewById(R.id.userNameTxtBox);
                passwordText = (EditText) findViewById(R.id.passWordTxtBox);
                userID = userNameText.getText().toString();
                passWord = passwordText.getText().toString();

                if ((!TextUtils.isEmpty(userID)) && (!TextUtils.isEmpty(passWord))) {
                    userID = checkUser(userID);
                    if (userID.equals("ahsdfg")) {
                        Toast.makeText(getBaseContext(), "Please enter a valid Username", Toast.LENGTH_LONG).show();
                    } else {
                        passWord = checkPass(passWord);
                        if (passWord.equals("ahsdfg")) {
                            Toast.makeText(getBaseContext(), "Please enter a valid Password", Toast.LENGTH_LONG).show();
                        } else if (!userID.equals("ahsdfg") && !passWord.equals("ahsdfg")) {
                            checkComplete();
                        }
                    }
                }
            }
        });



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspecition SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public String checkUser (String xTempVal) {
        String User = xTempVal;
        User = myDb.getUserName(User);
        if(User.equals("ahsdfg"))
        {
            return User;
        }
        else {
            return User;
        }
    }

    public String checkPass (String xTempVal) {
        String pass = xTempVal;
        pass = myDb.getPassword(pass);
        if(pass.equals("ahsdfg"))
        {
            return pass;
        }
        else {
            return pass;
        }
    }

    public void checkComplete () {
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
        //this.isDestroyed();
        myDb.close();
    }
}
