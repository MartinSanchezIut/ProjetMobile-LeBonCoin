package com.example.serveurannonce.Models;

import javax.persistence.*;
import java.util.List;
@Entity
public class Annonceur_Particulier extends Annonceur {



    public Annonceur_Particulier(){
        super();
    }
    public Annonceur_Particulier(long id_user, String pseudo, String nom, String prenom, String email, String numero, String mot_de_passe){//, List<Annonce> mesannonces) {
        super(id_user, pseudo, nom, prenom, email, numero, mot_de_passe);//,mesannonces);
    }

}
