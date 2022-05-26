package com.example.projetmobile.Model;



public class Message {
    private long id_message;
    private long user;
    private String message;



    public Message(long user, String message) {
        this.user = user;
        this.message = message;
    }

    public long getUser() {
        return user;
    }


    public String getMessage() {
        return message;
    }

}
