package com.bcantero.evaluacion2;

public class Sensor {

    private String id_sensor;
    private String sensor_type;
    private String sensor_value;
    private String date;
    private String observation;

    public Sensor(String id_sensor, String sensor_type, String sensor_value, String date, String observation) {
        this.id_sensor = id_sensor;
        this.sensor_type = sensor_type;
        this.sensor_value = sensor_value;
        this.date = date;
        this.observation = observation;
    }

    public Sensor() {
    }

    public String getId_sensor() {
        return id_sensor;
    }

    public void setId_sensor(String id_sensor) {
        this.id_sensor = id_sensor;
    }

    public String getSensor_type() {
        return sensor_type;
    }

    public void setSensor_type(String sensor_type) {
        this.sensor_type = sensor_type;
    }

    public String getSensor_value() {
        return sensor_value;
    }

    public void setSensor_value(String sensor_value) {
        this.sensor_value = sensor_value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }
}
