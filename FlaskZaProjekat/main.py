
from datetime import datetime
import pymysql
import json as j


import flask as f
from flask import Flask, redirect
from flask import request
from flask import send_from_directory
from flaskext.mysql import MySQL



app=Flask(__name__, static_url_path="")

#Povezivanje sa bazom(parametri)
mysql=MySQL(cursorclass=pymysql.cursors.DictCursor)
app.config["MYSQL_DATABASE_USER"] = "root"
app.config["MYSQL_DATABASE_PASSWORD"] = "root"
app.config["MYSQL_DATABASE_DB"] = "racunarske_komponente_shop"
app.config["MYSQL_DATABASE_HOST"] = "localhost"

mysql.init_app(app)


#Korisnici

#Provera autenticnosti korisnika
@app.route("/korisnici", methods=["POST"])
def dobaviKorisnike():

    prijava = request.get_json()

    cursor = mysql.get_db().cursor()
    cursor.execute("SELECT * FROM korisnici")
    korisnici = cursor.fetchall()

    for korisnik in korisnici:
        if korisnik["korisnicko_ime"] == prijava["korisnicko_ime"]:
            if korisnik["lozinka"] == prijava["lozinka"]:
                return f.jsonify(korisnik)

    return "greska"



#Registracija korisnika
@app.route("/dodajKorisnika", methods=["POST"])
def dodajKorisnika():
    data = request.get_json()
    db = mysql.get_db()
    cursor = db.cursor()


    cursor.execute("SELECT * FROM korisnici")
    korisnici = cursor.fetchall()

    for korisnik in korisnici:
        if korisnik["korisnicko_ime"] == data["korisnicko_ime"]:
                return "greska"

    upit = '''INSERT INTO
    korisnici(korisnicko_ime, lozinka, ime, prezime, adresa, email)
    VALUES(%s, %s, %s, %s, %s, %s)'''

    cursor.execute(upit, (data["korisnicko_ime"], data["lozinka"], data["ime"], data["prezime"],
    data["adresa"], data["email"]))

    db.commit()
    return "uspesno"



#Izmena korisnika
@app.route("/izmeniKorisnika/<int:id>", methods=["POST"])
def izmeniKorisnika(id):
    data = request.json
    print(data)
    db = mysql.get_db()
    cursor = db.cursor()
    upit = '''UPDATE korisnici SET korisnicko_ime=%s, lozinka=%s, ime=%s, prezime=%s, adresa=%s, email=%s WHERE id=%s'''
    
    cursor.execute(upit, (data["korisnicko_ime"], data["lozinka"], data["ime"], data["prezime"], data["adresa"], data["email"], id))
    db.commit()
    return "uspesno"




#Komponente

#Dobavi komponente
@app.route("/komponente", methods=["GET"])
def dobaviKomponente():
    cursor = mysql.get_db().cursor()
    cursor.execute("SELECT * FROM komponente")
    rows = cursor.fetchall()

    return f.jsonify(rows)




#Racuni

#Dodaj racun
@app.route("/dodavanjeKupovine", methods=["POST"])
def dodajKupovinu():
    data = request.get_json()

    if(data["kolicinaKomponente"] == 0):
        return "nemanastanju"
    
    db = mysql.get_db()
    cursor = db.cursor()

    upit = '''UPDATE komponente SET  kolicina = kolicina - 1 WHERE id=%s and kolicina > 0'''
    
    cursor.execute(upit, (data["id_komponente"]))
    db.commit()

    upit = '''INSERT INTO kupovine(datum_vreme, id_korisnika, naziv_proizvoda, cena) 
    VALUES(%s, %s, %s, %s)'''
    
    now = datetime.now()
    formatted_date = now.strftime('%Y-%m-%d %H:%M:%S')

    data["datum_vreme"] = formatted_date

    cursor.execute(upit, (data["datum_vreme"], data["id_korisnika"], data["naziv_proizvoda"], data["cena"]))
    db.commit()

    return "uspesno"



#Pokretanje aplikacije
app.run("192.168.0.13",port=5000, debug=True)