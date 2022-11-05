package com.juegos.kanbal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private String[] txtCorreoTraducir = {"Correo", "Pek", "Coreeo"};
    private String[] txtContraseñaTraducir = {"Contraseña", "Tunultuyichxu'uk", "Eetalil"};
    private String[] txtElegiridiomaTraducir = {"Elegir idioma", "Elegir idioma", "Elegir idioma"};
    private String[] btnIngresarTraducir = {"Ingresar", "Okol", "Okank'"};

    private TextView txtcorreo, txtcontraseña, txtelegiridioma;
    private Button btningresar;



    RadioButton radioButton1, radioButton2, radioButton3;
    SharedPreferences sharedPref;
    String idiomaSeleccionado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        idiomaSeleccionado = sharedPref.getString("idioma_pref", "Español");
        setContentView(R.layout.activity_main);
        idiomaSeleccionado = Ajustes.idioma;


        txtcorreo = findViewById(R.id.txtcorreo);
        txtcontraseña = findViewById(R.id.txtcontraseña);
        txtelegiridioma = findViewById(R.id.txtelegiridioma);
        btningresar  = findViewById (R.id.btn_ingresar);





        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);
        if(sharedPref.getString(idiomaSeleccionado, "Español").equals("Español"))
        {
            radioButton1.setChecked(true);
        }
        else if(sharedPref.getString(idiomaSeleccionado, "Español").equals("Maya Itzá"))
        {
            radioButton2.setChecked(true);
        }
        else if(sharedPref.getString(idiomaSeleccionado, "Español").equals("Q'eqchi'"))
        {
            radioButton3.setChecked(true);
        }
        traducir();

        radioButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPref.edit().putString(Ajustes.idioma, "Español").apply();
                traducir();
            }
        });
        radioButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPref.edit().putString(Ajustes.idioma, "Maya Itzá").apply();
                traducir();
            }
        });
        radioButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPref.edit().putString(Ajustes.idioma, "Q'eqchi'").apply();
                traducir();
            }
        });


        btningresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ingresar();
//Intent intento = new Intent(MainActivity.this, menuPrincipal.class);
//startActivity(intento);
            }
        });


    }


    public void traducir()
    {
        if(sharedPref.getString(Ajustes.idioma, "Español").equals("Español"))
        {
            txtcorreo.setText(txtCorreoTraducir[0]);
            txtcontraseña.setText(txtContraseñaTraducir[0]);
            txtelegiridioma.setText(txtElegiridiomaTraducir[0]);
            btningresar.setText(btnIngresarTraducir[0]);
        }
        else if(sharedPref.getString(Ajustes.idioma, "Español").equals("Maya Itzá"))
        {
            txtcorreo.setText(txtCorreoTraducir[1]);
            txtcontraseña.setText(txtContraseñaTraducir[1]);
            txtelegiridioma.setText(txtElegiridiomaTraducir[1]);
            btningresar.setText(btnIngresarTraducir[1]);

        } else if(sharedPref.getString(Ajustes.idioma, "Español").equals("Q'eqchi'"))
        {
            txtcorreo.setText(txtCorreoTraducir[2]);
            txtcontraseña.setText(txtContraseñaTraducir[2]);
            txtelegiridioma.setText(txtElegiridiomaTraducir[2]);
            btningresar.setText(btnIngresarTraducir[2]);
        }

    }

    public void ingresar()
    {
        EditText tcorreo  = findViewById (R.id.btncorreo);
        EditText tcontraseña  = findViewById (R.id.btncontraseña);
        String correo = tcorreo.getText().toString();
        String contraseña = tcontraseña.getText().toString();

        if(correo.isEmpty() || contraseña.isEmpty())
        {
            if(sharedPref.getString(Ajustes.idioma, "Español").equals("Español"))
            {
                Toast.makeText(this, "Correo o contraseña vacios", Toast.LENGTH_LONG).show();
            }
            else if(sharedPref.getString(Ajustes.idioma, "Español").equals("Maya Itzá"))
            {
                Toast.makeText(this, "Upektzilmak wa tunultuyichxu'uk", Toast.LENGTH_LONG).show();
            }
            else if(sharedPref.getString(Ajustes.idioma, "Español").equals("Q'eqchi'"))
            {
                Toast.makeText(this, "Tz'iib'a a coree ut aaweetalil wi naq mak'a'", Toast.LENGTH_LONG).show();
            }

        }
        else {
            DataBaseAccess databaseAccess  =  DataBaseAccess.getInstance(this);
            databaseAccess.open();

            int status = databaseAccess.login(correo.toString(), contraseña.toString());
            if(status < 0)
            {
                if(sharedPref.getString(Ajustes.idioma, "Español").equals("Español"))
                {
                    Toast.makeText(this, "No se pudo conectar a la base de datos", Toast.LENGTH_LONG);
                }
                else if(sharedPref.getString(Ajustes.idioma, "Español").equals("Maya Itzá"))
                {
                    Toast.makeText(this, "Ma' patalijij uyokol ti uchun pektzil", Toast.LENGTH_LONG);
                }
                else if(sharedPref.getString(Ajustes.idioma, "Español").equals("Q'eqchi'"))
                {
                    Toast.makeText(this, "Ink'a xru chi ok sa' b'ar wi wankat li eesil", Toast.LENGTH_LONG);
                }
            }
            if(status > 0)
            {
                databaseAccess.close();
                Intent intent = new Intent(MainActivity.this, menuPrincipal.class);
                startActivity(intent);
            }
            else
            {
                if(sharedPref.getString(Ajustes.idioma, "Español").equals("Español"))
                {
                    Toast.makeText(this, "Usuario y contraseña incorrectos", Toast.LENGTH_LONG).show();
                }
                else if(sharedPref.getString(Ajustes.idioma, "Español").equals("Maya Itzá"))
                {
                    Toast.makeText(this, "Ajk'ab'et yetel tunultuyichxu'uk ma'tojil", Toast.LENGTH_LONG).show();
                }
                else if(sharedPref.getString(Ajustes.idioma, "Español").equals("Q'eqchi'"))
                {
                    Toast.makeText(this, "Aawetalil ut tz'iib' ink'a' aawe", Toast.LENGTH_LONG).show();
                }

                databaseAccess.close();
            }

        }
    }
}


