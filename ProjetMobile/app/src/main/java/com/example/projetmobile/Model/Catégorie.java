package com.example.projetmobile.Model;

import java.util.ArrayList;

public class Catégorie {

    private String NomCategorie;
    private ArrayList<String> filtre;

    public Catégorie(String nomCategorie, ArrayList<String> filtre) {
        NomCategorie = nomCategorie;
        this.filtre = filtre;
    }

    public String getNomCategorie() {
        return NomCategorie;
    }

    public ArrayList<String> getFiltre() {
        return filtre;
    }
}
