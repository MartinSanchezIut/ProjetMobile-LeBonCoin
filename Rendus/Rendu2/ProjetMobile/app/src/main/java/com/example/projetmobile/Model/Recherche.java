package com.example.projetmobile.Model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Recherche {
    private String categorie;
    private String filtre;
    private Float Prix1;
    private Float Prix2;
    private ArrayList<String> departement ;
    private String ville;
    private Boolean particulier = false;
    private Boolean professionel = false;

    public Recherche(String categorie, String filtre, Float prix1, Float prix2, ArrayList<String> departement, String ville, Boolean particulier, Boolean professionel) {
        this.categorie = categorie;
        this.filtre = filtre;
        Prix1 = prix1;
        Prix2 = prix2;
        this.departement = departement;
        this.ville = ville;
        this.particulier = particulier;
        this.professionel = professionel;
    }


    public String getCategorie() {
        return categorie;
    }

    public String getFiltre() {
        return filtre;
    }

    public Float getPrix1() {
        return Prix1;
    }

    public Float getPrix2() {
        return Prix2;
    }

    public ArrayList<String> getDepartement() {
        return departement;
    }

    public String getVille() {
        return ville;
    }

    public Boolean isParticulier() {
        return particulier;
    }

    public Boolean isProfessionel() {
        return professionel;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setFiltre(String filtre) {
        this.filtre = filtre;
    }

    public void setPrix1(Float prix1) {
        Prix1 = prix1;
    }

    public void setPrix2(Float prix2) {
        Prix2 = prix2;
    }

    public void setDepartement(ArrayList<String> departement) {
        this.departement = departement;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setParticulier(Boolean particulier) {
        this.particulier = particulier;
    }

    public void setProfessionel(Boolean professionel) {
        this.professionel = professionel;
    }
    public void adddepartement(String dep){

        departement.add(dep);


    }
}
