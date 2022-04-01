package com.example.serveurannonce.Controlers;

import com.example.serveurannonce.Models.*;
import com.example.serveurannonce.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserControlers {

    @Autowired
    private AnnonceurRepository annonceur;

    @Autowired
    private AnnonceurProRepository annonceur_pro;

    @Autowired
    private AnnonceurPartRepository annonceur_part;

    @Autowired
    private ConsultantRepository consultant;

    @Autowired
    private UserRepository user;


    private static final String uri = "LeMauvaisCoin/api/User";
    @GetMapping(uri + "/Annonceur_parti")
    public List<Annonceur_Particulier> GetAnnonceurPart() {
        return (List<Annonceur_Particulier>) annonceur_part.findAll();

    }

    @GetMapping(uri + "/Annonceur_pro")
    public List<Annonceur_pro> GetAnnonceurPro() {
        return (List<Annonceur_pro>) annonceur_pro.findAll();

    }

    @GetMapping(uri + "/Annonceur")
    public List<Annonceur> GetAnnonceur() {
        return (List<Annonceur>) annonceur.findAll();

    }

    @GetMapping(uri + "/Consultant")
    public List<Consultant> GetConcurant() {
        return  (List<Consultant>)consultant.findAll();

    }
    @GetMapping(uri + "/User")
    public List<User> GetUser() {
        return  (List<User>)user.findAll();
    }

    @GetMapping(uri + "/UserById/{id}")
    public User GetUserById( @PathVariable Long id) {
        return user.findById(id).get();
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

        return consultant.findById(id).get().getSauvegarde();
    }


    @GetMapping(uri + "/AnnonceId/{id}")
    public List<Annonce> GetAnnonceById(@PathVariable Long id) {

        return annonceur.findById(id).get().getMesannonces();
    }

}
