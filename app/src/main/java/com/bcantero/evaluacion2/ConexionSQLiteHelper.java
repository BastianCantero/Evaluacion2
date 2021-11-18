package com.bcantero.evaluacion2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {

    final String CREATE_TABLE_SENSOR = "CREATE TABLE sensor (sensor_type TEXT, sensor_value TEXT, date TEXT, observation TEXT)";

    final String CREATE_TABLE_MAP = "CREATE TABLE map (place_name TEXT, latitude TEXT, longitude TEXT)";

    public ConexionSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SENSOR);
        db.execSQL(CREATE_TABLE_MAP);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS sensor");
        db.execSQL("DROP TABLE IF EXISTS map");

        onCreate(db);
    }
}
