package com.example.webgamingshop;

import com.example.webgamingshop.ApiHandle.Dobavi;
import com.example.webgamingshop.FragmentHandler.FragmentPocetna;
import com.example.webgamingshop.FragmentHandler.Profil;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;



public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dobaviKomponente();

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        FragmentPocetna fragment = new FragmentPocetna();
        fragmentTransaction.add(R.id.mainLayout, fragment, "pocetna");
        fragmentTransaction.commit();



    }

    //Funkcija za postavljanje fragmenata
    public void setFragmentView(Fragment fragment){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mainLayout, fragment);
        fragmentTransaction.addToBackStack("dodato");
        fragmentTransaction.commit();
    }

    public void dobaviKomponente(){
        try {
            String odgovor = new Dobavi("/komponente", "GET").execute().get();
            SharedPreferences.Editor sp = getSharedPreferences("sveKomponente", 0).edit();
            sp.clear();
            sp.putString("komponente", odgovor);
            sp.commit();

        }
        catch (Exception e){
            e.printStackTrace();
        }
    };

    //Izlazi sa aktivitija  samo kad je pocetna fragment
    @Override
    public void onBackPressed() {

        Fragment myFragment = fragmentManager.findFragmentByTag("pocetna");

        if(myFragment != null && myFragment.isVisible()) {


            //Builder za dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            //Dugme da i ne
            builder.setMessage("Da li zelite da se izlogujete?")
                    .setCancelable(false)

                    .setNegativeButton("Ne", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    })
                    .setPositiveButton("Da", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                            MainActivity.super.onBackPressed();
                        }
                    });

            //Kreiranje dialogboxa
            AlertDialog alert = builder.create();
            alert.show();

        }
        else{
            super.onBackPressed();
        }
    }
}
