import { Component, OnInit } from '@angular/core';
import { Departements, Categories } from '../_Models/Utils';

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

  
  constructor() { }

  ngOnInit(): void {
  }


  public hideMeToShow(hide : HTMLElement, show : HTMLElement) : void {
    hide.classList.add("hide");
    show.classList.remove("hide");
  }

  public setTextOf(elmt : HTMLElement, text : string) : void {
    elmt.innerText = text;
  }

  public chercher(cat : string, lieu : string) : void {
    console.log(cat + " " + lieu) ;
  }
}
