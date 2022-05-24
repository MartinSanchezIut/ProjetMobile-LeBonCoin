package com.example.projetmobile.Model;



public class Message {
    private long id_message;
    private long id_user;
    private String message;


    public Message(){

    }
    public Message(long id_message, long id_user, String message) {
        this.id_message = id_message;
        this.id_user = id_user;
        this.message = message;
    }

    public long getUser() {
        return id_user;
    }


    public String getMessage() {
        return message;
    }

}
