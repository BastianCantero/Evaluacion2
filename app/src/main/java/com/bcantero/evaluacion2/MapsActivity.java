package com.bcantero.evaluacion2;

import androidx.fragment.app.FragmentActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.bcantero.evaluacion2.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    private CameraPosition cameraPosition;

    private Bundle idItem;
    private String itemId;

    private String direcction;
    private String latitude;
    private String longitude;

    ConexionSQLiteHelper conexionSQLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        idItem =  getIntent().getExtras();
        itemId = idItem.getString("idItem");

        conexionSQLiteHelper = new ConexionSQLiteHelper(getApplicationContext(), "db_app", null, 1);

        getData(itemId);
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

        Double x = Double.parseDouble(latitude);

        Double y = Double.parseDouble(longitude);


        LatLng position = new LatLng(x, y);

        mMap.addMarker(new MarkerOptions().position(position).title(direcction));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(position));

        cameraPosition = new CameraPosition.Builder()
                .target(position) //Establece la posicion al centro de la panrtalla
                .zoom(17)       //Establece el nivel o zoom de acercamiento
                .bearing(8)     //Establece la orientacion de la camara
                .tilt(0)        //Establece la inclinacion de la camara ej:30 grados
                .build();

        setInitialCameraPosition();


    }

    private void setInitialCameraPosition(){
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    private void getData(String id){

        SQLiteDatabase db = conexionSQLiteHelper.getReadableDatabase();

        try{


            Cursor cursor = db.rawQuery("SELECT place_name, latitude, longitude FROM map WHERE id_location = '"+ id +"'", null);
            cursor.moveToFirst();

            direcction = cursor.getString(0);
            latitude = cursor.getString(1);
            longitude = cursor.getString(2);

            cursor.close();

        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
        }

    }
}