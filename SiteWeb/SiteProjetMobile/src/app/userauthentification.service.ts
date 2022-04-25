import { Injectable } from '@angular/core'
import { User } from './_Models/user';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})

export class UserauthentificationService {
  private urlBase: string = 'http://localhost:8080/LeMauvaisCoin/api/User/';

  constructor(private http: HttpClient) {}

  getUserConnexion(email: string, password: string): Observable<User>{
    console.log(this.urlBase+"Connexion/"+email+"/"+password);
    return this.http.get<User>(this.urlBase+'Connexion/'+ email +"/"+ password);
  }
}
