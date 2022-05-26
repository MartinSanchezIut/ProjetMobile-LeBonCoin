import { Component, OnInit } from '@angular/core';
import { Departements, Categories } from '../_Models/Utils';
import { UserauthentificationService } from '../userauthentification.service';
import { Router } from '@angular/router';
import { AnnonceService } from '../annonce.service';
import { Annonce } from '../_Models/dbAcces/Annonce';

@Component({
  selector: 'app-ajouterannonce',
  templateUrl: './ajouterannonce.component.html',
  styleUrls: ['./ajouterannonce.component.css']
})
export class AjouterannonceComponent implements OnInit {

    // Liste des départements francais : INCOMPLETE
  public departements : string[] = Departements.list;

    // Liste des catégories : INCOMPLETE
  public categories : { nomCat : string, listeSousCat : string[]}[] = Categories.list ;

  public responseAnnonce : any;

  
  constructor(private userauthentification: UserauthentificationService, private router: Router, private annonceservice: AnnonceService) { }

  ngOnInit(): void {
    if (!this.userauthentification.isConnected()) {
      console.log("Pas d'utilisateur connecté. Renvoie vers la page de connexion.");
      this.router.navigate(['/connexion']);
    }
  }

  public addAnnonce(titre : string, description : string, prix : string, 
                    categorie : string, sous_categorie : string, 
                    departement : string, ville : string, 
                    images : any[]) : void {
  
    console.log("Titre : " + titre + " Desc : " + description + " Prix : " + prix);
    console.log("Categorie : " + categorie + " (" + sous_categorie + ")");
    console.log("Departement : " + departement + " (" + ville + ")");
    console.log("Images : " + images) ;

    let annonce : Annonce = new Annonce();
    annonce.titre = titre;
    annonce.description = description;
    annonce.prix = prix;
    annonce.categories = categorie;
    annonce.filtre = sous_categorie;
    annonce.departement = departement;
    annonce.ville = ville;
    annonce.image = images;
    let user = JSON.parse(this.userauthentification.getConnectedUser()!);
    annonce.id_annonceur = user.id_user;

    this.annonceservice.ajouterAnnonce(annonce).subscribe(responseannonce => {
      this.responseAnnonce = responseannonce;
      console.log("Annonce envoyée !");
    });
    this.router.navigate(['/home']);
  }

  public hideMeToShow(hide : HTMLElement, show : HTMLElement) : void {
    hide.classList.add("hide");
    show.classList.remove("hide");
  }

  public setTextOf(elmt : HTMLElement, text : string) : void {
    elmt.innerText = text;
  }
  
  public getSousCategories(categorie : string) : string[] {
    return Categories.getSousCategorieOf(categorie) ;
  }

}
