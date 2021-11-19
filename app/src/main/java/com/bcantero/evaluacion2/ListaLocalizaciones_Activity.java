package com.bcantero.evaluacion2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListaLocalizaciones_Activity extends AppCompatActivity implements View.OnClickListener {

    private ListView listViewLocation;

    private ConexionSQLiteHelper conexionSQLiteHelper;
    private ArrayList<String> informationList;
    private ArrayList<Localizacion> locationList;
    private SwipeRefreshLayout swipeRefreshLayout;

    private ArrayAdapter adapter;

    private TextView btn_addrDireccion, btn_sensorLisr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_localizaciones);

        listViewLocation = (ListView) findViewById(R.id.listViewLocation);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);

        btn_addrDireccion = (Button) findViewById(R.id.btn_addrDireccion);
        btn_addrDireccion.setOnClickListener(this);

        btn_sensorLisr = (Button) findViewById(R.id.btn_sensorLisr);
        btn_sensorLisr.setOnClickListener(this);

        conexionSQLiteHelper = new ConexionSQLiteHelper(getApplicationContext(), "db_app", null, 1);

        consultarLocalizacion();

        adapter =  new ArrayAdapter(this, android.R.layout.simple_list_item_1, informationList);
        listViewLocation.setAdapter(adapter);

        listViewLocation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String idItem = locationList.get(position).getId_location();

                Intent editarLocalizacion = new Intent(ListaLocalizaciones_Activity.this , EditarLocalizacion_Activity.class);

                editarLocalizacion.putExtra("idItem", idItem.toString());

                startActivity(editarLocalizacion);

                //Toast.makeText(getApplicationContext(), idItem, Toast.LENGTH_SHORT).show();

            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    private void consultarLocalizacion(){

        SQLiteDatabase db = conexionSQLiteHelper.getReadableDatabase();

        Localizacion localizacion = null;

        locationList = new ArrayList<Localizacion>();

        try {

            Cursor cursor = db.rawQuery("SELECT * FROM map", null);

            while (cursor.moveToNext()){

                localizacion =  new Localizacion();
                localizacion.setId_location(cursor.getString(0));
                localizacion.setPlace_name(cursor.getString(1));
                localizacion.setLatitude(cursor.getString(2));
                localizacion.setLongitude(cursor.getString(3));

                locationList.add(localizacion);

            }

            obtenerListaLocalizaciones();

        }catch (Exception e){

            Toast.makeText(getApplicationContext(), "No se pudo obtener la lista.", Toast.LENGTH_SHORT).show();

        }
    }

    private  void obtenerListaLocalizaciones(){

        informationList = new ArrayList<String>();
        for (int i = 0; i < locationList.size(); i++){
            informationList.add("\nId registro: " +locationList.get(i).getId_location() + "\n\nLugar:\n" + locationList.get(i).getPlace_name() + "\n\nLatitud:\n" + locationList.get(i).getLatitude() + "\n\nLongitude:\n" + locationList.get(i).getLongitude() + "\n");
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_sensorLisr:

                Intent intentSensor = new Intent(this, MainActivity.class);
                startActivity(intentSensor);
                break;

            case R.id.btn_addrDireccion:

                Intent addDireccion = new Intent(this, RegistrarLocalizacion_Activity.class);
                startActivity(addDireccion);

                break;

        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}