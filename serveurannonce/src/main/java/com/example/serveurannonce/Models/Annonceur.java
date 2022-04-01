package com.example.serveurannonce.Models;

import javax.persistence.*;
import java.util.List;
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Annonceur extends User{

    @Column(name = "MESANNONCES")
    @OneToMany(mappedBy = "ann1", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Annonce> mesannonces;
    public Annonceur(){
        super();
    }
    public Annonceur(long id_user, String pseudo, String nom, String prenom, String email, String numero, String mot_de_passe){//, List<Annonce> mesannonces) {
        super(id_user, pseudo, nom, prenom, email, numero, mot_de_passe);
        //this.mesannonces = mesannonces;
    }

    public List<Annonce> getMesannonces() {
        return mesannonces;
    }

    public void setMesannonces(List<Annonce> mesannonces) {
        this.mesannonces = mesannonces;
    }


}
