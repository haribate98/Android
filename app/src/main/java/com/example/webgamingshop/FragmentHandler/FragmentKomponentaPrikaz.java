package com.example.webgamingshop.FragmentHandler;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.webgamingshop.ApiHandle.Dobavi;
import com.example.webgamingshop.MainActivity;
import com.example.webgamingshop.R;
import com.example.webgamingshop.SablonKlase.Komponenta;
import com.example.webgamingshop.SablonKlase.Korisnik;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

public class FragmentKomponentaPrikaz extends Fragment {

    private String data;
    private Komponenta komponenta;


    public FragmentKomponentaPrikaz(){};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            data = getArguments().getString("data");
            komponenta = new Komponenta().JSONStringUKomponentu(data);
        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layoutzapojedinacnukomponentu, container, false);

        ImageView slika = v.findViewById(R.id.prikazPojedinacneKomponenteSlika);
        TextView naziv = v.findViewById(R.id.prikazPojedinacneKomponenteNaziv);
        TextView cena = v.findViewById(R.id.prikazPojedinacneKomponenteCena);
        TextView opis = v.findViewById(R.id.prikazPojedinacneKomponenteSadrzaj);
        final TextView kolicina = v.findViewById(R.id.prikazPojedinacneKomponenteKolicina);
        Button kupiKomponentu = v.findViewById(R.id.dugmeKupiKomponentu);

        Picasso.with(getActivity().getApplicationContext()).load(komponenta.getSlika()).into(slika);
        naziv.setText(komponenta.getNaziv());
        cena.setText(Float.toString(komponenta.getCena()));
        opis.setText(komponenta.getSadrzaj());

        if(komponenta.getKolicina() > 0){
            kolicina.setText("Na stanju");
            kolicina.setTextColor(Color.parseColor("green"));
        }
        else{
            kolicina.setText("Nije na stanju");
            kolicina.setTextColor(Color.parseColor("red"));
        }



        kupiKomponentu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Builder za dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                //Dugme da i ne
                builder.setMessage("Da li zelite da kupite komponentu " + komponenta.getNaziv() + " po ceni od " + komponenta.getCena())
                        .setCancelable(false)

                        .setNegativeButton("Ne", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        })
                        .setPositiveButton("Da", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                kupi();
                            }
                        });

            //Kreiranje dialogboxa
            AlertDialog alert = builder.create();
                alert.setTitle("Potvrda kupovine");
                alert.show();
        }
        });

        return v;
    }

    public void kupi(){


        try{

            SharedPreferences sp = getActivity().getSharedPreferences("trenutniKorisnik", 0);
            Korisnik korisnik = new Korisnik().korisnikFromJSON(sp.getString("korisnik", "Nema ga"));

            JSONObject jo = new JSONObject();
            jo.put("id_korisnika", korisnik.getId());
            jo.put("id_komponente", komponenta.getId());
            jo.put("naziv_proizvoda", komponenta.getNaziv());
            jo.put("kolicinaKomponente", komponenta.getKolicina());
            jo.put("cena", komponenta.getCena());

            Dobavi konekcija = new Dobavi("/dodavanjeKupovine", "POST");
            konekcija.setPostData(jo.toString());
            String response = konekcija.execute().get();



            if(response.equals("uspesno")){

                getActivity().finish();
                Toast t = Toast.makeText(getActivity().getApplicationContext(),
                        "Komponenta " + komponenta.getNaziv() + " uspesno kupljena.", Toast.LENGTH_SHORT);
                t.show();

                startActivity(new Intent(getActivity().getApplicationContext(), MainActivity.class));
            }

            else{
                Toast t = Toast.makeText(getActivity().getApplicationContext(),
                        "Komponenta " + komponenta.getNaziv() + " nije na stanju.", Toast.LENGTH_SHORT);
                t.show();
            }


        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
