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
            Annonceur_Particulier a = new Annonceur_Particulier(0,"AnnonceurPart","toto","sanchez","martin","test1","06 16 66 16 30","A");
            annonceur_part.save(a);
            ArrayList<Annonce> n2= new ArrayList<>();
            Annonceur_pro ap = new Annonceur_pro(1,"AnnonceurPro","toto","TRINQUART","Matthieu","test2","06 06 06 06 06","A","matthieucompagny");
            annonceur_pro.save(ap);
            ArrayList<Annonce> n3= new ArrayList<>();

            Map<String, Integer> nbvue = new HashMap<>();
            ArrayList<Message> m1= new ArrayList<>();

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

            Annonce ann = new Annonce(0L,"Velo","Tres beau velo",50,"23/04/2022","Ile de france","Paris" ,0L,i1,nbvue,"Email","Sport","velo",m1);
            Annonce ann1 = new Annonce(1L,"Marteau","Tres beau Marteau",50,"23/04/2022","Herault","Montpellier" ,0L,i2,nbvue,"Email","Bricolage","Marteau",m1);
            Annonce ann2 = new Annonce(2L,"Phone","Tres beau téléphone",50,"23/04/2022","Bouche du Rhone","Miramas" ,0L,i3,nbvue,"Email","Multimedia","Telephone",m1);
            Annonce ann3 = new Annonce(3L,"ordinateur","Tres beau ordinateur",50,"23/04/2022","Bouche du Rhone","Eyguières" ,0L,i4,nbvue,"Email","Sport","velo",m1);
            Annonce ann4 = new Annonce(4L,"bateau","Tres beau bateau",50,"23/04/2022","Bouche du Rhone","Salon" ,0L,i5,nbvue,"Email","Sport","velo",m1);
            Annonce ann5 = new Annonce(5L,"cafetière","Tres beau cafetière",50,"23/04/2022","Ile est Vilaine","Renne" ,0L,i6,nbvue,"Email","Sport","velo",m1);
            Annonce ann6 = new Annonce(6L,"Eponge","Tres beau Eponge",50,"23/04/2022","Finistère","Brest" ,0L,i7,nbvue,"Email","Sport","velo",m1);

            ann.setAnn1(a);
            ann1.setAnn1(a);
            ann2.setAnn1(a);
            ann3.setAnn1(a);
            ann4.setAnn1(a);
            ann5.setAnn1(a);
            ann6.setAnn1(a);
          //  ann.addAnn3(c);
            annonce.save(ann);
           annonce.save(ann1);
            //annonce.save(ann6);
          // annonce.save(ann2);
         //   annonce.save(ann3);
            Message m = new Message(0,0,"Il est moche",ann);
            message.save(m);
            a.addsauvegarde(ann);
            annonceur_part.save(a);






        };
    }
}
