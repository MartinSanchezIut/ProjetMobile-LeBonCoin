package com.example.serveurannonce.Models;

import javax.persistence.*;
import java.util.ArrayList;
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


    @Column(name = "IMAGE",length=1000000)
    private String image;
    @Column(name = "PRENOM")
    private String prenom;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "NUMERO")
    private String numero;
    @Column(name = "MOT_DE_PASSE")
    private String mot_de_passe;
    @Column(name = "statu")
    private String statu;

    @Column(name = "MESANNONCES")
    @OneToMany(mappedBy = "ann1", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Annonce> mesannonces;

    @Column(name = "SAUVEGARDE")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "SauvegardeList",
            joinColumns = {
                    @JoinColumn(name = "id_user", referencedColumnName = "id_user",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "id_annonce", referencedColumnName = "id_annonce",
                            nullable = false, updatable = false)})
    private List<Annonce> sauvegarde = new ArrayList<>();
    public User(){

    }
    public User(String statu,String pseudo,String image ,String nom, String prenom, String email, String numero, String mot_de_passe) {
        this.pseudo = pseudo;
        this.image = image;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.numero = numero;
        this.mot_de_passe = mot_de_passe;
        this.statu = statu;
    }

    public long getId_user() {
        return id_user;
    }

    public String getPseudo() {
        return pseudo;
    }


    public String getStatu() {
        return statu;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Annonce> getMesannonces() {
        return mesannonces;
    }

    public List<Annonce> getSauvegarde() {
        return sauvegarde;
    }

    public void addsauvegarde(Annonce ann) {
        sauvegarde.add(ann);
    }





    public void setStatu(String statu) {
        this.statu = statu;
    }
}
