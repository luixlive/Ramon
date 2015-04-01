package com.ramon.umg.ramon;

import android.hardware.usb.UsbManager;
import android.content.Context;
import com.hoho.android.usbserial.driver.UsbSerialDriver;
import com.hoho.android.usbserial.driver.UsbSerialProber;

import java.io.IOException;

/**
 * Created by JCORREA on 30/03/2015.
 */
public class Conexion {

    // Get UsbManager from Android.
    private static UsbManager manager;
    // Find the first available driver.
    private static UsbSerialDriver driver;
    private static int baudRate = 115200;
    private static int tiempoCompruebaConexion = 5000;

    /**
     * Este metodo configura y crea la conexion con
     * Ramon(configura puerto serial)
     */
    public static void setConexion(FlightControls activity, int baudRate){
        manager = (UsbManager) activity.getSystemService(Context.USB_SERVICE);
        driver = UsbSerialProber.acquire(manager);
        setBaudRate(baudRate);
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
        String datos = null;

        if (driver != null) try {
            driver.open();
            driver.setBaudRate(getBaudRate());

            byte buffer[] = new byte[16];
            int numBytesRead = driver.read(buffer, 1000);
            datos = buffer.toString();
        } catch (IOException e) {
            System.out.println("Error en la lectura de datos");
        } finally {
            driver.close();
        }
        return datos;
    }

    /**
     * Este metodo escribe en el serial del dispositivo
     * Android
     * @param dato (byte)
     */
    public static void escribe(byte dato){
        try {
            byte [] datos  = new byte[1];
            datos[0] = dato;
            driver.write(datos,1);
        } catch (IOException e) {
            System.out.println("Error al ecribir en el puerto");
            e.printStackTrace();
        }
    }

    public static int getBaudRate() {
        return baudRate;
    }
    public static void setBaudRate(int baudRate) {
        Conexion.baudRate = baudRate;
    }
    public static int getTiempoCompruebaConexion() {
        return tiempoCompruebaConexion;
    }
    public static void setTiempoCompruebaConexion(int tiempoCompruebaConexion) {
        Conexion.tiempoCompruebaConexion = tiempoCompruebaConexion;
    }
}
