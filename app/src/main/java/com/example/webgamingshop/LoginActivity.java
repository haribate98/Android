package com.example.webgamingshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.webgamingshop.ApiHandle.Dobavi;
import com.example.webgamingshop.SablonKlase.Korisnik;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    EditText lozinka;
    EditText korisnicko;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        korisnicko = findViewById(R.id.loginKorisnicko);
        lozinka = findViewById(R.id.loginLozinka);
        lozinka.setTransformationMethod(new PasswordTransformationMethod());

        Button login = findViewById(R.id.button);
        TextView register=  findViewById(R.id.registracijaLink);

        ocistiForme();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proveriKorisnika();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { startActivity(new Intent(getApplicationContext(), RegistracijaActivity.class));
            finish();
            }
        });
    }

    public void proveriKorisnika(){
        //Provera da li je uneto nesto
        if(korisnicko.getText().toString().equals("") || lozinka.getText().toString().equals("")){
            Toast t = Toast.makeText(getApplicationContext(),"Niste uneli podatke", Toast.LENGTH_SHORT);
            t.show();
            return;
        }


        //Provera podataka
        try{

            JSONObject korisnik = new JSONObject();
            korisnik.put("korisnicko_ime", korisnicko.getText().toString());
            korisnik.put("lozinka", lozinka.getText().toString());

            Dobavi dobavi =  new Dobavi("/korisnici", "POST");
            dobavi.setPostData(korisnik.toString());
            String odgovor = dobavi.execute().get();

            if(!odgovor.equals("greska")){
                SharedPreferences.Editor sharedPref = getSharedPreferences("trenutniKorisnik", 0).edit();
                sharedPref.clear();
                sharedPref.putString("korisnik", odgovor);
                sharedPref.commit();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
            else{
                Toast t = Toast.makeText(getApplicationContext(),"Pogresni podaci", Toast.LENGTH_SHORT);
                t.show();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        ocistiForme();

    }

    public void ocistiForme(){
        ViewGroup group = (ViewGroup)findViewById(R.id.loginLayout);
        for (int i = 0, count = group.getChildCount(); i < count; ++i) {
            View view = group.getChildAt(i);
            if (view instanceof EditText) {
                ((EditText)view).setText("");
            }
        }
    }

}
