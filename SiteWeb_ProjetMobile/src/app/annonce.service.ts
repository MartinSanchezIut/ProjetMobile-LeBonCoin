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

  // Récupère une liste d'annonces
  getListAnnonce(url : string) : Observable<any> {
      console.log("Récupération d'annonces ("+ url +").");

      return this.http.get<any>(this.urlBase + url);
    }

  // Récupère une annonce par son id
  getAnnonceById(id : number) : Observable<any> {
    console.log("Récupération de l'annonces "+ id +".");

    return this.http.get<any>(this.urlBase + "/AnnonceById/"+id);
  }
  

}
