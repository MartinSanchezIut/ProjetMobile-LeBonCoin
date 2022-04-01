package com.example.serveurannonce.Models;

import javax.persistence.*;
import java.util.List;
@Entity
public class Annonceur_pro extends Annonceur{

    @Column(name = "NOM_SOCIETE")
    private String nom_societe;

    public Annonceur_pro(){
        super();
    }
    public Annonceur_pro(long id_user, String pseudo, String nom, String prenom, String email, String numero, String mot_de_passe, String nom_societe){//, List<Annonce> mesannonces) {
        super(id_user, pseudo, nom, prenom, email, numero, mot_de_passe);//,mesannonces);
        this.nom_societe = nom_societe;
    }

    public void setNom_societe(String nom_societe) {
        this.nom_societe = nom_societe;
    }


    public String getNom_societe() {
        return nom_societe;
    }

}
