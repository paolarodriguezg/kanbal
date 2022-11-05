package com.juegos.kanbal;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceFragmentCompat;


public class SettingsFragment extends PreferenceFragmentCompat {

    SharedPreferences sharedPref;
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

        setPreferencesFromResource(R.xml.preference, rootKey);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(getContext());
        traducir();


    }



    public void traducir()
    {
        if (sharedPref.getString(Ajustes.idioma, "Español").equals("Español")) {

            Preference customPref = findPreference("modos_juego_pref");
            customPref.setTitle("Modo de juego");
            customPref.setSummary("Escoge entre el modo tutorial");

            customPref = findPreference("sonido_pref");
            customPref.setTitle("Sonido ");
            customPref.setSummary("Activa la música para todos los juegos");

            customPref = findPreference("idioma_pref");
            customPref.setTitle("Cambiar idioma ");
            customPref.setSummary("Escoge entre español, itzá y q'eqchi'");

            customPref = findPreference("tiempo_pref");
            customPref.setTitle("Tiempo");
            customPref.setSummary("Modifica los segundos del contador en los juegos");

            customPref = findPreference("modo_supervivencia_pref");
            customPref.setTitle("Modo supervivencia");
            customPref.setSummary("Solo puedes tenes un solo error antes de que se acabe el juego");

            customPref = findPreference("suma_dificil");
            customPref.setTitle("Suma triple");

            customPref = findPreference("resta_dificil");
            customPref.setTitle("Resta triple");

            customPref = findPreference("multiplicacion_dificil");
            customPref.setTitle("Multiplicacion triple");

            customPref = findPreference("division_dificil");
            customPref.setTitle("Division triple");

            customPref = findPreference("suma_multiplicacion_dificil");
            customPref.setTitle("Suma y Multiplicacion");

            customPref = findPreference("resta_multiplicacion_dificil");
            customPref.setTitle("Resta y Multiplicacion");

            customPref = findPreference("suma_division_dificil");
            customPref.setTitle("Suma y Division");

            customPref = findPreference("resta_division_dificil");
            customPref.setTitle("Resta y Division");

            customPref = findPreference("potencia_dificil");
            customPref.setTitle("Potencias");

            customPref = findPreference("raiz_dificil");
            customPref.setTitle("Raíz cuadrada");

            PreferenceCategory prefcat = findPreference(("config"));
            prefcat.setTitle("Configuración General");

            prefcat = findPreference(("ajustes"));
            prefcat.setTitle("Ajustes de juego");

            prefcat = findPreference(("modo_pesadilla"));
            prefcat.setTitle("Configuracion Modo Pesadilla");


        } else if (sharedPref.getString(Ajustes.idioma, "Español").equals("Maya Itzá")) {

            Preference customPref = findPreference("modos_juego_pref");
            customPref.setTitle("Modojej ti b'axäl");
            customPref.setSummary("Xaxaaktej ichil a' modojej tutorali'ij");

            customPref = findPreference("sonido_pref");
            customPref.setTitle("Jum");
            customPref.setSummary("Tz'aj a' pax ti tulakal a' b'axäloo");

            customPref = findPreference("idioma_pref");
            customPref.setTitle("Jelb'aj t'an");
            customPref.setSummary("Yejtej ichil t'an español, Maya Itzá yetel Q'eqechi'");

            customPref = findPreference("tiempo_pref");
            customPref.setTitle("Tiempojej");
            customPref.setSummary("Jelkuntej a' segundoje ti ajxokil ti a' b'axäloo'");

            customPref = findPreference("modo_supervivencia_pref");
            customPref.setTitle("Modojej supervivenciajej");
            customPref.setSummary("Chen patal uyantaltech junp'eel ma'ki'il ma'toj ujo'mok a' b'axäl");

            customPref = findPreference("suma_dificil");
            customPref.setTitle("O'ox b'ak'xokil");

            customPref = findPreference("resta_dificil");
            customPref.setTitle("O'ox luk'saj");

            customPref = findPreference("multiplicacion_dificil");
            customPref.setTitle("O'ox yayaab'b'il");

            customPref = findPreference("division_dificil");
            customPref.setTitle("O'ox jujunpaya'an");

            customPref = findPreference("suma_multiplicacion_dificil");
            customPref.setTitle("b'ak'xokil yetel yayaab'b'il");

            customPref = findPreference("resta_multiplicacion_dificil");
            customPref.setTitle("Luk'saj yetel yayaab'b'il");

            customPref = findPreference("suma_division_dificil");
            customPref.setTitle("B'ak'xokil yetel junpaya'an");

            customPref = findPreference("resta_division_dificil");
            customPref.setTitle("Luksaj yetel junpaya'an");

            customPref = findPreference("potencia_dificil");
            customPref.setTitle("Potencias");

            customPref = findPreference("raiz_dificil");
            customPref.setTitle("Raiz cuadrada");

            PreferenceCategory prefcat = findPreference(("config"));
            prefcat.setTitle("Configuracionjej ti b'axäl");

            prefcat = findPreference(("ajustes"));
            prefcat.setTitle("Ajustilej ti b'axäl");

            prefcat = findPreference(("modo_pesadilla"));
            prefcat.setTitle("Modojej pesadillajej");

        } else if (sharedPref.getString(Ajustes.idioma, "Español").equals("Q'eqchi'")) {

            Preference customPref = findPreference("modos_juego_pref");
            customPref.setTitle("Xjalb'al li ru re xb'anunkil");
            customPref.setSummary("Naru taatiiru chi rix li eesil");

            customPref = findPreference("sonido_pref");
            customPref.setTitle("Xaab' ");
            customPref.setSummary("Leech lison chiru chixjunil li b'atz'unk");

            customPref = findPreference("idioma_pref");
            customPref.setTitle("Jalb'aal li aatinob'aal");
            customPref.setSummary("Naru taatiiru wi sa' kaxlan aatin, Mayab' itza' ut q'eqchi'");

            customPref = findPreference("tiempo_pref");
            customPref.setTitle("Hoonal");
            customPref.setSummary("Jal liru li k'asal chiru li b'isleb'aal hoonal re b'atz'unk");

            customPref = findPreference("modo_supervivencia_pref");
            customPref.setTitle("Xkawilal aach'ool xkolb0al liru aak'anjel");
            customPref.setSummary("K'a'aj wi naru taasach junaj wi, naq tooj maji' naraq'e li b'atz'unk");

            customPref = findPreference("suma_dificil");
            customPref.setTitle("Roxil Xch'utub'ankil");

            customPref = findPreference("resta_dificil");
            customPref.setTitle("Roxil xsach'b'al");

            customPref = findPreference("multiplicacion_dificil");
            customPref.setTitle("Roxil xpuktasinkil");

            customPref = findPreference("division_dificil");
            customPref.setTitle("Roxil xje'k'inkil");

            customPref = findPreference("suma_multiplicacion_dificil");
            customPref.setTitle("Xch'utub'ankil ut xpuktasinkil");

            customPref = findPreference("resta_multiplicacion_dificil");
            customPref.setTitle("Xjeeb' ut xpuktasinkil");

            customPref = findPreference("suma_division_dificil");
            customPref.setTitle("Xch'utub'ankil ut xje'k'inkil");

            customPref = findPreference("resta_division_dificil");
            customPref.setTitle("Xjeeb' ut xje'k'inkil");

            customPref = findPreference("potencia_dificil");
            customPref.setTitle("Puk'tesinkil");

            customPref = findPreference("raiz_dificil");
            customPref.setTitle("Xk'ab' xputasinkil li ajl");

            PreferenceCategory prefcat = (PreferenceCategory) findPreference(("config"));
            prefcat.setTitle("Xlechb'aal chi xjunil");

            prefcat = findPreference(("ajustes"));
            prefcat.setTitle("Xyib'ankil li ru li xb'atz'unkil");

            prefcat = findPreference(("modo_pesadilla"));
            prefcat.setTitle("Xyib'ankil chi rix limatq'ib'");

        }
    }


}
