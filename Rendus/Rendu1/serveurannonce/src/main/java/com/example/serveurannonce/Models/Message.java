package com.example.serveurannonce.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Message {
    @Id
    private long id_message;

    private long id_user;
    @Column(name = "MESSAGE")
    private String message;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="Annonce", nullable=false)
    private Annonce ann;

    public Message(){

    }
    public Message(long id_message, long id_user, String message, Annonce ann) {
        this.id_message = id_message;
        this.id_user = id_user;
        this.message = message;
        this.ann = ann;
    }

    public long getUser() {
        return id_user;
    }


    public String getMessage() {
        return message;
    }

    public Annonce getAnn() {
        return ann;
    }
}
