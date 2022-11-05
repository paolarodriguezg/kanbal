package com.juegos.kanbal;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class Ajustes extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    public static final String modo_oscuro ="modo_oscuro_pref";
    public static final String sonido ="sonido_pref";

    public static final String modos_juego ="modos_juego_pref";
    public static final String idioma = "idioma_pref";
    public static final String tiempo ="tiempo_pref";
    public static final String modo_supervivencia ="modo_supervivencia_pref";
    private SettingsFragment fragmento;

    public static final String suma_dificil ="suma_dificil";
    public static final String resta_dificil ="resta_dificil";
    public static final String multiplicacion_dificil ="multiplicacion_dificil";
    public static final String division_dificil ="division_dificil";
    public static final String suma_multiplicacion_dificil ="suma_multiplicacion_dificil";
    public static final String resta_multiplicacion_dificil ="resta_multiplicacion_dificil";
    public static final String suma_division_dificil ="suma_division_dificil";
    public static final String resta_division_dificil ="resta_division_dificil";
    public static final String potencia_dificil ="potencia_dificil";
    public static final String raiz_dificil ="raiz_dificil";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if(prefs.getBoolean(modo_oscuro,false)){
            setTheme(R.style.AppThemeWActionBarDark);
        }else{
            setTheme(R.style.AppThemeWActionBar);
        }
        fragmento = new SettingsFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, fragmento)
                .commit();
        prefs.registerOnSharedPreferenceChangeListener(this);
        traducir();
    }

    public void traducir()
    {

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        fragmento.traducir();
        if(!sharedPreferences.getBoolean(suma_dificil,false)
                && !sharedPreferences.getBoolean(resta_dificil,false)
                && !sharedPreferences.getBoolean(multiplicacion_dificil,false)
                && !sharedPreferences.getBoolean(division_dificil,false)
                && !sharedPreferences.getBoolean(suma_multiplicacion_dificil,false)
                && !sharedPreferences.getBoolean(resta_multiplicacion_dificil,false)
                && !sharedPreferences.getBoolean(resta_division_dificil,false)
                && !sharedPreferences.getBoolean(suma_division_dificil,false)
                && !sharedPreferences.getBoolean(raiz_dificil,false)
                && !sharedPreferences.getBoolean(potencia_dificil,false)){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(suma_dificil,true);
            editor.apply();
            Toast.makeText(getApplicationContext(),"Activa por lo menos una opción para poder jugar",Toast.LENGTH_SHORT).show();
        }


    }
}
