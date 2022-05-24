package com.example.projetmobile.Model;


import android.os.Parcel;
import android.os.Parcelable;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

public class User implements Parcelable {

    private long id_user;

    private String pseudo;
    private String nom;
    private String prenom;
    private String email;
    private String numero;
    private String mot_de_passe;

    private String image;
    private String statu;
    private List<Annonce> mesannonces;
    private List<Annonce> sauvegarde= new ArrayList<>();
    public User(){

    }
    public User(String statu,String pseudo, String image,String nom, String prenom, String email, String numero, String mot_de_passe) {
        this.pseudo = pseudo;
        this.nom = nom;
        this.image = image;
        this.prenom = prenom;
        this.email = email;
        this.numero = numero;
        this.mot_de_passe = mot_de_passe;
        this.statu = statu;
    }

    protected User(Parcel in) {
        pseudo = in.readString();
        nom = in.readString();
        image = in.readString();
        prenom = in.readString();
        email = in.readString();
        numero = in.readString();
        mot_de_passe = in.readString();
        statu = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

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

    public void setImage(String image) {
        image = image;
    }

    public void setStatu(String statu) {
        this.statu = statu;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(pseudo);
        parcel.writeString(nom);
        parcel.writeString(image);
        parcel.writeString(prenom);
        parcel.writeString(email);
        parcel.writeString(numero);
        parcel.writeString(mot_de_passe);
        parcel.writeString(statu);
    }

    public String getImage() {
        return image;
    }
}
