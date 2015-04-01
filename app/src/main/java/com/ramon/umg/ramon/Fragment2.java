package com.ramon.umg.ramon;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Fragment2 extends Fragment {
    /**
     * tvSensores: TextView que muestra los datos enviados por los sensores del drone.
     */
    private static TextView tvSensores;
    /**
     * tvSensoresDesactualizados: TextView predefinido, que se hace visible sí y solo sí se desconecta el drone.
     */
    private static TextView tvSensoresDesactualizados;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment2, container, false);
        tvSensores = (TextView)view.findViewById(R.id.tvInfoSensores);
        tvSensoresDesactualizados = (TextView)view.findViewById(R.id.tvSensoresDesactualizados);
        return view;
    }

    /**
     * actualizatrSensores: Actualiza el texto de tvSensores y hace invisible a tvSensoresDesactualizados
     * @param text es el texto, ya con el formato, que se mostrará al usuario en la app.
     */
    public static void actualizarSensores(String text){
        tvSensoresDesactualizados.setVisibility(View.INVISIBLE);
        if (!text.isEmpty())
            tvSensores.setText(text);
    }

    /**
     * avisoSensoresDesactualizados: Se debe llamar siempre que se pierda conexión, avisa al usuario que la información
     * de los sensores no está actualizada.
     */
    public static void avisoSensoresDesactualizados(){
        tvSensoresDesactualizados.setVisibility(View.VISIBLE);
    }
}