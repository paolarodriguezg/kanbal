package com.juegos.kanbal;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.TextView;

import java.util.Random;

public class Retroalimentacion {


    public static void retroalimentacionCorrecto(Context context, TextView txtRetroalimentacion, int retroalimentacion) {
        SharedPreferences sharedPreferences;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String preferencias = sharedPreferences.getString(Ajustes.idioma, "Español");


        if (retroalimentacion == 0) {
            if(preferencias.equals("Español"))
            {
                txtRetroalimentacion.setText(context.getString(R.string.good_job));
            }
            else if(preferencias.equals("Maya Itzá"))
            {
                txtRetroalimentacion.setText("Ma'lo' meyaj");
            }
            else if(preferencias.equals("Q'eqchi'"))
            {
                txtRetroalimentacion.setText("Chaab'il lik'anjel");
            }
        } else if (retroalimentacion == 1) {
            if(preferencias.equals("Español"))
            {
                txtRetroalimentacion.setText(context.getString(R.string.amazing));
            }
            else if(preferencias.equals("Maya Itzá"))
            {
                txtRetroalimentacion.setText("Ma'lo' meyaj");
            }
            else if(preferencias.equals("Q'eqchi'"))
            {
                txtRetroalimentacion.setText("Chaab'i li ru");
            }

        } else if (retroalimentacion == 2) {
            if(preferencias.equals("Español"))
            {
                txtRetroalimentacion.setText(context.getString(R.string.fantastic));
            }
            else if(preferencias.equals("Maya Itzá"))
            {
                txtRetroalimentacion.setText("Ma'lo' meyaj");
            }
            else if(preferencias.equals("Q'eqchi'"))
            {
                txtRetroalimentacion.setText("K'ajo' xchaab'il");
            }

        } else if (retroalimentacion == 3) {
            if(preferencias.equals("Español"))
            {
                txtRetroalimentacion.setText(context.getString(R.string.damn));
            }
            else if(preferencias.equals("Maya Itzá"))
            {
                txtRetroalimentacion.setText("Jach ma'lo'");
            }
            else if(preferencias.equals("Q'eqchi'"))
            {
                txtRetroalimentacion.setText("Chaab'il chaab'il");
            }

        } else if (retroalimentacion == 4) {
            if(preferencias.equals("Español"))
            {
                txtRetroalimentacion.setText(context.getString(R.string.genius));
            }
            else if(preferencias.equals("Maya Itzá"))
            {
                txtRetroalimentacion.setText("Jajach ma'lo'");
            }
            else if(preferencias.equals("Q'eqchi'"))
            {
                txtRetroalimentacion.setText("K'ajo' tz'aqal li ru");
            }

        } else if (retroalimentacion == 5) {
            if(preferencias.equals("Español"))
            {
                txtRetroalimentacion.setText(context.getString(R.string.sweet));
            }
            else if(preferencias.equals("Maya Itzá"))
            {
                txtRetroalimentacion.setText("Jajach ma'lo'");
            }
            else if(preferencias.equals("Q'eqchi'"))
            {
                txtRetroalimentacion.setText("K'ajo' xjab lik'a'uxl");
            }

        } else if (retroalimentacion == 6) {
            if(preferencias.equals("Español"))
            {
                txtRetroalimentacion.setText(context.getString(R.string.crazy));
            }
            else if(preferencias.equals("Maya Itzá"))
            {
                txtRetroalimentacion.setText("Jajach ma'lo'");
            }
            else if(preferencias.equals("Q'eqchi'"))
            {
                txtRetroalimentacion.setText("Jun xwankilal");
            }

        } else if (retroalimentacion == 7) {
            if(preferencias.equals("Español"))
            {
                txtRetroalimentacion.setText(context.getString(R.string.keep));
            }
            else if(preferencias.equals("Maya Itzá"))
            {
                txtRetroalimentacion.setText("Jajach ma'lo'");
            }
            else if(preferencias.equals("Q'eqchi'"))
            {
                txtRetroalimentacion.setText("Kawil Ch'oolej");
            }

        } else if (retroalimentacion == 8) {
            if(preferencias.equals("Español"))
            {
                txtRetroalimentacion.setText(context.getString(R.string.unbelievable));
            }
            else if(preferencias.equals("Maya Itzá"))
            {
                txtRetroalimentacion.setText("Tamentaj ma'lo'");
            }
            else if(preferencias.equals("Q'eqchi'"))
            {
                txtRetroalimentacion.setText("Chaab'il xaab'anu");
            }

        } else if (retroalimentacion == 9) {
            if(preferencias.equals("Español"))
            {
                txtRetroalimentacion.setText(context.getString(R.string.surprised));
            }
            else if(preferencias.equals("Maya Itzá"))
            {
                txtRetroalimentacion.setText("Tamentaj ma'lo'");
            }
            else if(preferencias.equals("Q'eqchi'"))
            {
                txtRetroalimentacion.setText("K'ajo tz'aqal");
            }

        } else if (retroalimentacion == 10) {
            if(preferencias.equals("Español"))
            {
                txtRetroalimentacion.setText(context.getString(R.string.brilliant));
            }
            else if(preferencias.equals("Maya Itzá"))
            {
                txtRetroalimentacion.setText("Tamentaj ma'lo'");
            }
            else if(preferencias.equals("Q'eqchi'"))
            {
                txtRetroalimentacion.setText("Lemtz'i ru");
            }

        } else if (retroalimentacion == 11) {
            if(preferencias.equals("Español"))
            {
                txtRetroalimentacion.setText(context.getString(R.string.bananas));
            }
            else if(preferencias.equals("Maya Itzá"))
            {
                txtRetroalimentacion.setText("Intech juntuulnojochna'atil");
            }
            else if(preferencias.equals("Q'eqchi'"))
            {
                txtRetroalimentacion.setText("Xkawilal aak'a'uxl");
            }

        }
    }


    public static void retroalimentacionIncorrecto(Context context, TextView txtRetroalimentacion, int retroalimentacion) {
        Random rd = new Random();
        SharedPreferences sharedPreferences;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String preferencias = sharedPreferences.getString(Ajustes.idioma, "Español");
        switch (rd.nextInt(4)) {
            case 0:
                if(preferencias.equals("Español"))
                {
                    txtRetroalimentacion.setText(context.getString(R.string.ohno));
                }
                else if(preferencias.equals("Maya Itzá"))
                {
                    txtRetroalimentacion.setText("Wa ma'");
                }
                else if(preferencias.equals("Q'eqchi'"))
                {
                    txtRetroalimentacion.setText("Wi ink'a'");
                }

                break;
            case 1:
                if(preferencias.equals("Español"))
                {
                    txtRetroalimentacion.setText(context.getString(R.string.quemal));
                }
                else if(preferencias.equals("Maya Itzá"))
                {
                    txtRetroalimentacion.setText("B'a'ax ma'ki'");
                }
                else if(preferencias.equals("Q'eqchi'"))
                {
                    txtRetroalimentacion.setText("Ink'a' chab'il");
                }

                break;
            case 2:
                if(preferencias.equals("Español"))
                {
                    txtRetroalimentacion.setText(context.getString(R.string.sad));
                }
                else if(preferencias.equals("Maya Itzá"))
                {
                    txtRetroalimentacion.setText(context.getString(R.string.sad));
                }
                else if(preferencias.equals("Q'eqchi'"))
                {
                    txtRetroalimentacion.setText(context.getString(R.string.sad));
                }

                break;
            case 3:
                if(preferencias.equals("Español"))
                {
                    txtRetroalimentacion.setText(context.getString(R.string.o_o));
                }
                else if(preferencias.equals("Maya Itzá"))
                {
                    txtRetroalimentacion.setText(context.getString(R.string.o_o));
                }
                else if(preferencias.equals("Q'eqchi'"))
                {
                    txtRetroalimentacion.setText(context.getString(R.string.o_o));
                }

                break;
        }
    }
}
