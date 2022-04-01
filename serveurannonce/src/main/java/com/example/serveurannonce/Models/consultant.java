package com.example.serveurannonce.Models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Consultant extends User {


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "Consultant_Annonce",
            joinColumns = {
                    @JoinColumn(name = "id_user", referencedColumnName = "id_user",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "id_annonce", referencedColumnName = "id_annonce",
                            nullable = false, updatable = false)})
    private List<Annonce> sauvegarde= new ArrayList<>();


    public Consultant(){
        super();
    }
    public Consultant(long id_user, String pseudo, String nom, String prenom, String email, String numero, String mot_de_passe){//, List<Annonce> sauvegarde) {
        super(id_user, pseudo, nom, prenom, email, numero, mot_de_passe);
        //this.sauvegarde = sauvegarde;
    }

    public List<Annonce> getSauvegarde() {
        return sauvegarde;
    }

    public void addsauvegarde(Annonce ann){
        this.sauvegarde.add(ann);
    }
}
