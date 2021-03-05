package com.example.webgamingshop.SablonKlase;


import com.google.gson.Gson;

import org.json.JSONObject;

public class Korisnik {
    String ime, prezime, korisnicko_ime, lozinka, email, adresa, brojTelefona;
    int id, admin;


    public Korisnik(String ime, String prezime, String korisnicko_ime, String lozinka, String email, String adresa, int id, int admin) {
        this.ime = ime;
        this.prezime = prezime;
        this.korisnicko_ime = korisnicko_ime;
        this.lozinka = lozinka;
        this.email = email;
        this.adresa = adresa;
        this.id = id;
        this.admin = admin;
    }

    public Korisnik() {
    }

    public Korisnik korisnikFromJSON(String json){
        Korisnik korisnik = new Korisnik();

        korisnik = new Gson().fromJson(json,Korisnik.class);

        return korisnik;
    }

    public String dodajKorisnika(String ime, String prezime, String korisnicko_ime, String lozinka, String email, String adresa){
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("ime", ime);
            jsonObject.put("prezime", prezime);
            jsonObject.put("korisnicko_ime", korisnicko_ime);
            jsonObject.put("lozinka", lozinka);
            jsonObject.put("email", email);
            jsonObject.put("adresa", adresa);

            return jsonObject.toString();

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public String korisnikUJSONString(Korisnik korisnik){
        Gson gs = new Gson();
        String result = gs.toJson(korisnik);

        return result;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getKorisnickoIme() {
        return korisnicko_ime;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnicko_ime = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int isAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

}
