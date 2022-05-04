package com.example.projetmobile.Model;

import android.widget.ImageView;

import java.util.ArrayList;

public class Catégorie {

    private String NomCategorie;
    private int icon;
    private ArrayList<String> filtre;

    public Catégorie(String nomCategorie,int icon,ArrayList<String> filtre) {
        NomCategorie = nomCategorie;
        this.icon = icon;
        this.filtre = filtre;
    }

    public String getNomCategorie() {
        return NomCategorie;
    }

    public int getIcon() {
        return icon;
    }

    public ArrayList<String> getFiltre() {
        return filtre;
    }
}
