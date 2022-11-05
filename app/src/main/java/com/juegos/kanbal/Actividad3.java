package com.juegos.kanbal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import es.dmoral.toasty.Toasty;
import uk.co.deanwild.materialshowcaseview.IShowcaseListener;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;

public class Actividad3 extends AppCompatActivity {
    private String[] txtpunteoTraducir = {"Punteo: 0", "Puntajej: 0", "Xtz'aq: 0"};
    private String[] btnResponderTraducir = {"Responder", "Nuktal", "Sumee"};
    private String[] txtRetroalimentacionTraducir = {"Empieza", "Chu'umpal", "Nara taatikib'"};
    private TextView txtPregunta, txtpunteo, txtTiempo;
    private TextView totalCorrectos, totalResueltos, txtRetroalimentacion;
    private Button btnResponder;
    private int punteo, numeroPreguntas, numeroRetroalimentacion, longitudMusica;
    private Dialog ventanaResultados;
    private Animation correctAnimation;
    private Boolean timer, sonido;
    operaciones op;
    private MediaPlayer mediaPlayer;
    private CountDownTimer countDownTimer;
    private EditText respuestaUsuario;
    SharedPreferences sharedPref;
    ImageButton botonSonido;
    private long tiempo = 30000;
    private String modo_juego;
    private String preferenciasIdioma;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad3);
        op = new operaciones(this);



        txtPregunta = findViewById(R.id.txtPregunta);
        txtpunteo = findViewById(R.id.txtpunteo);
        btnResponder = findViewById(R.id.btnResponder);
        ventanaResultados = new Dialog(this);
        ventanaResultados.setContentView(R.layout.ventana_resultados);
        ventanaResultados.getWindow().getAttributes().windowAnimations = R.style.ScorePopUpAnimation;
        ventanaResultados.getWindow().setLayout(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT);
        totalCorrectos = ventanaResultados.findViewById(R.id.totalCorrectos);
        totalResueltos = ventanaResultados.findViewById(R.id.totalResueltos);
        txtRetroalimentacion = findViewById(R.id.txtRetroalimentacion);
        botonSonido = findViewById(R.id.muteButton);
        correctAnimation = AnimationUtils.loadAnimation(this, R.anim.correct_animation);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        respuestaUsuario = findViewById(R.id.respuestaUsuario);
        txtTiempo = findViewById(R.id.txtTiempo);
        preferenciasIdioma = sharedPref.getString(Ajustes.idioma, "Español");
        tiempo = Long.parseLong(sharedPref.getString(Ajustes.tiempo, "30000")) * 1000;
        timer = Datos_Globales.tiempo;
        modo_juego = sharedPref.getString("modos_juego_pref", "Normal");

        sonido = sharedPref.getBoolean(Ajustes.sonido, false);

        generarPregunta();
        if (timer) {
            timer();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                ConstraintLayout constraintLayout = findViewById(R.id.quickMathBg);
                TransitionDrawable transitionDrawable = (TransitionDrawable) constraintLayout.getBackground();

                @Override
                public void run() {
                    transitionDrawable.startTransition(10000);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(Color.RED);
                    }
                }
            }, 15000);
        } else {

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
            mediaPlayer = MediaPlayer.create(this, R.raw.littleidea);
        }
        mediaPlayer.setLooping(true);
        if (sonido) {
            mediaPlayer.start();
            botonSonido.setImageResource(R.drawable.ic_volume_up_black_24dp);
        } else {
            botonSonido.setImageResource(R.drawable.ic_volume_off_black_24dp);
        }

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
                sequence.addSequenceItem(txtTiempo, "Bienvenido a la actividad 3! Aquí hay un cronómetro donde ves la cantidad de segundos que te quedan", "Siguiente");
            }
            else if(preferenciasIdioma.equals("Maya Itzá"))
            {
                sequence.addSequenceItem(txtTiempo, "Ma'lo atalel tia' peksb'aj 3! Wa'ye' yan junp'ed p'iisk'in tu'ux kacha'antika' segundoje kup'atiltech", "Ulaak'-kutal");
            }
            else if(preferenciasIdioma.equals("Q'eqchi'"))
            {
                sequence.addSequenceItem(txtTiempo, "Sahil ch'oolej xtikib'ankil li xb'een lik'anjel 3! Wayi' jun li xbislek' hoonal rerilb'al jarab' li k'asal wi maji na'osok'", "Jalan chik");

            }

            if(preferenciasIdioma.equals("Español")) {
                sequence.addSequenceItem(new MaterialShowcaseView.Builder(this)
                        .setTarget(txtPregunta)
                        .setDismissText("Siguiente")
                        .setContentText("Aquí se encuentra la ecuación que debes resolver")
                        .setDismissOnTargetTouch(true)
                        .setTargetTouchable(true)
                        .withRectangleShape()
                        .build());

                sequence.start();
            }
            else if(preferenciasIdioma.equals("Maya Itzá"))
            {
                sequence.addSequenceItem(new MaterialShowcaseView.Builder(this)
                        .setTarget(txtPregunta)
                        .setDismissText("Ulaak'-kutal")
                        .setContentText("Wa'ye' yan a'ecuacionej yan ameyajtej")
                        .setDismissOnTargetTouch(true)
                        .setTargetTouchable(true)
                        .withRectangleShape()
                        .build());

                sequence.start();
            }
            else if(preferenciasIdioma.equals("Q'eqchi'"))
            {
                sequence.addSequenceItem(new MaterialShowcaseView.Builder(this)
                        .setTarget(txtPregunta)
                        .setDismissText("Jalan chik")
                        .setContentText("Arin wan li k'anjel li taasume iru")
                        .setDismissOnTargetTouch(true)
                        .setTargetTouchable(true)
                        .withRectangleShape()
                        .build());

                sequence.start();
            }



        }


    }

    public void traducir() {
        if (sharedPref.getString(Ajustes.idioma, "Español").equals("Español")) {
            txtpunteo.setText(txtpunteoTraducir[0]);
            btnResponder.setText(btnResponderTraducir[0]);
            txtRetroalimentacion.setText(txtRetroalimentacionTraducir[0]);

        } else if (sharedPref.getString(Ajustes.idioma, "Español").equals("Maya Itzá")) {
            txtpunteo.setText(txtpunteoTraducir[1]);
            btnResponder.setText(btnResponderTraducir[1]);
            txtRetroalimentacion.setText(txtRetroalimentacionTraducir[1]);

        } else if (sharedPref.getString(Ajustes.idioma, "Español").equals("Q'eqchi'")) {
            txtpunteo.setText(txtpunteoTraducir[2]);
            btnResponder.setText(btnResponderTraducir[2]);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
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
        ventanaResultados.dismiss();
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        if (timer) {
            if(preferenciasIdioma.equals("Español")) {
                Toasty.warning(getApplicationContext(), "No puedes salir de la app mientras el tiempo este activo", Toast.LENGTH_LONG, true).show();
            }
            else if(preferenciasIdioma.equals("Maya Itzá"))
            {
                Toasty.warning(getApplicationContext(), "Ma' patal ajok'ol ti a'app ka'a'tiempojej tan umanil", Toast.LENGTH_LONG, true).show();

            }
            else if(preferenciasIdioma.equals("Q'eqchi'"))
            {
                Toasty.warning(getApplicationContext(), "Ink'a' nakaru xch'upb'al naq tooj maji naraq'e' li hoonal", Toast.LENGTH_LONG, true).show();

            }
        }
    }


    public void mostrarResultados() {
        ventanaResultados.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ventanaResultados.setCanceledOnTouchOutside(false);
        ventanaResultados.show();
        totalCorrectos.setText(Integer.toString(punteo));
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


    public void respuestaUsuario(final View view) {
        txtRetroalimentacion.startAnimation(AnimationUtils.loadAnimation(this, R.anim.flicker_animation));

        String data = respuestaUsuario.getText().toString();
        if(data.equals(""))
        {
            if(preferenciasIdioma.equals("Español")) {
                Toast.makeText(this, "Por favor escribe tu respuesta en el textbox", Toast.LENGTH_SHORT).show();

            }
            else if(preferenciasIdioma.equals("Maya Itzá"))
            {
                Toast.makeText(this, "Tz'iib'tej anuktaj ti a'textb'ox", Toast.LENGTH_SHORT).show();

            }
            else if(preferenciasIdioma.equals("Q'eqchi'"))
            {
                Toast.makeText(this, "B'anuu usilal tz'iib'a' xsumenkil sa' na'aj", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        respuestaUsuario.setText("");
        Integer numdata = Integer.parseInt(data);
        if (numdata == op.respuestaCorrecta) {
            punteo++;
            generarPregunta();
            txtPregunta.startAnimation(AnimationUtils.loadAnimation(this, R.anim.question_flicker));

            if(preferenciasIdioma.equals("Español")) {
                txtTiempo.setText(getString(R.string.punteoespañol));
            }
            else if(preferenciasIdioma.equals("Maya Itzá"))
            {
                txtTiempo.setText(getString(R.string.punteoitza));
            }
            else if(preferenciasIdioma.equals("Q'eqchi'"))
            {
                txtTiempo.setText(getString(R.string.punteoqeqchi));
            }
            numeroPreguntas++;
            Retroalimentacion.retroalimentacionCorrecto(this, txtRetroalimentacion, numeroRetroalimentacion);

        } else {
            txtpunteo.startAnimation(AnimationUtils.loadAnimation(this, R.anim.correct_animation));
            btnResponder.startAnimation(correctAnimation);


            if (sharedPref.getBoolean(Ajustes.modo_supervivencia, false)) {
                mostrarResultados();
            }
            Retroalimentacion.retroalimentacionCorrecto(this, txtRetroalimentacion, numeroRetroalimentacion);
            generarPregunta();
            numeroPreguntas++;

        }
        view.setEnabled(false);
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setEnabled(true);
            }
        }, 500);
    }

    public void generarPregunta() {
        Random rd = new Random();
        numeroRetroalimentacion = rd.nextInt(12);

        if(modo_juego.equals("Pesadilla"))
        {
            txtPregunta.setText(op.preguntaDificil(3));

        }
        else {
            txtPregunta.setText(op.preguntaNormal(3));

        }

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
                    txtTiempo.startAnimation(AnimationUtils.loadAnimation(Actividad3.this, R.anim.flicker_animation_2));

                } else if (milisRestantes <= tiempo * 0.30) {
                    txtTiempo.startAnimation(AnimationUtils.loadAnimation(Actividad3.this, R.anim.flicker_animation_1));

                } else {
                    if (milisRestantes < tiempo * 0.10) {
                        txtTiempo.startAnimation(AnimationUtils.loadAnimation(Actividad3.this, R.anim.flicker_animation));
                    }
                }
            }

            @Override
            public void onFinish() {
                //Shows user results
                if (modo_juego.equals("Tutorial")) {
                    ShowcaseConfig config = new ShowcaseConfig();
                    final MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(Actividad3.this);
                    sequence.setConfig(config);
                    if(preferenciasIdioma.equals("Español")) {
                        sequence.addSequenceItem(new MaterialShowcaseView.Builder(Actividad3.this)
                                .setTarget(txtTiempo)
                                .setDismissText("Siguiente")
                                .setContentText("Oh no! se acabó el tiempo, lo hiciste bien, obtuviste: " + punteo + " puntos")
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
                                .show());                    }
                    else if(preferenciasIdioma.equals("Maya Itzá"))
                    {
                        sequence.addSequenceItem(new MaterialShowcaseView.Builder(Actividad3.this)
                                .setTarget(txtTiempo)
                                .setDismissText("Ulaak'-kutal")
                                .setContentText("Wa ma'! Jo'mij a' tiempojej, tamentaj ma'lo', yanijijtecha: " + punteo + " puntojej")
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
                                .show());                    }
                    else if(preferenciasIdioma.equals("Q'eqchi'"))
                    {
                        sequence.addSequenceItem(new MaterialShowcaseView.Builder(Actividad3.this)
                                .setTarget(txtTiempo)
                                .setDismissText("Jalan chik")
                                .setContentText("Aah ink'a'b xoso' li hoonal, xaayib' chi chaab'il, xaatz'aq: " + punteo + " xtz'aq")
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
                                .show());                    }



                }
                mostrarResultados();
            }
        }.start();
    }

    public void reset() {
        numeroPreguntas = 0;
        punteo = 0;
        timer();
        generarPregunta();
    }

}