package com.bcantero.evaluacion2;

public class Localizacion {

    private String id_location;
    private String place_name;
    private String latitude;
    private String longitude;

    public Localizacion(String id_location, String place_name, String latitude, String longitude) {
        this.id_location = id_location;
        this.place_name = place_name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Localizacion() {

    }

    public String getId_location() {
        return id_location;
    }

    public void setId_location(String id_location) {
        this.id_location = id_location;
    }

    public String getPlace_name() {
        return place_name;
    }

    public void setPlace_name(String place_name) {
        this.place_name = place_name;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
