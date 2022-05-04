import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.css']
})
export class MessagesComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  public hideMeToShow(hide : HTMLElement, show : HTMLElement) : void {
    hide.classList.add("hide");
    show.classList.remove("hide");
  }
}
