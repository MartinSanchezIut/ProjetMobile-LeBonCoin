import { Component, OnInit, Input } from '@angular/core';
import { AnnonceService } from '../annonce.service' ;
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-listeannonce',
  templateUrl: './listeannonce.component.html',
  styleUrls: ['./listeannonce.component.css']
})
export class ListeannonceComponent implements OnInit {

  constructor(private route: ActivatedRoute, 
              private annonce : AnnonceService,
              private router : Router) { }


  public listeAnnonces : any[] = [];

  @Input()
  public type : string = "/getAll";

  ngOnInit(): void {
    if (this.route.snapshot.params['type'] != undefined) {
      this.type = this.route.snapshot.params['type'];    
    }
    this.type = this.type.replace('_', '/' ) ;      


    //if (this.route.snapshot.params['listeAnnonce'] != undefined) {
    if (localStorage.getItem("listAnnonces") != null) {
      this.listeAnnonces = JSON.parse(localStorage.getItem("listAnnonces")!);
      localStorage.removeItem("listAnnonces");  
      console.log(this.listeAnnonces);
    }else {
      console.log(this.type) ;
      this.annonce.getListAnnonce(this.type).subscribe(doc => {
        console.log(doc) ;
        this.listeAnnonces = doc; 
        if (this.listeAnnonces.length == 0) {
          let error = document.getElementById("error")  ;
          if (error){
            error.classList.remove("hide");
          }
        }
      });
    }
  }
}
