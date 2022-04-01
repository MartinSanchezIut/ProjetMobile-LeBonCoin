package com.example.serveurannonce.Data;

import com.example.serveurannonce.Models.*;
import com.example.serveurannonce.Repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class UserData {
    private Logger logger = LoggerFactory.getLogger(UserData.class);

    @Bean
    public CommandLineRunner initDatabase(AnnonceurRepository annonceur, AnnonceurProRepository annonceur_pro, AnnonceurPartRepository annonceur_part, ConsultantRepository consultant , AnnonceRepository annonce,MessageRepository message) {
        return args -> {
            ArrayList<Annonce> n1= new ArrayList<>();
            Annonceur_Particulier a = new Annonceur_Particulier(0,"toto","sanchez","martin","martin.sanchez@gmail.com","06 16 66 16 30","azerty");
            annonceur_part.save(a);
            ArrayList<Annonce> n2= new ArrayList<>();
            Annonceur_pro ap = new Annonceur_pro(1,"toto","TRINQUART","Matthieu","matthieu.trinquart@gmail.com","06 06 06 06 06","azerty","matthieucompagny");
            annonceur_pro.save(ap);
            ArrayList<Annonce> n3= new ArrayList<>();
            Consultant c = new Consultant(2,"toto","MONOT","Lea","lea.monot@gmail.com","06 06 06 06 06","azerty");
            consultant.save(c);

            Map<String, Integer> nbvue = new HashMap<>();
            ArrayList<Message> m1= new ArrayList<>();
            Annonce ann = new Annonce(0L,"Velo","Tres beau velo",50,"30/03/2022","Paris" ,0L,"com/example/serveurannonce/Image/Velo.jpg",nbvue,"Sport","velo",m1);
            ann.setAnn1(a);
          //  ann.addAnn3(c);
            annonce.save(ann);
            Message m = new Message(0,c,"Il est moche",ann);
            message.save(m);
            c.addsauvegarde(ann);
            consultant.save(c);






        };
    }
}
