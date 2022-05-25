package com.example.projetmobile.Model;


public class Annonceur_Particulier extends User {



    public Annonceur_Particulier(){
        super();
    }
    public Annonceur_Particulier(String statu,String pseudo, String nom, String prenom, String email, String numero, String mot_de_passe){//, List<Annonce> mesannonces) {
        super(statu,pseudo, nom, prenom, email, numero, mot_de_passe);//,mesannonces);
    }

}
