package com.ramon.umg.ramon;

import java.util.Locale;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class FlightControls extends FragmentActivity {

    private ViewPager viewpager;
    public boolean aterrizaje = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_controls);
        viewpager = (ViewPager)findViewById(R.id.pager);
        PagerAdapter padapter = new PagerAdapter(getSupportFragmentManager());
        viewpager.setAdapter(padapter);
        //Cuando un boton es presionado, y se arrastra hacia la siguiente pagina (se cambia de fragmen el viewpager)
        //los botones deben reiniciarse (volver a su imagen original) y enviar el codigo al arduino de que ya no se
        //debe seguir moviendo.
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {
            }
            @Override
            public void onPageSelected(int i) {
            }
            @Override
            public void onPageScrollStateChanged(int i) {
                if (viewpager.getCurrentItem() == 0)
                    Fragment1.inicializarBotones(); // ESTE METODO Y ACTUALIZAR PARA QUE LE MANDE AL ARDUINO LA VARIABLE QUE
            }                                   //INDICA QUE YA DEJO DE SER PRESIONADO EL BOTON
        });
    }

    public void clickActualizar(View v){
        //INSERTAR CODIGO PARA ENVIAR UN DATO AL ARDUINO Y ESPERAR UNA RESPUESTA
        makeToast("Buscando Conexión...");
    }

    public void clickAterrizaje(View v){
        Toast toast;
        if (aterrizaje) {
            makeToast("Guardando Patín de Aterrizaje");
            aterrizaje = false;
        } else {
            makeToast("Desplegando Patín de Aterrizaje");
            aterrizaje = true;
        }
    }

    private void makeToast(String texto){
        Toast toast = Toast.makeText(getApplicationContext(), texto, Toast.LENGTH_SHORT);
        toast.show();
    }
}
