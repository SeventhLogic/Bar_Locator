package com.example.jen.bar_locator;

import android.content.Context;
import android.content.IntentSender;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
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
    Context context = getApplicationContext();
    int duration = Toast.LENGTH_SHORT;

    private static final int REQUEST_RESOLVE_ERROR = 1001;

    GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_layout);
        createMapView();
        addMarker();
    }
    /*
    *  Called by Location Services when the request to connect the
     * client finishes successfully. At this point, you can
     * request the current location or start periodic updates
    * */

    //TODO figure out why the superclass isn't implemented on this file...
    public void onConnected(Bundle dataBundle)
    {
        Toast.makeText(context, "Connected to location services", duration).show();
    }

    /*
     * Called by Location Services if the connection to the
     * location client drops because of an error.
     */

    public void onDisconnected()
    {
        Toast.makeText(context, "Disconnected from location services please reconnect", duration).show();
    }

    /*
     * Called by Location Services if the attempt to
     * Location Services fails.
     */

    public void onConnectionFailed(ConnectionResult connectionResult)
    {
        /*
         * Google Play services can resolve some errors it detects.
         * If the error has a resolution, try sending an Intent to
         * start a Google Play services activity that can resolve
         * error.
         */
        if(connectionResult.hasResolution())
        {
            try
            {
                //start an activity that tries to resolve the error
                connectionResult.startResolutionForResult(
                        this, REQUEST_RESOLVE_ERROR);
                 /*
                 * Thrown if Google Play services canceled the original
                 * PendingIntent
                 */
            }catch (IntentSender.SendIntentException e)
            {
                //log the error
                e.printStackTrace();
            }
        }
        else
        {
            /*
             * If no resolution is available, display a dialog to the
             * user with the error.
             */
            Toast.makeText(context, "Connection Error has no resolution", duration).show();
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

    }

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
        if(null != mMap)
        {
            mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(0,0))
                    .title("Marker"));
        }
    }

    protected boolean isPlayServicesAvailable()//a boolean to determine if Google Play Services is available
    {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);

        if(status == ConnectionResult.SUCCESS)
        {
            return true;
        }

        else if(GooglePlayServicesUtil.isUserRecoverableError(status))
        {
            //deal with error
        }
        else
        {
            //maps is not available.
        }

        return (false);
    }


}
