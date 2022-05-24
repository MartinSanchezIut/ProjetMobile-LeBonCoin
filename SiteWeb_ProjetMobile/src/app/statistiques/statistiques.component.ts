import { Component, OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-statistiques',
  templateUrl: './statistiques.component.html',
  styleUrls: ['./statistiques.component.css']
})
export class StatistiquesComponent implements OnInit {

  private todaysDate = this.datepipe.transform((new Date), 'dd/MM/yyyy');

  constructor(public datepipe: DatePipe){  }
  ngOnInit(): void {

  }


  public hideMeToShow(hide : HTMLElement, show : HTMLElement) : void {
    hide.classList.add("hide");
    show.classList.remove("hide");
  }
  public setNombreVuesTotal(nbVues : number) : void {
    let elmt : HTMLElement | null = document.getElementById("nbVues") ;
    if (elmt) {
      elmt.innerText = nbVues + " vues." ;
    }
  }
  public setBarText(bar : string, date : string, nbVues : number) : void {
    let elmt : HTMLElement | null = document.getElementById(bar) ;
    if (elmt) {
      elmt.innerHTML = '<p style="padding: 13px; margin: 0px; position: absolute;"> '+date+' : '+nbVues+ ' vues. </p>';
      //nbVues + "vues." ;
    }    
  }
  public setBarWidth(bar : string, percent : number) : void {
    let elmt : HTMLElement | null = document.getElementById(bar) ;
    if (elmt) {
      elmt.style.width = percent+"%";
    } 
  }


  public test() {
    for(let i=1; i<8; i++) {
      if (this.todaysDate) {

        this.setNombreVuesTotal(Math.floor((Math.random() * 10000) + 1)) ;
        this.setBarText("day"+i, this.todaysDate, Math.floor((Math.random() * 100) + 1));
        this.setBarWidth("day"+i, Math.floor((Math.random() * 100) + 1));
      }
    }
  }
}
