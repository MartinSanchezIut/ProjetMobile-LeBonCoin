import { Component, OnInit } from '@angular/core';
import { UserauthentificationService } from '../userauthentification.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(public userauthentification: UserauthentificationService) { }

  ngOnInit(): void {
  }

}
