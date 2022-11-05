package com.juegos.kanbal;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.Random;

public class operaciones {
    ArrayList<Integer> listaOperaciones = new ArrayList<Integer>();
    Boolean suma, resta, multiplicacion, division, potencia, raiz;
    Boolean raizdif, potenciadif, tripsuma, tripresta, tripmult, tripdiv, sumamult, restamult, sumadiv, restadiv;
    int grado;
    Context contexto;
    public int respuestaCorrecta = 0;
    ArrayList<Integer> listaRespuestasIncorrectas = new ArrayList<Integer>();
    public int rangosumamax, rangorestamax, rangomultiplicacionmax, rangodivisionmax, rangopotenciamax, rangoraizmax;
    public int rangosumadifmax, rangorestadifmax, rangomultiplicaciondifmax, rangodivisiondifmax,
            rangosumamultdifmaxsuma, rangosumadivdifmaxsuma, rangorestamultdifmaxresta, rangorestadivdifmaxresta,
            rangosumamultdifmaxmult, rangosumadivdifmaxdiv, rangorestamultdifmaxmult, rangorestadivdifmaxdiv,
            rangopotenciadifmax, rangoraizdifmax;
    public int rangosumadifmin, rangorestadifmin, rangomultiplicaciondifmin, rangodivisiondifmin,
            rangosumamultdifmin, rangosumadivdifmin, rangorestamultdifmin, rangorestadivdifmin,
            rangopotenciadifmin, rangoraizdifmin;


    public operaciones(Context contexto) {
        this.contexto = contexto;
        grado = Datos_Globales.grado;
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(contexto);
        String modo_juego = sharedPref.getString("modos_juego_pref", "Normal");

        if (modo_juego.equals("Pesadilla")) {
            tripsuma = sharedPref.getBoolean(Ajustes.suma_dificil, true);
            tripresta = sharedPref.getBoolean(Ajustes.resta_dificil, true);
            tripmult = sharedPref.getBoolean(Ajustes.multiplicacion_dificil, true);
            tripdiv = sharedPref.getBoolean(Ajustes.division_dificil, true);
            sumamult = sharedPref.getBoolean(Ajustes.suma_multiplicacion_dificil, true);
            sumadiv = sharedPref.getBoolean(Ajustes.suma_division_dificil, true);
            restamult = sharedPref.getBoolean(Ajustes.resta_multiplicacion_dificil, true);
            restadiv = sharedPref.getBoolean(Ajustes.resta_division_dificil, true);
            potenciadif = sharedPref.getBoolean(Ajustes.potencia_dificil, true);
            raizdif = sharedPref.getBoolean(Ajustes.raiz_dificil, true);

            if (tripsuma) {
                listaOperaciones.add(1);
            }
            if (tripresta) {
                listaOperaciones.add(2);
            }
            if (tripmult) {
                listaOperaciones.add(3);
            }
            if (tripdiv) {
                listaOperaciones.add(4);
            }
            if (sumamult) {
                listaOperaciones.add(5);
            }
            if (sumadiv) {
                listaOperaciones.add(6);
            }
            if (restamult) {
                listaOperaciones.add(7);
            }
            if (restadiv) {
                listaOperaciones.add(8);
            }
            if (potenciadif) {
                listaOperaciones.add(9);
            }
            if (raizdif) {
                listaOperaciones.add(10);
            }

        }
        else {
            suma = Datos_Globales.suma;
            resta = Datos_Globales.resta;
            multiplicacion = Datos_Globales.multiplicacion;
            division = Datos_Globales.division;
            potencia = Datos_Globales.potencia;
            raiz = Datos_Globales.raiz;

            if (suma) {
                listaOperaciones.add(1);
            }
            if (resta) {
                listaOperaciones.add(2);
            }
            if (multiplicacion) {
                listaOperaciones.add(3);
            }
            if (division) {
                listaOperaciones.add(4);
            }
            if (potencia) {
                listaOperaciones.add(5);
            }
            if (raiz) {
                listaOperaciones.add(6);
            }
        }

    }

    public operaciones(Context contexto, int num) {
        this.contexto = contexto;


    }


    public void rangofacil() {
        if (grado == 1) {
            rangosumamax = 3;
            rangorestamax = 3;
        } else if (grado == 2) {
            rangosumamax = 5;
            rangorestamax = 5;
            rangomultiplicacionmax = 3;
        } else if (grado == 3) {
            rangosumamax = 7;
            rangorestamax = 7;
            rangomultiplicacionmax = 4;
            rangodivisionmax = 3;
        } else if (grado == 4) {
            rangosumamax = 10;
            rangorestamax = 10;
            rangomultiplicacionmax = 5;
            rangodivisionmax = 4;
            rangopotenciamax = 3;
        } else if (grado == 5) {
            rangosumamax = 12;
            rangorestamax = 12;
            rangomultiplicacionmax = 6;
            rangodivisionmax = 5;
            rangopotenciamax = 4;
        } else if (grado == 6) {
            rangosumamax = 15;
            rangorestamax = 15;
            rangomultiplicacionmax = 8;
            rangodivisionmax = 7;
            rangopotenciamax = 6;
            rangoraizmax = 5;
        }
    }

    public void rangonormal() {
        if (grado == 1) {
            rangosumamax = 5;
            rangorestamax = 5;
        } else if (grado == 2) {
            rangosumamax = 7;
            rangorestamax = 7;
            rangomultiplicacionmax = 4;
        } else if (grado == 3) {
            rangosumamax = 10;
            rangorestamax = 10;
            rangomultiplicacionmax = 5;
            rangodivisionmax = 4;
        } else if (grado == 4) {
            rangosumamax = 13;
            rangorestamax = 13;
            rangomultiplicacionmax = 6;
            rangodivisionmax = 4;
            rangopotenciamax = 5;
        } else if (grado == 5) {
            rangosumamax = 16;
            rangorestamax = 16;
            rangomultiplicacionmax = 8;
            rangodivisionmax = 7;
            rangopotenciamax = 7;
        } else if (grado == 6) {
            rangosumamax = 20;
            rangorestamax = 20;
            rangomultiplicacionmax = 10;
            rangodivisionmax = 10;
            rangopotenciamax = 10;
            rangoraizmax = 10;
        }
    }


    public void rangodificil() {
        if (grado == 1) {
            rangosumamax = 10;
            rangorestamax = 10;
        } else if (grado == 2) {
            rangosumamax = 15;
            rangorestamax = 15;
            rangomultiplicacionmax = 6;
        } else if (grado == 3) {
            rangosumamax = 25;
            rangorestamax = 25;
            rangomultiplicacionmax = 7;
            rangodivisionmax = 6;
        } else if (grado == 4) {
            rangosumamax = 50;
            rangorestamax = 50;
            rangomultiplicacionmax = 11;
            rangodivisionmax = 10;
            rangopotenciamax = 10;
        } else if (grado == 5) {
            rangosumamax = 100;
            rangorestamax = 100;
            rangomultiplicacionmax = 15;
            rangodivisionmax = 15;
            rangopotenciamax = 15;
        } else if (grado == 6) {
            rangosumamax = 150;
            rangorestamax = 150;
            rangomultiplicacionmax = 20;
            rangodivisionmax = 20;
            rangopotenciamax = 20;
            rangoraizmax = 20;
        }
    }


    public void rangoultradificil() {
        if (grado == 1) {
            rangosumamax = 20;
            rangorestamax = 20;
        } else if (grado == 2) {
            rangosumamax = 40;
            rangorestamax = 40;
            rangomultiplicacionmax = 10;
        } else if (grado == 3) {
            rangosumamax = 75;
            rangorestamax = 75;
            rangomultiplicacionmax = 13;
            rangodivisionmax = 13;
        } else if (grado == 4) {
            rangosumamax = 100;
            rangorestamax = 100;
            rangomultiplicacionmax = 16;
            rangodivisionmax = 16;
            rangopotenciamax = 16;
        } else if (grado == 5) {
            rangosumamax = 150;
            rangorestamax = 150;
            rangomultiplicacionmax = 20;
            rangodivisionmax = 20;
            rangopotenciamax = 20;
        } else if (grado == 6) {
            rangosumamax = 300;
            rangorestamax = 300;
            rangomultiplicacionmax = 25;
            rangodivisionmax = 25;
            rangopotenciamax = 25;
            rangoraizmax = 25;
        }
    }


    public void rangopesadilla() {
        if (grado == 1) {
            rangosumadifmax = 12;
            rangorestadifmax = 12;
            rangosumadifmin = 5;
            rangorestadifmin = 5;
        } else if (grado == 2) {
            rangosumadifmax = 18;
            rangorestadifmax = 18;
            rangomultiplicaciondifmax = 8;
            rangosumadifmin = 8;
            rangorestadifmin = 8;
            rangomultiplicaciondifmin = 2;
        } else if (grado == 3) {
            rangosumadifmax = 25;
            rangorestadifmax = 25;
            rangomultiplicaciondifmax = 10;
            rangodivisiondifmax = 10;
            rangosumadifmin = 15;
            rangorestadifmin = 15;
            rangomultiplicaciondifmin = 5;
            rangodivisiondifmin = 5;
        } else if (grado == 4) {
            rangosumadifmax = 40;
            rangorestadifmax = 40;
            rangomultiplicaciondifmax = 13;
            rangodivisiondifmax = 13;
            rangopotenciadifmax = 13;
            rangosumadifmin = 20;
            rangorestadifmin = 20;
            rangomultiplicaciondifmin = 6;
            rangodivisiondifmin = 6;
            rangopotenciadifmin = 3;
        } else if (grado == 5) {
            rangosumadifmax = 60;
            rangorestadifmax = 60;
            rangomultiplicaciondifmax = 16;
            rangodivisiondifmax = 16;
            rangopotenciadifmax = 16;
            rangosumadifmin = 40;
            rangorestadifmin = 40;
            rangomultiplicaciondifmin = 8;
            rangodivisiondifmin = 8;
            rangopotenciadifmin = 25;
        } else if (grado == 6) {
            rangosumadifmax = 100;
            rangorestadifmax = 100;
            rangomultiplicaciondifmax = 20;
            rangodivisiondifmax = 20;
            rangopotenciadifmax = 20;
            rangoraizdifmax = 20;
            rangosumadifmin = 50;
            rangorestadifmin = 50;
            rangomultiplicaciondifmin = 10;
            rangodivisiondifmin = 10;
            rangopotenciadifmin = 30;
            rangoraizdifmin = 30;
        }
    }

    public void preferencias() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(contexto);
        String modo_juego = sharedPref.getString("modos_juego_pref", "Normal");
        if (modo_juego.equals("Tutorial") || modo_juego.equals("Facil")) {
            rangofacil();
        } else if (modo_juego.equals("Normal")) {
            rangonormal();
        } else if (modo_juego.equals("Dificil")) {
            rangodificil();
        } else if (modo_juego.equals("Ultra Dificil")) {
            rangoultradificil();
        } else if (modo_juego.equals("Pesadilla")) {
            rangopesadilla();
        }

    }


    public String preguntaNormal(int modo) {
        preferencias();
        Random rd = new Random();
        int tipoPregunta = rd.nextInt(listaOperaciones.size());
        if (listaOperaciones.get(tipoPregunta) == 1) {
            int a = rd.nextInt(rangosumamax + 1);
            int b = rd.nextInt(rangosumamax + 1);
            respuestaCorrecta = a + b;
            if (modo == 1) {
                return contexto.getString(R.string.sum, a, b, respuestaCorrecta);
            } else if (modo == 2) {
                int c = rd.nextInt(rangosumamax * 2 + 1);
                while (c == respuestaCorrecta) {
                    c = rd.nextInt(rangosumamax * 2 + 1);
                }
                return contexto.getString(R.string.sum, a, b, c);
            } else if (modo == 3) {
                return contexto.getString(R.string.sum2, a, b);
            } else if (modo == 4) {
                listaRespuestasIncorrectas.clear();
                for (int i = 0; i < 3; i++) {
                    int corrector = 0;
                    int c = rd.nextInt(rangosumamax * 2 + 1);
                    while (c == respuestaCorrecta || yaEsta(c)) {
                        c = rd.nextInt(rangosumamax * 2 + 1 + corrector);
                        corrector++;
                    }
                    listaRespuestasIncorrectas.add(c);
                }
                return contexto.getString(R.string.sum2, a, b);
            }
        } else if (listaOperaciones.get(tipoPregunta) == 2) {
            int a = rd.nextInt(rangorestamax + 1);
            int b = rd.nextInt(rangorestamax + 1) + a;
            respuestaCorrecta = b - a;
            if (modo == 1) {
                return contexto.getString(R.string.sub, b, a, respuestaCorrecta);
            } else if (modo == 2) {
                int c = rd.nextInt(rangorestamax + 1);
                while (c == respuestaCorrecta) {
                    c = rd.nextInt(rangorestamax + 1);
                }
                return contexto.getString(R.string.sub, b, a, c);
            } else if (modo == 3) {
                return contexto.getString(R.string.sub2, b, a);
            } else if (modo == 4) {
                listaRespuestasIncorrectas.clear();
                for (int i = 0; i < 3; i++) {
                    int corrector = 0;
                    int c = rd.nextInt(rangorestamax + 1);
                    while (c == respuestaCorrecta || yaEsta(c)) {
                        c = rd.nextInt(rangorestamax + 1 + corrector);
                        corrector++;
                    }
                    listaRespuestasIncorrectas.add(c);
                }
                return contexto.getString(R.string.sub2, b, a);
            }
        } else if (listaOperaciones.get(tipoPregunta) == 3) {
            int a = rd.nextInt(rangomultiplicacionmax + 1);
            int b = rd.nextInt(rangomultiplicacionmax + 1);
            respuestaCorrecta = a * b;
            if (modo == 1) {
                return contexto.getString(R.string.mult, a, b, a * b);
            } else if (modo == 2) {
                int c = rd.nextInt(rangomultiplicacionmax * rangomultiplicacionmax + 1);
                while (c == respuestaCorrecta) {
                    c = rd.nextInt(rangomultiplicacionmax * rangomultiplicacionmax + 1);
                }
                return contexto.getString(R.string.mult, a, b, c);
            } else if (modo == 3) {
                return contexto.getString(R.string.mult2, a, b);
            } else if (modo == 4) {
                listaRespuestasIncorrectas.clear();
                for (int i = 0; i < 3; i++) {
                    int corrector = 0;
                    int c = rd.nextInt(rangomultiplicacionmax * rangomultiplicacionmax + 1);
                    while (c == respuestaCorrecta || yaEsta(c)) {
                        c = rd.nextInt(rangomultiplicacionmax * rangomultiplicacionmax + 1 + corrector);
                        corrector++;
                    }
                    listaRespuestasIncorrectas.add(c);
                }
                return contexto.getString(R.string.mult2, a, b);
            }
        } else if (listaOperaciones.get(tipoPregunta) == 4) {
            int a = rd.nextInt(rangodivisionmax + 1) + 1;
            int b = rd.nextInt(rangodivisionmax + 1);
            respuestaCorrecta = a * b;
            b = respuestaCorrecta;
            respuestaCorrecta = b / a;
            if (modo == 1) {
                return contexto.getString(R.string.div, b, a, respuestaCorrecta);
            } else if (modo == 2) {
                int c = rd.nextInt(rangodivisionmax + 1);
                while (c == respuestaCorrecta) {
                    c = rd.nextInt(rangodivisionmax + 1);
                }
                return contexto.getString(R.string.div, b, a, c);
            } else if (modo == 3) {
                return contexto.getString(R.string.div2, b, a);
            } else if (modo == 4) {
                listaRespuestasIncorrectas.clear();
                for (int i = 0; i < 3; i++) {
                    int corrector = 0;
                    int c = rd.nextInt(rangodivisionmax + 1);
                    while (c == respuestaCorrecta || yaEsta(c)) {
                        c = rd.nextInt(rangodivisionmax + 1 + corrector);
                        corrector++;
                    }
                    listaRespuestasIncorrectas.add(c);
                }
                return contexto.getString(R.string.div2, b, a);
            }
        } else if (listaOperaciones.get(tipoPregunta) == 5) {
            int a = rd.nextInt(rangopotenciamax + 1);
            int b = a * a;
            respuestaCorrecta = a * a;
            if (modo == 1) {
                return contexto.getString(R.string.pot, a, 2, b);
            } else if (modo == 2) {
                int c = rd.nextInt(rangopotenciamax * rangopotenciamax + 1);
                while (c == respuestaCorrecta) {
                    c = rd.nextInt(12 + 1);
                }
                return contexto.getString(R.string.pot, a, b, c);
            } else if (modo == 3) {
                return contexto.getString(R.string.pot2, a, 2);
            } else if (modo == 4) {
                listaRespuestasIncorrectas.clear();
                for (int i = 0; i < 3; i++) {
                    int corrector = 0;
                    int c = rd.nextInt(rangopotenciamax * rangopotenciamax + 1);
                    while (c == respuestaCorrecta || yaEsta(c)) {
                        c = rd.nextInt(rangopotenciamax * rangopotenciamax + 1 + corrector);
                        corrector++;
                    }
                    listaRespuestasIncorrectas.add(c);
                }
                return contexto.getString(R.string.pot2, a, 2);
            }
        } else if (listaOperaciones.get(tipoPregunta) == 6) {
            int a = rd.nextInt(rangoraizmax + 1);
            int b = a * a;
            respuestaCorrecta = a;
            if (modo == 1) {
                return contexto.getString(R.string.raiz, b, a);
            } else if (modo == 2) {
                int c = rd.nextInt(rangoraizmax + 1);
                while (c == respuestaCorrecta) {
                    c = rd.nextInt(rangoraizmax + 1);
                }
                return contexto.getString(R.string.raiz, b, c);
            } else if (modo == 3) {
                return contexto.getString(R.string.raiz2, b);
            } else if (modo == 4) {
                listaRespuestasIncorrectas.clear();
                for (int i = 0; i < 3; i++) {
                    int corrector = 0;
                    int c = rd.nextInt(rangoraizmax + 1);
                    while (c == respuestaCorrecta || yaEsta(c)) {
                        c = rd.nextInt(rangoraizmax + 1 + corrector);
                        corrector++;
                    }
                    listaRespuestasIncorrectas.add(c);
                }
                return contexto.getString(R.string.raiz2, b);
            }
        }
        return "Hubo un error";
    }

    private boolean yaEsta(int numero) {
        for (int i = 0; i < listaRespuestasIncorrectas.size(); i++) {
            if (listaRespuestasIncorrectas.get(i) == numero) {
                return true;
            }
        }
        return false;
    }


    public String preguntaDificil(int modo) {
        preferencias();
        Random rd = new Random();
        int tipoPregunta = rd.nextInt(listaOperaciones.size());
        if (listaOperaciones.get(tipoPregunta) == 1) {
            int a = rd.nextInt(rangosumadifmax) + rangosumadifmin;
            int b = rd.nextInt(rangosumadifmax) + rangosumadifmin;
            int c = rd.nextInt(rangosumadifmax) + rangosumadifmin;

            respuestaCorrecta = a + b + c;
            if (modo == 1) {
                return contexto.getString(R.string.triple_suma_modo1, a, b, c, respuestaCorrecta);
            } else if (modo == 2) {
                int d = rd.nextInt(rangosumadifmax * 3 + rangosumadifmin);
                while (d == respuestaCorrecta) {
                    d = rd.nextInt(rangosumadifmax * 3 + rangosumadifmin);
                }
                return contexto.getString(R.string.triple_suma_modo1, a, b, c, d);
            } else if (modo == 3) {
                return contexto.getString(R.string.triple_suma_modo2, a, b, c);
            } else if (modo == 4) {
                listaRespuestasIncorrectas.clear();
                for (int i = 0; i < 3; i++) {
                    int corrector = 0;
                    int d = rd.nextInt(rangosumadifmax * 3 + rangosumadifmin);
                    while (d == respuestaCorrecta || yaEsta(d)) {
                        d = rd.nextInt(rangosumadifmax * 3 + rangosumadifmin + corrector);
                        corrector++;
                    }
                    listaRespuestasIncorrectas.add(d);
                }
                return contexto.getString(R.string.sum2, a, b);
            }

        } else if (listaOperaciones.get(tipoPregunta) == 2) {
            respuestaCorrecta = rd.nextInt(rangorestadifmax) + rangorestadifmin;
            int b = rd.nextInt(rangorestadifmax) + rangorestadifmin;
            int c = rd.nextInt(rangorestadifmax) + rangorestadifmin;

            int a = respuestaCorrecta + b + c;
            if (modo == 1) {
                return contexto.getString(R.string.triple_resta_modo1, a, b, c, respuestaCorrecta);
            } else if (modo == 2) {
                int d = rd.nextInt(rangorestadifmax + rangorestadifmin);
                while (d == respuestaCorrecta) {
                    d = rd.nextInt(rangorestadifmax + rangorestadifmin);
                }
                return contexto.getString(R.string.triple_resta_modo1, a, b, c, d);
            } else if (modo == 3) {
                return contexto.getString(R.string.triple_resta_modo2, a, b, c);
            } else if (modo == 4) {
                listaRespuestasIncorrectas.clear();
                for (int i = 0; i < 3; i++) {
                    int corrector = 0;
                    int d = rd.nextInt(rangorestadifmax + rangorestadifmin);
                    while (d == respuestaCorrecta || yaEsta(d)) {
                        d = rd.nextInt(rangorestadifmax + rangorestadifmin + corrector);
                        corrector++;
                    }
                    listaRespuestasIncorrectas.add(d);
                }
                return contexto.getString(R.string.triple_resta_modo2, a, b, c);
            }
        } else if (listaOperaciones.get(tipoPregunta) == 3) {
            int a = rd.nextInt(rangomultiplicaciondifmax + 1);
            int b = rd.nextInt(rangomultiplicaciondifmax + 1);
            int c = rd.nextInt(rangomultiplicaciondifmax + 1);
            respuestaCorrecta = a * b * c;
            if (modo == 1) {
                return contexto.getString(R.string.triple_multiplicacion_modo1, a, b, c, respuestaCorrecta);
            } else if (modo == 2) {
                int d = rd.nextInt(rangomultiplicaciondifmax * rangomultiplicaciondifmax * rangomultiplicaciondifmax + 1);
                while (d == respuestaCorrecta) {
                    d = rd.nextInt(rangomultiplicaciondifmax * rangomultiplicaciondifmax * rangomultiplicaciondifmax + 1);
                }
                return contexto.getString(R.string.triple_resta_modo1, a, b, c, d);
            } else if (modo == 3) {
                return contexto.getString(R.string.triple_multiplicacion_modo2, a, b, c);
            } else if (modo == 4) {
                listaRespuestasIncorrectas.clear();
                for (int i = 0; i < 3; i++) {
                    int corrector = 0;
                    int d = rd.nextInt(rangomultiplicaciondifmax * rangomultiplicaciondifmax * rangomultiplicaciondifmax + 1);
                    while (d == respuestaCorrecta || yaEsta(d)) {
                        d = rd.nextInt(rangomultiplicaciondifmax * rangomultiplicaciondifmax * rangomultiplicaciondifmax + 1 + corrector);
                        corrector++;
                    }
                    listaRespuestasIncorrectas.add(d);
                }
                return contexto.getString(R.string.triple_multiplicacion_modo2, a, b, c);
            }
        } else if (listaOperaciones.get(tipoPregunta) == 4) {
            respuestaCorrecta = rd.nextInt(rangodivisiondifmax + 1) + 1;
            int b = rd.nextInt(rangodivisiondifmax + 1) + 1;
            int c = rd.nextInt(rangodivisiondifmax + 1) + 1;
            int a = b * c * respuestaCorrecta;
            if (modo == 1) {
                return contexto.getString(R.string.triple_division_modo1, a, b, c, respuestaCorrecta);
            } else if (modo == 2) {
                int d = rd.nextInt(rangodivisiondifmax + 1);
                while (d == respuestaCorrecta) {
                    d = rd.nextInt(rangodivisiondifmax + 1);
                }
                return contexto.getString(R.string.triple_division_modo1, a, b, c, d);
            } else if (modo == 3) {
                return contexto.getString(R.string.triple_division_modo2, a, b, c);
            } else if (modo == 4) {
                listaRespuestasIncorrectas.clear();
                for (int i = 0; i < 3; i++) {
                    int corrector = 0;
                    int d = rd.nextInt(rangodivisiondifmax + 1);
                    while (d == respuestaCorrecta || yaEsta(d)) {
                        d = rd.nextInt(rangodivisiondifmax + 1 + corrector);
                        corrector++;
                    }
                    listaRespuestasIncorrectas.add(d);
                }
                return contexto.getString(R.string.triple_division_modo2, a, b, c);
            }
        }
        if (listaOperaciones.get(tipoPregunta) == 5) {
            int a = rd.nextInt(rangosumadifmax) + rangosumadifmin;
            int b = rd.nextInt(rangosumadifmax) + rangosumadifmin;
            int c =  rd.nextInt(3) + 2;

            respuestaCorrecta = (a + b) * c;
            if (modo == 1) {
                return contexto.getString(R.string.triple_suma_mult_modo1, a, b, c, respuestaCorrecta);
            } else if (modo == 2) {
                int d = rd.nextInt((rangosumadifmax * 2 + rangosumadifmin) + c);
                while (d == respuestaCorrecta) {
                    d = rd.nextInt((rangosumadifmax * 2 + rangosumadifmin) * c);
                }
                return contexto.getString(R.string.triple_suma_mult_modo1, a, b, c, d);
            } else if (modo == 3) {
                return contexto.getString(R.string.triple_suma_mult_modo2, a, b, c);
            } else if (modo == 4) {
                listaRespuestasIncorrectas.clear();
                for (int i = 0; i < 3; i++) {
                    int corrector = 0;
                    int d = rd.nextInt((rangosumadifmax * 2 + rangosumadifmin) + c);
                    while (d == respuestaCorrecta || yaEsta(d)) {
                        d = rd.nextInt((rangosumadifmax * 2 + rangosumadifmin) + c + corrector);
                        corrector++;
                    }
                    listaRespuestasIncorrectas.add(d);
                }
                return contexto.getString(R.string.triple_suma_mult_modo2, a, b, c);
            }

        }
        if (listaOperaciones.get(tipoPregunta) == 6) {
            int a = rd.nextInt(rangodivisiondifmax) + rangosumadifmin;
            int c = rd.nextInt(rangodivisiondifmax) ;
            respuestaCorrecta = rd.nextInt(rangodivisiondifmax);
            int b = rd.nextInt(c * respuestaCorrecta+1)-2;
            if (modo == 1) {
                return contexto.getString(R.string.triple_suma_div_modo1, a, b, c, respuestaCorrecta);
            } else if (modo == 2) {
                int d = rd.nextInt(c * respuestaCorrecta+1)-2;
                while (d == respuestaCorrecta) {
                    d = rd.nextInt(c * respuestaCorrecta+1)-2;
                }
                return contexto.getString(R.string.triple_suma_div_modo1, a, b, c, d);
            } else if (modo == 3) {
                return contexto.getString(R.string.triple_suma_div_modo2, a, b, c);
            } else if (modo == 4) {
                listaRespuestasIncorrectas.clear();
                for (int i = 0; i < 3; i++) {
                    int corrector = 0;
                    int d = rd.nextInt(c * respuestaCorrecta+1)-2;
                    while (d == respuestaCorrecta || yaEsta(d)) {
                        d = rd.nextInt(c * respuestaCorrecta+1)-2;
                        corrector++;
                    }
                    listaRespuestasIncorrectas.add(d);
                }
                return contexto.getString(R.string.triple_suma_div_modo2, a, b, c);

            }

        }
        if (listaOperaciones.get(tipoPregunta) == 7) {
            int a = rd.nextInt(rangosumadifmax) + rangosumadifmin;
            int b = rd.nextInt(rangosumadifmax) + rangosumadifmin;
            int c =  rd.nextInt(3) + 2;

            respuestaCorrecta = (a - b) * c;
            if (modo == 1) {
                return contexto.getString(R.string.triple_resta_mult_modo1, a, b, c, respuestaCorrecta);
            } else if (modo == 2) {
                int d = rd.nextInt((rangosumadifmax * 2 + rangosumadifmin) * c);
                while (d == respuestaCorrecta) {
                    d = rd.nextInt((rangosumadifmax * 2 + rangosumadifmin) * c);
                }
                return contexto.getString(R.string.triple_resta_mult_modo1, a, b, c, d);
            } else if (modo == 3) {
                return contexto.getString(R.string.triple_resta_mult_modo2, a, b, c);
            } else if (modo == 4) {
                listaRespuestasIncorrectas.clear();
                for (int i = 0; i < 3; i++) {
                    int corrector = 0;
                    int d = rd.nextInt((rangosumadifmax * 2 + rangosumadifmin) * c);
                    while (d == respuestaCorrecta || yaEsta(d)) {
                        d = rd.nextInt((rangosumadifmax * 2 + rangosumadifmin) * c + corrector);
                        corrector++;
                    }
                    listaRespuestasIncorrectas.add(d);
                }
                return contexto.getString(R.string.triple_resta_mult_modo2, a, b, c);
            }

        }
        if (listaOperaciones.get(tipoPregunta) == 8) {
            int a = rd.nextInt(rangodivisiondifmax) + rangosumadifmin;
            int c = rd.nextInt(rangodivisiondifmax);
            respuestaCorrecta = rd.nextInt(rangodivisiondifmax);
            int b = rd.nextInt(c * respuestaCorrecta+1)-2;
            a = b + c*respuestaCorrecta;
            if (modo == 1) {
                return contexto.getString(R.string.triple_resta_div_modo1, a, b, c, respuestaCorrecta);
            } else if (modo == 2) {
                int d = rd.nextInt(c * respuestaCorrecta+1)-2;
                while (d == respuestaCorrecta) {
                    d = rd.nextInt(c * respuestaCorrecta+1)-2;
                }
                return contexto.getString(R.string.triple_resta_div_modo1, a, b, c, d);
            } else if (modo == 3) {
                return contexto.getString(R.string.triple_resta_div_modo2, a, b, c);
            } else if (modo == 4) {
                listaRespuestasIncorrectas.clear();
                for (int i = 0; i < 3; i++) {
                    int corrector = 0;
                    int d = rd.nextInt(c * respuestaCorrecta+1)-2;
                    while (d == respuestaCorrecta || yaEsta(d)) {
                        d = rd.nextInt(c * respuestaCorrecta+1+corrector)-2;
                        corrector++;
                    }
                    listaRespuestasIncorrectas.add(d);
                }
                return contexto.getString(R.string.triple_resta_div_modo2, a, b, c);

            }

        }
        else if (listaOperaciones.get(tipoPregunta) == 9) {
            int a = rd.nextInt(rangopotenciadifmax + 1);
            int b = a * a;
            respuestaCorrecta = a * a;
            if (modo == 1) {
                return contexto.getString(R.string.pot, a, 2, b);
            } else if (modo == 2) {
                int c = rd.nextInt(rangopotenciadifmax * rangopotenciadifmax + 1);
                while (c == respuestaCorrecta) {
                    c = rd.nextInt(12 + 1);
                }
                return contexto.getString(R.string.pot, a, b, c);
            } else if (modo == 3) {
                return contexto.getString(R.string.pot2, a, 2);
            } else if (modo == 4) {
                listaRespuestasIncorrectas.clear();
                for (int i = 0; i < 3; i++) {
                    int corrector = 0;
                    int c = rd.nextInt(rangopotenciadifmax * rangopotenciadifmax + 1);
                    while (c == respuestaCorrecta || yaEsta(c)) {
                        c = rd.nextInt(rangopotenciadifmax * rangopotenciadifmax + 1 + corrector);
                        corrector++;
                    }
                    listaRespuestasIncorrectas.add(c);
                }
                return contexto.getString(R.string.pot2, a, 2);
            }
        } else if (listaOperaciones.get(tipoPregunta) == 10) {
            int a = rd.nextInt(rangoraizdifmax + 1);
            int b = a * a;
            respuestaCorrecta = a;
            if (modo == 1) {
                return contexto.getString(R.string.raiz, b, a);
            } else if (modo == 2) {
                int c = rd.nextInt(rangoraizdifmax + 1);
                while (c == respuestaCorrecta) {
                    c = rd.nextInt(rangoraizdifmax + 1);
                }
                return contexto.getString(R.string.raiz, b, c);
            } else if (modo == 3) {
                return contexto.getString(R.string.raiz2, b);
            } else if (modo == 4) {
                listaRespuestasIncorrectas.clear();
                for (int i = 0; i < 3; i++) {
                    int corrector = 0;
                    int c = rd.nextInt(rangoraizdifmax + 1);
                    while (c == respuestaCorrecta || yaEsta(c)) {
                        c = rd.nextInt(rangoraizdifmax + 1 + corrector);
                        corrector++;
                    }
                    listaRespuestasIncorrectas.add(c);
                }
                return contexto.getString(R.string.raiz2, b);
            }
        }
        return "Hubo un error";
    }
}
