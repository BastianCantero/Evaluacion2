package com.bcantero.evaluacion2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class EditarLocalizacion_Activity extends AppCompatActivity implements View.OnClickListener {

    private TextView lbl_valueIdRegitro, lbl_valueLugar, lbl_valueLatitud, lbl_valueLongitud;
    private Button btn_viewOnMap, btn_deletePlace;

    private ConexionSQLiteHelper conexionSQLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_localizacion);

        Bundle idItem =  getIntent().getExtras();
        String itemId = idItem.getString("idItem");

        lbl_valueIdRegitro = (TextView) findViewById(R.id.lbl_valueIdRegitro);
        lbl_valueLugar = (TextView) findViewById(R.id.lbl_valueLugar);
        lbl_valueLatitud = (TextView) findViewById(R.id.lbl_valueLatitud);
        lbl_valueLongitud = (TextView) findViewById(R.id.lbl_valueLongitud);

        btn_viewOnMap = (Button) findViewById(R.id.btn_viewOnMap);
        btn_viewOnMap.setOnClickListener(this);

        btn_deletePlace = (Button) findViewById(R.id.btn_deletePlace);
        btn_deletePlace.setOnClickListener(this);

        conexionSQLiteHelper = new ConexionSQLiteHelper(getApplicationContext(), "db_app", null,1);

        selectLocation(itemId);

        lbl_valueIdRegitro.setText(itemId);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btn_viewOnMap:

                break;

            case R.id.btn_deletePlace:

                break;
        }
    }

    private void selectLocation(String id){

        SQLiteDatabase db = conexionSQLiteHelper.getReadableDatabase();

        try{


            Cursor cursor = db.rawQuery("SELECT place_name, latitude, longitude FROM map WHERE id_location = '"+ id +"'", null);
            cursor.moveToFirst();

            lbl_valueLugar.setText(cursor.getString(0));
            lbl_valueLatitud.setText(cursor.getString(1));
            lbl_valueLongitud.setText(cursor.getString(2));

            //Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();

            cursor.close();

        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
        }

    }
}