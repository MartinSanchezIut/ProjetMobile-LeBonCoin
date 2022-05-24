package com.example.projetmobile.BDD.models;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UserBDD {
    @PrimaryKey
    private long id_user;
    @ColumnInfo(name = "Pseudo")
    private String pseudo;
    @ColumnInfo(name = "Nom")
    private String nom;
    @ColumnInfo(name = "Prenom")
    private String prenom;
    @ColumnInfo(name = "Email")
    private String email;
    @ColumnInfo(name = "Numero")
    private String numero;
    @ColumnInfo(name = "MDP")
    private String mot_de_passe;
    @ColumnInfo(name = "Statu")
    private String statu;
    @ColumnInfo(name = "Image")
    private String Image;


    @ColumnInfo(name = "Entreprise")
    private String entreprise;


    public UserBDD(long id_user, String pseudo,String Image, String nom ,String prenom, String email, String numero, String mot_de_passe, String statu, String entreprise) {
        this.id_user = id_user;
        this.pseudo = pseudo;
        this.Image = Image;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.numero = numero;
        this.mot_de_passe = mot_de_passe;
        this.statu = statu;
        this.entreprise = entreprise;
    }

    public long getId_user() {
        return id_user;
    }

    public String getPseudo() {
        return pseudo;
    }


    public String getStatu() {
        return statu;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getNumero() {
        return numero;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    public String getEntreprise() {
        return entreprise;
    }

    public void setStatu(String statu) {
        this.statu = statu;
    }

    public String getImage() {
        return Image;
    }
}
