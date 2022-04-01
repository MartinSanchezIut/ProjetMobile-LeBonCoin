package com.example.serveurannonce.Models;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {
    @Id
    @GeneratedValue
    private long id_user;

    @Column(name = "PSEUDO")
    private String pseudo;
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

    public User(){

    }
    public User(long id_user, String pseudo, String nom, String prenom, String email, String numero, String mot_de_passe) {
        this.id_user = id_user;
        this.pseudo = pseudo;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.numero = numero;
        this.mot_de_passe = mot_de_passe;
    }

    public long getId_user() {
        return id_user;
    }

    public String getPseudo() {
        return pseudo;
    }




    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getNumero() {
        return numero;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }
}
