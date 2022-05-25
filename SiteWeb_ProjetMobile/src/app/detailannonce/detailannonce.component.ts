import { Component, OnInit } from '@angular/core';
import { AnnonceService } from '../annonce.service' ;
import { Router, ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-detailannonce',
  templateUrl: './detailannonce.component.html',
  styleUrls: ['./detailannonce.component.css']
})
export class DetailannonceComponent implements OnInit {

  constructor(private route: ActivatedRoute, 
              private annonce : AnnonceService,
              private router : Router) { }


  private idAnnonce : number = -1;
  public detailedAnnonce : any;

  ngOnInit(): void {
    console.log(this.route.snapshot.params['id']) ;
    this.idAnnonce = this.route.snapshot.params['id'];      
    
    if (this.idAnnonce != undefined) {
      this.annonce.getListAnnonce("/AnnonceById/"+this.idAnnonce).subscribe(doc => {
        console.log(doc) ;
        this.detailedAnnonce = doc; 
      });
    }else {
      this.router.navigate(['/home']);
    }
  }
}
