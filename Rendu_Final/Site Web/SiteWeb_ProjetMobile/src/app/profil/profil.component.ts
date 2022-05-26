import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AnnonceService } from '../annonce.service';
import { UserauthentificationService } from '../userauthentification.service';
import { User } from '../_Models/dbAcces/User';

@Component({
  selector: 'app-profil',
  templateUrl: './profil.component.html',
  styleUrls: ['./profil.component.css']
})
export class ProfilComponent implements OnInit {

  constructor(public userauthentification : UserauthentificationService,
              private route: Router,
              private annonce : AnnonceService) { }

  public user : any;

  ngOnInit(): void {

    this.user = JSON.parse(this.userauthentification.getConnectedUser()!);
  }

  deconnect() {
    this.userauthentification.removeUserConnected();
    this.route.navigate(['/home']);
  }

  getAnnonces() {
    let listAnnonces = [];
    this.annonce.getListAnnonce("/GetMesAnnonces/" + this.user.id_user).subscribe(list => {
      listAnnonces = list;
      localStorage.setItem("listAnnonces", JSON.stringify(listAnnonces));
      this.route.navigate(['/listeannonce']);
    })
  }
}


