package com.bcantero.evaluacion2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DatosSensor_Activity extends AppCompatActivity implements View.OnClickListener {

    private TextView lbl_getSensorTypeValue, lblgetValueSensor, lbl_getDate, txt_valueObservation;
    private Button btn_update, btn_delete;

    private String sensor_type, sensor_value, date, observation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_sensor);

        lbl_getSensorTypeValue = (TextView) findViewById(R.id.lbl_getSensorTypeValue);
        lblgetValueSensor = (TextView) findViewById(R.id.lblgetValueSensor);
        lbl_getDate = (TextView) findViewById(R.id.lbl_getDate);
        txt_valueObservation = (TextView) findViewById(R.id.txt_valueObservation);

        btn_update = (Button) findViewById(R.id.btn_update);
        btn_update.setOnClickListener(this);

        btn_delete = (Button) findViewById(R.id.btn_delete);
        btn_delete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_update:

                break;

            case R.id.btn_delete:

                break;
        }
    }
}