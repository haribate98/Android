package com.example.webgamingshop.FragmentHandler;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.webgamingshop.MainActivity;
import com.example.webgamingshop.R;
import com.example.webgamingshop.SablonKlase.Komponenta;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;


public class PrikazKomponentePoTipu extends Fragment {

    String tipKomponente;
    ArrayList<Komponenta> komponente;
    ArrayList<Komponenta> komponentaPoTipu;
    ArrayList<Komponenta> prikazaneKomponente;

    LinearLayout mainLay;
    LinearLayout zamenskiLay;
    ArrayList<LinearLayout> listaView;
    EditText search;
    TextView sortText;
    boolean sort = true;






    public PrikazKomponentePoTipu() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            tipKomponente = getArguments().getString("tip");
            sort = getArguments().getBoolean("sort");
            System.out.println(sort);
        }



    }


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {



        listaView = new ArrayList<>();
        SharedPreferences sp = getActivity().getSharedPreferences("sveKomponente", 0);
        String niz = sp.getString("komponente", "Nema ih");
        komponente = new Komponenta().komponenteFromJSON(niz);
        komponentaPoTipu = new ArrayList<>();


        Collections.sort(komponente);


        View v = inflater.inflate(R.layout.layout_prikaz_komponenti,
                container,
                false);

        search = v.findViewById(R.id.search);
        sortText = v.findViewById(R.id.sortText);
        sortText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sort){
                    sortRastuce();
                }
                else{
                    sortOpadajuce();

                }
            }
        });
        //Crtanje fragmenta

            //Dobavljanje komponenti


            mainLay = v.findViewById(R.id.layoutZaPrikazKomponenti);

            if(sort){
                Komponenta.sort = true;
                sortText.setText("Sortiraj po ceni rastuce");
            }
            else {
                Komponenta.sort = false;
                sortText.setText("Sortiraj po ceni opadajuce");
            }


            for(int i = 0; i<komponente.size();i++){

                if(komponente.get(i).getTip().equals(tipKomponente)){

                    zamenskiLay = (LinearLayout) inflater.inflate(R.layout.layout_komponenta,
                            mainLay, false);


                    listaView.add(zamenskiLay);

                    final Komponenta komponenta = komponente.get(i);
                    komponentaPoTipu.add(komponenta);

                    TextView tvNaziv = zamenskiLay.findViewById(R.id.komponentaNaziv);
                    TextView tvCena = zamenskiLay.findViewById(R.id.komponentaCena);
                    TextView tvStanje = zamenskiLay.findViewById(R.id.komponentaStanje);
                    ImageView slika = zamenskiLay.findViewById(R.id.komponentaSlika);



                    tvNaziv.setText("Model: " + komponente.get(i).getNaziv());
                    tvCena.setText("Cena: " + Float.toString(komponenta.getCena()));
                    Picasso.with(getActivity().getApplicationContext()).load(
                                    komponenta.getSlika()).into(slika);

                    if(komponenta.getKolicina() > 0){
                        tvStanje.setText("Na stanju");
                        tvStanje.setTextColor(Color.parseColor("white"));
                    }
                    else{
                        tvStanje.setText("Nije na stanju");
                        tvStanje.setTextColor(Color.parseColor("red"));
                    }


                    //Detalji o komponenti
                    zamenskiLay.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            prikazJedneKomponente(komponenta);
                        }
                    });



                    mainLay.addView(zamenskiLay);


                }
            }


        pretraga(v);


        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        sort = false;
    }

    public void prikazJedneKomponente(Komponenta komponenta){
        FragmentKomponentaPrikaz fragment = new FragmentKomponentaPrikaz();
        Bundle b = new Bundle();
        b.putString("data", new Komponenta().komponentaUJSONString(komponenta));
        fragment.setArguments(b);
        ((MainActivity)getActivity()).setFragmentView(fragment);
    }

    public void pretraga(View v){
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                prikazaneKomponente = new ArrayList<Komponenta>();
                if(search.getText().equals("")){
                    prikazaneKomponente = komponentaPoTipu;
                }

                else{
                    for(Komponenta k: komponentaPoTipu){
                        if(k.getNaziv().toLowerCase().contains(search.getText().toString().toLowerCase())){
                            prikazaneKomponente.add(k);
                        }
                    }
                    pisiBrisi();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void pisiBrisi(){
        for(Komponenta k: komponentaPoTipu){
            if(!prikazaneKomponente.contains(k)){
                listaView.get(komponentaPoTipu.indexOf(k)).setVisibility(View.GONE);
            }
            else{
                listaView.get(komponentaPoTipu.indexOf(k)).setVisibility(View.VISIBLE);
            }
        }
    }

    public void sortRastuce(){

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        getFragmentManager().popBackStack();


        PrikazKomponentePoTipu prikazKomponentePoTipu = new PrikazKomponentePoTipu();
        Bundle b = new Bundle();
        b.putString("tip", tipKomponente);
        b.putBoolean("sort", false);
        prikazKomponentePoTipu.setArguments(b);
        fragmentTransaction.replace(R.id.mainLayout, prikazKomponentePoTipu);
        fragmentTransaction.addToBackStack("dodato");
        fragmentTransaction.commit();


        }

    public void sortOpadajuce(){
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        getFragmentManager().popBackStack();


        PrikazKomponentePoTipu prikazKomponentePoTipu = new PrikazKomponentePoTipu();
        Bundle b = new Bundle();
        b.putString("tip", tipKomponente);
        b.putBoolean("sort", true);
        prikazKomponentePoTipu.setArguments(b);
        fragmentTransaction.replace(R.id.mainLayout, prikazKomponentePoTipu);
        fragmentTransaction.addToBackStack("dodato");
        fragmentTransaction.commit();

    }
    }





