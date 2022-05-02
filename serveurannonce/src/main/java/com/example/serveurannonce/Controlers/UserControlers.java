package com.example.serveurannonce.Controlers;

import com.example.serveurannonce.Models.*;
import com.example.serveurannonce.Repository.*;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserControlers {


    @Autowired
    private AnnonceurProRepository annonceur_pro;

    @Autowired
    private AnnonceurPartRepository annonceur_part;



    @Autowired
    private UserRepository user;


    private static final String uri = "LeMauvaisCoin/api/User";
    @GetMapping(uri + "/UserById/{id}")
    public User GetUserById( @PathVariable Long id) {
        return user.findById(id).get();
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping(uri + "/InscriptionAnnonceurPro")
    public Annonceur_pro GetUserById( @RequestBody Annonceur_pro annonceur) {

        System.out.println(annonceur.getImage());
        return annonceur_pro.save(annonceur);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping(uri + "/InscriptionAnnonceurPart")
    public Annonceur_Particulier GetUserById(@RequestBody Annonceur_Particulier annonceur) {
        System.out.println(annonceur.getImage());
        return annonceur_part.save(annonceur);
    }


    @GetMapping(uri + "/Connexion/{email}/{password}")
    public User GetConnexion( @PathVariable String email ,@PathVariable String password ) {

        for(User u : user.findAll() ){
            if(u.getEmail().equals(email) && u.getMot_de_passe().equals(password)){
                return u;
            }
        }
        return null;
    }


    @GetMapping(uri + "/Sauvegarde/{id}")
    public List<Annonce> GetConnexion(@PathVariable Long id) {

        return user.findById(id).get().getSauvegarde();
    }


    @GetMapping(uri + "/AnnonceId/{id}")
    public List<Annonce> GetAnnonceById(@PathVariable Long id) {

        return user.findById(id).get().getMesannonces();
    }

}
