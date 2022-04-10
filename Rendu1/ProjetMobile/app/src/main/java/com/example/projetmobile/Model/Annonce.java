package com.example.projetmobile.Model;



import java.util.List;
import java.util.Map;


public class Annonce {

    private long id_annonce;
    private String titre;
    private String description;
    private float prix;
    private String date_publication;
    private Long id_annonceur;
    private String lieux;
    private String adresse_image;
    private Map<String, Integer> nbvues;
    private String categories;
    private String filtre;
    private List<Message> list_messages;


    public Annonce(long id_annonce, String titre, String description, float prix, String date_publication, String lieux,Long annonceur, String adresse_image, Map<String, Integer> nbvues, String categories,String filtre , List<Message> list_messages) {
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


}
