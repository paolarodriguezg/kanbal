package com.juegos.kanbal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

public class menuPrincipal extends AppCompatActivity {

    ImageView actividad1, actividad2, actividad3, actividad4;
    ImageView sumaimg, restaimg, multiplicacionimg, divisionimg, potenciaimg, raizimg;
    CheckBox suma, resta, multiplicacion, division, potencia, raiz;
    CheckBox actividad1Tiempo, actividad2Tiempo, actividad3Tiempo, actividad4Tiempo;
    ImageView año;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        ConstraintLayout fondo = findViewById(R.id.fondo);
        if (Datos_Globales.grado == 1) {
            fondo.setBackground(ContextCompat.getDrawable(this, R.color.primero));
        }
        if (Datos_Globales.grado == 2) {
            fondo.setBackground(ContextCompat.getDrawable(this, R.color.segundo));
        }
        if (Datos_Globales.grado == 3) {
            fondo.setBackground(ContextCompat.getDrawable(this, R.color.tercero));
        }
        if (Datos_Globales.grado == 4) {
            fondo.setBackground(ContextCompat.getDrawable(this, R.color.cuarto));
        }
        if (Datos_Globales.grado == 5) {
            fondo.setBackground(ContextCompat.getDrawable(this, R.color.quinto));
        }
        if (Datos_Globales.grado == 6) {
            fondo.setBackground(ContextCompat.getDrawable(this, R.color.sexto));
        }

        actividad1 = (ImageView) findViewById(R.id.actividad1);
        actividad2 = (ImageView) findViewById(R.id.actividad2);
        actividad3 = (ImageView) findViewById(R.id.actividad3);
        actividad4 = (ImageView) findViewById(R.id.actividad4);

        suma = (CheckBox) findViewById(R.id.sumaActivar);
        resta = (CheckBox) findViewById(R.id.restaActivar);
        multiplicacion = (CheckBox) findViewById(R.id.multiplicacionActivar);
        division = (CheckBox) findViewById(R.id.divisionActivar);
        potencia = (CheckBox) findViewById(R.id.potenciaActivar);
        raiz = (CheckBox) findViewById(R.id.raizActivar);

        actividad1Tiempo = (CheckBox) findViewById(R.id.actividad1Tiempo);
        actividad2Tiempo = (CheckBox) findViewById(R.id.actividad2Tiempo);
        actividad3Tiempo = (CheckBox) findViewById(R.id.actividad3Tiempo);
        actividad4Tiempo = (CheckBox) findViewById(R.id.actividad4Tiempo);


        sumaimg = (ImageView) findViewById(R.id.sumaimg);
        restaimg = (ImageView) findViewById(R.id.restaimg);
        multiplicacionimg = (ImageView) findViewById(R.id.multiplicacionimg);
        divisionimg = (ImageView) findViewById(R.id.divisionimg);
        potenciaimg = (ImageView) findViewById(R.id.potenciacionimg);
        raizimg = (ImageView) findViewById(R.id.raizimg);


        suma.setChecked(false);
        resta.setChecked(false);
        multiplicacion.setChecked(false);
        division.setChecked(false);
        potencia.setChecked(false);
        raiz.setChecked(false);

        suma.setVisibility(View.GONE);
        resta.setVisibility(View.GONE);
        multiplicacion.setVisibility(View.GONE);
        division.setVisibility(View.GONE);
        potencia.setVisibility(View.GONE);
        raiz.setVisibility(View.GONE);

        sumaimg.setVisibility(View.GONE);
        restaimg.setVisibility(View.GONE);
        multiplicacionimg.setVisibility(View.GONE);
        divisionimg.setVisibility(View.GONE);
        potenciaimg.setVisibility(View.GONE);
        raizimg.setVisibility(View.GONE);


        año = (ImageView) findViewById(R.id.grado);
        if (Datos_Globales.grado == 1) {
            año.setImageResource(R.drawable.primero);
            suma.setChecked(true);
            resta.setChecked(true);


            suma.setVisibility(View.VISIBLE);
            resta.setVisibility(View.VISIBLE);

            sumaimg.setVisibility(View.VISIBLE);
            restaimg.setVisibility(View.VISIBLE);
        }
        if (Datos_Globales.grado == 2) {
            año.setImageResource(R.drawable.segundo);
            suma.setChecked(true);
            resta.setChecked(true);
            multiplicacion.setChecked(true);

            suma.setVisibility(View.VISIBLE);
            resta.setVisibility(View.VISIBLE);
            multiplicacion.setVisibility(View.VISIBLE);


            sumaimg.setVisibility(View.VISIBLE);
            restaimg.setVisibility(View.VISIBLE);
            multiplicacionimg.setVisibility(View.VISIBLE);
        }
        if (Datos_Globales.grado == 3) {
            año.setImageResource(R.drawable.tercero);
            suma.setChecked(true);
            resta.setChecked(true);
            multiplicacion.setChecked(true);
            division.setChecked(true);

            suma.setVisibility(View.VISIBLE);
            resta.setVisibility(View.VISIBLE);
            multiplicacion.setVisibility(View.VISIBLE);
            division.setVisibility(View.VISIBLE);

            sumaimg.setVisibility(View.VISIBLE);
            restaimg.setVisibility(View.VISIBLE);
            multiplicacionimg.setVisibility(View.VISIBLE);
            divisionimg.setVisibility(View.VISIBLE);
        }
        if (Datos_Globales.grado == 4) {
            año.setImageResource(R.drawable.quinto);
            suma.setChecked(true);
            resta.setChecked(true);
            multiplicacion.setChecked(true);
            division.setChecked(true);
            potencia.setChecked(true);

            suma.setVisibility(View.VISIBLE);
            resta.setVisibility(View.VISIBLE);
            multiplicacion.setVisibility(View.VISIBLE);
            division.setVisibility(View.VISIBLE);
            potencia.setVisibility(View.VISIBLE);

            sumaimg.setVisibility(View.VISIBLE);
            restaimg.setVisibility(View.VISIBLE);
            multiplicacionimg.setVisibility(View.VISIBLE);
            divisionimg.setVisibility(View.VISIBLE);
            potenciaimg.setVisibility(View.VISIBLE);

        }
        if (Datos_Globales.grado == 5) {
            año.setImageResource(R.drawable.quinto);
            suma.setChecked(true);
            resta.setChecked(true);
            multiplicacion.setChecked(true);
            division.setChecked(true);
            potencia.setChecked(true);

            suma.setVisibility(View.VISIBLE);
            resta.setVisibility(View.VISIBLE);
            multiplicacion.setVisibility(View.VISIBLE);
            division.setVisibility(View.VISIBLE);
            potencia.setVisibility(View.VISIBLE);

            sumaimg.setVisibility(View.VISIBLE);
            restaimg.setVisibility(View.VISIBLE);
            multiplicacionimg.setVisibility(View.VISIBLE);
            divisionimg.setVisibility(View.VISIBLE);
            potenciaimg.setVisibility(View.VISIBLE);

        }
        if (Datos_Globales.grado == 6) {
            año.setImageResource(R.drawable.sexto);
            suma.setChecked(true);
            resta.setChecked(true);
            multiplicacion.setChecked(true);
            division.setChecked(true);
            potencia.setChecked(true);
            raiz.setChecked(true);

            suma.setVisibility(View.VISIBLE);
            resta.setVisibility(View.VISIBLE);
            multiplicacion.setVisibility(View.VISIBLE);
            division.setVisibility(View.VISIBLE);
            potencia.setVisibility(View.VISIBLE);
            raiz.setVisibility(View.VISIBLE);

            sumaimg.setVisibility(View.VISIBLE);
            restaimg.setVisibility(View.VISIBLE);
            multiplicacionimg.setVisibility(View.VISIBLE);
            divisionimg.setVisibility(View.VISIBLE);
            potenciaimg.setVisibility(View.VISIBLE);
            raizimg.setVisibility(View.VISIBLE);

        }
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        if (sharedPref.getString(Ajustes.idioma, "Español").equals("Español")) {

            suma.setText("Activar");
            resta.setText("Activar");
            multiplicacion.setText("Activar");
            division.setText("Activar");
            potencia.setText("Activar");
            raiz.setText("Activar");
            sumaimg.setImageResource(R.drawable.suma);
            restaimg.setImageResource(R.drawable.resta);
            multiplicacionimg.setImageResource(R.drawable.multiplicacion);
            divisionimg.setImageResource(R.drawable.division);
            potenciaimg.setImageResource(R.drawable.potenciacion);
            raizimg.setImageResource(R.drawable.raiz);
            actividad1Tiempo.setText("Tiempo");
            actividad2Tiempo.setText("Tiempo");
            actividad3Tiempo.setText("Tiempo");
            actividad4Tiempo.setText("Tiempo");
            actividad1.setImageResource(R.drawable.actividad1);
            actividad2.setImageResource(R.drawable.actividad2);
            actividad3.setImageResource(R.drawable.actividad3);
            actividad4.setImageResource(R.drawable.actividad4);
            if (Datos_Globales.grado == 1) {
                año.setImageResource(R.drawable.primero);
            } else if (Datos_Globales.grado == 2) {
                año.setImageResource(R.drawable.segundo);
            } else if (Datos_Globales.grado == 3) {
                año.setImageResource(R.drawable.tercero);
            } else if (Datos_Globales.grado == 4) {
                año.setImageResource(R.drawable.cuarto);
            } else if (Datos_Globales.grado == 5) {
                año.setImageResource(R.drawable.quinto);
            } else if (Datos_Globales.grado == 6) {
                año.setImageResource(R.drawable.sexto);
            }

        } else if (sharedPref.getString(Ajustes.idioma, "Español").equals("Maya Itzá")) {
            suma.setText("Tz'ajtimeyaj");
            resta.setText("Tz'ajtimeyaj");
            multiplicacion.setText("Tz'ajtimeyaj");
            division.setText("Tz'ajtimeyaj");
            potencia.setText("Tz'ajtimeyaj");
            raiz.setText("Tz'ajtimeyaj");
            sumaimg.setImageResource(R.drawable.sumamaya);
            restaimg.setImageResource(R.drawable.restamaya);
            multiplicacionimg.setImageResource(R.drawable.multiplicacionmaya);
            divisionimg.setImageResource(R.drawable.divisionmaya);
            potenciaimg.setImageResource(R.drawable.potenciacionmaya);
            raizimg.setImageResource(R.drawable.raiz);
            actividad1Tiempo.setText("Tiempojej");
            actividad2Tiempo.setText("Tiempojej");
            actividad3Tiempo.setText("Tiempojej");
            actividad4Tiempo.setText("Tiempojej");
            actividad1.setImageResource(R.drawable.actividad1itza);
            actividad2.setImageResource(R.drawable.actividad2itza);
            actividad3.setImageResource(R.drawable.actividad3itza);
            actividad4.setImageResource(R.drawable.actividad4itza);
            if (Datos_Globales.grado == 1) {
                año.setImageResource(R.drawable.primeromaya);
            } else if (Datos_Globales.grado == 2) {
                año.setImageResource(R.drawable.segundomaya);
            } else if (Datos_Globales.grado == 3) {
                año.setImageResource(R.drawable.terceromaya);
            } else if (Datos_Globales.grado == 4) {
                año.setImageResource(R.drawable.cuartomaya);
            } else if (Datos_Globales.grado == 5) {
                año.setImageResource(R.drawable.quintomaya);
            } else if (Datos_Globales.grado == 6) {
                año.setImageResource(R.drawable.sextomaya);
            }

        } else if (sharedPref.getString(Ajustes.idioma, "Español").equals("Q'eqchi'")) {
            suma.setText("Xteeb'al");
            resta.setText("Xteeb'al");
            multiplicacion.setText("Xteeb'al");
            division.setText("Xteeb'al");
            potencia.setText("Xteeb'al");
            raiz.setText("Xteeb'al");
            sumaimg.setImageResource(R.drawable.sumaqeqchi);
            restaimg.setImageResource(R.drawable.restaqeqchi);
            multiplicacionimg.setImageResource(R.drawable.multiplicacionqeqchi);
            divisionimg.setImageResource(R.drawable.divisionqeqchi);
            potenciaimg.setImageResource(R.drawable.potenciacionqeqchi);
            raizimg.setImageResource(R.drawable.raiz);
            actividad1Tiempo.setText("Hoonal");
            actividad2Tiempo.setText("Hoonal");
            actividad3Tiempo.setText("Hoonal");
            actividad4Tiempo.setText("Hoonal");
            actividad1.setImageResource(R.drawable.actividad1qeqchi);
            actividad2.setImageResource(R.drawable.actividad2qeqchi);
            actividad3.setImageResource(R.drawable.actividad3qeqchi);
            actividad4.setImageResource(R.drawable.actividad4qeqchi);
            if (Datos_Globales.grado == 1) {
                año.setImageResource(R.drawable.primeroqeqchi);
            } else if (Datos_Globales.grado == 2) {
                año.setImageResource(R.drawable.segundoqeqchi);
            } else if (Datos_Globales.grado == 3) {
                año.setImageResource(R.drawable.terceroqeqchi);
            } else if (Datos_Globales.grado == 4) {
                año.setImageResource(R.drawable.cuartoqeqchi);
            } else if (Datos_Globales.grado == 5) {
                año.setImageResource(R.drawable.quintoqeqchi);
            } else if (Datos_Globales.grado == 6) {
                año.setImageResource(R.drawable.sextoqeqchi);
            }
        }


        actividad1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checarDatos();
                Datos_Globales.tiempo = false;
                if (actividad1Tiempo.isChecked()) {
                    Intent intent = new Intent(menuPrincipal.this, Contador.class);
                    Datos_Globales.tiempo = true;
                    Datos_Globales.numeroActividad = 1;
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(menuPrincipal.this, Actividad1.class);
                    startActivity(intent);
                }

            }
        });
        actividad2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checarDatos();
                Datos_Globales.tiempo = false;
                if (actividad2Tiempo.isChecked()) {
                    Intent intent = new Intent(menuPrincipal.this, Contador.class);
                    Datos_Globales.numeroActividad = 2;
                    Datos_Globales.tiempo = true;
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(menuPrincipal.this, Actividad2.class);
                    startActivity(intent);
                }

            }
        });
        actividad3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checarDatos();
                Datos_Globales.tiempo = false;
                if (actividad3Tiempo.isChecked()) {
                    Intent intent = new Intent(menuPrincipal.this, Contador.class);
                    Datos_Globales.numeroActividad = 3;
                    Datos_Globales.tiempo = true;
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(menuPrincipal.this, Actividad3.class);
                    startActivity(intent);
                }

            }
        });
        actividad4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checarDatos();
                Datos_Globales.tiempo = false;
                if (actividad4Tiempo.isChecked()) {
                    Intent intent = new Intent(menuPrincipal.this, Contador.class);
                    Datos_Globales.numeroActividad = 4;
                    Datos_Globales.tiempo = true;
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(menuPrincipal.this, Actividad4.class);
                    startActivity(intent);
                }

            }
        });
    }

    public void checarDatos() {
        Datos_Globales.suma = false;
        Datos_Globales.resta = false;
        Datos_Globales.multiplicacion = false;
        Datos_Globales.division = false;
        Datos_Globales.potencia = false;
        Datos_Globales.raiz = false;

        if (suma.isChecked()) {
            Datos_Globales.suma = true;
        }
        if (resta.isChecked()) {
            Datos_Globales.resta = true;
        }
        if (multiplicacion.isChecked()) {
            Datos_Globales.multiplicacion = true;
        }
        if (division.isChecked()) {
            Datos_Globales.division = true;
        }
        if (potencia.isChecked()) {
            Datos_Globales.potencia = true;
        }
        if (raiz.isChecked()) {
            Datos_Globales.raiz = true;
        }


    }

    public void abrir_configuraciones(View view) {
        Intent intent = new Intent(menuPrincipal.this, Ajustes.class);
        startActivity(intent);
    }
}