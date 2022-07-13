package edu.val.buscarcancionesitunes.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import edu.val.buscarcancionesitunes.R;
import edu.val.buscarcancionesitunes.adapter.CancionesViewPagerAdapter;
import edu.val.buscarcancionesitunes.modelo.Cancion;

/**
 * esta actividad es para haya un fragment con su view pager, para poder ir deslizando por las canciones
 * viendo su detalle
 */
public class ListaCancionesActivity extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private CancionesViewPagerAdapter cancionesViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_canciones);
        Intent intent_oringen = getIntent();
        int posicion = intent_oringen.getIntExtra("posicion",0);
        ArrayList<Cancion> lista_canciones = intent_oringen.getParcelableArrayListExtra("lista_canciones");
        Log.d("ETIQUETA_LOG", "POS = " + posicion);
        Log.d("ETIQUETA_LOG", "lista_canciones = " + lista_canciones);

        this.viewPager2 = findViewById(R.id.viewpager);
        this.cancionesViewPagerAdapter = new CancionesViewPagerAdapter(this, lista_canciones);
        this.viewPager2.setAdapter(cancionesViewPagerAdapter);
        this.viewPager2.setCurrentItem(posicion, true);

    }
}