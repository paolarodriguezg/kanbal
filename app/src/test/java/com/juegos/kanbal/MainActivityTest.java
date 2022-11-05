package com.juegos.kanbal;

import static org.junit.Assert.*;

import org.junit.Test;

public class MainActivityTest {


    @Test
    public void traducir() {
        String sharedPref = "q'eqchi'";
        String frase = "Correo";
        String fraseTraducida = "";
        if(sharedPref == "q'eqchi'")
        {
            fraseTraducida = "Coreeo";
        }
        assertEquals(fraseTraducida, "Coreeo");
    }



    @Test
    public void ingresar() {
        String correo = "primergrado@gmail.com";
        String contraseña = "primero123";
        assertEquals(correo,"primergrado@gmail.com");
        assertEquals(contraseña,"primero123");

    }
}