import { Component, OnInit } from '@angular/core';
import { ConnexionService } from '../connexion.service';
import { User } from '../user';

@Component({
  selector: 'app-connexion',
  templateUrl: './connexion.component.html',
  styleUrls: ['./connexion.component.css']
})
export class ConnexionComponent implements OnInit {

  public userconnected : User;

  constructor(private user : ConnexionService) { }

  ngOnInit(): void {
    console.log("Recherche des identifiants pour la connexion");
    this.user.getUserConnexion(email, password).subscribe(userconnected => {
      this.userconnected = userconnected});
  }

}
