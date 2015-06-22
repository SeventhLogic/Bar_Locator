package com.example.jen.bar_locator;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener;

import java.util.ArrayList;


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
    String  barInfo = "The Old Man",
            barInfo2 = "Park Center",
            barInfo3 = "Chilli's",
            barInfo4 = "Hooters ( . Y . )",
            barInfo5 = "C.B. & Potts";
    ArrayList<Double> dbLAT = new ArrayList<>();
    ArrayList<Double> dbLNG = new ArrayList<>();
    ArrayList<String> barDescription = new ArrayList<>();
    double dbLongitude = -105.0435580;//database placeholder OLD MAN
    double dbLatitude = 39.9145310;
    double dbLongitude2 = -105.006652;//Park Center
    double dbLatitude2 = 39.914905;
    double dbLongitude3 = -104.998845;//Chillis
    double dbLatitude3 = 39.914653;
    double dbLongitude4 = -104.999092;//Hooters
    double dbLatitude4 = 39.915632;
    double dbLongitude5 = -105.000730;//C.B. & Potts
    double dbLatitude5 = 39.915173;
    private static final String TAG = MapActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_layout);
        createMapView();
        mMap.setMyLocationEnabled(true);
        popArrayList();
        markerPop();

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

    public void popArrayList()
    {
        dbLAT.add(dbLatitude);
        dbLNG.add(dbLongitude);
        dbLAT.add(dbLatitude2);
        dbLNG.add(dbLongitude2);
        dbLAT.add(dbLatitude3);
        dbLNG.add(dbLongitude3);
        dbLAT.add(dbLatitude4);
        dbLNG.add(dbLongitude4);
        dbLAT.add(dbLatitude5);
        dbLNG.add(dbLongitude5);

        barDescription.add(barInfo);
        barDescription.add(barInfo2);
        barDescription.add(barInfo3);
        barDescription.add(barInfo4);
        barDescription.add(barInfo5);
//        for(int i = 0; i < 5; i++)
//        {
//            dbLAT.add()
//            for(int m = 0; m < 5; i++)
//            {
//
//            }
//
//        }
    }
    //http://stackoverflow.com/questions/13855049/how-to-show-multiple-markers-on-mapfragment-in-google-map-api-v2
    public void markerPop()
    {
        for(int i = 0; i < dbLNG.size(); i++) {
            LatLng latLng = new LatLng(dbLAT.get(i), dbLNG.get(i));

            BitmapDescriptor bitmapMarker;
            switch (i) {
                case 0:
                    bitmapMarker = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED);
                    Log.i(TAG, "RED");
                    break;
                case 1:
                    bitmapMarker = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE);
                    Log.i(TAG, "BLUE");
                    break;
                case 2:
                    bitmapMarker = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN);
                    Log.i(TAG, "GREEN");
                    break;
                case 3:
                    bitmapMarker = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE);
                    Log.i(TAG, "ORANGE");
                    break;
                case 4:
                    bitmapMarker = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA);
                    Log.i(TAG, "MAGENTA");
                    break;
                default:
                    bitmapMarker = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED);
                    Log.i(TAG, "DEFAULT");
            }

            Marker markerList = mMap.addMarker(new MarkerOptions().position(latLng).icon(bitmapMarker).title(barDescription.get(i)));


            //begin circle and personal location
        }

        int radius = 4024;

        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);//create a Location manager
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);//get last known location
        double longitude = location.getLongitude();//set longitude
        double latitude = location.getLatitude();//set latitude
        LatLng yourLatLng = new LatLng(latitude, longitude);// add them into a single var so we can use camera stuffs
        CameraUpdate camUpdate = CameraUpdateFactory.newLatLngZoom(yourLatLng, 12);//create an update for the camera (LatLng, zoom)
        mMap.animateCamera(camUpdate);//call the update when the marker is added to show your current location


        if (null != mMap) {
            boolean inCircle;

            final Marker marker = mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(latitude, longitude))//make use of location manager to add marker at your location
                    .title("This be you"));

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

            if (distance[0] > circle.getRadius()) {
                Toast.makeText(getBaseContext(), "Outside", Toast.LENGTH_LONG).show();
                inCircle = false;
            } else {
                Toast.makeText(getBaseContext(), "Inside", Toast.LENGTH_LONG).show();
                inCircle = true;
            }

            if (inCircle == false) {
                marker.setVisible(false);
            } else if (inCircle == true) {
                marker.setVisible(true);
            }//TODO figure out how to check for multiple points are inside circle

            mMap.setOnMyLocationButtonClickListener(new OnMyLocationButtonClickListener() {
                @Override
                public boolean onMyLocationButtonClick() {
                    marker.remove();
                    circle.remove();
                    markerPop();
                    //Toast.makeText(getBaseContext(), "TRUE", Toast.LENGTH_LONG).show();
                    return true;
                }
            });

        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}
