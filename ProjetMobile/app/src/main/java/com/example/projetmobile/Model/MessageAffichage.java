package com.example.projetmobile.Model;

import java.util.ArrayList;
import java.util.List;

public class MessageAffichage {

    private long id_conversation;
    private String profil;
    private String titreAnnonce;
    private String pseudo;
    private List<Message> messages;


    public MessageAffichage(String Profil, String TitreAnnonce, String pseudo, List<Message> Messages) {
        this.profil = Profil;
        this.titreAnnonce = TitreAnnonce;
        this.pseudo = pseudo;
        this.messages = Messages;
    }


    public String getProfil() {
        return this.profil;
    }

    public String getTitreAnnonce() {
        return this.titreAnnonce;
    }

    public String getPseudo() {
        return this.pseudo;
    }

    public List<Message> getMessage() {
        return this.messages;
    }



    public void setProfil(String profil) {
        this.profil = profil;
    }

    public void setTitreAnnonce(String titreAnnonce) {
        this.titreAnnonce = titreAnnonce;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setMessages(ArrayList<Message> Messages) {
        this.messages = Messages;
    }

    public long getId_conversation() {
        return id_conversation;
    }

    public void setId_conversation(long id_conversation) {
        this.id_conversation = id_conversation;
    }
}
