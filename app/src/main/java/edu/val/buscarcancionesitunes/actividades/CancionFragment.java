package edu.val.buscarcancionesitunes.actividades;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.squareup.picasso.Picasso;

import edu.val.buscarcancionesitunes.R;
import edu.val.buscarcancionesitunes.modelo.Cancion;

public class CancionFragment extends Fragment {

    private ImageView imagen_disco;
    private ImageView imagen_play_pause;
    private TextView artista_cancion;
    private TextView titulo_cancion;
    private MediaPlayer mediaPlayer;//para reproducir la cancion
    private SeekBar seekBar;

    private boolean sonando;
    private boolean pausado;
    private Cancion cancion;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = null;

            view = inflater.inflate(R.layout.fragment_cancion, container, false);

            Bundle saquito = getArguments();
            this.cancion = saquito.getParcelable("cancion");

            this.imagen_disco = view.findViewById(R.id.imagen_disco);
            this.titulo_cancion = view.findViewById(R.id.titulo_cancion);
            this.artista_cancion = view.findViewById(R.id.artista_cancion);
            this.imagen_play_pause = view.findViewById(R.id.play_pause);
            this.seekBar = view.findViewById(R.id.seekbar);

            this.sonando = false;
            this.pausado = false;

            //necesito cargar la imagen de la canción URL
            Picasso.get().load(this.cancion.getArtworkUrl100()).into(this.imagen_disco);
            this.artista_cancion.setText(this.cancion.getArtistName());
            this.titulo_cancion.setText(this.cancion.getTrackName());


        return view;
    }
}
