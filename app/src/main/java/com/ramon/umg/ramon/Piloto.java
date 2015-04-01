package com.ramon.umg.ramon;

/**
 * Created by JCORREA on 30/03/2015.
 */
public class Piloto {

    public static final byte MODO_ESTATICO = 0;
    public static final byte DIR_IZQUIERDA = 1;
    public static final byte DIR_DERECHA = 2;
    public static final byte DIR_ADELANTE = 3;
    public static final byte DIR_ATRAS = 4;
    private static final byte DIR_BAJAR = 5;
    private static final byte DIR_SUBIR = 6;
    private static final byte ATERRIZA = 7;

    public static void desplazar(byte direccion){
        if(direccion < 5 && direccion > -1)
        Conexion.escribe(direccion);
    }

    public static void subir(){
        Conexion.escribe(DIR_SUBIR);
    }

    public static void bajar(){
        Conexion.escribe(DIR_BAJAR);
    }

    public static void aterriza(){
        Conexion.escribe(ATERRIZA);
    }
}
