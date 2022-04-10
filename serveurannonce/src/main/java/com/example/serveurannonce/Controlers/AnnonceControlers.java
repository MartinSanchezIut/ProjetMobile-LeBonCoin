package com.example.serveurannonce.Controlers;

import com.example.serveurannonce.Models.Annonce;
import com.example.serveurannonce.Repository.AnnonceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
@RestController
public class AnnonceControlers {

    @Autowired
    private AnnonceRepository annonce;


    private static final String uri = "LeMauvaisCoin/api/annonce";
    @GetMapping(uri + "/GetAnnonce")
    public List<Annonce> GetAnnonce() {
        return annonce.findAll();
    }

    @GetMapping(uri + "/AnnonceById/{id}")
    public Annonce GetAnnonceById(@PathVariable Long id) {
        return annonce.findById(id).get();
    }

    @GetMapping(uri + "/Recherche/{lieux}/{categories}/{filtre}")
    public List<Annonce> GetRechercheAnnonce(@PathVariable String lieux,@PathVariable String categories ,@PathVariable String filtre) {
        List<Annonce> result = new ArrayList<Annonce>();
        for(Annonce ann : annonce.findAll()){
            if((lieux.equals("null") || lieux.equals(ann.getLieux())) && (categories.equals("null") || categories.equals(ann.getCategories())) &&(filtre.equals("null") || filtre.equals(ann.getFiltre()))){
                result.add(ann);
            }
        }
        return result;
    }

    @GetMapping(uri + "/Recent")
    public List<Annonce> GetRecentAnnonce() {
        SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();

        Date dateObj = calendar.getTime();
        String formattedDate = dtf.format(dateObj);
        List<Annonce> result = new ArrayList<Annonce>();
        for(Annonce ann : annonce.findAll()){
            if(ann.getDate_publication().equals(formattedDate)){
                result.add(ann);
            }
        }
        return result;
    }
}
