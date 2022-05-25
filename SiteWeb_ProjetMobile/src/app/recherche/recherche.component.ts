import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Departements, Categories } from '../_Models/Utils';
import { AnnonceService } from '../annonce.service' ;

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

  public chercher(cat : string, lieu : string, titre : string) : void {
    let listeAnnonces = [] ;

    let recherche = {categorie : cat, departement: [lieu]};
    this.annonce.rechercher(recherche).subscribe(doc => {
      console.log(doc) ;
      listeAnnonces = doc; 
      this.router.navigate(['/listeannonce', recherche]);
    });      
    console.log(cat + " " + lieu + " " + titre) ;
  }
}
