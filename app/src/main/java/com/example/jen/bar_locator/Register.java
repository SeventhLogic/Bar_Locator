package com.example.jen.bar_locator;


        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.text.TextUtils;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.Button;
        import android.widget.TextView;
        import android.widget.Toast;

/**
 * Created by Brock on 6/9/2015.
 */
public class Register extends Activity {

    EditText firstID, secondID, firstPassword, secondPassword;
    TextView registerTxt;
    Button registerBtn;
    DatabaseHelper myDb;




    String userOne, userTwo, passOne, passTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);

        myDb = new DatabaseHelper(this);

        firstID = (EditText) findViewById(R.id.registerTxtBox);
        secondID = (EditText) findViewById(R.id.checkUserIDTxtBox);

        firstPassword = (EditText) findViewById(R.id.registerPasswordTxtBox);
        firstPassword.setTransformationMethod(new AsteriskPasswordTransformationMetod());

        secondPassword = (EditText) findViewById(R.id.checkPasswordTxtBox);
        secondPassword.setTransformationMethod(new AsteriskPasswordTransformationMetod());

        registerTxt = (TextView) findViewById(R.id.registerInfo);

        registerBtn = (Button) findViewById(R.id.registerBtn);




    }

    public void verifyInputs(View view) {

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userOne = firstID.getText().toString();
                userTwo = secondID.getText().toString();
                passOne = firstPassword.getText().toString();
                passTwo = secondPassword.getText().toString();

                if((!TextUtils.isEmpty(userOne)) && (!TextUtils.isEmpty(userTwo)) && (!TextUtils.isEmpty(passOne)) && (!TextUtils.isEmpty(passTwo))) {
                    if (userOne.equals(userTwo)) {
                        if (passOne.equals(passTwo)) {
                            Toast.makeText(Register.this, "Cleared", Toast.LENGTH_LONG).show();

                            myDb.insertData(userOne, passOne);
                            newPageCloseThis();
                        } else if (userOne != userTwo || userOne.matches("")) {
                            Toast.makeText(Register.this, "Passwords do not match, please try again", Toast.LENGTH_LONG).show();
                            firstPassword.setText("");
                            secondPassword.setText("");
                        }
                    } else if (passOne != passTwo || passOne.matches("")) {
                        Toast.makeText(Register.this, "IDs do not match, please try again", Toast.LENGTH_LONG).show();
                        firstID.setText("");
                        secondID.setText("");

                    }
                }

            }
        });

    }

    private void newPageCloseThis() {
        Intent intent = new Intent(Register.this, MainActivity.class);
        startActivity(intent);
        this.isDestroyed();
        myDb.close();

    }
}

