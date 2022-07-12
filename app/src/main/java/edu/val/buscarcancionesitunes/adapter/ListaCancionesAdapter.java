package edu.val.buscarcancionesitunes.adapter;

import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.val.buscarcancionesitunes.R;
import edu.val.buscarcancionesitunes.actividades.ListaCancionesActivity;
import edu.val.buscarcancionesitunes.modelo.Cancion;

public class ListaCancionesAdapter extends RecyclerView.Adapter<CancionViewHolder> {

    private List<Cancion> lista_canciones;

    public ListaCancionesAdapter (List<Cancion> lista_canciones_obtenidas)
    {
        this.lista_canciones = lista_canciones_obtenidas;
    }

    @NonNull
    @Override
    public CancionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CancionViewHolder cancionViewHolder = null;

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.fila_cancion, parent, false);
        cancionViewHolder = new CancionViewHolder(view);

        //TODO programar el evento para cuando toque cada fila --ir a ver el detalle de la canción
        cancionViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int posicion = (int) view.getTag();
                Log.d("ETIQUETA_LOG", "ha tocado la fila " + posicion);
                Intent intent = new Intent(view.getContext(), ListaCancionesActivity.class);
                //TODO pasarle la posición y la lista de canciones a la actividad destino
                intent.putExtra("posicion", posicion);
                intent.putParcelableArrayListExtra("lista_canciones", (ArrayList<Cancion>) lista_canciones);
                view.getContext().startActivity(intent);
            }
        });

        return cancionViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CancionViewHolder holder, int position) {

        Cancion cancion = this.lista_canciones.get(position);
        holder.cargarCancionEnHolder(cancion, position);
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return this.lista_canciones.size();
    }
}
