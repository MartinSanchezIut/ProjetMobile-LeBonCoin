import { Component, OnInit } from '@angular/core';
import { AnnonceService } from '../annonce.service' ;

@Component({
  selector: 'app-listeannonce',
  templateUrl: './listeannonce.component.html',
  styleUrls: ['./listeannonce.component.css']
})
export class ListeannonceComponent implements OnInit {

  constructor(private annonce : AnnonceService) { }

  public listeAnnonces : any[] = [];
  ngOnInit(): void {
     
    this.annonce.getAllAnnonces().subscribe(doc => {
      console.log(doc) ;
      this.listeAnnonces = doc; 
    });
  }



}
