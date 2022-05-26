package com.example.serveurannonce.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class Message {
    @Id
    @GeneratedValue
    private long id_message;

    private long user;

    @Column(name = "MESSAGE")
    private String message;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="Conversation", nullable=false)
    private Conversation messagec;
    public Message(){

    }
    public Message(long user, String message,Conversation messagec) {
        this.user = user;
        this.message = message;
        this.messagec = messagec;
    }

    public long getUser() {
        return user;
    }


    public String getMessage() {
        return message;
    }


    public void setId_message(long id_message) {
        this.id_message = id_message;
    }

    public void setId_user(long user) {
        this.user = user;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setMessagec(Conversation messagec) {
        this.messagec = messagec;
    }
}
