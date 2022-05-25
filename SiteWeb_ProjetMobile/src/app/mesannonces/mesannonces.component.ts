import { Component, OnInit } from '@angular/core';
import { AnnonceService } from '../annonce.service' ;
import { UserauthentificationService } from '../userauthentification.service' ;
import { Router } from '@angular/router';

@Component({
  selector: 'app-mesannonces',
  templateUrl: './mesannonces.component.html',
  styleUrls: ['./mesannonces.component.css']
})
export class MesannoncesComponent implements OnInit {

  constructor(private annonce : AnnonceService,
              private user : UserauthentificationService,
              private router : Router) { }

  public listeAnnonces : any[] = [];
  ngOnInit(): void {
    if (!this.user.isConnected()) {
      console.log("Pas d'utilisateur connecté. Renvoie vers la page de connexion.");
      this.router.navigate(['/connexion']);
    }

     if (this.user.isConnected()) {
        let loggegUserString = this.user.getConnectedUser() ;
        if (loggegUserString != null ) {
          let loggedUser = JSON.parse(loggegUserString) ;
          this.annonce.getListAnnonce("/GetMesAnnonces/"+loggedUser.id_user).subscribe(doc => {
            console.log(doc) ;
            this.listeAnnonces = doc; 
          });      
        }else {
          this.router.navigate(['/home']);
        }
      }else {
        this.router.navigate(['/home']);
      }
    }
}
