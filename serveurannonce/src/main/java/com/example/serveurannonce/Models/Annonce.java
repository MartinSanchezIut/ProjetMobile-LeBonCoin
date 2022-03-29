package com.example.serveurannonce.Models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Annonce {
    @Id
    private long id_annonce;
    @Column(name = "TITRE")
    private String titre;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "PRIX")
    private float prix;
    @Column(name = "DATE_PUBLICATION")
    private String date_publication;
    @Column(name = "ANNONCEUR")
    @OneToOne(fetch = FetchType.LAZY)
    private User annonceur;

    @Column(name = "IMAGE")
    private String adresse_image;
    @Column(name = "STASTISTIQUE")
    private String stastique;
    @Column(name = "CATEGORIES")
    private String categories;

    @Column(name = "LIST_MESSAGES")
    @OneToMany(mappedBy = "form", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Message> list_messages;

}
