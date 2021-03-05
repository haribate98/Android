package com.example.webgamingshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.webgamingshop.ApiHandle.Dobavi;
import com.example.webgamingshop.SablonKlase.Korisnik;

public class RegistracijaActivity extends AppCompatActivity {

    EditText ime;
    EditText prezime;
    EditText adresa;
    EditText email;
    EditText korisnicko;
    EditText lozinka;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_registracija);

        ime = findViewById(R.id.registracijaIme);
        prezime = findViewById(R.id.registracijaPrezime);
        adresa = findViewById(R.id.registracijaAdresa);
        email = findViewById(R.id.registracijaEmail);
        korisnicko = findViewById(R.id.registracijaKorisnickoIme);
        lozinka = findViewById(R.id.registracijaLozinka);
        lozinka.setTransformationMethod(new PasswordTransformationMethod());


        Button dugmeRegistruj = findViewById(R.id.dugmeRegistrujSe);
        Button dugmeOdustani = findViewById(R.id.dugmeOdustani);

        ocistiForme();

        dugmeOdustani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();}});
        dugmeRegistruj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dodajKorisnika();
            }
        });


    }

    public void dodajKorisnika(){

        //Proveri da li je nesto uneto
        if(ime.getText().toString().equals("") ||
                prezime.getText().toString().equals("") ||
                korisnicko.getText().toString().equals("") ||
                lozinka.getText().toString().equals("") ||
                email.getText().toString().equals("") ||
                adresa.getText().toString().equals("")){
            Toast t = Toast.makeText(getApplicationContext(), "Niste uneli sve podatke", Toast.LENGTH_SHORT);
            t.show();
            return;
        }

        //Probaj da dodas korisnika
        try{

            String postData = new Korisnik().dodajKorisnika(ime.getText().toString(),
                            prezime.getText().toString(),
                            korisnicko.getText().toString(),
                            lozinka.getText().toString(),
                            email.getText().toString(),
                            adresa.getText().toString());

            Dobavi dobavi = new Dobavi("/dodajKorisnika", "POST");
            dobavi.setPostData(postData);
            String odgovor = dobavi.execute().get();


            if(odgovor.equals("uspesno")){
                Toast t = Toast.makeText(getApplicationContext(), "Uspesno ste se registrovali", Toast.LENGTH_SHORT);
                t.show();
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
                finish();
            }
            else{
                Toast t = Toast.makeText(getApplicationContext(), "Korisnicko ime vec postoji", Toast.LENGTH_SHORT);
                t.show();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void ocistiForme(){
        ViewGroup group = (ViewGroup)findViewById(R.id.registracijaLayout);
        for (int i = 0, count = group.getChildCount(); i < count; ++i) {
            View view = group.getChildAt(i);
            if (view instanceof EditText) {
                ((EditText)view).setText("");
            }
        }
    }
}
