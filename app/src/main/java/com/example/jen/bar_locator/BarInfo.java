package com.example.jen.bar_locator;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.Marker;


public class BarInfo extends MapFragment {


     public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
         return inflater.inflate(R.layout.fragment_marker,container,false);
     }
    public void onActivityCreated(Bundle savedInstanceState) {

        getMap().setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

            public void onInfoWindowClick(Marker marker) {
                Intent i = new Intent(getActivity(), MainActivity.class);
                startActivity(i);
            }
        });
    }
}
