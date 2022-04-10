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
    public CommandLineRunner initDatabase( AnnonceurProRepository annonceur_pro, AnnonceurPartRepository annonceur_part, AnnonceRepository annonce,MessageRepository message) {
        return args -> {
            ArrayList<Annonce> n1= new ArrayList<>();
            Annonceur_Particulier a = new Annonceur_Particulier(0,"AnnonceurPart","toto","sanchez","martin","martin.sanchez@gmail.com","06 16 66 16 30","azerty");
            annonceur_part.save(a);
            ArrayList<Annonce> n2= new ArrayList<>();
            Annonceur_pro ap = new Annonceur_pro(1,"AnnonceurPro","toto","TRINQUART","Matthieu","matthieu.trinquart@gmail.com","06 06 06 06 06","azerty","matthieucompagny");
            annonceur_pro.save(ap);
            ArrayList<Annonce> n3= new ArrayList<>();

            Map<String, Integer> nbvue = new HashMap<>();
            ArrayList<Message> m1= new ArrayList<>();
            Annonce ann = new Annonce(0L,"Velo","Tres beau velo",50,"10/04/2022","Paris" ,0L,"com/example/serveurannonce/Image/Velo.jpg",nbvue,"Sport","velo",m1);
            Annonce ann1 = new Annonce(1L,"Velo","Tres beau velo",50,"10/04/2022","Paris" ,0L,"com/example/serveurannonce/Image/Velo.jpg",nbvue,"Sport","velo",m1);
            Annonce ann2 = new Annonce(2L,"Velo","Tres beau velo",50,"10/04/2022","Paris" ,0L,"com/example/serveurannonce/Image/Velo.jpg",nbvue,"Sport","velo",m1);
            Annonce ann3 = new Annonce(3L,"Velo","Tres beau velo",50,"10/04/2022","Paris" ,0L,"com/example/serveurannonce/Image/Velo.jpg",nbvue,"Sport","velo",m1);
            Annonce ann4 = new Annonce(4L,"Velo","Tres beau velo",50,"10/04/2022","Paris" ,0L,"com/example/serveurannonce/Image/Velo.jpg",nbvue,"Sport","velo",m1);
            Annonce ann5 = new Annonce(5L,"Velo","Tres beau velo",50,"10/04/2022","Paris" ,0L,"com/example/serveurannonce/Image/Velo.jpg",nbvue,"Sport","velo",m1);
            Annonce ann6 = new Annonce(6L,"Velo","Tres beau velo",50,"10/04/2022","Paris" ,0L,"com/example/serveurannonce/Image/Velo.jpg",nbvue,"Sport","velo",m1);

            ann.setAnn1(a);
            ann1.setAnn1(a);
            ann2.setAnn1(a);
            ann3.setAnn1(a);
            ann4.setAnn1(a);
            ann5.setAnn1(a);
            ann6.setAnn1(a);
          //  ann.addAnn3(c);
            annonce.save(ann);
            Message m = new Message(0,0,"Il est moche",ann);
            message.save(m);
            a.addsauvegarde(ann);
            annonceur_part.save(a);






        };
    }
}
