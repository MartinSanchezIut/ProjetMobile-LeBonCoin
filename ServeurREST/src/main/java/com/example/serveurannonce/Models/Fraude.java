package com.example.serveurannonce.Models;

public class Fraude {
    private String motif;
    private long id_annonce;
    private boolean isfraude;

    public Fraude(String motif, long id_annonce, boolean isfraude) {
        this.motif = motif;
        this.id_annonce = id_annonce;
        this.isfraude = isfraude;
    }

    public String getMotif() {
        return motif;
    }

    public long getId_annonce() {
        return id_annonce;
    }

    public boolean isIsfraude() {
        return isfraude;
    }
}
