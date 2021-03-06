import { Component } from '@angular/core';
import { UserauthentificationService } from '../userauthentification.service';
import { Router } from '@angular/router';
import { User } from '../_Models/dbAcces/User';

@Component({
  selector: 'app-connexion',
  templateUrl: './connexion.component.html',
  styleUrls: ['./connexion.component.css']
})
export class ConnexionComponent {

  public email: string = "";
  public password: string = "";
  private userconnected: User = new User();


  constructor(private connectingUser: UserauthentificationService, private router: Router) { }

  ngOnInit(): void {}

  //Permet de valider la connexion d'un utilisateur
  //Si les informations rentrées par l'utilisateur sont celles rendues par le serveur alors redirigé vers la page '/home'
  //Sinon un message d'alerte s'affiche disant que cet utilisateur n'existe pas
  submit(): void {
    this.email = (<HTMLInputElement>document.getElementById('Identifiant')!).value;
    this.password = (<HTMLInputElement>document.getElementById('password')!).value;
    console.log("Recherche des identifiants pour la connexion");
    this.connectingUser.connexion(this.email, this.password).subscribe(userconnected => {
      this.userconnected = userconnected; 
      localStorage.setItem("user", JSON.stringify(this.userconnected));
      this.router.navigate(['/home']); //Redirection  
    });
    this.userconnected.setEmail("*******");
  }
}
