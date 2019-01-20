package com.popm.yoinvito;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.SQLException;
import android.graphics.Camera;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class localiza_tienda extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Marker marcador;
    double lat = 0.0;
    double lon = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localiza_tienda);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        ////////////////////////////////////////////////////////////////////
    }


    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;
  /*      if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);

        mMap.getUiSettings().setZoomControlsEnabled(true);
*/
        miUbicacion();

        /**for (Tienda tienda: Tiendas()){
            Marker marker = mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(tienda.getLatitud_x(),tienda.getLatitud_y()))
                            .title(tienda.getNombre())
                    );
        }**/

    }


    private void agregarMarcador(double lat, double lon) {
        LatLng coordenadas = new LatLng(lat, lon);
        CameraUpdate ubicacion = CameraUpdateFactory.newLatLngZoom(coordenadas, 16);

        if (marcador != null) {
            marcador.remove();
        }

        marcador = mMap.addMarker(new MarkerOptions()
                .position(coordenadas)
                .title("Ubicacion actual")
                .snippet("Aqui estoy")
        );
        mMap.animateCamera(ubicacion);

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                Toast.makeText(localiza_tienda.this, "CLICK", Toast.LENGTH_LONG).show();


                return false;
            }
        });
    }


    private void actualizarUbicacion(Location location) {

        if (location != null) {
            lat = location.getLatitude();
            lon = location.getLongitude();
            agregarMarcador(lat, lon);
        }
    }



    LocationListener locListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {

            actualizarUbicacion(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };


    private void miUbicacion() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        actualizarUbicacion(location);

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,15000,0,locListener);
    }

    public Connection conexionBD(){
        Connection conexion = null;

        try{

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();

            conexion = DriverManager.getConnection("jdbc:jtds:sqlserver://200.38.35.62; databaseName = XLR8: user = popmobile2: password = R3c4rg4f4c1l2.16#");

            Toast.makeText(getApplicationContext(), "Conexion exitosa", Toast.LENGTH_LONG ).show();

        }catch (Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG ).show();
        }


        return conexion;
    }



    public List<Tienda> Tiendas (){

        List<Tienda> tiendas  = new ArrayList<>();

        try {

            Statement statement = conexionBD().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM TIENDAS");

            while (resultSet.next()){
                    tiendas.add(new Tienda(
                                            resultSet.getInt("id_tienda"),
                                            resultSet.getInt("calif"),
                                            resultSet.getString("nombre"),
                                            resultSet.getFloat("latitud_x"),
                                            resultSet.getFloat("latitud_y")
                    ));

            }

        }catch (java.sql.SQLException e) {
            e.printStackTrace();
        }


        return tiendas;
    }




}



