import { Injectable } from '@angular/core'
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from './Models/user';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class UserauthentificationService {
  private urlBase: string = 'http://localhost:8888/';

  constructor(private http: HttpClient) {}

  getUserConnexion(email: String, password: String): Observable<User>{
    return this.http.get<User>(this.urlBase+'Connexion/'+ email + password);
  }
}