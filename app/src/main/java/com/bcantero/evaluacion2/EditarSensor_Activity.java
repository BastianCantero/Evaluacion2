package com.bcantero.evaluacion2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class EditarSensor_Activity extends AppCompatActivity implements View.OnClickListener {


    private TextView lbl_valueIdSensor, lbl_valueTypeSensor, lbl_valueSensorValor, lbl_valueDate, txt_editObservation;
    private Button btn_edit, btn_deleteRegistro, btn_back;

    private ConexionSQLiteHelper conexionSQLiteHelper;

    Bundle idItem;
    String itemId;

    private AlertDialog alertDialog;
    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_sensor);

        builder =  new AlertDialog.Builder(EditarSensor_Activity.this);
        builder.setTitle("¿Eliminar registro?");

        idItem =  getIntent().getExtras();
        itemId = idItem.getString("idItem");

        lbl_valueIdSensor = (TextView) findViewById(R.id.lbl_valueIdSensor);
        lbl_valueTypeSensor = (TextView) findViewById(R.id.lbl_valueTypeSensor);
        lbl_valueSensorValor = (TextView) findViewById(R.id.lbl_valueSensorValor);
        lbl_valueDate = (TextView) findViewById(R.id.lbl_valueDate);
        txt_editObservation = (TextView) findViewById(R.id.txt_editObservation);

        btn_edit = (Button) findViewById(R.id.btn_edit);
        btn_edit.setOnClickListener(this);

        btn_deleteRegistro = (Button) findViewById(R.id.btn_deleteRegistro);
        btn_deleteRegistro.setOnClickListener(this);

        btn_back = (Button) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(this);

        conexionSQLiteHelper = new ConexionSQLiteHelper(getApplicationContext(), "db_app", null,1);

        selectSensor(itemId);

        lbl_valueIdSensor.setText(itemId);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btn_edit:

                idItem =  getIntent().getExtras();
                itemId = idItem.getString("idItem");
                editObservation(itemId);

                break;

            case R.id.btn_deleteRegistro:

                builder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        deleteRegistro(itemId);

                        idItem =  getIntent().getExtras();
                        itemId = idItem.getString("idItem");

                        Toast.makeText(getApplicationContext(), "Registro Eliminado", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //alertDialog.closeOptionsMenu();
                    }
                });

                alertDialog = builder.create();
                alertDialog.show();

                break;

            case R.id.btn_back:

                Intent main = new Intent(this, MainActivity.class);
                startActivity(main);

                break;

        }
    }

    private void selectSensor(String id){

        SQLiteDatabase db = conexionSQLiteHelper.getReadableDatabase();

        try{


            Cursor cursor = db.rawQuery("SELECT sensor_type,sensor_value, date, observation FROM sensor WHERE id_sensor = '"+ id +"'", null);
            cursor.moveToFirst();

            lbl_valueTypeSensor.setText(cursor.getString(0));
            lbl_valueSensorValor.setText(cursor.getString(1));
            lbl_valueDate.setText(cursor.getString(2));
            txt_editObservation.setText(cursor.getString(3));

            //Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();

            cursor.close();

        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
        }

    }

    private void editObservation(String id){

        SQLiteDatabase db  = conexionSQLiteHelper.getWritableDatabase();

        try {

            db.execSQL("UPDATE sensor SET observation = '" + txt_editObservation.getText().toString() +"' WHERE id_sensor = '" + id + "'");
            db.close();

            Toast.makeText(getApplicationContext(), "Datos Actualizados", Toast.LENGTH_SHORT).show();

            Intent main = new Intent(this, MainActivity.class);
            startActivity(main);

        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Error al actualizar", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteRegistro(String id){

        SQLiteDatabase db  = conexionSQLiteHelper.getWritableDatabase();

        try {

            db.execSQL("DELETE FROM sensor WHERE id_sensor = '"+ id + "'");
            db.close();

            Toast.makeText(getApplicationContext(), "Registro eliminado", Toast.LENGTH_SHORT).show();

            Intent main = new Intent(this, MainActivity.class);
            startActivity(main);

        }catch (Exception e){

            Toast.makeText(getApplicationContext(), "Error al eliminar", Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}