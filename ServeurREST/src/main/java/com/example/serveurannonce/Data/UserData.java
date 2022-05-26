package com.example.serveurannonce.Data;

import com.example.serveurannonce.Models.*;
import com.example.serveurannonce.Repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class UserData {
    private Logger logger = LoggerFactory.getLogger(UserData.class);

    @Bean
    public CommandLineRunner initDatabase( AnnonceurProRepository annonceur_pro, AnnonceurPartRepository annonceur_part, AnnonceRepository annonce,MessageRepository message, ConversationRepository conversation) {
        return args -> {
            ArrayList<Annonce> n1= new ArrayList<>();
            Annonceur_Particulier a = new Annonceur_Particulier("AnnonceurPart","toto",null,"sanchez","martin","test1","06 16 66 16 30","A");
            annonceur_part.save(a);
            ArrayList<Annonce> n2= new ArrayList<>();
            Annonceur_pro ap = new Annonceur_pro("AnnonceurPro","tata",null,"TRINQUART","Matthieu","test2","06 06 06 06 06","A","matthieucompagny");
            annonceur_pro.save(ap);
            ArrayList<Annonce> n3= new ArrayList<>();

            Map<String, Integer> nbvue = new HashMap<>();
            nbvue.put("16/05/2022",10);
            nbvue.put("17/05/2022",6);
            nbvue.put("20/05/2022",8);
            nbvue.put("22/05/2022",12);
            nbvue.put("23/05/2022",3);
            nbvue.put("24/05/2022",1);
            ArrayList<Conversation> m1= new ArrayList<>();

            ArrayList<String> i1 = new ArrayList<>();
            i1.add("src/main/java/com/example/serveurannonce/Image/Velo.jpg");
            ArrayList<String> i2 = new ArrayList<>();
            i2.add("src/main/java/com/example/serveurannonce/Image/marteau.jpg");
            ArrayList<String> i3 = new ArrayList<>();
            i3.add("src/main/java/com/example/serveurannonce/Image/phone.jpg");
            ArrayList<String> i4 = new ArrayList<>();
            i4.add("src/main/java/com/example/serveurannonce/Image/ordinateur.jpg");
            ArrayList<String> i5 = new ArrayList<>();
            i5.add("src/main/java/com/example/serveurannonce/Image/bateau.jpg");
            ArrayList<String> i6 = new ArrayList<>();
            i6.add("src/main/java/com/example/serveurannonce/Image/caf.jpg");
            ArrayList<String> i7 = new ArrayList<>();
            i7.add("src/main/java/com/example/serveurannonce/Image/eponge.jpg");

            Annonce ann = new Annonce("Velo","Tres beau velo",50,"26/05/2022","Bouche du Rhone","Montpellier" ,a.getId_user(),i1,nbvue,"Email","Vacances","Location et Gites");
            Annonce ann1 = new Annonce("Marteau","Tres beau Marteau",50,"26/05/2022","Hérault","Montpellier" ,ap.getId_user(),i2,nbvue,"Email","Bricolage","Marteau");
            Annonce ann2 = new Annonce("Phone","Tres beau téléphone",50,"26/05/2022","Bouche du Rhone","Miramas" ,a.getId_user(),i3,nbvue,"Email","Multimedia","Telephone");
            Annonce ann3 = new Annonce("ordinateur","Tres bel ordinateur",50,"26/05/2022","Bouche du Rhone","Eyguières" ,a.getId_user(),i4,nbvue,"Email","Sport","velo");
            Annonce ann4 = new Annonce("bateau","Tres beau bateau",50,"26/05/2022","Bouche du Rhone","Salon" ,a.getId_user(),i5,nbvue,"Email","Vacances","bateau");
            Annonce ann5 = new Annonce("cafetière","Tres belle cafetière",50,"26/05/2022","Ille et Vilaine","Renne" ,a.getId_user(),i6,nbvue,"Email","Autre","Cafetière");
            Annonce ann6 = new Annonce("Eponge","Tres belle Eponge",50,"26/05/2022","Finistère","Brest" ,a.getId_user(),i7,nbvue,"Email","Autre","cuisine");

            ann.setAnn1(a);
            ann1.setAnn1(ap);
            ann2.setAnn1(a);
            ann3.setAnn1(ap);
            ann4.setAnn1(a);
            ann5.setAnn1(ap);
            ann6.setAnn1(a);
          //  ann.addAnn3(c);
            //annonce.save(ann6);
          // annonce.save(ann2);
         //   annonce.save(ann3);
            /*
            Conversation c = new Conversation(ann.getId_annonce(),ap.getId_user(),a.getId_user(),ann);
            conversation.save(c);
            Message m = new Message(a.getId_user(),"Il est moche1",c);
            message.save(m);
            Message m2 = new Message(ap.getId_user(),"Il est moche2",c);
            message.save(m2);
            Message m3 = new Message(a.getId_user(),"Il est moche3",c);
            message.save(m3);
            ArrayList<Message> conv = new ArrayList<>();
            conv.add(m);
            conv.add(m2);
            conv.add(m3);

            ArrayList<Conversation> cl = new ArrayList<>();
            cl.add(c);

            ann.setList_messages(cl);


            Conversation c2 = new Conversation(ann1.getId_annonce(),ap.getId_user(),a.getId_user(),ann1);
            conversation.save(c2);
            Message m12 = new Message(ap.getId_user(),"TEST1",c2);
            message.save(m12);
            Message m22 = new Message(a.getId_user(),"TEST2",c2);
            message.save(m22);
            Message m32 = new Message(ap.getId_user(),"TEST3",c2);
            message.save(m32);
            ArrayList<Message> conv2 = new ArrayList<>();
            conv2.add(m12);
            conv2.add(m22);
            conv2.add(m32);

            ArrayList<Conversation> cl2 = new ArrayList<>();
            cl2.add(c2);

            ann1.setList_messages(cl2);

             */

            a.addsauvegarde(ann);
            a.addsauvegarde(ann1);
            annonce.save(ann);
            annonce.save(ann1);
            annonce.save(ann2);
            annonce.save(ann3);
            annonce.save(ann4);
            annonce.save(ann5);
            annonce.save(ann6);
            annonceur_part.save(a);
            annonceur_pro.save(ap);






        };
    }
}
