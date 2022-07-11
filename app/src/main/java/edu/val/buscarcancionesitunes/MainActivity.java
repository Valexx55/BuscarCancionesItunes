package edu.val.buscarcancionesitunes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

//aquí vamos a poner el menú de búsqueda
//y listaremos las canciones
public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private SearchView cajabusqueda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.cajabusqueda = findViewById(R.id.cajabuscacanciones);
        this.cajabusqueda.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String termino_busqueda) {
        //si hay internet, pido la canción
        if (UtilRed.hayInternetViejo(this))
        {
            Log.d("ETIQUETA_LOG", "Hay internet");
            BuscarEnItunes buscarEnItunes = new BuscarEnItunes(this);
            buscarEnItunes.execute(termino_busqueda);//lanzo la búsqueda
        }
        else {
            Log.d("ETIQUETA_LOG", "NO Hay internet");
            Toast.makeText(this, "SIN CONEXIÓN A INTERNET", Toast.LENGTH_LONG).show();
        }
        //si no, mostramos un mensaje TOAST informativo
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    public void mostrarResultados (ResultadoBusquedaCanciones resultadoBusquedaCanciones)
    {
        Log.d("ETIQUETA_LOG", "CANCIONES RECIBIDAS = " + resultadoBusquedaCanciones);
        Log.d("ETIQUETA_LOG", "CANCIONES RECIBIDAS = " + resultadoBusquedaCanciones);
    }
}