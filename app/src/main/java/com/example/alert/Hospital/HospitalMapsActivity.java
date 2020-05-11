package com.example.alert.Hospital;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.alert.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;



public class HospitalMapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    SupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_hospital_maps);

        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(13.0373, 80.2123))
                        .title("Pallava Hospital")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(13.0348,80.2076))
                        .title("ESI Hospital")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(13.0216, 80.1855))
                        .title("MIOT Hospital")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(13.0418, 80.2246))
                        .title("SRM Hospital")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));

                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(13.0373, 80.2123), 10));
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(13.0373, 80.2123))
                .title("Pallava Hospital")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(13.0348,80.2076))
                .title("ESI Hospital")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(13.0216, 80.1855))
                .title("MIOT Hospital")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));



        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(13.0418, 80.2246))
                .title("SRM Hospital")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));


        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(13.0373, 80.2123), 10));
    }
}
