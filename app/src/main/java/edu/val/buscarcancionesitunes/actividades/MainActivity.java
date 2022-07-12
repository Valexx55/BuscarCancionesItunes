package edu.val.buscarcancionesitunes.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import edu.val.buscarcancionesitunes.adapter.ListaCancionesAdapter;
import edu.val.buscarcancionesitunes.modelo.Cancion;
import edu.val.buscarcancionesitunes.remoto.BuscarEnItunes;
import edu.val.buscarcancionesitunes.R;
import edu.val.buscarcancionesitunes.modelo.ResultadoBusquedaCanciones;
import edu.val.buscarcancionesitunes.util.UtilRed;

//aquí vamos a poner el menú de búsqueda
//y listaremos las canciones
public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private SearchView cajabusqueda;
    private ProgressBar progressBar;
    private TextView textoNresultados;
    private ListaCancionesAdapter listaCancionesAdapter;
    private List<Cancion> listaCanciones;
    private RecyclerView recyclerViewCanciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.cajabusqueda = findViewById(R.id.cajabuscacanciones);
        this.cajabusqueda.setOnQueryTextListener(this);
        this.progressBar = findViewById(R.id.pbc);
        this.textoNresultados = findViewById(R.id.nresultados);
        this.recyclerViewCanciones = findViewById(R.id.recicler_canciones);
    }

    @Override
    public boolean onQueryTextSubmit(String termino_busqueda) {
        //si hay internet, pido la canción
        if (UtilRed.hayInternetViejo(this))
        {
            Log.d("ETIQUETA_LOG", "Hay internet");
            //haré visible el circulo de espera
            this.progressBar.setVisibility(View.VISIBLE);
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
        //haré invisible el circulo de espera
        this.progressBar.setVisibility(View.INVISIBLE);
        if (resultadoBusquedaCanciones!=null)
        {
            if (resultadoBusquedaCanciones.getResultCount()==0)
            {
                Log.d("ETIQUETA_LOG", "SIN RESULTADOS");
                Toast.makeText(this, "SIN RESULTADOS", Toast.LENGTH_LONG).show();
            } else {
                Log.d("ETIQUETA_LOG", "CANCIONES RECIBIDAS = " + resultadoBusquedaCanciones);
                this.listaCanciones = resultadoBusquedaCanciones.getResults();
                this.listaCancionesAdapter = new ListaCancionesAdapter(this.listaCanciones);
                this.recyclerViewCanciones.setAdapter(this.listaCancionesAdapter);
                this.recyclerViewCanciones.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
            }
            String texto_resultado = resultadoBusquedaCanciones.getResultCount() +" canciones recuperadas";
            this.textoNresultados.setText(texto_resultado);

        } else if ((resultadoBusquedaCanciones==null)|| (resultadoBusquedaCanciones.getResultCount()==0)) {
            Log.d("ETIQUETA_LOG", "SIN RESULTADOS");
            Toast.makeText(this, "SIN RESULTADOS INTÉNTELO MÁS TARDE", Toast.LENGTH_LONG).show();
        }

    }
}