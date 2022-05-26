import { Component, OnInit } from '@angular/core';
import { AnnonceService } from '../annonce.service' ;
import { Router, ActivatedRoute } from '@angular/router';
import { Departements, Categories } from '../_Models/Utils';

@Component({
  selector: 'app-editerannonce',
  templateUrl: './editerannonce.component.html',
  styleUrls: ['./editerannonce.component.css']
})
export class EditerannonceComponent implements OnInit {

  constructor(private route: ActivatedRoute, 
    private annonce : AnnonceService,
    private router : Router) { }

    // Liste des départements francais : INCOMPLETTE
  public departements : string[] = Departements.list;

    // Liste des catégories : INCOMPLETTE
  public categories : { nomCat : string, listeSousCat : string[]}[] = Categories.list ;

  private idAnnonce : number = -1;
  public detailedAnnonce : any;

  ngOnInit(): void {
    console.log(this.route.snapshot.params['id']) ;
    this.idAnnonce = this.route.snapshot.params['id'];      
    
    if (this.idAnnonce != undefined) {
      this.annonce.getListAnnonce("/AnnonceById/"+this.idAnnonce).subscribe(doc => {
        console.log(doc) ;
        this.detailedAnnonce = doc; 
      });
    }else {
      this.router.navigate(['/home']);
    }
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

  public editerAnnonce(titre : string, description : string, prix : string, 
    categorie : string, sous_categorie : string, 
    departement : string, ville : string, 
    images : any) : void {

      console.log("Titre : " + titre + " Desc : " + description + " Prix : " + prix);
      console.log("Categorie : " + categorie + " (" + sous_categorie + ")");
      console.log("Departement : " + departement + " (" + ville + ")");
      console.log("Images : " + images) ;

  }
}