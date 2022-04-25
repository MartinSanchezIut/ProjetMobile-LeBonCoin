package com.example.projetmobile.Model;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Annonce {

    private long id_annonce;
    private String titre;
    private String description;
    private Float prix;
    private String date_publication;
    private Long id_annonceur;

    private String ville;

    private String departement;
    private ArrayList<String> adresse_image;
    private Map<String, Integer> nbvues;
    private String categories;

    private String contact;
    private String filtre;
    private ArrayList<String> pic_bytes;
    private List<Message> list_messages;


    public Annonce(long id_annonce, String titre, String description, Float prix, String date_publication, String ville,String departement , Long annonceur, ArrayList<String> pic_bytes, Map<String, Integer> nbvues,String contact, String categories ,String filtre , List<Message> list_messages) {
        this.id_annonce = id_annonce;
        this.titre = titre;
        this.description = description;
        this.prix = prix;
        this.date_publication = date_publication;
        this.id_annonceur = annonceur;
        this.pic_bytes = pic_bytes;
        this.nbvues = nbvues;
        this.contact = contact;
        this.categories = categories;
        this.filtre = filtre;
        this.list_messages = list_messages;
        this.ville = ville;
        this.departement = departement;

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

    public Float getPrix() {
        return prix;
    }

    public String getDate_publication() {
        return date_publication;
    }

    public Long getAnnonceur() {
        return id_annonceur;
    }

    public ArrayList<String> getAdresse_image() {
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

    public String getville() {
        return ville;
    }

    public ArrayList<String> getimage(){
        return this.pic_bytes;
    }

    public void setId_annonce(long id_annonce) {
        this.id_annonce = id_annonce;
    }

    public String getDepartement() {
        return departement;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getVille() {
        return ville;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public String getContact() {
        return contact;
    }

    public ArrayList<String> getPic_bytes() {
        return pic_bytes;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public void setDate_publication(String date_publication) {
        this.date_publication = date_publication;
    }

    public void setId_annonceur(Long id_annonceur) {
        this.id_annonceur = id_annonceur;
    }

    public void setLieux(String ville) {
        this.ville = ville;
    }

    public void setAdresse_image(ArrayList<String> adresse_image) {
        this.adresse_image = adresse_image;
    }

    public void setNbvues(Map<String, Integer> nbvues) {
        this.nbvues = nbvues;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public void setFiltre(String filtre) {
        this.filtre = filtre;
    }

    public void setPic_bytes(ArrayList<String> pic_bytes) {
        this.pic_bytes = pic_bytes;
    }

    public void setList_messages(List<Message> list_messages) {
        this.list_messages = list_messages;
    }

    @Override
    public String toString() {
        return "Annonce{" +
                "id_annonce=" + id_annonce +
                ", titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                ", prix=" + prix +
                ", date_publication='" + date_publication + '\'' +
                ", id_annonceur=" + id_annonceur +
                ", ville='" + ville + '\'' +
                ", departement='" + departement + '\'' +
                ", adresse_image=" + adresse_image +
                ", nbvues=" + nbvues +
                ", categories='" + categories + '\'' +
                ", contact='" + contact + '\'' +
                ", filtre='" + filtre + '\'' +
                ", pic_bytes=" + pic_bytes +
                ", list_messages=" + list_messages +
                '}';
    }
}
