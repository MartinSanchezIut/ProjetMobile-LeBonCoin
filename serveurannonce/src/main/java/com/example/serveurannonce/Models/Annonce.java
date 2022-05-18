package com.example.serveurannonce.Models;



import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.imageio.ImageIO;
import javax.persistence.*;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

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


    @Column(name = "id_annonceur")
    private Long id_annonceur;

    @Column(name = "Departement")
    private String departement;

    @Column(name = "VILLE")
    private String ville;
    @ElementCollection
    @CollectionTable(name="Adresse_Image")
    private List<String> adresse_image ;

    @Column(length=1000000)
    @ElementCollection
    @CollectionTable(name="Image")
    private List<String> image = new ArrayList<>();

    @Column(name = "STASTISTIQUE")
    @ElementCollection
    @MapKeyColumn(name="Date")
    @CollectionTable(name="nbVu", joinColumns=@JoinColumn(name="id"))
    private Map<String, Integer> nbvues;


    @Column(name = "Contact")
    private String contact;
    @Column(name = "CATEGORIES")
    private String categories;

    @Column(name = "FILTRE")
    private String filtre;

    @OneToMany(mappedBy = "ann", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Conversation> list_messages;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MesAnnonce")
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



    public Annonce( String titre, String description, float prix, String date_publication, String departement,String ville , Long annonceur, List<String> adresse_image, Map<String, Integer> nbvues, String contact,String categories,String filtre ) throws IOException {
        this.titre = titre;
        this.description = description;
        this.prix = prix;
        this.date_publication = date_publication;
        this.id_annonceur = annonceur;
        this.adresse_image = adresse_image;
        this.nbvues = nbvues;
        this.categories = categories;
        this.contact = contact;
        this.filtre = filtre;
        this.list_messages = new ArrayList<>();
        this.departement = departement;
        this.ville = ville;

        for(String s : adresse_image) {
            File fichier = new File(s);

            BufferedImage bi = ImageIO.read(fichier);

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bi, "jpg", bos);
            this.image.add(Base64.getEncoder().encodeToString(bos.toByteArray()));
        }

    }

    public Annonce(String titre, String description, float prix, String date_publication, String departement,String ville , Long annonceur, List<String> image, String contact,String categories,String filtre ) throws IOException {
        this.titre = titre;
        this.description = description;
        this.prix = prix;
        this.date_publication = date_publication;
        this.id_annonceur = annonceur;
        this.image = image;
        this.categories = categories;
        this.contact = contact;
        this.filtre = filtre;
        this.departement = departement;
        this.ville = ville;
        this.adresse_image = new ArrayList<>();
        this.nbvues = new HashMap<>();
        this.list_messages = new ArrayList<>();


    }
    public Annonce() {

    }

    public User getAnn1() {
        return ann1;
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

    public List<String> getAdresse_image() {
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

    public List<Conversation> getList_messages() {
        return list_messages;
    }

    public Long getId_annonceur() {
        return id_annonceur;
    }

    public String getDepartement() {
        return departement;
    }

    public String getVille() {
        return ville;
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
    public List<String> getPic_bytes() {
        return this.image;
    }

    public List<String> getImage() {
        return image;
    }

    public String getContact() {
        return contact;
    }

    public void setId_annonce(long id_annonce) {
        this.id_annonce = id_annonce;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setDate_publication(String date_publication) {
        this.date_publication = date_publication;
    }

    public void setId_annonceur(Long id_annonceur) {
        this.id_annonceur = id_annonceur;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setList_messages(List<Conversation> list_messages) {
        this.list_messages = list_messages;
    }

    public List<User> getAnn3() {
        return ann3;
    }

    public void setAdresse_image(List<String> adresse_image) {
        this.adresse_image = adresse_image;
    }

    public void setImage(List<String> image) {
        this.image = image;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public void setFiltre(String filtre) {
        this.filtre = filtre;
    }
}
