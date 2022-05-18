package com.example.serveurannonce.Controlers;

import com.example.serveurannonce.Models.Annonce;
import com.example.serveurannonce.Models.Recherche;
import com.example.serveurannonce.Models.User;
import com.example.serveurannonce.Repository.AnnonceRepository;
import com.example.serveurannonce.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
public class AnnonceControlers {

    @Autowired
    private AnnonceRepository annonce;

    @Autowired
    private UserRepository user;
    private static final String uri = "LeMauvaisCoin/api/annonce";

    @GetMapping(uri + "/AnnonceById/{id}")
    public Annonce GetAnnonceById(@PathVariable Long id) {
        return annonce.findById(id).get();
    }

    @GetMapping(uri + "/Getsauvegardeid/{id_user}")
    public List<Long> GetSauvegardeid(@PathVariable long id_user) {
        System.out.println(id_user);
        ArrayList<Long> result = new ArrayList<>();
        for(Annonce a : user.findById(id_user).get().getSauvegarde()){
            result.add(a.getId_annonce());
        }
        return result;
    }
    @GetMapping(uri + "/NbVuAnnonce/{id_annonce}")
    public Map<String,Integer> GetBnVu(@PathVariable long id_annonce) {
        return annonce.findById(id_annonce).get().getNbvues();
    }

    @GetMapping(uri + "/Getsauvegarde/{id_user}")
    public List<Annonce> GetSauvegarde(@PathVariable long id_user) {
        return user.findById(id_user).get().getSauvegarde();
    }

    @GetMapping(uri + "/GetMesAnnonces/{id_user}")
    public List<Annonce> GetMesAnnonces(@PathVariable long id_user) {
        return user.findById(id_user).get().getMesannonces();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(uri+"/deleteannonce/{id_user}/{id_annonce}")
    public void deleteEmployee(@PathVariable long id_user,@PathVariable long id_annonce)  {

        Annonce a = annonce.findById(id_annonce).get();
        for(User u : a.getAnn3()){
            u.deletesauvegarde(a);
            user.save(u);
        }
        annonce.delete(a);
    }

    @PostMapping(uri + "/Recherche")
    public List<Annonce> GetRechercheAnnonce(@RequestBody Recherche re) {
        List<Annonce> result = new ArrayList<Annonce>();
        for(Annonce ann : annonce.findAll()){
            System.out.println(ann.getTitre());
            System.out.println(re.getCategorie().equals("")||re.getCategorie().equals("Toutes les catégories") || re.getCategorie().equals(ann.getCategories())  );
            System.out.println(re.getFiltre().equals("") || re.getFiltre().equals("Tous filtres") || re.getFiltre().equals(ann.getFiltre()) );
            System.out.println(re.getPrix1() == null || re.getPrix1() <= ann.getPrix());
            System.out.println(re.getPrix2() == null || re.getPrix2() >= ann.getPrix());
            System.out.println(re.getVille().equals("") || re.getVille().equals(ann.getVille()));
            System.out.println(ann.getId_annonceur());
            User u = user.findById(ann.getId_annonceur()).get();
            System.out.println(u.getStatu());
            System.out.println(u.getStatu().equals("AnnonceurPart") && re.isParticulier());
            System.out.println(u.getStatu().equals("AnnonceurPro") && re.isProfessionel());
            boolean departement = false;
            if(re.getDepartement() != null) {
                for (String s : re.getDepartement()) {
                    if (s.equals(ann.getDepartement())) {
                        departement = true;
                    }
                }
            }
            else{
                departement = true;
            }

            if(
                    (re.getCategorie() == null||re.getCategorie().equals("Toutes les catégories")  || re.getCategorie().equals(ann.getCategories()) ) &&
                            (re.getFiltre() == null|| re.getFiltre().equals("Tous filtres") || re.getFiltre().equals(ann.getFiltre()) ) &&
                            (re.getPrix1() == null || re.getPrix1() <= ann.getPrix())  &&
                            (re.getPrix2() == null || re.getPrix2() >= ann.getPrix())  &&
                            departement  &&
                            (re.getVille().equals("") || re.getVille().equals(ann.getVille())) &&
                            ((u.getStatu().equals("AnnonceurPart") && re.isParticulier()) ||
                            (u.getStatu().equals("AnnonceurPro") && re.isProfessionel()))
            ){
                    result.add(ann);

            }
        }
        //System.out.println(result.get(0).getTitre());
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

    @PostMapping(uri + "/Favoris")
    public void PostFavoris(@RequestBody ArrayList<String> params) {
        System.out.println(params.get(0) + "   " + params.get(1));
        user.findById(Long.valueOf(params.get(0)))
                .map(User -> {
                    User.addsauvegarde(annonce.findById(Long.valueOf(params.get(1))).get());
                    return user.save(User);
                    });
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping(uri+"/PutAnnonce")
    public Annonce Putformulaire(@RequestBody Annonce f) throws IOException {
        User d = user.findById(f.getId_annonceur()).get();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Annonce n = new Annonce(f.getTitre(),f.getDescription(),f.getPrix(),dtf.format(LocalDateTime.now()),f.getDepartement(),f.getVille(),f.getAnnonceur(),f.getImage(),f.getContact(),f.getCategories(),f.getFiltre());
        n.setAnn1(d);
        return annonce.save(n);
    }

    @PostMapping(uri+"/PostAnnonce")
    public void Postformulaire(@RequestBody Annonce f) throws IOException {
        System.out.println("ID ANNONCE " + Long.valueOf(f.getId_annonce()));
        Annonce a = annonce.findById(Long.valueOf(f.getId_annonce())).get();
        a.setTitre(f.getTitre());
        a.setDescription(f.getDescription());
        a.setCategories(f.getCategories());
        a.setFiltre(f.getFiltre());
        a.setContact(f.getContact());
        a.setDepartement(f.getDepartement());
        a.setImage(f.getImage());
        a.setPrix(f.getPrix());
        a.setVille(f.getVille());
        annonce.save(a);

    }
}
