import { Component, OnInit } from '@angular/core';
import { Departements, Categories } from '../_Models/Utils';

@Component({
  selector: 'app-ajouterannonce',
  templateUrl: './ajouterannonce.component.html',
  styleUrls: ['./ajouterannonce.component.css']
})
export class AjouterannonceComponent implements OnInit {

    // Liste des départements francais : INCOMPLETTE
  public departements : string[] = Departements.list;

    // Liste des catégories : INCOMPLETTE
  public categories : { nomCat : string, listeSousCat : string[]}[] = Categories.list ;

  
  constructor() { }

  ngOnInit(): void {
  }

  public addAnnonce(titre : string, description : string, prix : string, 
                    categorie : string, sous_categorie : string, 
                    departement : string, ville : string, 
                    images : any) : void {
  
    console.log("Titre : " + titre + " Desc : " + description + " Prix : " + prix);
    console.log("Categorie : " + categorie + " (" + sous_categorie + ")");
    console.log("Departement : " + departement + " (" + ville + ")");
    console.log("Images : " + images) ;

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
