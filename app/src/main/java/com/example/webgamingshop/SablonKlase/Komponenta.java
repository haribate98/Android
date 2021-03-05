package com.example.webgamingshop.SablonKlase;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.*;
import java.lang.*;

public class Komponenta implements Comparable {
    String naziv, tip, sadrzaj, slika;
    int id, kolicina;
    float cena;

   public static boolean sort = true;



    public Komponenta(String naziv, String tip, String sadrzaj, int id, int kolicina, float cena, String slika) {
        this.naziv = naziv;
        this.tip = tip;
        this.sadrzaj = sadrzaj;
        this.id = id;
        this.kolicina = kolicina;
        this.cena = cena;
        this.slika = slika;
    }

    public Komponenta() {

    }

    public ArrayList<Komponenta> komponenteFromJSON(String jsonArray){
        Gson gs = new Gson();
        Type tipListe = new TypeToken<ArrayList<Komponenta>>(){}.getType();
        ArrayList<Komponenta>komponente = gs.fromJson(jsonArray, tipListe);

        return komponente;
    }

    public String komponentaUJSONString(Komponenta komponenta){
        try {
            JSONObject jo = new JSONObject();
            jo.put("id", komponenta.getId());
            jo.put("tip", komponenta.getTip());
            jo.put("naziv", komponenta.getNaziv());
            jo.put("cena", komponenta.getCena());
            jo.put("sadrzaj", komponenta.getSadrzaj());
            jo.put("slika", komponenta.getSlika());
            jo.put("kolicina", komponenta.getKolicina());

            return jo.toString();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public Komponenta JSONStringUKomponentu(String jsonString){
        Gson gs = new Gson();
        Komponenta k = gs.fromJson(jsonString, Komponenta.class);
        return k;
    }

    public boolean isSort() {
        return sort;
    }

    public void setSort(boolean sort) {
        this.sort = sort;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getOpis() {
        return sadrzaj;
    }

    public void setOpis(String opis) {
        this.sadrzaj = opis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public float getCena() {
        return cena;
    }

    public void setCena(float cena) {
        this.cena = cena;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public String getSadrzaj() {
        return sadrzaj;
    }

    public void setSadrzaj(String sadrzaj) {
        this.sadrzaj = sadrzaj;
    }


    @Override
    public int compareTo(Object o) {
        int comparePrice = (int)((Komponenta)o).getCena();
        if (!sort){
            return comparePrice - (int)this.getCena();
        }
        else {
            return (int)this.getCena() - comparePrice;
        }

    }
}
