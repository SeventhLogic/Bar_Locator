package com.example.jen.bar_locator;

import android.content.Context;
import android.content.IntentSender;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;//these will be used later just trying to figure out the error checking stuff
import com.google.android.gms.maps.internal.Point;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.*;


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
        LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);//create a Location manager
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);//get last known location
        double longitude = location.getLongitude();//set longitude
        double latitude = location.getLatitude();//set latitude

        if(null != mMap)
        {
            mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(latitude, longitude))//make use of location manager to add marker at your location
                    .title("Marker"));

            //YAY FOR CIRCLES!
            CircleOptions circleOptions = new CircleOptions()
                    .center(new LatLng(latitude,longitude))//create the circle around your current location
                    .radius(1000);//size of circle meters
            Circle circle = mMap.addCircle(circleOptions);//call said circle
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}
