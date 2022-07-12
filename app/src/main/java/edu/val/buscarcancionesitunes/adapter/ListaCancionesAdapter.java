package edu.val.buscarcancionesitunes.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.val.buscarcancionesitunes.R;
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

        //TODO programar el evento para cuando toque cada fila

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
