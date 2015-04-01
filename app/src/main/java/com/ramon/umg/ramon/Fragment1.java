package com.ramon.umg.ramon;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Fragment1: Clase que hereda de fragment, mostrará controles de vuelo y botón para despleguar o guardar el patín de aterrizaje.
 */
public class Fragment1 extends Fragment {
    /**
     * bAdelante: Botón para hacer al drone moverse hacia adelante.
     */
     public static ImageButton bAdelante;
    /**
     * bAtras: Botón para hacer al drone moverse hacia atrás.
     */
     public static ImageButton bAtras;
    /**
     * bDerecha: Botón para hacer al drone moverse hacia la derecha.
     */
     public static ImageButton bDerecha;
    /**
     * bIzquierda: Botón para hacer al drone moverse hacia la izquierda.
     */
     public static ImageButton bIzquierda;
    /**
     * bArriba: Botón para hacer al drone moverse hacia arriba.
     */
     public static ImageButton bArriba;
    /**
     * bAbajo: Botón para hacer al drone moverse hacia abajo.
     */
     public static ImageButton bAbajo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment1, container, false);

        bAdelante = (ImageButton)view.findViewById(R.id.ibAdelante);
        bAtras = (ImageButton)view.findViewById(R.id.ibAtras);
        bDerecha = (ImageButton)view.findViewById(R.id.ibDerecha);
        bIzquierda = (ImageButton)view.findViewById(R.id.ibIzquierda);
        bArriba = (ImageButton)view.findViewById(R.id.ibArriba);
        bAbajo = (ImageButton)view.findViewById(R.id.ibAbajo);

        //AÑADIR EN CADA EVENTO EL DATO QUE SE LE ENVIARÁ AL ARDUINO PARA QUE ESTE SE ENCARGUE DEL MOVIENTO
        bAdelante.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event){
                if (!FlightControls.conexion)
                    return true;
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    bAdelante.setImageResource(R.drawable.flecha_arriba);
                if (event.getAction() == MotionEvent.ACTION_UP)
                    bAdelante.setImageResource(R.drawable.flecha_adelante);
                return true;
            }
        });
        bAtras.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event){
                if (!FlightControls.conexion)
                    return true;
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    bAtras.setImageResource(R.drawable.flecha_abajo);
                if (event.getAction() == MotionEvent.ACTION_UP)
                    bAtras.setImageResource(R.drawable.flecha_atras);
                return true;
            }
        });
        bDerecha.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event){
                if (!FlightControls.conexion)
                    return true;
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    bDerecha.setImageResource(R.drawable.flecha_derecha2);
                if (event.getAction() == MotionEvent.ACTION_UP)
                    bDerecha.setImageResource(R.drawable.flecha_derecha);
                return true;
            }
        });
        bIzquierda.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event){
                if (!FlightControls.conexion)
                    return true;
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    bIzquierda.setImageResource(R.drawable.flecha_izquierda2);
                if (event.getAction() == MotionEvent.ACTION_UP)
                    bIzquierda.setImageResource(R.drawable.flecha_izquierda);
                return true;
            }
        });
        bArriba.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event){
                if (!FlightControls.conexion)
                    return true;
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    bArriba.setImageResource(R.drawable.flecha_adelante);
                if (event.getAction() == MotionEvent.ACTION_UP)
                    bArriba.setImageResource(R.drawable.flecha_arriba);
                return true;
            }
        });
        bAbajo.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event){
                if (!FlightControls.conexion)
                    return true;
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    bAbajo.setImageResource(R.drawable.flecha_atras);
                if (event.getAction() == MotionEvent.ACTION_UP)
                    bAbajo.setImageResource(R.drawable.flecha_abajo);
                return true;
            }
        });

        return view;
    }

    /**
     * inicializarBotones: Reinicia el estado de los botones del control de vuelo.
     */
    public static void inicializarBotones(){
        //ACTUALIZAR CUANDO TENGAMOS EL PROTOCOLO CON EL QUE LE DIREMOS EL MOVIENTO AL ARDUINO, PUES
        //ESTE METODO DEBE REINICIAR LOS BOTONES, LO QUE INDICA QUE YA NO ESTAN SIENDO PRESIONADOS
        bAdelante.setImageResource(R.drawable.flecha_adelante);
        bAtras.setImageResource(R.drawable.flecha_atras);
        bDerecha.setImageResource(R.drawable.flecha_derecha);
        bIzquierda.setImageResource(R.drawable.flecha_izquierda);
        bArriba.setImageResource(R.drawable.flecha_arriba);
        bAbajo.setImageResource(R.drawable.flecha_abajo);
    }

}
