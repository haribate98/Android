package com.example.webgamingshop.FragmentHandler;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.webgamingshop.MainActivity;
import com.example.webgamingshop.R;


public class FragmentPocetna extends Fragment {



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_pocetna, container, false);

        Button dugmeMaticne = v.findViewById(R.id.dugmeMaticne);
        dugmeMaticne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postaviTip("Maticna");
            }
        });

        Button dugmeGraficke = v.findViewById(R.id.dugmeGraficke);
        dugmeGraficke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postaviTip("Graficka");
            }
        });

        Button dugmeProcesori = v.findViewById(R.id.dugmeProcesori);
        dugmeProcesori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postaviTip("Procesor");
            }
        });

        LinearLayout profilClick = v.findViewById(R.id.profilClick);
        profilClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).setFragmentView(new Profil());
            }
        });




        return v;
    }

    public void postaviTip(String tip){
        PrikazKomponentePoTipu fragment = new PrikazKomponentePoTipu();
        Bundle b = new Bundle();
        b.putString("tip", tip);
        fragment.setArguments(b);
        ((MainActivity)getActivity()).setFragmentView(fragment);
    }





}
