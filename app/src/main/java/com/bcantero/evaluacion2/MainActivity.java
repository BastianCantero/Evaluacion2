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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_addSensor, btn_addDireccion;
    private ListView listView_sensor;
    private SwipeRefreshLayout swipeRefreshLayout;

    private ArrayList<String> informationList;
    private ArrayList<Sensor> sensorList;

    private ArrayAdapter adapter;

    ConexionSQLiteHelper conexionSQLiteHelper;

    private String sensor_type, sensor_value, date, observation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_addSensor = (Button) findViewById(R.id.btn_addSensor);
        btn_addSensor.setOnClickListener(this);

        btn_addDireccion = (Button) findViewById(R.id.btn_addDireccion);
        btn_addDireccion.setOnClickListener(this);

        listView_sensor = (ListView) findViewById(R.id.listView_sensor);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);

        conexionSQLiteHelper =  new ConexionSQLiteHelper(getApplicationContext(), "db_sensor", null, 1);

        consultarSensor();

        adapter =  new ArrayAdapter(this, android.R.layout.simple_list_item_1, informationList);
        listView_sensor.setAdapter(adapter);

        listView_sensor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ///

            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshList();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_addSensor:

                Intent btn_addSensorIntent = new Intent(this, RegistrarSensor_Activity.class);
                startActivity(btn_addSensorIntent);

                break;

            case R.id.btn_addDireccion:

                break;
        }
    }

    private void consultarSensor() {

        SQLiteDatabase db = conexionSQLiteHelper.getReadableDatabase();

        Sensor sensor =  null;
        sensorList =  new ArrayList<Sensor>();

        Cursor cursor =  db.rawQuery("SELECT * FROM sensor", null);

        while (cursor.moveToNext()){
            sensor =  new Sensor();
            sensor.setSensor_type(cursor.getString(0));
            sensor.setSensor_value(cursor.getString(1));
            sensor.setDate(cursor.getString(2));
            sensor.setObservation(cursor.getString(3));

            sensorList.add(sensor);
        }
        obtenerListaSensores();
    }

    private void obtenerListaSensores(){
        informationList =  new ArrayList<String>();
        for (int i = 0; i < sensorList.size(); i++){
            informationList.add( "\n"+ sensorList.get(i).getSensor_type() + " \n\nValor:\n" + sensorList.get(i).getSensor_value() + "\n");
        }
    }

    private void refreshList(){

        consultarSensor();

        adapter =  new ArrayAdapter(this, android.R.layout.simple_list_item_1, informationList);
        listView_sensor.setAdapter(adapter);

        swipeRefreshLayout.setRefreshing(false);

    }

}