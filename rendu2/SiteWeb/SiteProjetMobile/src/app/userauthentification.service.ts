import { Injectable } from '@angular/core'
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { FormUser } from './_Models/FormUser';

@Injectable({
  providedIn: 'root'
})

export class UserauthentificationService {
  private urlBase: string = 'http://localhost:8080/LeMauvaisCoin/api/User/';

  constructor(private http: HttpClient) {}

  //Récupérer l'utilisateur connecté avec l'email et le mot de passe
  getUserConnexion(email: string, password: string): Observable<FormUser>{
    console.log(this.urlBase+"Connexion/"+email+"/"+password);
    return this.http.get<FormUser>(this.urlBase+'Connexion/'+ email +"/"+ password);
  }

  //Inscrire un utilisateur
  setUserLogIn(user : FormUser) : Observable<FormUser>{
    let urlAnnonceur = "";
    const httpOptions = { headers: new HttpHeaders({ 'Content-Type':  'application/json' }) };

    if (user.getStatu() == "AnnonceurPart")
      urlAnnonceur = "InscriptionAnnonceurPart";
    else if (user.getStatu() == "AnnonceurPro")
      urlAnnonceur = "InscriptionAnnonceurPro";
    else //Sinon problème lors de la création
      alert("Un problème est survenue, veuillez réessayer");
    
    console.log(this.urlBase+urlAnnonceur);

    return this.http.put<FormUser>(this.urlBase+urlAnnonceur, JSON.stringify(user), httpOptions);
  }
}
