package com.example.serveurannonce.Models;

import javax.persistence.*;

@Entity
public class Message {
    @Id
    private long id_message;
    @Column(name = "USER")
    @OneToOne(fetch = FetchType.LAZY)
    private User user;
    @Column(name = "ANNONCE")
    @OneToOne(fetch = FetchType.LAZY)
    private Annonce annonce;
    @Column(name = "MESSAGE")
    private String message;
}
