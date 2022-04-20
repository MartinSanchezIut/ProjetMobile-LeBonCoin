import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-inscription',
  templateUrl: './inscription.component.html',
  styleUrls: ['./inscription.component.css']
})
export class InscriptionComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  public select(first : HTMLElement, second : HTMLElement) : void {
    first.style.backgroundColor = "#9BE7FF";
    second.style.backgroundColor = "#F1EEEE";
    //first.classList.remove("disabled") ;
    //second.classList.add("disabled") ;
  }
}
