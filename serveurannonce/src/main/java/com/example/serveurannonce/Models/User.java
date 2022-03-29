package com.example.serveurannonce.Models;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {
    @Id
    private long id_user;

    @Column(name = "PSEUDO")
    private String pseudo;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "NOM_SOCIETE")
    private String nom_societe;
    @Column(name = "NOM")
    private String nom;
    @Column(name = "PRENOM")
    private String prenom;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "NUMERO")
    private String numero;
    @Column(name = "MOT_DE_PASSE")
    private String mot_de_passe;


    @Column(name = "MESANNONCES")
    @OneToMany(mappedBy = "form", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Annonce> mesannonces;

    @Column(name = "SAUVEGARDE")
    @OneToMany(mappedBy = "form", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Annonce> sauvegarde;

}
