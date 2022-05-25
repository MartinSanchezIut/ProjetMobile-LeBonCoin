package com.example.projetmobile.Model;

public class Departement {
    private String nom;
    private boolean ischecked;

    public Departement(String nom, boolean ischecked) {
        this.nom = nom;
        this.ischecked = ischecked;
    }

    public String getNom() {
        return nom;
    }

    public boolean isIschecked() {
        return ischecked;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setIschecked(boolean ischecked) {
        this.ischecked = ischecked;
    }
}
