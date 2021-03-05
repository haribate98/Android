package com.example.webgamingshop.ApiHandle;
import android.os.AsyncTask;

import org.json.JSONObject;
import org.json.JSONStringer;

import java.net.*;
import java.io.*;


public class Dobavi extends AsyncTask<Void, Void, String>{

    public String adresa;
    public String metod;
    public String postData;
    public static final String ip = "http://192.168.0.13:5000";

    public Dobavi(String adresa, String metod){
        this.adresa = adresa;
        this.metod = metod;
        this.postData = "";
    }

    public void setPostData(String newPostData){
        this.postData = newPostData;
    }

    @Override
    protected String doInBackground(Void... voids) {

        String odgovor = "";

        if (metod.equals("GET")) {

            try {
                URL url = new URL(ip + adresa);
                HttpURLConnection konekcija = (HttpURLConnection) url.openConnection();
                BufferedReader br = new BufferedReader(new InputStreamReader(konekcija.getInputStream()));
                String red;

                while ((red = br.readLine()) != null) {
                    odgovor += red;
                }
                br.close();
                konekcija.disconnect();

            } catch (Exception e) {
                e.printStackTrace();
            }

            return odgovor;

        }

        if (metod.equals("POST")) {
            try {
            URL url = new URL(ip + adresa);
            HttpURLConnection konekcija = (HttpURLConnection) url.openConnection();
            konekcija.setRequestMethod("POST");
            konekcija.setRequestProperty("Content-Type", "application/json");
            konekcija.setDoInput(true);
            konekcija.setDoOutput(true);
            konekcija.connect();

            BufferedWriter writer = new BufferedWriter(
                                    new OutputStreamWriter(
                                    konekcija.getOutputStream()));

            writer.write(postData);
            writer.flush();
            writer.close();

            int responseCode = konekcija.getResponseCode();
            if (responseCode == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(konekcija.getInputStream()));
                String red;

                while ((red = reader.readLine()) != null) {
                    odgovor+=red;
                }
                return odgovor;
            }

            konekcija.disconnect();
        } catch(Exception e){
            e.printStackTrace();
        }

    }


        return "greska";
    }
    }





