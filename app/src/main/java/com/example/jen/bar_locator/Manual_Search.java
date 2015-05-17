package com.example.jen.bar_locator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Brock on 5/14/2015.
 */
public class Manual_Search extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manual_search);
    }

    public void submitManualSearch(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.isDestroyed();

    }
}
