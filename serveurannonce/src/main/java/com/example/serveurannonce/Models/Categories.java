package com.example.serveurannonce.Models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Categories {
    @Id
    @GeneratedValue
    private long id_categories;

    @Column(name = "NOM")
    private String nom;

    @ElementCollection
    private List<String> filtre;

    public Categories(long id_categories, String nom, List<String> filtre) {
        this.id_categories = id_categories;
        this.nom = nom;
        this.filtre = filtre;
    }

    public Categories() {

    }

    public long getId_categories() {
        return id_categories;
    }

    public String getNom() {
        return nom;
    }

    public List<String> getFiltre() {
        return filtre;
    }
}
