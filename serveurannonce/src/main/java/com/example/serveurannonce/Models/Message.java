package com.example.serveurannonce.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Message {
    @Id
    private long id_message;
    @OneToOne
    @JoinColumn( name="idUser" )
    private User user;
    @Column(name = "MESSAGE")
    private String message;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_user5", nullable=false)
    private Annonce ann;

    public Message(){

    }
    public Message(long id_message, User user, String message, Annonce ann) {
        this.id_message = id_message;
        this.user = user;
        this.message = message;
        this.ann = ann;
    }

    public User getUser() {
        return user;
    }


    public String getMessage() {
        return message;
    }

    public Annonce getAnn() {
        return ann;
    }
}
