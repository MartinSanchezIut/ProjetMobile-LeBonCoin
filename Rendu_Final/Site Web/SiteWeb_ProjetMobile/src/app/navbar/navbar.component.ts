import { Component, OnInit } from '@angular/core';
import { Categories } from '../_Models/Utils';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  // Liste des catégories : INCOMPLETTE
  public categories : { nomCat : string, listeSousCat : string[]}[] = Categories.list ;


  constructor() { }

  ngOnInit(): void {
  }

}
