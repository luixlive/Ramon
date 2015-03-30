package com.ramon.umg.ramon;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment1.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment1 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment1, container, false);

        final ImageButton bAdelante = (ImageButton)view.findViewById(R.id.ibAdelante);
        final ImageButton bAtras = (ImageButton)view.findViewById(R.id.ibAtras);
        final ImageButton bDerecha = (ImageButton)view.findViewById(R.id.ibDerecha);
        final ImageButton bIzquierda = (ImageButton)view.findViewById(R.id.ibIzquierda);
        final ImageButton bArriba = (ImageButton)view.findViewById(R.id.ibArriba);
        final ImageButton bAbajo = (ImageButton)view.findViewById(R.id.ibAbajo);

        bAdelante.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event){
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
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    bAbajo.setImageResource(R.drawable.flecha_atras);
                if (event.getAction() == MotionEvent.ACTION_UP)
                    bAbajo.setImageResource(R.drawable.flecha_abajo);
                return true;
            }
        });

        return view;
    }

}
