package com.example.jen.bar_locator;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
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

    DatabaseHelper myDb;
    EditText userNameText, passwordText;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);

        userNameText = (EditText)findViewById(R.id.userNameTxtBox);
        passwordText = (EditText)findViewById(R.id.passWordTxtBox);
        loginBtn = (Button)findViewById(R.id.loginBtn);

        BarInfo frag =  new BarInfo();
        FragmentManager manager = getFragmentManager();
        android.app.FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.map,frag,"markerFragment");
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

        loginBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View e)
            {
                boolean isInserted = myDb.insertData(userNameText.getText().toString(),
                        passwordText.getText().toString());

                if(isInserted == true)
                {
                    Toast.makeText(MainActivity.this, "IT WORKED", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "FAILURE", Toast.LENGTH_LONG).show();
                }
            }
        });

        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
        this.isDestroyed();

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
}
