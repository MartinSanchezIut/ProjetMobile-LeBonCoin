import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Departements, Categories } from '../_Models/Utils';
import { AnnonceService } from '../annonce.service' ;
import { Recherche } from '../_Models/dbAcces/Recherche';

@Component({
  selector: 'app-recherche',
  templateUrl: './recherche.component.html',
  styleUrls: ['./recherche.component.css']
})
export class RechercheComponent implements OnInit {


    // Liste des départements francais : INCOMPLETTE
  public departements : string[] = Departements.list;

    // Liste des catégories : INCOMPLETTE
  public categories : { nomCat : string, listeSousCat : string[]}[] = Categories.list ;

  
  constructor(private router : Router, private annonce : AnnonceService,) { }

  ngOnInit(): void {
  }


  public hideMeToShow(hide : HTMLElement, show : HTMLElement) : void {
    hide.classList.add("hide");
    show.classList.remove("hide");
  }

  public setTextOf(elmt : HTMLElement, text : string) : void {
    elmt.innerText = text;
  }

  public chercher(cat : string, departement : string) : void {
    let listeAnnonces : Recherche[] ;

    let recherche : Recherche = new Recherche();
    if (cat == "CHOISIR UNE CATÉGORIE ..."){
      cat = "";
    }
    if (departement == "OU ?"){
      departement = "";
    }
    recherche.categorie = cat;
    recherche.departement?.push(departement);
    console.log(recherche);
    this.annonce.rechercher(recherche).subscribe(doc => {
      console.log("Résultat recherche :" + doc) ;
      listeAnnonces = doc; 
      localStorage.setItem("listAnnonces", JSON.stringify(listeAnnonces));
      this.router.navigate(['/listeannonce']);
    });
    console.log(cat + " " + departement + " ") ;
  }
}
