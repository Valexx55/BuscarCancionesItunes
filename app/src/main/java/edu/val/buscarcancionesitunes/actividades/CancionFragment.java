package edu.val.buscarcancionesitunes.actividades;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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

import java.util.Timer;
import java.util.TimerTask;

import edu.val.buscarcancionesitunes.R;
import edu.val.buscarcancionesitunes.modelo.Cancion;

public class CancionFragment extends Fragment implements View.OnClickListener, MediaPlayer.OnCompletionListener {

    private ImageView imagen_disco;
    private ImageView imagen_play_pause;
    private TextView artista_cancion;
    private TextView titulo_cancion;
    private MediaPlayer mediaPlayer;//para reproducir la cancion
    private SeekBar seekBar;

    private boolean sonando;
    private boolean pausado;
    private Cancion cancion;

    private Timer timer;


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

        this.imagen_play_pause.setOnClickListener(this);


        return view;
    }

    @Override
    public void onStop() {
        Log.d("ETIQUETA_LOG", "onStop()");
        //TODO MEJORA; haced que cuando transite de fragment, si la canción se está reproduciendo, pararla
        super.onStop();
    }



    @Override
    public void onClick(View view) {

        if (sonando) //canción iniciada
        {
            //
            Log.d("ETIQUETA_LOG", "Entramos en pausa");
            mediaPlayer.pause();
            this.imagen_play_pause.setImageResource(R.drawable.ic_baseline_play_arrow_24);
            sonando = false;
            pausado = true;

        } else if (pausado) {
            Log.d("ETIQUETA_LOG", "Reanudar la canción tras la pausa");
            mediaPlayer.seekTo(mediaPlayer.getCurrentPosition());//avanzo el mediaplater a ese momento de la cancíón
            mediaPlayer.start();//inicio desde donde se quedó
            this.imagen_play_pause.setImageResource(R.drawable.ic_baseline_pause_circle_outline_24);
            pausado = false;
            sonando = true;
        } else //estaba en STOP - 1º vez
        {
            Log.d("ETIQUETA_LOG", "Reproducir la canción de primeras");
            Uri ruta_cancion = Uri.parse(cancion.getPreviewUrl());//convierto el string a la uri

            this.mediaPlayer = MediaPlayer.create(getContext(), ruta_cancion);
            this.mediaPlayer.setOnCompletionListener(this);
            mediaPlayer.start();//reproduzco la canción
            this.imagen_play_pause.setImageResource(R.drawable.ic_baseline_pause_circle_outline_24);
            this.sonando = true;


            //cada segundo voy avanznado y corriendo la barrita del tiempo "seekBar"
            timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                }
            }, 0, 1000);
        }
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        Log.d("ETIQUETA_LOG", "La canción ha finalizado");
        this.mediaPlayer.stop();
        this.seekBar.setProgress(0);//la pongo al principio
        this.imagen_play_pause.setImageResource(R.drawable.ic_baseline_play_arrow_24);
        timer.cancel();//cancelo el anvace de la seekbar - detengo la alarma
    }
}
