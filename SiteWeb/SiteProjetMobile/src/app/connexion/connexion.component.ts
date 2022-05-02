import { Component } from '@angular/core';
import { UserauthentificationService } from '../userauthentification.service';
import { User } from '../_Models/user';

@Component({
  selector: 'app-connexion',
  templateUrl: './connexion.component.html',
  styleUrls: ['./connexion.component.css']
})
export class ConnexionComponent {

  public email: string = "";
  public password: string = "";
  public userconnected: User | null | undefined;


  constructor() { } //private connectingUser: UserauthentificationService

  ngOnInit(): void {
    // this.email = (<HTMLInputElement>document.getElementById('emailInput')!).value;
    // this.password = (<HTMLInputElement>document.getElementById('passwordInput')!).value;
  }
/*
  submit(): void {
    this.email = (<HTMLInputElement>document.getElementById('emailInput')!).value;
    this.password = (<HTMLInputElement>document.getElementById('passwordInput')!).value;
    console.log("Recherche des identifiants pour la connexion");
    this.connectingUser.getUserConnexion(this.email, this.password).subscribe(userconnected => {
      this.userconnected = userconnected}); 
  }
  */
}
