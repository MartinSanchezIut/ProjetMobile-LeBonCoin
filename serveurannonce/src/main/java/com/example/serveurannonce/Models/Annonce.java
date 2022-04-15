package com.example.serveurannonce.Models;



import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.imageio.ImageIO;
import javax.persistence.*;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
public class Annonce {
    @Id
    @GeneratedValue
    private long id_annonce;
    @Column(name = "TITRE")
    private String titre;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "PRIX")
    private float prix;
    @Column(name = "DATE_PUBLICATION")
    private String date_publication;


    @Column(name = "Annonceur")
    private Long id_annonceur;

    @Column(name = "LIEUX")
    private String lieux;


    @Column(name = "IMAGE")
    private String adresse_image;

    @Lob
    @Column(name = "content")
    private byte[] image;

    @Column(name = "STASTISTIQUE")
    @ElementCollection
    @MapKeyColumn(name="Date")
    @CollectionTable(name="nbVu", joinColumns=@JoinColumn(name="id"))
    private Map<String, Integer> nbvues;


    @Column(name = "CATEGORIES")
    private String categories;

    @Column(name = "FILTRE")
    private String filtre;

    @OneToMany(mappedBy = "ann", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Message> list_messages;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MesAnnonce", nullable=false)
    private User ann1;

/*
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="", nullable=true)
    private User ann2;
*/
    @JsonIgnore
    @ManyToMany(mappedBy = "sauvegarde", fetch = FetchType.LAZY)
    private List<User> ann3 = new ArrayList<>();


    public Annonce(long id_annonce, String titre, String description, float prix, String date_publication, String lieux,Long annonceur, String adresse_image, Map<String, Integer> nbvues, String categories,String filtre , List<Message> list_messages) throws IOException {
        this.id_annonce = id_annonce;
        this.titre = titre;
        this.description = description;
        this.prix = prix;
        this.date_publication = date_publication;
        this.id_annonceur = annonceur;
        this.adresse_image = adresse_image;
        this.nbvues = nbvues;
        this.categories = categories;
        this.filtre = filtre;
        this.list_messages = list_messages;
        this.lieux = lieux;

        File fichier = new File (adresse_image);

        BufferedImage bi = ImageIO.read (fichier);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bi, "jpg", bos );
        this.image = bos.toByteArray();

    }

    public Annonce() {

    }

    public long getId_annonce() {
        return id_annonce;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public float getPrix() {
        return prix;
    }

    public String getDate_publication() {
        return date_publication;
    }

    public Long getAnnonceur() {
        return id_annonceur;
    }

    public String getAdresse_image() {
        return adresse_image;
    }

    public Map<String, Integer> getNbvues() {
        return nbvues;
    }

    public String getCategories() {
        return categories;
    }

    public String getFiltre() {
        return filtre;
    }

    public List<Message> getList_messages() {
        return list_messages;
    }

    public String getLieux() {
        return lieux;
    }

    public void setAnn1(User ann1) {
        this.ann1 = ann1;
    }
/*
    public void setAnn2(Annonceur_pro ann2) {
        this.ann2 = ann2;
    }
*/
    public void addAnn3(User ann3) {
        this.ann3.add(ann3);
    }
    public byte[] getPic_bytes() {
        return this.image;
    }
}
