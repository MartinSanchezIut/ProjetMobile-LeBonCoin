package com.example.serveurannonce.Models;

import javax.persistence.*;
import java.util.List;
@Entity
public class Annonceur_Particulier extends User {



    public Annonceur_Particulier(){
        super();
    }
    public Annonceur_Particulier(long id_user, String statu,String pseudo,String Image , String nom, String prenom, String email, String numero, String mot_de_passe){//, List<Annonce> mesannonces) {
        super(id_user, statu,pseudo, Image,nom, prenom, email, numero, mot_de_passe);//,mesannonces);
    }

}
