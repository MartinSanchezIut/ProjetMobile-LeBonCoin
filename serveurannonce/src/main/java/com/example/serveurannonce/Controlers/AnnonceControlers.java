package com.example.serveurannonce.Controlers;

import com.example.serveurannonce.Models.Annonce;
import com.example.serveurannonce.Models.Recherche;
import com.example.serveurannonce.Repository.AnnonceRepository;
import com.example.serveurannonce.Repository.UserRepository;
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

    @Autowired
    private UserRepository user;
    private static final String uri = "LeMauvaisCoin/api/annonce";
    @GetMapping(uri + "/GetAnnonce")
    public List<Annonce> GetAnnonce() {
        return annonce.findAll();
    }

    @GetMapping(uri + "/AnnonceById/{id}")
    public Annonce GetAnnonceById(@PathVariable Long id) {
        return annonce.findById(id).get();
    }

    @PostMapping(uri + "/Recherche")
    public List<Annonce> GetRechercheAnnonce(@RequestBody Recherche re) {
        List<Annonce> result = new ArrayList<Annonce>();
        /*
        System.out.println(re.getPrix1());
        System.out.println(re.getPrix2());
        System.out.println(re.getCategorie());
        System.out.println(re.getDepartement());
        System.out.println(re.getFiltre());
        System.out.println(re.getVille());
        System.out.println(re.isParticulier());
        System.out.println(re.isProfessionel());

         */
        for(Annonce ann : annonce.findAll()){
            System.out.println(ann.getTitre());
            System.out.println(re.getCategorie() == null || re.getCategorie().equals(ann.getCategories()));
            System.out.println(re.getFiltre() == null || re.getFiltre().equals(ann.getFiltre()) );
            System.out.println(re.getPrix1() == null || re.getPrix1() <= ann.getPrix());
            System.out.println(re.getPrix2() == null || re.getPrix2() >= ann.getPrix());
            System.out.println(re.getDepartement() == null || re.getDepartement().equals(ann.getDepartement()));
            System.out.println(re.getVille() == null || re.getVille().equals(ann.getVille()));

            if(
                    (re.getCategorie() == null || re.getCategorie().equals(ann.getCategories()) ) &&
                            (re.getFiltre() == null || re.getFiltre().equals(ann.getFiltre()) ) &&
                            (re.getPrix1() == null || re.getPrix1() <= ann.getPrix())  &&
                            (re.getPrix2() == null || re.getPrix2() >= ann.getPrix())  &&
                            (re.getDepartement() == null || re.getDepartement().equals(ann.getDepartement()))  &&
                            (re.getVille() == null || re.getVille().equals(ann.getVille()))
            ){
                    result.add(ann);

            }
        }
        System.out.println(result.get(0).getTitre());
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
