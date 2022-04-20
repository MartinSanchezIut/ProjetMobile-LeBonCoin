import { Component, OnInit } from '@angular/core';
import { UserauthentificationService } from '../userauthentification.service';
import { User } from '../Models/user';

@Component({
  selector: 'app-connexion',
  templateUrl: './connexion.component.html',
  styleUrls: ['./connexion.component.css']
})
export class ConnexionComponent implements OnInit {

  private email: string = "";
  private password: string = "";
  private userconnected: User | null | undefined;


  constructor(private connectingUser: UserauthentificationService) { }

  ngOnInit(): void {
    this.email = (<HTMLInputElement>document.getElementById('emailInput')!).value;
    this.password = (<HTMLInputElement>document.getElementById('passwordInput')!).value;
  }

  submit(): void {
    console.log("Recherche des identifiants pour la connexion");
    this.connectingUser.getUserConnexion(this.email, this.password).subscribe(userconnected => {
      this.userconnected = userconnected});
    if (this.userconnected != null){
      console.log("Vous êtes connectés");
    }
    else
      console.log("Connection échouée");
  }
}
