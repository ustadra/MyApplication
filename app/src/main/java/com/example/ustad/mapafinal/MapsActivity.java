package com.example.ustad.mapafinal;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private double d1=0;
    private double d2=0;
    private double d3=0;
    private double d4=0;
    private GoogleMap mMap;
    private MainActivity mactivity;
    private GestorBD bd;


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        bd=new GestorBD(this);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final int numeroid = extras.getInt("numeroid");
        final int startbutton = extras.getInt("startbutton");
        if(startbutton==0) {

            // Acquire a reference to the system Location Manager
            LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

            // Define a listener that responds to location updates
            LocationListener locationListener = new LocationListener() {
                public void onLocationChanged(Location location) {
                    //f.actualizarPosicion(location);
                    Calendar c = new GregorianCalendar();  // This creates a Calendar instance with the current time

                    final int mHour = c.get(Calendar.HOUR_OF_DAY);
                    final int mMinute = c.get(Calendar.MINUTE);
                    final int mSecond = c.get(Calendar.SECOND);
                    final int mDay = c.get(Calendar.DAY_OF_MONTH);
                    final int mMonth = c.get(Calendar.MONTH);
                    final int mYear = c.get(Calendar.YEAR);

                    // Called when a new location is found by the network location provider.
                    //COGER POSICIONES ANTERIORES
                    if (d2 != 0) {

                        d3 = d1;
                        d4 = d2;

                    }
                    //COGER POSICION ACTUAL
                    d1 = location.getLatitude();
                    d2 = location.getLongitude();
                    bd.creaPosicion(numeroid, d1, d2);


                    //UNIR POSICION ANTERIOR Y ACTUAL
                    if (d4 != 0) {
                        Polyline line = mMap.addPolyline(new PolylineOptions()
                                .add(new LatLng(d3, d4), new LatLng(d1, d2))
                                .width(5)
                                .color(Color.RED));

                    }
                    //CREAR EL PRIMER MARKER
                    if (d4 == 0) {
                        mMap.addMarker(new MarkerOptions()
                                        .position(new LatLng(location.getLatitude(), location.getLongitude()))
                                        .title(String.valueOf(mHour) + ":" + String.valueOf(mMinute) + ":" + String.valueOf(mSecond))
                        );

                    }

                }


                public void onStatusChanged(String provider, int status, Bundle extras) {
                }

                public void onProviderEnabled(String provider) {
                }

                public void onProviderDisabled(String provider) {
                }
            };

            // Register the listener with the Location Manager to receive location updates

            // http://developer.android.com/intl/es/training/permissions/requesting.html
            int permissionCheck = ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION);
            if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
                //locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            }

        }else if(startbutton==1){

        }


    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;



        // Add a marker in Sydney and move the camera
    }


}