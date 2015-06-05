package com.example.jen.bar_locator;

import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;//these will be used later just trying to figure out the error checking stuff
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import com.google.android.gms.maps.*;


/**
 * Created by Cyrus on 5/20/2015.
 */
public class MapActivity extends FragmentActivity
    implements OnMapReadyCallback{
    /*
    * Make it easier to use toasts
    * */
    //Context context = getApplicationContext();
    //int duration = Toast.LENGTH_SHORT;

    private static final int REQUEST_RESOLVE_ERROR = 1001;

    GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_layout);
        createMapView();
        mMap.setMyLocationEnabled(true);
        addLocationsToMap();
    }

    //http://stackoverflow.com/questions/14226453/google-maps-api-v2-how-to-make-markers-clickable
    private void createMapView()
    {
        try//a try catch to deal with map failing to load.
        {
            if (null == mMap)
            {
                mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
            }

            if (null == mMap)
            {
                Toast.makeText(getApplicationContext(),"Error Creating Map", Toast.LENGTH_SHORT).show();
            }
        }catch (NullPointerException e)
        {
            Log.e("MAP APP FAILED", e.toString());
        }
    }

    private void addLocationsToMap()
    {
        if(null != mMap)
        {
            mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(0,0))
                    .title("Marker"));
            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    if(marker.getTitle().equals("Marker")) // if marker source is clicker

                    Toast.makeText(MapActivity.this, marker.getTitle(), Toast.LENGTH_SHORT).show();// display toast
                    return true;
                }
            });
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}
