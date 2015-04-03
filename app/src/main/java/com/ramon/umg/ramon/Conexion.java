package com.ramon.umg.ramon;

import android.os.Handler;

import java.io.IOException;
import java.util.LinkedList;

import serialUSB.ISerial;
import serialUSB.SerialEvent;
import serialUSB.SerialListener;
import serialUSB.UsbDeviceID;
import serialUSB.UsbSerial;

/**
 * Created by JCORREA on 30/03/2015.
 * Esta clase soporta las acciones para escribir y escuchar
 * el puerto serial, vital para la comunicacion con Ramon
 */
public class Conexion {

    private static int tiempoCompruebaConexion = 5000;
    private static Handler handler=new Handler();
    private static ISerial usb_serial=null;
    private static LinkedList<String> data_raw = new LinkedList<String>();
    private static volatile String datos = new String();

    /**
     * Este metodo inicializa la configuracion del puerto serial, y crea el lister
     * que estara escuchando el puerto serial para leer los datos que lleguen
     */
    public static void setConexion(FlightControls activity, int baudRate){
        if(usb_serial != null)
            return;

        usb_serial = new UsbSerial(UsbDeviceID.FT232RL,baudRate,activity);
        usb_serial.open();

        usb_serial.addEventListener(new SerialListener() {

            @Override
            public void incomingDataEvent(final SerialEvent evt) {

                handler.post(new Runnable(){

                    @Override
                    public void run() {
                        data_raw.addFirst(new String(evt.read()));

                        StringBuilder data = new StringBuilder();
                        for(String c : data_raw){
                            data.append(c);
                        }
                        datos.concat(String.valueOf(data));
                        if(data_raw.size() > 20){
                            data_raw.removeLast();
                        }

                    }
                });
            }

        });
    }

    /**
     * Este metodo verifica que exista una conexion con Ramon
     * @return True si existe la conexion, False si no existe
     */
    public static Boolean pruebaConexion() throws IOException {
        String datos = lee();
        if(datos != null)
            return true;
        return false;
    }

    /**
     * Este metodo lee el puerto serial y
     * toma lo que hay en este
     * @return String con datos leidos
     */
    public static String lee() throws IOException {
        String cadena = datos;
        datos = new String();
        return cadena;
    }

    /**
     * Este metodo escribe en el serial del dispositivo
     * Android
     * @param dato (byte)
     */
    public static void escribe(byte dato){
        byte [] datos = new byte [1];
        datos[0]= dato;
        usb_serial.write(datos);
    }

    /**
     * Este metod escribe en el serial del dispositivo
     * el String que se pasa como parametro
     * @param dato String que se quiere escribir en el puerto serial
     */
    public static void escribe(String dato){
        usb_serial.write(dato);
    }

    public static int getTiempoCompruebaConexion() {
        return tiempoCompruebaConexion;
    }
    public static void setTiempoCompruebaConexion(int tiempoCompruebaConexion) {
        Conexion.tiempoCompruebaConexion = tiempoCompruebaConexion;
    }
}
