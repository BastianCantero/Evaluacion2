package com.bcantero.evaluacion2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class RegistrarLocalizacion_Activity extends AppCompatActivity implements View.OnClickListener {

    private TextView txt_place, txt_latitude, txt_longitude;
    private Button btn_save, btn_viewList;
    private String place_name;
    private String latitude;
    private String longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_localizacion);

        txt_place = (TextView) findViewById(R.id.txt_place);
        txt_latitude = (TextView) findViewById(R.id.txt_latitude);
        txt_longitude = (TextView) findViewById(R.id.txt_longitude);

        btn_save = (Button) findViewById(R.id.btn_save);
        btn_save.setOnClickListener(this);

        btn_viewList = (Button) findViewById(R.id.btn_viewList);
        btn_viewList.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_save:

                place_name = txt_place.getText().toString();
                latitude = txt_latitude.getText().toString();
                longitude = txt_latitude.getText().toString();

                inserlocation(place_name, latitude, longitude);

                break;

            case R.id.btn_viewList:

                Intent viewListIntent =  new Intent(this, ListaLocalizaciones_Activity.class);
                startActivity(viewListIntent);

                break;
        }
    }

    private void inserlocation(String place_name, String latitude, String longitude){

        ConexionSQLiteHelper conn =   new ConexionSQLiteHelper(this, "db_app", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();

        try {

            String INSERT = "INSERT INTO map (place_name, latitude, longitude) VALUES ('" + place_name + "', '" + latitude + "', '" + longitude + "')";

            db.execSQL(INSERT);
            db.close();

            Toast.makeText(getApplicationContext(), "Localización guardada correctamente", Toast.LENGTH_SHORT).show();


        }catch (Exception e){

            Toast.makeText(getApplicationContext(), "Error al guardar localización", Toast.LENGTH_SHORT).show();


        }

    }
}