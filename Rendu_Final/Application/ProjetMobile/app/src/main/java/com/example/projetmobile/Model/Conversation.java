package com.example.projetmobile.Model;

import java.util.ArrayList;
import java.util.List;

public class Conversation {
    private long id_conversation;

    private long id_user1;

    private long id_user2;


    private long id_annonce;


    private ArrayList<Message> list_messages;





    public Conversation(long id_annonce,long id_user1, long id_user2) {
        this.id_annonce = id_annonce;
        this.list_messages = list_messages;
        this.id_user1 = id_user1;
        this.id_user2 = id_user2;
    }

    public long getId_annonce() {
        return id_annonce;
    }

    public ArrayList<Message> getList_messages() {
        return list_messages;
    }

    public void addmessage(Message msg){
        this.list_messages.add(msg);
    }

    public long getId_conversation() {
        return id_conversation;
    }

    public long getId_user1() {
        return id_user1;
    }

    public long getId_user2() {
        return id_user2;
    }

    public void setId_conversation(long id_conversation) {
        this.id_conversation = id_conversation;
    }

    public void setId_user1(long id_user1) {
        this.id_user1 = id_user1;
    }

    public void setId_user2(long id_user2) {
        this.id_user2 = id_user2;
    }

    public void setId_annonce(long id_annonce) {
        this.id_annonce = id_annonce;
    }

    public void setList_messages(ArrayList<Message> list_messages) {
        this.list_messages = list_messages;
    }
}
