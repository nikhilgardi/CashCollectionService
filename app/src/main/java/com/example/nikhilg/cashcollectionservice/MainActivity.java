package com.example.nikhilg.cashcollectionservice;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback  {

    LoginDataBaseAdapter loginDatabaseAdapter;
    private byte[] img=null;
    //Button getData;
    GPSTracker gpsTracker;
    GoogleMap map;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

//        getData=(Button)findViewById(R.id.getData);
        loginDatabaseAdapter=new LoginDataBaseAdapter(this);
        loginDatabaseAdapter=loginDatabaseAdapter.open();
        String name="Nikhil Gardi";
        String latitude="19.191969";
        String longitude="72.838255";
        int retailer_id=1;
        Bitmap b= BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG, 100, bos);
        img=bos.toByteArray();
        loginDatabaseAdapter.insertEntry(retailer_id,name,latitude,longitude);
//        getData.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ArrayList<String> str_value=loginDatabaseAdapter.getInformation();
//                String name=str_value.get(0);
//                String str_latitude=str_value.get(1);
//                String str_longitude=str_value.get(2);
//                Toast.makeText(getApplicationContext(),"hi"+name,Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(),"hi"+str_latitude,Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(),"hi"+str_longitude,Toast.LENGTH_SHORT).show();
//
//
//            }
//        });
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
//        map = googleMap;
//        gpsTracker = new GPSTracker(this);
//        if (gpsTracker.canGetLocation()) {
//
//
//            gpsTracker.stopUsingGPS();
//            LatLng sydney = new LatLng(gpsTracker.latitude, gpsTracker.longitude);
//            map.addMarker(new MarkerOptions().position(sydney).title("Marker in MOM"));
//            map.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//
//        }

        //map.animateCamera(CameraUpdateFactory.zoomTo(12.0f));
        map = googleMap;
        gpsTracker = new GPSTracker(this);


        MarkerOptions markerOptions = new MarkerOptions();
        LatLng latLng = new LatLng(19.1889029, 72.8421405);
        map.addMarker(new MarkerOptions().position(latLng).title("Retailer").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        map.moveCamera(CameraUpdateFactory.newLatLng(latLng));








        if (gpsTracker.canGetLocation()) {


            gpsTracker.stopUsingGPS();
            LatLng sydney = new LatLng(gpsTracker.latitude, gpsTracker.longitude);
            Log.e("LAtLONg","lat"+"--"+gpsTracker.latitude+"--"+"long"+"--"+gpsTracker.longitude);
            map.addMarker(new MarkerOptions().position(sydney).title("MOM"));
            map.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            map.animateCamera(CameraUpdateFactory.zoomTo(17.0f));
        }
    }
}
