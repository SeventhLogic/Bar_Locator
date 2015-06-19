package com.example.jen.bar_locator;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener;


/**
 * Created by Cyrus on 5/20/2015.
 */
public class MapActivity extends FragmentActivity
    implements OnMapReadyCallback
{
    /*
    * Make it easier to use toasts
    * */
    //Context context = getApplicationContext();
    //int duration = Toast.LENGTH_SHORT;

    //private static final int REQUEST_RESOLVE_ERROR = 1001;
    Boolean isClicked = false;
    GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_layout);
        createMapView();
        mMap.setMyLocationEnabled(true);
        addMarker();

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

    private void addMarker()
    {
        int radius = 1000;

        LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);//create a Location manager
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);//get last known location
        double longitude = location.getLongitude();//set longitude
        double latitude = location.getLatitude();//set latitude
        double dbLongitude = -105.0435580;//database placeholder
        double dbLatitude = 39.9145310;//database placeholder
        LatLng latLng = new LatLng(latitude,longitude);// add them into a single var so we can use camera stuffs
        CameraUpdate camUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 15);//create an update for the camera (LatLng, zoom)
        mMap.animateCamera(camUpdate);//call the update when the marker is added to show your current location


        if(null != mMap)
        {
            boolean inCircle;

            final Marker marker = mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(latitude, longitude))//make use of location manager to add marker at your location
                    .title("This be a bar"));

            //YAY FOR CIRCLES!
            CircleOptions circleOptions = new CircleOptions()
                    //.fillColor(-16776961)//blue...like REALLY BLUE
                    .center(new LatLng(latitude, longitude))//create the circle around your current location
                    .radius(radius);//size of circle meters
            final Circle circle = mMap.addCircle(circleOptions);//call said circle


            //http://stackoverflow.com/questions/16082622/check-if-marker-is-inside-circle-radius
            float[] distance = new float[2];

            Location.distanceBetween(marker.getPosition().latitude, marker.getPosition().longitude,
                    circle.getCenter().latitude, circle.getCenter().longitude, distance);

            if( distance[0] > circle.getRadius()  )
            {
                Toast.makeText(getBaseContext(), "Outside", Toast.LENGTH_LONG).show();
                inCircle = false;
            }
            else
            {
                Toast.makeText(getBaseContext(), "Inside", Toast.LENGTH_LONG).show();
                inCircle = true;
            }

            if(inCircle == false)
            {
                marker.setVisible(false);
            }
            else if(inCircle == true)
            {
                marker.setVisible(true);
            }

            mMap.setOnMyLocationButtonClickListener(new OnMyLocationButtonClickListener() {
                @Override
                public boolean onMyLocationButtonClick() {
                    marker.remove();
                    circle.remove();
                    addMarker();
                    Toast.makeText(getBaseContext(), "TRUE", Toast.LENGTH_LONG).show();
                    return true;
                }
            });

        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}
