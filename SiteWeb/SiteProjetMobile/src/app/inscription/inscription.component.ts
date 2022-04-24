import { Component, OnInit } from '@angular/core';
import { FormUser } from '../Models/FormUser';

@Component({
  selector: 'app-inscription',
  templateUrl: './inscription.component.html',
  styleUrls: ['./inscription.component.css']
})
export class InscriptionComponent implements OnInit {

  private page : number = 1;
  private usr : FormUser = new FormUser() ;

  

  constructor() { }

  ngOnInit(): void {
  }


  public nextPage() : number{
    let next = this.page+1 ;
    console.log("From : " + this.page + " to : " + next) ;
    this.page += 1;
    
    for(let i = 0; i<document.getElementsByClassName("page").length; i++) {
      if (document.getElementsByClassName("page")[i].id == String(this.page)) {
        document.getElementsByClassName("page")[i].classList.remove("hide");
      }else {
        document.getElementsByClassName("page")[i].classList.add("hide");
      } 
    }

    if (this.page == 2) {
      if(this.type == 'PROFESSIONNEL') {
          document.getElementById("nEntreprise")?.classList.remove("hide");
      }
    }

    this.ngOnInit() ;
    return this.page ;
  }

  
  // -*-*-*-*-*-*-*-*-*-* PAGE 1 -*-*-*-*-*-*-*-*-*-*
  public type : String = "";
  public select(first : HTMLElement, second : HTMLElement, nextPage : HTMLElement) : void {
    nextPage.classList.remove("disabled");

    // Set type of created user to the value of the selected button
    this.usr.setType(first.innerText);
    this.type = first.innerText;

    first.style.backgroundColor = "#9BE7FF";
    second.style.backgroundColor = "#F1EEEE";
  }

  // -*-*-*-*-*-*-*-*-*-* PAGE 1 -*-*-*-*-*-*-*-*-*-*

  public saveForm(nom : String, prenom : String, email : String, tel : String,
                  pseudo : String, mdp : String, nomEntreprise : String) : void {
                    
      this.usr.setNom(nom) ;
      this.usr.setPrenom(prenom) ;
      this.usr.setEmail(email) ;
      this.usr.setNumero(tel) ;
      this.usr.setPseudo(pseudo) ;
      this.usr.setMot_de_passe(mdp) ;
      this.usr.setNomEntreprise(nomEntreprise) ;

      console.log(this.usr.toString()) ;
    }
}
