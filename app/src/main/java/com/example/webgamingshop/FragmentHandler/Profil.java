package com.example.webgamingshop.FragmentHandler;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.webgamingshop.ApiHandle.Dobavi;
import com.example.webgamingshop.LoginActivity;
import com.example.webgamingshop.MainActivity;
import com.example.webgamingshop.R;
import com.example.webgamingshop.SablonKlase.Korisnik;

public class Profil extends Fragment {

    Korisnik korisnik;
    EditText ime, prezime, korisnicko, lozinka, adresa, email;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_profil, container, false);
        SharedPreferences sp = getActivity().getSharedPreferences("trenutniKorisnik", 0);
        String json = sp.getString("korisnik", "Nema ga");

        korisnik = new Korisnik().korisnikFromJSON(json);


        Button izmeni;

        ime = v.findViewById(R.id.profilIme);
        prezime = v.findViewById(R.id.profilPrezime);
        korisnicko = v.findViewById(R.id.profilKorisnicko);
        lozinka = v.findViewById(R.id.profilLozinka);
        adresa = v.findViewById(R.id.profilAdresa);
        email = v.findViewById(R.id.profilEmail);

        //Popuni profil formu
        ime.setText(korisnik.getIme());
        prezime.setText(korisnik.getPrezime());
        korisnicko.setText(korisnik.getKorisnickoIme());
        lozinka.setText(korisnik.getLozinka());
        adresa.setText(korisnik.getAdresa());
        email.setText(korisnik.getEmail());

        izmeni = v.findViewById(R.id.dugmeIzmeniProfil);
        izmeni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                izmeniKorisnika();
            }
        });






        return v;
    }

    public void izmeniKorisnika(){


        //Builder za dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //Dugme da i ne
        builder.setMessage("Da li zelite da izmenite svoj profil?")
                .setCancelable(false)

                .setNegativeButton("Ne", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                })
                .setPositiveButton("Da", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        try{
                            zameniPolja();
                            String postData = new Korisnik().korisnikUJSONString(korisnik);
                            Dobavi konekcija = new Dobavi("/izmeniKorisnika/"+korisnik.getId(), "POST");
                            konekcija.setPostData(postData);
                            String odgovor = konekcija.execute().get();

                            if(!odgovor.equals("pogesno")){
                                Intent intent = new Intent(getActivity(), LoginActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                getActivity().finish();
                                Toast t = Toast.makeText(getActivity().getApplicationContext(), "Uspesno ste izmenili podatke", Toast.LENGTH_SHORT);
                                t.show();

                            }
                            else{
                                Toast t = Toast.makeText(getActivity().getApplicationContext(), "Nesto je poslo po zlu.", Toast.LENGTH_SHORT);
                            }


                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });

        //Kreiranje dialogboxa
        AlertDialog alert = builder.create();
        alert.show();

    }

    public void zameniPolja(){
        korisnik.setIme(ime.getText().toString());
        korisnik.setPrezime(prezime.getText().toString());
        korisnik.setKorisnickoIme(korisnicko.getText().toString());
        korisnik.setLozinka(korisnicko.getText().toString());
        korisnik.setAdresa(adresa.getText().toString());
        korisnik.setEmail(email.getText().toString());
    }
}
