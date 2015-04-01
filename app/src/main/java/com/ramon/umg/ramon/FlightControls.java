package com.ramon.umg.ramon;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Vibrator;

/**
 *Autor Luis Alfonso Chávez Abbadie
 * Proyecto Ramón
 * Integrantes : Luis Alfonso, José Manuel Correa y Fernanda Islas
 * POR FAVOR, SIEMPRE HAY QUE LEER LOS COMENTARIOS Y DOCUMENTACION ANTES DE MODIFICAR CODIGO, Y CUANDO LO HAGAS
 * DEJA UN COMENTARIO INDICANDO LO QUE HACE
 *
 * FlighControls es la Activity main del proyecto
 */
public class FlightControls extends FragmentActivity {  //Activity principal
    /**
     * viewpager: Encargado de la vista de los 3 fragments.
     */
    private ViewPager viewpager;
    /**
     * aterrizaje: Bandera que indica el estado del mecanismo de aterrizaje. 0 = mecanismo guardado, 1 = mecanismo expuesto
     */
    public boolean aterrizaje = false;
    /**
     * conexion: Bandera que indica el estado de la conexion con el drone. 0 = desconectado, 1 = conectado
     */
    public static boolean conexion = false;    //Siempre que se modifique esta bandera, hay que llamar al metodo actualizarConexion()
    /**
     * tvconexion: TextView que se utilizará para acceder al letrero de conexión que forma parte de esta activity.
     */
    private TextView tvconexion;
    /**
     * vib: Instancia de la clase Vibrator, se utiliza para que el telefono vibre cuando se pulse algún botón.
     */
    Vibrator vib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_controls);
        vib = (Vibrator)this.getApplicationContext().getSystemService(getApplicationContext().VIBRATOR_SERVICE);
        tvconexion = (TextView)findViewById(R.id.tvEstadoConexion);
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

    /**
     * actualizarConexion: Actualiza el letrero de conexión, y se encarga de todos los procesos que se alteran cuando
     * se pierde o se recupera la conexión.
     */
    private void actualizarConexion(){
        ImageButton power = (ImageButton)findViewById(R.id.ibPower);
        ImageButton actualizar = (ImageButton)findViewById(R.id.ibReload);
        String textSensores = "";       //AÑADIR TEXTO RECIBIDO DE LOS SENSORES YA CON FORMATO A MOSTRAR

        if (conexion && (tvconexion != null)) {
            tvconexion.setText(getResources().getString(R.string.EstadoConexion1));
            tvconexion.setTextColor(getResources().getColor(R.color.verde));
            power.setVisibility(View.VISIBLE);
            actualizar.setVisibility(View.INVISIBLE);
            Fragment2.actualizarSensores(textSensores);
        }
        else if (tvconexion != null) {
            tvconexion.setText(getResources().getString(R.string.EstadoConexion0));
            tvconexion.setTextColor(getResources().getColor(R.color.rojo));
            power.setVisibility(View.INVISIBLE);
            actualizar.setVisibility(View.VISIBLE);
            Fragment2.avisoSensoresDesactualizados();
        }
    }

    /**
     * clickActualizar: Envia un bit al arduino y espera la respueta de este, si esta llega, se establece el modo conectado,
     * si no, se mantiene la desconexión.
     * @param v
     */
    public void clickActualizar(View v){
        //INSERTAR CODIGO PARA ENVIAR UN DATO AL ARDUINO Y ESPERAR UNA RESPUESTA
        vib.vibrate(200);
        makeToast("Buscando conexión...");
    }

    /**
     * clickAterrizaje: Envía un dato al arduino que le indica que guarde o desplegue el patín de aterrizaje.
     * @param v
     */
    public void clickAterrizaje(View v){
        if (!conexion)
            return;
        vib.vibrate(200);
        Toast toast;
        if (aterrizaje) {
            makeToast("Guardando Patín de Aterrizaje");
            aterrizaje = false;
        } else {
            makeToast("Desplegando Patín de Aterrizaje");
            aterrizaje = true;
        }
    }

    /**
     * clickPower: Lanza un dialogo de confirmación cuando el usuario pulse el botón de power.
     * @param v
     */
    public void clickPower(View v) {
        vib.vibrate(200);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("¿Segur@?");
        builder.setMessage("Una posición de despeje o aterrizaje comprometida, podría afectar la integridad de Ramón.");

        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                //ENVIAR DATO AL ARDUINO INDICANDO QUE SE APAGUEN/PRENDAN LOS MOTORES
                dialog.dismiss();
            }

        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    /**
     * makeToast: Crea un toast con el texto que se le envíe.
     * @param texto : Es el texto a mostrar.
     */
    private void makeToast(String texto){
        Toast toast = Toast.makeText(getApplicationContext(), texto, Toast.LENGTH_SHORT);
        toast.show();
    }
}