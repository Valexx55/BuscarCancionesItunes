package edu.val.buscarcancionesitunes.remoto;


//va a ser el programa "ROJO"- AsyncTask
//en el interior de esta clase, se va a producir la comunicación
//HTTP con el servidor

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import edu.val.buscarcancionesitunes.modelo.ResultadoBusquedaCanciones;
import edu.val.buscarcancionesitunes.actividades.MainActivity;

//String - paramétro de entrada
//VOID - no voy a contabilizar el avance no uso ningún número
//ResultadoBusquedaCanciones tipo de dato devuelto
public class BuscarEnItunes extends AsyncTask<String, Void, ResultadoBusquedaCanciones> {

    private static final String URL_ITUNES = "https://itunes.apple.com/search/?media=music&term=";

    private Context actividad_llamante;//MainActivity

    public BuscarEnItunes (Context context)
    {
        this.actividad_llamante = context;
    }

    @Override //en este método, se lleva a cabo la comunicación HTTP
    protected ResultadoBusquedaCanciones doInBackground(String... strings) {
        ResultadoBusquedaCanciones resultadoBusquedaCanciones = null;
        String busqueda = strings[0];// ... varargs es como un array
        URL url = null; //aquí vamos a poner la ruta
        HttpURLConnection httpURLConnection = null;//esta clase representa el mensajes / la comunicación http
        Gson gson = null;//este objeto me ayuda a (des)serializar JSON a JAVA
        InputStreamReader inputStreamReader = null; //leo el cuerpo del mensaje
        try {
            //todo el proceso de comunicación es susceptible de lanzar una execepción
            //por eso, vamos a agruparlo en un try-catch
            busqueda = URLEncoder.encode(busqueda, "UTF-8");
            busqueda = URL_ITUNES + busqueda;
            Log.d("ETIQUETA_LOG", "URL búsqueda = " + busqueda);
            url = new URL(busqueda);
            Log.d("ETIQUETA_LOG", "URL búsqueda url = " + url);
            httpURLConnection = (HttpURLConnection) url.openConnection(); //porque sé que el tipo de conexión es HTTP
            httpURLConnection.setRequestMethod("GET");//consultar, obtener info del servidor, no envío nada (el cuerpo de la petición ,va vacío)
            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) //200
            {
                Log.d("ETIQUETA_LOG", "La conexión ha ido bien! - Respuesta OK");
                //accedo al cuerpo de la respuesta httpURLConnection.getInputStream()
                //uso la clase InputStream para leer ese cuerpo
                Log.d("ETIQUETA_LOG", "Obtenidos " +httpURLConnection.getContentLength() + " bytes" );
                Log.d("ETIQUETA_LOG", "TIPO MIME " +httpURLConnection.getContentType() );
                inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
                gson = new Gson(); //para pasar el cuerpo de JSON a Resultado canciones
                resultadoBusquedaCanciones = gson.fromJson(inputStreamReader, ResultadoBusquedaCanciones.class);
            }

        } catch (Exception e) {
            Log.e("ETIQUETA_LOG", "Algo ha salido mal", e);
        } finally {
            //liberar recursos
            try {
                inputStreamReader.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
            httpURLConnection.disconnect();


        }


        return resultadoBusquedaCanciones;
    }

    @Override //este otro método, es invocado, al finalizar la conexión
    protected void onPostExecute(ResultadoBusquedaCanciones resultadoBusquedaCanciones) {
        //super.onPostExecute(resultadoBusquedaCanciones);
        Log.d("ETIQUETA_LOG", "en onPostExecute ... comunicación terminada");
        //¿Cómo le aviso a la clase MAIN que he acabado ?¿
        MainActivity actividad_principal_UI = ((MainActivity) this.actividad_llamante);
        actividad_principal_UI.mostrarResultados(resultadoBusquedaCanciones);

    }
}
