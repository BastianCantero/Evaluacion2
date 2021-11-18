package com.bcantero.evaluacion2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;

public class RegistrarSensor_Activity extends AppCompatActivity implements View.OnClickListener, SensorEventListener {

    private TextView lbl_valueProximitySensor, lbl_valueMagnetismSensor, lbl_valueLightSensor, lbl_valueAccelerometer, lbl_valueGravitySensor;
    private TextView txt_observation;

    private Button btn_proximitySensor, btn_magnetismSensor, btn_lightSensor, btn_accelerometer, btn_gravitySensor;

    private Sensor proximitySensor, magnetismSensor, lightSensor, accelerometer, gravitySensor;
    private SensorManager sensorManager;

    private String typeSensor;
    private String valueSensor;
    private String date;
    private String observation;

    private String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_sensor);

        lbl_valueProximitySensor = (TextView) findViewById(R.id.lbl_valueProximitySensor);
        lbl_valueMagnetismSensor = (TextView) findViewById(R.id.lbl_valueMagnetismSensor);
        lbl_valueLightSensor = (TextView) findViewById(R.id.lbl_valueLightSensor);
        lbl_valueAccelerometer = (TextView) findViewById(R.id.lbl_valueAccelerometer);
        lbl_valueGravitySensor = (TextView) findViewById(R.id.lbl_valueGravitySensor);

        txt_observation = (TextView) findViewById(R.id.txt_observation);

        btn_proximitySensor = (Button) findViewById(R.id.btn_proximitySensor);
        btn_proximitySensor.setOnClickListener(this);

        btn_magnetismSensor = (Button) findViewById(R.id.btn_magnetismSensor);
        btn_magnetismSensor.setOnClickListener(this);

        btn_lightSensor = (Button) findViewById(R.id.btn_lightSensor);
        btn_lightSensor.setOnClickListener(this);

        btn_accelerometer = (Button) findViewById(R.id.btn_accelerometer);
        btn_accelerometer.setOnClickListener(this);

        btn_gravitySensor = (Button) findViewById(R.id.btn_gravitySensor);
        btn_gravitySensor.setOnClickListener(this);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        proximitySensor = (Sensor) sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        magnetismSensor = (Sensor) sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        lightSensor = (Sensor) sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        accelerometer = (Sensor) sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gravitySensor = (Sensor) sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);

        sensorManager.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, magnetismSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, gravitySensor, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btn_proximitySensor:

                typeSensor = "Sensor de proximidad";
                valueSensor = lbl_valueProximitySensor.getText().toString();
                date = currentDateTimeString.toString();
                observation = txt_observation.getText().toString();

                insertSensor(typeSensor, valueSensor, date, observation);

                break;

            case R.id.btn_magnetismSensor:

                typeSensor = "Sensor de magnetismo";
                valueSensor = lbl_valueMagnetismSensor.getText().toString();
                date = currentDateTimeString.toString();
                observation = txt_observation.getText().toString();

                insertSensor(typeSensor, valueSensor, date, observation);

                break;

            case R.id.btn_lightSensor:

                typeSensor = "Sensor de luz";
                valueSensor = lbl_valueLightSensor.getText().toString();
                date = currentDateTimeString.toString();
                observation = txt_observation.getText().toString();

                insertSensor(typeSensor, valueSensor, date, observation);

                break;

            case R.id.btn_accelerometer:

                typeSensor = "Acelerometro";
                valueSensor = lbl_valueAccelerometer.getText().toString();
                date = currentDateTimeString.toString();
                observation = txt_observation.getText().toString();

                insertSensor(typeSensor, valueSensor, date, observation);

                break;

            case R.id.btn_gravitySensor:

                typeSensor = "Sensor de gravedad";
                valueSensor = lbl_valueGravitySensor.getText().toString();
                date = currentDateTimeString.toString();
                observation = txt_observation.getText().toString();

                insertSensor(typeSensor, valueSensor, date, observation);

                break;

        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        switch (sensorEvent.sensor.getType()){

            case Sensor.TYPE_PROXIMITY:
                lbl_valueProximitySensor.setText(String.valueOf(sensorEvent.values[0]));
                break;

            case Sensor.TYPE_MAGNETIC_FIELD:
                lbl_valueMagnetismSensor.setText(String.valueOf(sensorEvent.values[0]));
                break;

            case Sensor.TYPE_LIGHT:
                lbl_valueLightSensor.setText(String.valueOf(sensorEvent.values[0]));
                break;

            case Sensor.TYPE_ACCELEROMETER:
                lbl_valueAccelerometer.setText(String.valueOf(sensorEvent.values[0]));
                break;

            case Sensor.TYPE_GRAVITY:
                lbl_valueGravitySensor.setText(String.valueOf(sensorEvent.values[0]));
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void insertSensor(String typeSensor, String valueSensor, String date, String observation){
        ConexionSQLiteHelper conn =   new ConexionSQLiteHelper(this, "db_sensor", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();

        if(!txt_observation.getText().toString().isEmpty()){

            try {

                String INSERT = "INSERT INTO sensor (sensor_type, sensor_value, date, observation) VALUES ('" + typeSensor + "', '" + valueSensor + "', '" + date + "', '" + observation + "')";

                db.execSQL(INSERT);
                db.close();

                txt_observation.setText("");

                Toast.makeText(getApplicationContext(), "Valores guardados correctamente", Toast.LENGTH_SHORT).show();

            }catch (Exception e){
                Toast.makeText(getApplicationContext(), "Error al guardar valores", Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(getApplicationContext(), "El campo de observacion se encuentra vacio.", Toast.LENGTH_SHORT).show();
        }

    }

}