import { Component, OnInit } from '@angular/core';
import { AnnonceService } from '../annonce.service' ;
import { Router, ActivatedRoute } from '@angular/router';
import { User } from '../_Models/dbAcces/User';
import { UserauthentificationService } from '../userauthentification.service';
import { Annonce } from '../_Models/dbAcces/Annonce';


@Component({
  selector: 'app-detailannonce',
  templateUrl: './detailannonce.component.html',
  styleUrls: ['./detailannonce.component.css']
})
export class DetailannonceComponent implements OnInit {

  constructor(private route: ActivatedRoute,
              private annonce : AnnonceService,
              private router : Router, 
              private userauthentification: UserauthentificationService) {}


  private idAnnonce : number = -1;
  public detailedAnnonce : Annonce = new Annonce();
  public user : any;

  ngOnInit(): void {
    console.log(this.route.snapshot.params['id']) ;
    this.idAnnonce = this.route.snapshot.params['id'];

    if (this.idAnnonce != undefined) {
      this.initAnnonce();
    }else {
      this.router.navigate(['/home']);
    }
  }

  initAnnonce() : void {
    this.annonce.getAnnonceById(this.idAnnonce).subscribe(doc => {
      console.log(doc);
      this.initUser(doc.id_annonceur!);
      this.detailedAnnonce = doc; 
    });
  }

  initUser(id : number) : void {
    this.userauthentification.getUserById(id).subscribe(user => {
      console.log(user);
      this.user = user;
    });
  }
}
