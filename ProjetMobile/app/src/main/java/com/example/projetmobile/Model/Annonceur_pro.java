package com.example.projetmobile.Model;


public class Annonceur_pro extends User{

    private String nom_societe;

    public Annonceur_pro(String statu,String pseudo, String Image, String nom, String prenom, String email, String numero, String mot_de_passe, String nom_societe){//, List<Annonce> mesannonces) {
        super(statu,pseudo, Image,nom, prenom, email, numero, mot_de_passe);//,mesannonces);
        this.nom_societe = nom_societe;
    }

    public void setNom_societe(String nom_societe) {
        this.nom_societe = nom_societe;
    }


    public String getNom_societe() {
        return nom_societe;
    }

}
