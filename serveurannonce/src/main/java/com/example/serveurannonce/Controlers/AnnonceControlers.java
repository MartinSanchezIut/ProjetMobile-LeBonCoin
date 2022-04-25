package com.example.serveurannonce.Controlers;

import com.example.serveurannonce.Models.Annonce;
import com.example.serveurannonce.Repository.AnnonceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    @GetMapping(uri + "/Recherche/{Ville}/{categories}/{filtre}")
    public List<Annonce> GetRechercheAnnonce(@PathVariable String Ville,@PathVariable String categories ,@PathVariable String filtre) {
        List<Annonce> result = new ArrayList<Annonce>();
        for(Annonce ann : annonce.findAll()){
            if((Ville.equals("null") || Ville.equals(ann.getVille())) && (categories.equals("null") || categories.equals(ann.getCategories())) &&(filtre.equals("null") || filtre.equals(ann.getFiltre()))){
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

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping(uri+"/PutAnnonce")
    public Annonce Postformulaire(@RequestBody Annonce f) throws IOException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Annonce n = new Annonce(f.getTitre(),f.getDescription(),f.getPrix(),dtf.format(LocalDateTime.now()),f.getDepartement(),f.getVille(),f.getId_annonce(),f.getImage(),f.getContact(),f.getCategories(),f.getFiltre());
        System.out.println();
        return annonce.save(n);
    }
}
