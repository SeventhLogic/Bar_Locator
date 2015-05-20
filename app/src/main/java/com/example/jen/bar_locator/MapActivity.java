package com.example.jen.bar_locator;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import com.google.android.gms.maps.*;


/**
 * Created by Cyrus on 5/20/2015.
 */
public class MapActivity extends FragmentActivity
{
    @Override
    protected void onCreate(Bundle saveedInstanceState)
    {
        super.onCreate(saveedInstanceState);
        setContentView(R.layout.map_layout);
    }
}
