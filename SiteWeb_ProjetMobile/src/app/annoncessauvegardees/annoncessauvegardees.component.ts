import { Component, OnInit } from '@angular/core';
import { AnnonceService } from '../annonce.service' ;
import { UserauthentificationService } from '../userauthentification.service' ;
import { Router } from '@angular/router';


@Component({
  selector: 'app-annoncessauvegardees',
  templateUrl: './annoncessauvegardees.component.html',
  styleUrls: ['./annoncessauvegardees.component.css']
})
export class AnnoncessauvegardeesComponent implements OnInit {

  constructor(private annonce : AnnonceService,
    private user : UserauthentificationService,
    private router : Router) { }

    public listeAnnonces : any[] = [];
    ngOnInit(): void {
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
