package com.juegos.kanbal;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.Random;

import es.dmoral.toasty.Toasty;
import uk.co.deanwild.materialshowcaseview.IShowcaseListener;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;


public class Actividad4 extends AppCompatActivity {
    private String[] txtpunteoTraducir = {"Punteo: 0", "Puntajej: 0", "Xtz'aq: 0"};
    private String[] txtRetroalimentacionTraducir = {"Empieza", "Chu'umpal", "Nara taatikib'"};
    private Button boton1, boton2, boton3, boton4;
    private TextView txtPregunta, txtpunteo, txtSeAcaboElTiempo, totalCorrectos, totalResueltos;
    private int lugarRespuestaCorrecta, puntaje = 0, longitudMusica = 0;
    private ArrayList<Integer> listaRespuestas = new ArrayList<Integer>();
    private Animation buttonsInit;
    private Dialog ventanaResultados;
    private TextView txtTiempo;
    private Chronometer scoreChronometer;
    private Boolean sonido;
    private CountDownTimer countDownTimer;
    private MediaPlayer mediaPlayer;
    SharedPreferences sharedPref;
    ImageButton botonSonido;
    String modo_juego;
    private long tiempo = 10000;
    private boolean timer;
    private int numeroRetroalimentacion;
    private TextView txtRetroalimentacion;
    private String preferenciasIdioma;
    operaciones op;
    private int numeroPreguntas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad4);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        modo_juego = sharedPref.getString("modos_juego_pref", "Normal");
        op = new operaciones(this);
        scoreChronometer = new Chronometer(this);
        scoreChronometer.setBase(SystemClock.elapsedRealtime());
        scoreChronometer.start();
        boton1 = findViewById(R.id.boton1);
        boton2 = findViewById(R.id.boton2);
        boton3 = findViewById(R.id.boton3);
        boton4 = findViewById(R.id.boton4);
        txtPregunta = findViewById(R.id.txtPregunta);
        txtpunteo = findViewById(R.id.txtpunteo);
        txtRetroalimentacion = findViewById(R.id.txtRetroalimentacion);
        ventanaResultados = new Dialog(this);
        ventanaResultados.setContentView(R.layout.ventana_resultados);
        ventanaResultados.getWindow().getAttributes().windowAnimations = R.style.ScorePopUpAnimation;
        ventanaResultados.getWindow().setLayout(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT);
        txtSeAcaboElTiempo = ventanaResultados.findViewById(R.id.txtSeAcaboElTiempo);
        totalCorrectos = ventanaResultados.findViewById(R.id.totalCorrectos);
        totalResueltos = ventanaResultados.findViewById(R.id.totalResueltos);
        txtTiempo = findViewById(R.id.txtTiempo);
        botonSonido = findViewById(R.id.muteButton);
        buttonsInit = AnimationUtils.loadAnimation(this, R.anim.advanced_init);

        sonido = sharedPref.getBoolean(Ajustes.sonido, false);
        tiempo = Long.parseLong(sharedPref.getString(Ajustes.tiempo, "30000")) * 1000;
        timer = Datos_Globales.tiempo;
        preferenciasIdioma = sharedPref.getString(Ajustes.idioma, "Español");




        if (sharedPref.getBoolean(Ajustes.modo_supervivencia, false) || modo_juego.equals("Pesadilla")) {
            mediaPlayer = MediaPlayer.create(this, R.raw.the_duel);
        }
        else
        {
            mediaPlayer = MediaPlayer.create(this, R.raw.up_your_street);
        }
        mediaPlayer.setLooping(true);
        if (sonido) {
            mediaPlayer.start();
            botonSonido.setImageResource(R.drawable.ic_volume_up_black_24dp);
        } else {
            botonSonido.setImageResource(R.drawable.ic_volume_off_black_24dp);
        }

        generarPregunta();

        if (timer) {
            txtTiempo.setTextSize(25);
            timer();
        } else {
            txtTiempo.setTextSize(12);
            if(preferenciasIdioma.equals("Español")) {
                txtTiempo.setText(getString(R.string.tiempodeshabilitadoespañol2));
            }
            else if(preferenciasIdioma.equals("Maya Itzá"))
            {
                txtTiempo.setText(getString(R.string.tiempodeshabilitadoitza2));
            }
            else if(preferenciasIdioma.equals("Q'eqchi'"))
            {
                txtTiempo.setText(getString(R.string.tiempodeshabilitadoqeqchi2));
            }        }

        traducir();

        if (modo_juego.equals("Tutorial")) {
            tiempo = 999999;
            timer();
            ShowcaseConfig config = new ShowcaseConfig();
            final MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(this);
            config.setMaskColor(getResources().getColor(R.color.colorAccent50));
            config.setRenderOverNavigationBar(true);
            config.setShapePadding(50);
            config.setDelay(500);
            sequence.setConfig(config);


            if(preferenciasIdioma.equals("Español")) {
                sequence.addSequenceItem(txtTiempo, "Bienvenido a la actividad 4! Aquí hay un cronómetro donde ves la cantidad de segundos que te quedan", "Siguiente");
            }
            else if(preferenciasIdioma.equals("Maya Itzá"))
            {
                sequence.addSequenceItem(txtTiempo, "Ma'lo atalel tia' peksb'aj 4! Wa'ye' yan junp'ed p'iisk'in tu'ux kacha'antika' segundoje kup'atiltech", "Ulaak'-kutal");
            }
            else if(preferenciasIdioma.equals("Q'eqchi'"))
            {
                sequence.addSequenceItem(txtTiempo, "Sahil ch'oolej xtikib'ankil li xb'een lik'anjel 4! Wayi' jun li xbislek' hoonal rerilb'al jarab' li k'asal wi maji na'osok'", "Jalan chik");
            }
            if(preferenciasIdioma.equals("Español")) {
                sequence.addSequenceItem(new MaterialShowcaseView.Builder(this)
                        .setTarget(txtPregunta)
                        .setDismissText("Siguiente")
                        .setContentText("Aquí se encuentra la ecuación que debes resolver")
                        .setDismissOnTargetTouch(true)
                        .setTargetTouchable(true)
                        .withRectangleShape()
                        .build());            }
            else if(preferenciasIdioma.equals("Maya Itzá"))
            {
                sequence.addSequenceItem(new MaterialShowcaseView.Builder(this)
                        .setTarget(txtPregunta)
                        .setDismissText("Ulaak'-kutal")
                        .setContentText("Wa'ye' yan a'ecuacionej yan ameyajtej")
                        .setDismissOnTargetTouch(true)
                        .setTargetTouchable(true)
                        .withRectangleShape()
                        .build());            }
            else if(preferenciasIdioma.equals("Q'eqchi'"))
            {
                sequence.addSequenceItem(new MaterialShowcaseView.Builder(this)
                        .setTarget(txtPregunta)
                        .setDismissText("Jalan chik")
                        .setContentText("Arin wan li k'anjel li taasume iru")
                        .setDismissOnTargetTouch(true)
                        .setTargetTouchable(true)
                        .withRectangleShape()
                        .build());            }




            if(preferenciasIdioma.equals("Español")) {
                switch (lugarRespuestaCorrecta) {
                    case 0:
                        sequence.addSequenceItem(
                                new MaterialShowcaseView.Builder(Actividad4.this)
                                        .setTarget(boton1)
                                        .setContentText("Te ayudaré con la primer pregunta. El resultado es: " + boton1.getText() + ". Presiona el botón para seleccionar la respuesta")
                                        .setMaskColour(getResources().getColor(R.color.colorAccent50))
                                        .withRectangleShape()
                                        .setDismissOnTargetTouch(true)
                                        .setTargetTouchable(true)
                                        .build()
                        );
                        break;
                    case 1:
                        sequence.addSequenceItem(
                                new MaterialShowcaseView.Builder(Actividad4.this)
                                        .setTarget(boton2)
                                        .setContentText("Te ayudaré con la primer pregunta. El resultado es: " + boton2.getText() + ". Presiona el botón para seleccionar la respuesta")
                                        .setMaskColour(getResources().getColor(R.color.colorAccent50))
                                        .withRectangleShape()
                                        .setDismissOnTargetTouch(true)
                                        .setTargetTouchable(true)
                                        .build()
                        );
                        break;
                    case 2:
                        sequence.addSequenceItem(
                                new MaterialShowcaseView.Builder(Actividad4.this)
                                        .setTarget(boton3)
                                        .setContentText("Te ayudaré con la primer pregunta. El resultado es: " + boton3.getText() + ". Presiona el botón para seleccionar la respuesta")
                                        .setMaskColour(getResources().getColor(R.color.colorAccent50))
                                        .withRectangleShape()
                                        .setDismissOnTargetTouch(true)
                                        .setTargetTouchable(true)
                                        .build()
                        );
                        break;
                    case 3:
                        sequence.addSequenceItem(
                                new MaterialShowcaseView.Builder(Actividad4.this)
                                        .setTarget(boton4)
                                        .setContentText("Te ayudaré con la primer pregunta. El resultado es: " + boton4.getText() + ". Presiona el botón para seleccionar la respuesta")
                                        .setMaskColour(getResources().getColor(R.color.colorAccent50))
                                        .withRectangleShape()
                                        .setDismissOnTargetTouch(true)
                                        .setTargetTouchable(true)
                                        .build()
                        );
                        break;
                }
                sequence.addSequenceItem(new MaterialShowcaseView.Builder(Actividad4.this)
                        .setTarget(txtpunteo)
                        .setDismissText("Siguiente")
                        .setContentText("Aquí puedes ver tu punteo, por el momento llevas 1. Ahora que ya sabes lo básico puedes empezar por tu cuenta.")
                        .setDismissOnTargetTouch(true)
                        .setTargetTouchable(true)
                        .withRectangleShape()
                        .setListener(new IShowcaseListener() {
                            @Override
                            public void onShowcaseDisplayed(MaterialShowcaseView showcaseView) {

                            }

                            @Override
                            public void onShowcaseDismissed(MaterialShowcaseView showcaseView) {
                                countDownTimer.cancel();
                                tiempo = 10000;
                                timer();
                            }
                        })
                        .build());            }
            else if(preferenciasIdioma.equals("Maya Itzá"))
            {
                switch (lugarRespuestaCorrecta) {
                    case 0:
                        sequence.addSequenceItem(
                                new MaterialShowcaseView.Builder(Actividad4.this)
                                        .setTarget(boton1)
                                        .setContentText("Inka'aj inwantech etel uyaxk'atal. A' resultadoje a'lo': " + boton1.getText() + ". Presiona a' botonej ti seleccionarje a' nunktal")
                                        .setMaskColour(getResources().getColor(R.color.colorAccent50))
                                        .withRectangleShape()
                                        .setDismissOnTargetTouch(true)
                                        .setTargetTouchable(true)
                                        .build()
                        );
                        break;
                    case 1:
                        sequence.addSequenceItem(
                                new MaterialShowcaseView.Builder(Actividad4.this)
                                        .setTarget(boton2)
                                        .setContentText("Inka'aj inwantech etel uyaxk'atal. A' resultadoje a'lo': " + boton2.getText() + ". Presiona a' botonej ti seleccionarje a' nunktal")
                                        .setMaskColour(getResources().getColor(R.color.colorAccent50))
                                        .withRectangleShape()
                                        .setDismissOnTargetTouch(true)
                                        .setTargetTouchable(true)
                                        .build()
                        );
                        break;
                    case 2:
                        sequence.addSequenceItem(
                                new MaterialShowcaseView.Builder(Actividad4.this)
                                        .setTarget(boton3)
                                        .setContentText("Inka'aj inwantech etel uyaxk'atal. A' resultadoje a'lo': " + boton3.getText() + ". Presiona a' botonej ti seleccionarje a' nunktal")
                                        .setMaskColour(getResources().getColor(R.color.colorAccent50))
                                        .withRectangleShape()
                                        .setDismissOnTargetTouch(true)
                                        .setTargetTouchable(true)
                                        .build()
                        );
                        break;
                    case 3:
                        sequence.addSequenceItem(
                                new MaterialShowcaseView.Builder(Actividad4.this)
                                        .setTarget(boton4)
                                        .setContentText("Inka'aj inwantech etel uyaxk'atal. A' resultadoje a'lo': " + boton4.getText() + ".  Presiona a' botonej ti seleccionarje a' nunktal")
                                        .setMaskColour(getResources().getColor(R.color.colorAccent50))
                                        .withRectangleShape()
                                        .setDismissOnTargetTouch(true)
                                        .setTargetTouchable(true)
                                        .build()
                        );
                        break;
                }
                sequence.addSequenceItem(new MaterialShowcaseView.Builder(Actividad4.this)
                        .setTarget(txtpunteo)
                        .setDismissText("Ulaak'-kutal")
                        .setContentText("Wa'ye' patal acha'antik apuntajej, b'aje'laj kab'ensik 1. B'aje'laj awojel a' b'asico patal achu'umpal chen intech")
                        .setDismissOnTargetTouch(true)
                        .setTargetTouchable(true)
                        .withRectangleShape()
                        .setListener(new IShowcaseListener() {
                            @Override
                            public void onShowcaseDisplayed(MaterialShowcaseView showcaseView) {

                            }

                            @Override
                            public void onShowcaseDismissed(MaterialShowcaseView showcaseView) {
                                countDownTimer.cancel();
                                tiempo = 10000;
                                timer();
                            }
                        })
                        .build());            }
            else if(preferenciasIdioma.equals("Q'eqchi'"))
            {
                switch (lugarRespuestaCorrecta) {
                    case 0:
                        sequence.addSequenceItem(
                                new MaterialShowcaseView.Builder(Actividad4.this)
                                        .setTarget(boton1)
                                        .setContentText("Taatintenq'a chitix li xb'een li patz'om. Lixsumenkil a'an: " + boton1.getText() + ". Nat' chikaw li eetalil chi rix li xsumenkil")
                                        .setMaskColour(getResources().getColor(R.color.colorAccent50))
                                        .withRectangleShape()
                                        .setDismissOnTargetTouch(true)
                                        .setTargetTouchable(true)
                                        .build()
                        );
                        break;
                    case 1:
                        sequence.addSequenceItem(
                                new MaterialShowcaseView.Builder(Actividad4.this)
                                        .setTarget(boton2)
                                        .setContentText("Taatintenq'a chitix li xb'een li patz'om. Lixsumenkil a'an: " + boton2.getText() + ". Nat' chikaw li eetalil chi rix li xsumenkil")
                                        .setMaskColour(getResources().getColor(R.color.colorAccent50))
                                        .withRectangleShape()
                                        .setDismissOnTargetTouch(true)
                                        .setTargetTouchable(true)
                                        .build()
                        );
                        break;
                    case 2:
                        sequence.addSequenceItem(
                                new MaterialShowcaseView.Builder(Actividad4.this)
                                        .setTarget(boton3)
                                        .setContentText("Taatintenq'a chitix li xb'een li patz'om. Lixsumenkil a'an: " + boton3.getText() + ". Nat' chikaw li eetalil chi rix li xsumenkil")
                                        .setMaskColour(getResources().getColor(R.color.colorAccent50))
                                        .withRectangleShape()
                                        .setDismissOnTargetTouch(true)
                                        .setTargetTouchable(true)
                                        .build()
                        );
                        break;
                    case 3:
                        sequence.addSequenceItem(
                                new MaterialShowcaseView.Builder(Actividad4.this)
                                        .setTarget(boton4)
                                        .setContentText("Taatintenq'a chitix li xb'een li patz'om. Lixsumenkil a'an: " + boton4.getText() + ". Nat' chikaw li eetalil chi rix li xsumenkil")
                                        .setMaskColour(getResources().getColor(R.color.colorAccent50))
                                        .withRectangleShape()
                                        .setDismissOnTargetTouch(true)
                                        .setTargetTouchable(true)
                                        .build()
                        );
                        break;
                }
                sequence.addSequenceItem(new MaterialShowcaseView.Builder(Actividad4.this)
                        .setTarget(txtpunteo)
                        .setDismissText("Jalan chik")
                        .setContentText("Arin nara taawil jarub' xaawisi, tooja' jun najab'eres. Anaqwan xaaq'e reetal naq naru taatikib' aajones")
                        .setDismissOnTargetTouch(true)
                        .setTargetTouchable(true)
                        .withRectangleShape()
                        .setListener(new IShowcaseListener() {
                            @Override
                            public void onShowcaseDisplayed(MaterialShowcaseView showcaseView) {

                            }

                            @Override
                            public void onShowcaseDismissed(MaterialShowcaseView showcaseView) {
                                countDownTimer.cancel();
                                tiempo = 10000;
                                timer();
                            }
                        })
                        .build());            }




            sequence.start();


        }


    }

    public void traducir() {
        if (sharedPref.getString(Ajustes.idioma, "Español").equals("Español")) {
            txtpunteo.setText(txtpunteoTraducir[0]);
            txtRetroalimentacion.setText(txtRetroalimentacionTraducir[0]);

        } else if (sharedPref.getString(Ajustes.idioma, "Español").equals("Maya Itzá")) {
            txtpunteo.setText(txtpunteoTraducir[1]);
            txtRetroalimentacion.setText(txtRetroalimentacionTraducir[1]);

        } else if (sharedPref.getString(Ajustes.idioma, "Español").equals("Q'eqchi'")) {
            txtpunteo.setText(txtpunteoTraducir[2]);
            txtRetroalimentacion.setText(txtRetroalimentacionTraducir[2]);
        }
    }




    public void muteTemp(View view) {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            sharedPref.edit().putBoolean(Ajustes.sonido, false).apply();
            botonSonido.setImageResource(R.drawable.ic_volume_off_black_24dp);
        } else {
            mediaPlayer.start();
            sharedPref.edit().putBoolean(Ajustes.sonido, true).apply();
            botonSonido.setImageResource(R.drawable.ic_volume_up_black_24dp);
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer.isPlaying() && sonido) {
            longitudMusica = mediaPlayer.getCurrentPosition();
            mediaPlayer.pause();
        }
        if (timer) {
            finish();
        }

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (!mediaPlayer.isPlaying() && sonido) {
            mediaPlayer.seekTo(longitudMusica);
            mediaPlayer.start();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        mediaPlayer.release();
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        Toasty.warning(getApplicationContext(), "Exiting Actividad 4 - you cannot leave the app while in a session", Toast.LENGTH_LONG, true).show();

    }

    public void showPopUp() {
        ventanaResultados.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ventanaResultados.setCanceledOnTouchOutside(false);
        ventanaResultados.show();
        totalCorrectos.setText(Integer.toString(puntaje));
        totalResueltos.setText(Integer.toString(numeroPreguntas));
        Button botonjugardenuevo = ventanaResultados.findViewById(R.id.botonJugarDeNuevo);
        Button botonmenu = ventanaResultados.findViewById(R.id.botonMenu);
        TextView txtseacaboeltiempo = ventanaResultados.findViewById(R.id.txtSeAcaboElTiempo);
        TextView txtejercicioscorrectos = ventanaResultados.findViewById(R.id.txtEjerciciosCorrectos);
        TextView txtejerciciosresueltos = ventanaResultados.findViewById(R.id.txtEjerciciosResueltos);

        String[] botonjugardenuevoTraducir = {"Jugar de nuevo", "B'axäl tuka'ye'", "Q'Xtikib'ankil li xb'atz'unkil'"};
        String[] botonmenuTraducir = {"Menú", "Suut", "xb'eresinkil li xtoch'b'al"};
        String[] txtseacaboeltiempoTraducir = {"Se acabó el tiempo", "Jo'mij a tiempoje", "x'ooso' li hoonal"};
        String[] txtejercicioscorrectosTraducir = {"Ejercicios correctos", "Utulakal a' meyaj ma'lo'", "Li tz'aqal li k'anjel tz'qal li ru"};
        String[] txtejerciciosresueltosTraducir = {"Ejercicios resueltos", "Utulakal a' meyaj meyajnaja'an", "Li tz'aqal li k'anjel xb'anuman"};

        if(preferenciasIdioma.equals("Español")) {
            botonjugardenuevo.setText(botonjugardenuevoTraducir[0]);
            botonmenu.setText(botonmenuTraducir[0]);
            txtseacaboeltiempo.setText(txtseacaboeltiempoTraducir[0]);
            txtejercicioscorrectos.setText(txtejercicioscorrectosTraducir[0]);
            txtejerciciosresueltos.setText(txtejerciciosresueltosTraducir[0]);
        }
        else if(preferenciasIdioma.equals("Maya Itzá"))
        {
            botonjugardenuevo.setText(botonjugardenuevoTraducir[1]);
            botonmenu.setText(botonmenuTraducir[1]);
            txtseacaboeltiempo.setText(txtseacaboeltiempoTraducir[1]);
            txtejercicioscorrectos.setText(txtejercicioscorrectosTraducir[1]);
            txtejerciciosresueltos.setText(txtejerciciosresueltosTraducir[1]);
        }
        else if(preferenciasIdioma.equals("Q'eqchi'"))
        {
            botonjugardenuevo.setText(botonjugardenuevoTraducir[2]);
            botonmenu.setText(botonmenuTraducir[2]);
            txtseacaboeltiempo.setText(txtseacaboeltiempoTraducir[2]);
            txtejercicioscorrectos.setText(txtejercicioscorrectosTraducir[2]);
            txtejerciciosresueltos.setText(txtejerciciosresueltosTraducir[2]);
        }

        ventanaResultados.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                finish();
            }
        });
    }

    public void playAgain(View view) {
        finish();
        startActivity(new Intent(getApplicationContext(), Actividad4.class));
    }

    public void quit(View view) {
        finish();
    }

    public void choose(View view) {
        if (view.getTag().toString().equals(Integer.toString(lugarRespuestaCorrecta))) {
            puntaje++;
            generarPregunta();
            numeroPreguntas++;

            Retroalimentacion.retroalimentacionCorrecto(this, txtRetroalimentacion, numeroRetroalimentacion);

        } else {
            numeroPreguntas++;
            txtpunteo.startAnimation(AnimationUtils.loadAnimation(this, R.anim.correct_animation));
            if (sharedPref.getBoolean(Ajustes.modo_supervivencia, false)) {
                showPopUp();
            }
            if (lugarRespuestaCorrecta == 0) {
                boton1.startAnimation(AnimationUtils.loadAnimation(this, R.anim.correct_animation));
            } else if (lugarRespuestaCorrecta == 1) {
                boton2.startAnimation(AnimationUtils.loadAnimation(this, R.anim.correct_animation));
            } else if (lugarRespuestaCorrecta == 2) {
                boton3.startAnimation(AnimationUtils.loadAnimation(this, R.anim.correct_animation));
            } else if (lugarRespuestaCorrecta == 3) {
                boton4.startAnimation(AnimationUtils.loadAnimation(this, R.anim.correct_animation));
            }
            Random rd = new Random();
            Retroalimentacion.retroalimentacionIncorrecto(this, txtRetroalimentacion, numeroRetroalimentacion);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    generarPregunta();
                }
            }, 1000);
        }
        if(preferenciasIdioma.equals("Español")) {
            txtpunteo.setText(getString(R.string.punteoespañol, puntaje));
        }
        else if(preferenciasIdioma.equals("Maya Itzá"))
        {
            txtpunteo.setText(getString(R.string.punteoitza, puntaje));
        }
        else if(preferenciasIdioma.equals("Q'eqchi'"))
        {
            txtpunteo.setText(getString(R.string.punteoqeqchi, puntaje));
        }
        txtPregunta.startAnimation(AnimationUtils.loadAnimation(this, R.anim.question_flicker));

    }

    public void generarPregunta() {
        Random rd = new Random();
        listaRespuestas.clear();
        boton1.startAnimation(buttonsInit);
        boton2.startAnimation(AnimationUtils.loadAnimation(this, R.anim.advanced_init_1));
        boton3.startAnimation(AnimationUtils.loadAnimation(this, R.anim.advanced_init_2));
        boton4.startAnimation(AnimationUtils.loadAnimation(this, R.anim.advanced_init_3));
        numeroRetroalimentacion = rd.nextInt(12);


        if(modo_juego.equals("Pesadilla"))
        {
            txtPregunta.setText(op.preguntaDificil(4));

        }
        else {
            txtPregunta.setText(op.preguntaNormal(4));

        }
        lugarRespuestaCorrecta = rd.nextInt(4);
        int contador = 0;
        for (int i = 0; i < 4; i++) {
            if (i == lugarRespuestaCorrecta) {
                listaRespuestas.add(op.respuestaCorrecta);
            } else {
                listaRespuestas.add(op.listaRespuestasIncorrectas.get(contador));
                contador++;
            }
        }
        boton1.setText(getString(R.string.numero, listaRespuestas.get(0)));
        boton2.setText(getString(R.string.numero, listaRespuestas.get(1)));
        boton3.setText(getString(R.string.numero, listaRespuestas.get(2)));
        boton4.setText(getString(R.string.numero, listaRespuestas.get(3)));

    }

    public void timer() {
        countDownTimer = new CountDownTimer(tiempo, 1000) {

            @Override
            public void onTick(long milisRestantes) {
                if(preferenciasIdioma.equals("Español")) {
                    txtTiempo.setText(getString(R.string.tiempoespañol, (int) milisRestantes / 1000));
                }
                else if(preferenciasIdioma.equals("Maya Itzá"))
                {
                    txtTiempo.setText(getString(R.string.tiempoitza, (int) milisRestantes / 1000));
                }
                else if(preferenciasIdioma.equals("Q'eqchi'"))
                {
                    txtTiempo.setText(getString(R.string.tiempoqeqchi, (int) milisRestantes / 1000));

                }

                if (milisRestantes <= tiempo * 0.50) {
                    txtTiempo.startAnimation(AnimationUtils.loadAnimation(Actividad4.this, R.anim.flicker_animation_2));

                } else if (milisRestantes <= tiempo * 0.30) {
                    txtTiempo.startAnimation(AnimationUtils.loadAnimation(Actividad4.this, R.anim.flicker_animation_1));

                } else {
                    if (milisRestantes < tiempo * 0.10) {
                        txtTiempo.startAnimation(AnimationUtils.loadAnimation(Actividad4.this, R.anim.flicker_animation));
                    }
                }
            }

            @Override
            public void onFinish() {
                if (modo_juego.equals("Tutorial")) {
                    ShowcaseConfig config = new ShowcaseConfig();
                    final MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(Actividad4.this);
                    sequence.setConfig(config);

                    if(preferenciasIdioma.equals("Español")) {
                        sequence.addSequenceItem(new MaterialShowcaseView.Builder(Actividad4.this)
                                .setTarget(txtTiempo)
                                .setDismissText("Siguiente")
                                .setContentText("Oh no! se acabó el tiempo, lo hiciste bien, obtuviste: " + puntaje + " puntos")
                                .setDismissOnTargetTouch(true)
                                .setTargetTouchable(true)
                                .setListener(new IShowcaseListener() {
                                    @Override
                                    public void onShowcaseDisplayed(MaterialShowcaseView showcaseView) {

                                    }

                                    @Override
                                    public void onShowcaseDismissed(MaterialShowcaseView showcaseView) {
                                        showPopUp();
                                    }
                                })
                                .show());
                    }
                    else if(preferenciasIdioma.equals("Maya Itzá"))
                    {
                        sequence.addSequenceItem(new MaterialShowcaseView.Builder(Actividad4.this)
                                .setTarget(txtTiempo)
                                .setDismissText("Ulaak'-kutal")
                                .setContentText("Wa ma'! Jo'mij a' tiempojej, tamentaj ma'lo', yanijijtecha: " + puntaje + " puntojej")
                                .setDismissOnTargetTouch(true)
                                .setTargetTouchable(true)
                                .setListener(new IShowcaseListener() {
                                    @Override
                                    public void onShowcaseDisplayed(MaterialShowcaseView showcaseView) {

                                    }

                                    @Override
                                    public void onShowcaseDismissed(MaterialShowcaseView showcaseView) {
                                        showPopUp();
                                    }
                                })
                                .show());
                    }
                    else if(preferenciasIdioma.equals("Q'eqchi'"))
                    {
                        sequence.addSequenceItem(new MaterialShowcaseView.Builder(Actividad4.this)
                                .setTarget(txtTiempo)
                                .setDismissText("Jalan chik")
                                .setContentText("Aah ink'a'b xoso' li hoonal, xaayib' chi chaab'il, xaatz'aq: " + puntaje + " xtz'aq")
                                .setDismissOnTargetTouch(true)
                                .setTargetTouchable(true)
                                .setListener(new IShowcaseListener() {
                                    @Override
                                    public void onShowcaseDisplayed(MaterialShowcaseView showcaseView) {

                                    }

                                    @Override
                                    public void onShowcaseDismissed(MaterialShowcaseView showcaseView) {
                                        showPopUp();
                                    }
                                })
                                .show());                    }


                }
                showPopUp();
            }
        }.start();
    }


}
