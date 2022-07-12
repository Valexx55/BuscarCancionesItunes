package edu.val.buscarcancionesitunes.adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

import edu.val.buscarcancionesitunes.actividades.CancionFragment;
import edu.val.buscarcancionesitunes.modelo.Cancion;

//esta clase va a darle los fragmtes de canciones (CancionFragment)
//al ViewPager de ListaCancionesActivity
public class CancionesViewPagerAdapter extends FragmentStateAdapter {

    private List<Cancion> cancionList;

    public CancionesViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, List<Cancion> cancionList) {
        super(fragmentActivity);
        this.cancionList = cancionList;


    }

    @NonNull
    @Override //inflamos el fragment
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        Bundle saquito = null;
        Cancion cancion = null;


            cancion = cancionList.get(position);
            fragment = new CancionFragment();
            saquito = new Bundle();
            saquito.putParcelable("cancion", cancion);
            fragment.setArguments(saquito);


        return fragment;
    }

    @Override //¿cuántos fragments voy a mostrar
    public int getItemCount() {
        return cancionList.size();
    }
}
