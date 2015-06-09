package com.example.jen.bar_locator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Brock on 6/9/2015.
 */
public class Register extends Activity{

    EditText firstID, secondID, firstPassword, secondPassword;
    Button registerBtn;


    String userOne, userTwo, passOne, passTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);

    }

    public void verifyInputs (View view){
        firstID = (EditText)findViewById(R.id.registerTxtBox);
        secondID = (EditText)findViewById(R.id.checkUserIDTxtBox);
        firstPassword = (EditText)findViewById(R.id.registerPasswordTxtBox);
        secondPassword = (EditText)findViewById(R.id.checkPasswordTxtBox);


        userOne = firstID.getText().toString();
        userTwo = secondID.getText().toString();
        passOne = firstPassword.getText().toString();
        passTwo = secondPassword.getText().toString();

        //userOne = (EditText) findViewById(R.id.registerTxtBox).getText().toString();

        if (userOne == userTwo){
            if (passOne == passTwo) {
                Toast.makeText(Register.this, "Cleared", Toast.LENGTH_LONG).show();
                //Intent intent = new Intent(this, MainActivity.class);
                //startActivity(intent);
                //this.isDestroyed();
            }
        }
        else if (userOne != userTwo) {
            Toast.makeText(Register.this, "IDs or Passwords do not match, please try again" + firstID + secondID + firstPassword + secondPassword, Toast.LENGTH_LONG).show();
            firstID.setText("");
            secondID.setText("");
            firstPassword.setText("");
            secondPassword.setText("");
        }




    }





}
