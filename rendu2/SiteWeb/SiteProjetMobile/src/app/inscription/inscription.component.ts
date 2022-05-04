import { Component, OnInit } from '@angular/core';
import { FormUser } from '../_Models/FormUser';
import { UserauthentificationService } from '../userauthentification.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-inscription',
  templateUrl: './inscription.component.html',
  styleUrls: ['./inscription.component.css']
})
export class InscriptionComponent implements OnInit {

  private page : number = 1;
  private usr : FormUser = new FormUser() ;
  private registeredUser : FormUser = new FormUser() ;

  

  constructor(private userauthentification : UserauthentificationService, private router: Router) { }

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
      if(this.usr.getStatu() == 'AnnonceurPro') {
        document.getElementById("nEntreprise")?.classList.remove("hide");
      }
    }

    this.ngOnInit() ;
    return this.page ;
  }

  
  // -*-*-*-*-*-*-*-*-*-* PAGE 1 -*-*-*-*-*-*-*-*-*-*
  public select(first : HTMLElement, second : HTMLElement, nextPage : HTMLElement) : void {
    nextPage.classList.remove("disabled");

    // Set type of created user to the value of the selected button
    if (first.innerText == "PARTICULIER")
      this.usr.setStatu("AnnonceurPart");
    else
      this.usr.setStatu("AnnonceurPro");

    first.style.backgroundColor = "#9BE7FF";
    second.style.backgroundColor = "#F1EEEE";
  }

  // -*-*-*-*-*-*-*-*-*-* PAGE 1 -*-*-*-*-*-*-*-*-*-*

  public saveForm(nom : string, prenom : string, email : string, tel : string,
                  pseudo : string, mdp : string, nomEntreprise : string) : void {
                    
      this.usr.setNom(nom) ;
      this.usr.setPrenom(prenom) ;
      this.usr.setEmail(email) ;
      this.usr.setNumero(tel) ;
      this.usr.setPseudo(pseudo) ;
      this.usr.setMot_de_passe(mdp) ;
      this.usr.setNom_Societe(nomEntreprise);

      this.usr.tostring();
    }


  //Fonction pour l'inscription
  public signIn() : void {
    if (this.userauthentification.setUserLogIn(this.usr) != null){
      this.router.navigate(['/connexion']);
    } else {
      alert("Un problème est survenue");
    }

  }
}
