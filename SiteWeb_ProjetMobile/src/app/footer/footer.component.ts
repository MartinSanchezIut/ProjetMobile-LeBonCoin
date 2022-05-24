import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  public showContact() : void {
    alert("Contactez nous par mail : adresse.mail@leMauvaisCoin.fr");
  }


  public downloadApp() : void {
    alert("Téléchargez notre application android nule part ...");
  }
}
