package com.juegos.kanbal;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DataBaseOpenHelper extends SQLiteAssetHelper {

    public static final String DATABASE_NAME = "proyecto_matemaya.db";
    public static final int DATABASE_VERSION = 1;
    public DataBaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}
