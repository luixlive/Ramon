package com.ramon.umg.ramon;

/**
 * Created by JCORREA on 30/03/2015.
 * Esta clase tinen el unico objetivo de comunicar al Piloto de Ramon
 * los movimientos que tiene que realiza.
 * Contiene todas las instrucciones que Ramon soporta.
 */
public class Torre{

    public static final byte MODO_ESTATICO = 0;
    public static final byte DIR_IZQUIERDA = 1;
    public static final byte DIR_DERECHA = 2;
    public static final byte DIR_ADELANTE = 3;
    public static final byte DIR_ATRAS = 4;
    private static final byte DIR_BAJAR = 5;
    private static final byte DIR_SUBIR = 6;
    private static final byte ATERRIZA = 7;
    private static final byte TREN_ATERRIZAJE_CAMBIOESTADO = 8;
    private static final byte ON_OFF_MOTORES = 9;

    public static boolean desplazar(byte direccion){
        if(direccion < 5 && direccion > -1) {
            Conexion.escribe(direccion);
            return true;
        }
        return false;
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
    public static void guardarTrenAterrizaje(){
        Conexion.escribe(TREN_ATERRIZAJE_CAMBIOESTADO);
    }
    public static void despliegaTrenAterrizaje(){
        Conexion.escribe(TREN_ATERRIZAJE_CAMBIOESTADO);
    }
    /**
     * Este metodo cambia el estado de Ramon de encendido a apagado
     * o de pagado a encendido dependiendo del estado en el que se encuentre
     */
    public static void switchMotores(){
        Conexion.escribe(ON_OFF_MOTORES);
    }
}
