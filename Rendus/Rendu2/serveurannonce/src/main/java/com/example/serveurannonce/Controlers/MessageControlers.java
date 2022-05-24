package com.example.serveurannonce.Controlers;

import com.example.serveurannonce.Models.*;
import com.example.serveurannonce.Repository.AnnonceRepository;
import com.example.serveurannonce.Repository.ConversationRepository;
import com.example.serveurannonce.Repository.MessageRepository;
import com.example.serveurannonce.Repository.UserRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class MessageControlers {

    @Autowired
    private ConversationRepository conversation;

    @Autowired
    private MessageRepository message;
    @Autowired
    private AnnonceRepository annonce;
    @Autowired
    private UserRepository user;

private static final String uri = "LeMauvaisCoin/api/message";

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping(uri + "/PutMessage")
    public Message PutMessage(@RequestBody ArrayList<String> argument) {
        Gson gson = new Gson();
        System.out.println(argument.get(1));
        Message newmessage = gson.fromJson(argument.get(1),Message.class);
        System.out.println(newmessage.getMessage());
        newmessage.setMessagec(conversation.findById(Long.valueOf(argument.get(0))).get());
        System.out.println(newmessage.getUser());
        message.save(newmessage);
        conversation.findById(Long.valueOf(argument.get(0)))
                .map(Conversation -> {
                    Conversation.addMessage(newmessage);
                    return conversation.save(Conversation);
                });


         return newmessage;




    }

    @PostMapping(uri + "/ConversationById")
    public Conversation GetConversationById(@RequestBody long id) {
        System.out.println(id);
        //System.out.println(conversation.findById(id).get().getList_messages().get(0).getUser());
        return conversation.findById(id).get();
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping(uri+"/PutConversation")
    public Conversation PutConversation(@RequestBody Conversation f) throws IOException {
        Conversation n = new Conversation(f.getId_annonce(),f.getId_user1(),f.getId_user2(),annonce.findById(f.getId_annonce()).get());
        return conversation.save(n);
    }

    @PostMapping(uri + "/MessageByIdAnnonceur")
    public List<MessageAffichage> MessageByIdAnnonceur(@RequestBody long id) {
        List<Annonce> Mesannonces = user.findById(id).get().getMesannonces();
        List<MessageAffichage> m = new ArrayList<>();

        for(Conversation c : conversation.findAll()){
            String profil = null;
            String pseudo = null;
            if(c.getId_user2() == id || c.getId_user1() == id ){
                User user1 = null;
                if(id == c.getId_user2()) {
                     user1 = user.findById(c.getId_user1()).get();
                    profil = user1.getImage();
                    pseudo = user1.getPseudo();
                }else{
                    user1 = user.findById(c.getId_user2()).get();
                    profil = user1.getImage();
                    pseudo = user1.getPseudo();
                }

                MessageAffichage p = new MessageAffichage(profil,c.getAnn().getTitre(),pseudo,c.getList_messages(),c.getId_conversation());
                m.add(p);
            }
        }

        return m;
    }



}
