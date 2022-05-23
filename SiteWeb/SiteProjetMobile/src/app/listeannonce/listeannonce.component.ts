import { Component, OnInit } from '@angular/core';
import { AnnonceService } from '../annonce.service' ;

@Component({
  selector: 'app-listeannonce',
  templateUrl: './listeannonce.component.html',
  styleUrls: ['./listeannonce.component.css']
})
export class ListeannonceComponent implements OnInit {

  constructor(private annonce : AnnonceService) { }

  ngOnInit(): void {
    this.annonce.getAllAnnonces() ;
  }



}
