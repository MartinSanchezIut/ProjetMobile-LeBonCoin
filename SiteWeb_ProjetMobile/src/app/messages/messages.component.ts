import { Component, OnInit } from '@angular/core';
import { AnnonceService } from '../annonce.service' ;
import { UserauthentificationService } from '../userauthentification.service' ;
import { Router } from '@angular/router';

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.css']
})
export class MessagesComponent implements OnInit {

  constructor(private annonce : AnnonceService,
    private user : UserauthentificationService,
    private router : Router) { }

  ngOnInit(): void {
    if (!this.user.isConnected()) {
      console.log("Pas d'utilisateur connecté. Renvoie vers la page de connexion.");
      this.router.navigate(['/connexion']);
    }
  }

  public hideMeToShow(hide : HTMLElement, show : HTMLElement) : void {
    hide.classList.add("hide");
    show.classList.remove("hide");
  }
}
