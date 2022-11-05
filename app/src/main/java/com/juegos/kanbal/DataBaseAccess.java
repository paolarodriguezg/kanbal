package com.juegos.kanbal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DataBaseAccess instancia;
    Cursor cursor = null;

    private DataBaseAccess(Context context)
    {
        this.openHelper = new DataBaseOpenHelper(context);
    }

    public static DataBaseAccess getInstance(Context context)
    {
        if(instancia == null)
        {
            instancia = new DataBaseAccess(context);
        }
        return instancia;
    }

    public void open()
    {
        this.db = openHelper.getWritableDatabase();
    }


    public void close()
    {
        if(db != null)
        {
            this.db.close();
        }
    }

    public int login(String correo, String contraseña)
    {

        String seleccionar = ("SELECT * FROM alumnos" + " where correo = '" + correo + "' and contraseña = '" + contraseña + "'");
        try {
            cursor = db.rawQuery(seleccionar, null);
        }catch (Exception e){
        e.printStackTrace();
        return -1;
        }

        if (cursor.moveToFirst()){
            Datos_Globales.grado = Integer.valueOf(cursor.getString(6));
            return 1;
        }
        Log.e("Error", seleccionar);
        return 0;
    }


}
