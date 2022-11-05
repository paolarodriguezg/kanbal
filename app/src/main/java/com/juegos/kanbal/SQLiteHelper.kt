package com.juegos.kanbal

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "z.db"
        private const val tabla_alumnos = "alumnos"
        private const val id_alumno = "id_alumno"
        private const val correo = "correo"
        private const val contraseña = "contraseña"
        private const val id_colegio = "id_colegio"
        private const val grado = "grado"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        var crearTablaAlumnos = ("CREATE TABLE " + tabla_alumnos + " ( " + 	id_alumno
                + " INTEGER, " + correo + " TEXT, " + contraseña + " TEXT, " + id_colegio +
                " INTEGER, " + grado + " INTEGER,  PRIMARY KEY(" + id_alumno + " AUTOINCREMENT)" + ");")
        db?.execSQL(crearTablaAlumnos)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $tabla_alumnos")
        onCreate(db)
    }

    @SuppressLint("Range")
    fun query(correo: String, contraseña: String) :Long{
        val seleccionar = ("SELECT * FROM $tabla_alumnos" + " where correo = '" + correo + "' and contraseña = '" + contraseña + "'")
        val db = this.readableDatabase

        val cursor: Cursor?
        try {
            cursor = db.rawQuery(seleccionar, null)
        }catch (e: Exception){
            e.printStackTrace()
            return -1
        }

        if (cursor.moveToFirst()){
            Datos_Globales.grado = cursor.getInt(cursor.getColumnIndex("grado"))
            return 1
        }
        return 0
    }

    fun insertarAlumnos(alumno: Alumno_DAO): Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(correo, alumno.correo)
        contentValues.put(contraseña, alumno.contraseña)
        contentValues.put(id_colegio, alumno.id_colegio)
        contentValues.put(grado, alumno.grado)

        val success = db.insert(tabla_alumnos, null, contentValues)
        db.close()
        return success
    }

    @SuppressLint("Range")
    fun seleccionarAlumnos(): ArrayList<Alumno_DAO>{
        val lista_alumnos: ArrayList<Alumno_DAO> = ArrayList()
        val seleccionar = "SELECT * FROM $tabla_alumnos"
        val db = this.readableDatabase

        val cursor: Cursor?
        try {
            cursor = db.rawQuery(seleccionar, null)
        }catch (e: Exception){
            e.printStackTrace()
            return ArrayList()
        }

        var id_alumno: Int
        var correo:	String
        var contraseña:	String
        var id_colegio:	Int
        var grado: Int

        if (cursor.moveToFirst()){
            do {
                id_alumno = cursor.getInt(cursor.getColumnIndex("id_alumno"))
                correo = cursor.getString(cursor.getColumnIndex("correo"))
                contraseña = cursor.getString(cursor.getColumnIndex("contraseña"))
                id_colegio = cursor.getInt(cursor.getColumnIndex("id_colegio"))
                grado = cursor.getInt(cursor.getColumnIndex("grado"))

                val alumno = Alumno_DAO(id_alumno = id_alumno, correo = correo, contraseña = contraseña, id_colegio = id_colegio, grado = grado)
                lista_alumnos.add(alumno)
            }while (cursor.moveToNext())
        }
        return lista_alumnos
    }
}