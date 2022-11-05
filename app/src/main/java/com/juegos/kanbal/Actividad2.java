package com.juegos.kanbal;

import static android.graphics.Color.GRAY;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
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

import java.util.ArrayList;
import java.util.Random;

import es.dmoral.toasty.Toasty;
import uk.co.deanwild.materialshowcaseview.IShowcaseListener;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;

public class Actividad2 extends AppCompatActivity {
    private String[] txtpunteoTraducir = {"Punteo: 0", "Puntajej: 0", "Xtz'aq: 0"};
    private String[] txtBuscaLaEcuacionCorrectaTraducir = {"Busca la ecuación correcta", "Käxtej a' ecuacionej ma'lo'", "Sik' li tz'aqal li ru"};
    private String[] txtRetroalimentacionTraducir = {"Empieza", "Chu'umpal", "Nara taatikib'"};
    private TextView txtTiempo;
    private TextView txtPunteo;
    private TextView txtRetroalimentacion;
    private TextView totalCorrectos;
    private TextView totalResueltos;
    private TextView txtBuscaLaEcuacionCorrecta;
    private Button boton1, boton2, boton3, boton4;
    private ArrayList<String> listaPreguntas = new ArrayList<String>();
    int lugarRespuestaCorrecta, puntaje = 0, numeroPreguntas = 0, numeroRetroalimentacion, correctoOEquivocado, longitudMusica;
    private CountDownTimer countDownTimer;
    private Animation correctAnimation, feedBackAnimation;
    private Dialog ventanaResultados;
    private MediaPlayer mediaPlayer;
    private Boolean sonido;
    private SharedPreferences scorePreference;
    private Chronometer chronometer;
    SharedPreferences sharedPref;
    private long tiempo = 30000;
    ImageButton botonSonido;

    private Boolean timer;
    private String modo_juego;
    private String preferenciasIdioma;
    operaciones op;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad2);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        op = new operaciones(Actividad2.this);
        tiempo = Long.parseLong(sharedPref.getString(Ajustes.tiempo, "30000")) * 1000;
        timer = Datos_Globales.tiempo;
        scorePreference = getSharedPreferences("highScore", MODE_PRIVATE);
        chronometer = new Chronometer(this);
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();
        txtTiempo = findViewById(R.id.txtTiempo);
        txtPunteo = findViewById(R.id.txtpunteo);
        txtBuscaLaEcuacionCorrecta = findViewById(R.id. txtBuscaLaEcuacionCorrecta);
        boton1 = findViewById(R.id.boton1);
        boton2 = findViewById(R.id.boton2);
        boton3 = findViewById(R.id.boton3);
        boton4 = findViewById(R.id.boton4);
        txtRetroalimentacion = findViewById(R.id.txtRetroalimentacion);
        ventanaResultados = new Dialog(this);
        ventanaResultados.setContentView(R.layout.ventana_resultados);
        ventanaResultados.getWindow().getAttributes().windowAnimations = R.style.ScorePopUpAnimation;
        ventanaResultados.getWindow().setLayout(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT);
        totalCorrectos = ventanaResultados.findViewById(R.id.totalCorrectos);
        totalResueltos = ventanaResultados.findViewById(R.id.totalResueltos);
        botonSonido = findViewById(R.id.muteButton);
        modo_juego = sharedPref.getString("modos_juego_pref", "Normal");

        correctAnimation = AnimationUtils.loadAnimation(this, R.anim.correct_animation);
        feedBackAnimation = AnimationUtils.loadAnimation(this, R.anim.flicker_animation);

        boton1.startAnimation(AnimationUtils.loadAnimation(this, R.anim.from_right_0));
        boton2.startAnimation(AnimationUtils.loadAnimation(this, R.anim.from_right_1));
        boton3.startAnimation(AnimationUtils.loadAnimation(this, R.anim.from_right_2));
        boton4.startAnimation(AnimationUtils.loadAnimation(this, R.anim.from_right_3));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(GRAY);
        }
        sonido = sharedPref.getBoolean(Ajustes.sonido, false);

        preferenciasIdioma = sharedPref.getString(Ajustes.idioma, "Español");

        if (timer)
            timer();
        else {
            if(preferenciasIdioma.equals("Español")) {
                txtTiempo.setText(getString(R.string.tiempodeshabilitadoespañol));
            }
            else if(preferenciasIdioma.equals("Maya Itzá"))
            {
                txtTiempo.setText(getString(R.string.tiempodeshabilitadoitza));
            }
            else if(preferenciasIdioma.equals("Q'eqchi'"))
            {
                txtTiempo.setText(getString(R.string.tiempodeshabilitadoqeqchi));
            }
        }


        if (sharedPref.getBoolean(Ajustes.modo_supervivencia, false) || modo_juego.equals("Pesadilla")) {
            mediaPlayer = MediaPlayer.create(this, R.raw.the_duel);
        }
        else
        {
            mediaPlayer = MediaPlayer.create(this, R.raw.retrosoul);
        }
        mediaPlayer.setLooping(true);
        if (sonido) {
            mediaPlayer.start();
            botonSonido.setImageResource(R.drawable.ic_volume_up_black_24dp);
        } else {
            botonSonido.setImageResource(R.drawable.ic_volume_off_black_24dp);
        }



        generarPreguntas();
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
                sequence.addSequenceItem(txtTiempo, "Bienvenido a la actividad 2! Aqui hay un cronometro donde ves la cantidad de segundos que te quedan", "Siguiente");
            }
            else if(preferenciasIdioma.equals("Maya Itzá"))
            {
                sequence.addSequenceItem(txtTiempo, "Ma'lo atalel tia' peksb'aj 2! Wa'ye' yan junp'ed p'iisk'in tu'ux kacha'antika' segundoje kup'atiltech", "Ulaak'-kutal");
            }
            else if(preferenciasIdioma.equals("Q'eqchi'"))
            {
                sequence.addSequenceItem(txtTiempo, "Sahil ch'oolej xtikib'ankil li xb'een lik'anjel 2! Wayi' jun li xbislek' hoonal rerilb'al jarab' li k'asal wi maji na'osok'", "Jalan chik");

            }

            if(preferenciasIdioma.equals("Español")) {
                sequence.addSequenceItem(boton1, "Responde correctamente", "Siguiente");
            }
            else if(preferenciasIdioma.equals("Maya Itzá"))
            {
                sequence.addSequenceItem(boton1, "Nuktej ma'lo", "Ulaak'-kutal");
            }
            else if(preferenciasIdioma.equals("Q'eqchi'"))
            {
                sequence.addSequenceItem(boton1, "Sumee chi tz'aqal liru", "Jalan chik");

            }

            sequence.start();


        }

    }
    public void traducir() {
        if (sharedPref.getString(Ajustes.idioma, "Español").equals("Español")) {
            txtPunteo.setText(getString(R.string.punteoespañol, puntaje));
            txtBuscaLaEcuacionCorrecta.setText(txtBuscaLaEcuacionCorrectaTraducir[0]);
            txtRetroalimentacion.setText(txtRetroalimentacionTraducir[0]);


        } else if (sharedPref.getString(Ajustes.idioma, "Español").equals("Maya Itzá")) {
            txtPunteo.setText(getString(R.string.punteoitza, puntaje));
            txtBuscaLaEcuacionCorrecta.setText(txtBuscaLaEcuacionCorrectaTraducir[1]);
            txtRetroalimentacion.setText(txtRetroalimentacionTraducir[1]);

        } else if (sharedPref.getString(Ajustes.idioma, "Español").equals("Q'eqchi'")) {
            txtPunteo.setText(getString(R.string.punteoqeqchi, puntaje));
            txtBuscaLaEcuacionCorrecta.setText(txtBuscaLaEcuacionCorrectaTraducir[2]);
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
        ventanaResultados.dismiss();
        mediaPlayer.stop();
        mediaPlayer.release();
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        if (timer) {
            Toasty.warning(getApplicationContext(), "Exiting Timetrials - you cannot leave the app while timer is on", Toast.LENGTH_LONG, true).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
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
                    txtTiempo.startAnimation(AnimationUtils.loadAnimation(Actividad2.this, R.anim.flicker_animation_2));

                } else if (milisRestantes <= tiempo * 0.30) {
                    txtTiempo.startAnimation(AnimationUtils.loadAnimation(Actividad2.this, R.anim.flicker_animation_1));

                } else {
                    if (milisRestantes < tiempo * 0.10) {
                        txtTiempo.startAnimation(AnimationUtils.loadAnimation(Actividad2.this, R.anim.flicker_animation));
                    }
                }
            }

            @Override
            public void onFinish() {
                //Shows user results
                if (modo_juego.equals("Tutorial")) {
                    ShowcaseConfig config = new ShowcaseConfig();
                    final MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(Actividad2.this);
                    sequence.setConfig(config);

                    if(preferenciasIdioma.equals("Español")) {
                        sequence.addSequenceItem(new MaterialShowcaseView.Builder(Actividad2.this)
                                .setTarget(txtTiempo)
                                .setDismissText("siguiente")
                                .setContentText("Oh no! se acabo el tiempo, lo hiciste bien, obtuviste: " + puntaje + " puntos")
                                .setDismissOnTargetTouch(true)
                                .setTargetTouchable(true)
                                .setListener(new IShowcaseListener() {
                                    @Override
                                    public void onShowcaseDisplayed(MaterialShowcaseView showcaseView) {

                                    }

                                    @Override
                                    public void onShowcaseDismissed(MaterialShowcaseView showcaseView) {
                                        mostrarResultados();
                                    }
                                })
                                .show());
                    }
                    else if(preferenciasIdioma.equals("Maya Itzá"))
                    {
                        sequence.addSequenceItem(new MaterialShowcaseView.Builder(Actividad2.this)
                                .setTarget(txtTiempo)
                                .setDismissText("Ulaak'-kutal")
                                .setContentText("Wa ma'! Jo'mij a' tiempojej, tamentaj ma'lo', yanijijtecha: " + puntaje + "puntojej")
                                .setDismissOnTargetTouch(true)
                                .setTargetTouchable(true)
                                .setListener(new IShowcaseListener() {
                                    @Override
                                    public void onShowcaseDisplayed(MaterialShowcaseView showcaseView) {

                                    }

                                    @Override
                                    public void onShowcaseDismissed(MaterialShowcaseView showcaseView) {
                                        mostrarResultados();
                                    }
                                })
                                .show());
                    }
                    else if(preferenciasIdioma.equals("Q'eqchi'"))
                    {
                        sequence.addSequenceItem(new MaterialShowcaseView.Builder(Actividad2.this)
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
                                        mostrarResultados();
                                    }
                                })
                                .show());

                    }



                }
                mostrarResultados();
            }
        }.start();
    }

    public void mostrarResultados() {
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
        startActivity(new Intent(getApplicationContext(), Contador.class));
    }


    public void quit(View view) {
        finish();
    }

    public void generarPreguntas() {
        Random rd = new Random();
        correctoOEquivocado = rd.nextInt(2);
        lugarRespuestaCorrecta = rd.nextInt(4);
        for (int i = 0; i < 4; i++) {
            if(modo_juego.equals("Pesadilla"))
            {
                if (i == lugarRespuestaCorrecta) {
                    listaPreguntas.add(op.preguntaDificil(1));
                } else {
                    listaPreguntas.add(op.preguntaDificil(2));
                }
            }
            else {
                if (i == lugarRespuestaCorrecta) {
                    listaPreguntas.add(op.preguntaNormal(1));
                } else {
                    listaPreguntas.add(op.preguntaNormal(2));
                }
            }

        }
        numeroRetroalimentacion = rd.nextInt(12);
        if(preferenciasIdioma.equals("Español")) {
            txtPunteo.setText(getString(R.string.punteoespañol,puntaje));
        }
        else if(preferenciasIdioma.equals("Maya Itzá"))
        {
            txtPunteo.setText(getString(R.string.punteoitza, puntaje));
        }
        else if(preferenciasIdioma.equals("Q'eqchi'"))
        {
            txtPunteo.setText(getString(R.string.punteoqeqchi, puntaje));
        }
        boton1.setText(listaPreguntas.get(0));
        boton2.setText(listaPreguntas.get(1));
        boton3.setText(listaPreguntas.get(2));
        boton4.setText(listaPreguntas.get(3));
        listaPreguntas.clear();

    }

    public void respuestaUsuario(View view) {
        txtRetroalimentacion.startAnimation(feedBackAnimation);

        if (view.getTag().toString().equals(Integer.toString(lugarRespuestaCorrecta))) {
            puntaje++;
            numeroPreguntas++;
            generarPreguntas();
            Retroalimentacion.retroalimentacionCorrecto(this, txtRetroalimentacion, numeroRetroalimentacion);

        } else {
            numeroPreguntas++;

            if (sharedPref.getBoolean(Ajustes.modo_supervivencia, false)) {
                mostrarResultados();
            }
            if (lugarRespuestaCorrecta == 0) {
                boton1.startAnimation(correctAnimation);
            } else if (lugarRespuestaCorrecta == 1) {
                boton2.startAnimation(correctAnimation);
            } else if (lugarRespuestaCorrecta == 2) {
                boton3.startAnimation(correctAnimation);
            } else if (lugarRespuestaCorrecta == 3) {
                boton4.startAnimation(correctAnimation);
            }

            Retroalimentacion.retroalimentacionIncorrecto(this, txtRetroalimentacion, numeroRetroalimentacion);
            generarPreguntas();


        }
    }

}