package com.example.serveurannonce.Models;

import java.util.ArrayList;
import java.util.List;

public class MessageAffichage {
    private long id_conversation;
    private String Profil;
    private String TitreAnnonce;
    private String pseudo;
    private List<Message> Messages;


    public MessageAffichage(String profil, String titreAnnonce, String pseudo, List<Message> Messages,long id_conversation) {
        Profil = profil;
        TitreAnnonce = titreAnnonce;
        this.pseudo = pseudo;
        this.Messages = Messages;
        this.id_conversation = id_conversation;
    }


    public String getProfil() {
        return Profil;
    }

    public String getTitreAnnonce() {
        return TitreAnnonce;
    }

    public String getPseudo() {
        return pseudo;
    }

    public List<Message> getMessage() {
        return Messages;
    }



    public void setProfil(String profil) {
        Profil = profil;
    }

    public void setTitreAnnonce(String titreAnnonce) {
        TitreAnnonce = titreAnnonce;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setMessages(ArrayList<Message> Messages) {
        this.Messages = Messages;
    }

    public long getId_conversation() {
        return id_conversation;
    }
}
