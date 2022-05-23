import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { apiUrl } from './_Models/Utils' ;

@Injectable({
  providedIn: 'root'
})
export class AnnonceService {

  constructor(private http: HttpClient) {}

  private urlBase: string = apiUrl + '/annonce';

  // Récupère toute les annonces
  getAllAnnonces(): any[]{
      console.log("Récupération de toutes les annonces.");
      
      this.http.get<any>(this.urlBase + "/getAll").subscribe(listeAnnonces => {
        console.log(listeAnnonces) 
      });
      
      return [] ;
    }
  

}
