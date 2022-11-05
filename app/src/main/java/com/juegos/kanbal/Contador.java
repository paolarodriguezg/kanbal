package com.juegos.kanbal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.widget.TextView;

import java.util.Random;

public class Contador extends AppCompatActivity {
    private static int loadingScreenTime = 4000;
    TextView timer;
    private String preferenciasIdioma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contador);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        TextView hintText = findViewById(R.id.hintText);
        TextView preparate = findViewById(R.id.txtPreparate);
        Random rd = new Random();
        preferenciasIdioma = sharedPref.getString(Ajustes.idioma, "Español");

        preparate.setText("Preparate");
        switch (rd.nextInt(7)){
            case 0:
                if(preferenciasIdioma.equals("Español")) {
                    hintText.setText("El tiempo se reinicia cuando respondes una pregunta correctamente");
                }
                else if(preferenciasIdioma.equals("Maya Itzá"))
                {
                    hintText.setText("A' tiempojej kutz'ab'iltech ka'anuktik junp'eel tojilk'atal");
                }
                else if(preferenciasIdioma.equals("Q'eqchi'"))
                {
                    hintText.setText("Li hoonal natikla naq taasume li patz'om chi us");
                }
                break;

            case 1:
                if(preferenciasIdioma.equals("Español")) {
                    hintText.setText("Puedes ahorrar tiempo si calculas el último dígito y no concuerda con la respuesta en pantalla");
                }
                else if(preferenciasIdioma.equals("Maya Itzá"))
                {
                    hintText.setText("Patal ahorrartej tiempojej wa ama'tik a' uxul dijitojej ma' uketik etel a'nuktal ti ta'axil");
                }
                else if(preferenciasIdioma.equals("Q'eqchi'"))
                {
                    hintText.setText("Naru, naq tak'osk aahonal wi tz'aqal taawil li, xmaril li ajl ut naq ink'a' tz'aqal li xsumenkil chi ru li leemal ulul chíich'");
                }
                break;

            case 2:
                if(preferenciasIdioma.equals("Español")) {
                    hintText.setText("Si se te hace difícil puedes disminuir la dificultad");
                }
                else if(preferenciasIdioma.equals("Maya Itzá"))
                {
                    hintText.setText("Wa ma' patalech patal amentik tz'eek uma'patalil");
                }
                else if(preferenciasIdioma.equals("Q'eqchi'"))
                {
                    hintText.setText("Wi ch'a'aj taawil nara naq taak'osk li xch'a'ajkilal");
                }
                break;

            case 3:
                if(preferenciasIdioma.equals("Español")) {
                    hintText.setText("Compara tu resultado con el de tus amigos");
                }
                else if(preferenciasIdioma.equals("Maya Itzá"))
                {
                    hintText.setText("Ketej a' resultadoje yetel ti a' wet'okoo'");
                }
                else if(preferenciasIdioma.equals("Q'eqchi'"))
                {
                    hintText.setText("K'e' reetal wi juntaq'et ruk'ineb' laakomon");
                }
                break;

            case 4:
                if(preferenciasIdioma.equals("Español")) {
                    hintText.setText("Mantente atento a los cambios de colores, estos te indican cuanto tiempo te queda");
                }
                else if(preferenciasIdioma.equals("Maya Itzá"))
                {
                    hintText.setText("Yan ayantal jeb'a'an awich ti a' jelkunb'ilb'onil, a' lo' kuyalik b'oon tiempojej kup'atältech");
                }
                else if(preferenciasIdioma.equals("Q'eqchi'"))
                {
                    hintText.setText("Sahilak aach'ool naq jalrib' li xb'onol, a'an tixye jarub hoonal chik na'oso'");
                }
                break;

            case 5:
                if(preferenciasIdioma.equals("Español")) {
                    hintText.setText("No te alarme cuando veas el primer cambio de color, esto indica que has gastado la mitad de tu tiempo aun te queda la otra mitad");
                }
                else if(preferenciasIdioma.equals("Maya Itzá"))
                {
                    hintText.setText("Ma' ujak'il awod ka achantik a' yaxjelkuntik ub'onil, a' laj kuya'lik jo'mijaxupik uchumukti atiempoje ka'ax up'atiltech ulaak chumuk");
                }
                else if(preferenciasIdioma.equals("Q'eqchi'"))
                {
                    hintText.setText("Ma xib'e'aawib' naq jalak xb'onol, a'an naxq'e retal naq xaasach li yii toq' li hoonal, jun toq'ol chik la hoonal");
                }
                break;

            case 6:
                if(preferenciasIdioma.equals("Español")) {
                    hintText.setText("¿Muchos errores al principio? Prueba reiniciando, el juego solo dura 30 segundos de todos modos");
                }
                else if(preferenciasIdioma.equals("Maya Itzá"))
                {
                    hintText.setText("¿Yaab' umaki'il ti uchu'umpal? tuntej uka'chu'umpal, a' b'axäl chen kuchichtal 30 segundojej");
                }
                else if(preferenciasIdioma.equals("Q'eqchi'"))
                {
                    hintText.setText("¿Waal xaatsach sa' xtiklajik? nara taatikib', li yaalb'aix nab'ay lajeeb' xka'k'aal k'asal");
                }
                break;
        }
        timer = findViewById(R.id.timeText);

        if(Datos_Globales.numeroActividad == 1)
        {
            abrirActividad1();
        }
        if(Datos_Globales.numeroActividad == 2)
        {
            abrirActividad2();
        }
        if(Datos_Globales.numeroActividad == 3)
        {
            abrirActividad3();
        }
        if(Datos_Globales.numeroActividad == 4)
        {
            abrirActividad4();
        }


        new CountDownTimer(loadingScreenTime, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(getString(R.string.loading_screen_timer, (int) millisUntilFinished/1000));
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }
    @Override
    public void onBackPressed() {

    }


    public void abrirActividad1()
    {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), Actividad1.class));
                finish();
            }
        },loadingScreenTime);

    }

    public void abrirActividad2()
    {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), Actividad2.class));
                finish();
            }
        },loadingScreenTime);

    }

    public void abrirActividad3()
    {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), Actividad3.class));
                finish();
            }
        },loadingScreenTime);

    }

    public void abrirActividad4()
    {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), Actividad4.class));
                finish();
            }
        },loadingScreenTime);

    }
}

