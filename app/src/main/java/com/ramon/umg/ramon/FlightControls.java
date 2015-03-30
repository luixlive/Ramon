package com.ramon.umg.ramon;

import java.util.Locale;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;


public class FlightControls extends FragmentActivity {

    private ViewPager viewpager;
    private boolean aterrizaje = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_controls);
        viewpager = (ViewPager)findViewById(R.id.pager);
        PagerAdapter padapter = new PagerAdapter(getSupportFragmentManager());
        viewpager.setAdapter(padapter);
    }

    public void clickActualizar(View v){
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
