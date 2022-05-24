import { Injectable } from '@angular/core'
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from './_Models/dbAcces/User';
import { apiUrl } from './_Models/Utils' ;

@Injectable({
  providedIn: 'root'
})

export class UserauthentificationService {
  private urlBase: string = apiUrl + '/User';


  constructor(private http: HttpClient) {}


  //Récupérer l'utilisateur connecté avec l'email et le mot de passe
  connexion(email: string, password: string): Observable<User>{
    console.log(this.urlBase + "/Connexion/" +email+ "/" +password);
    return this.http.get<User>(this.urlBase +'/Connexion/'+ email + "/" + password);
  }

  //Inscrire un utilisateur
  setUserLogIn(user : User) : Observable<User>{
    let urlAnnonceur = "";
    const httpOptions = { headers: new HttpHeaders({ 'Content-Type':  'application/json' }) };

    if (user.getStatu() == "AnnonceurPart")
      urlAnnonceur = "/InscriptionAnnonceurPart";
    else if (user.getStatu() == "AnnonceurPro")
      urlAnnonceur = "/InscriptionAnnonceurPro";
    else //Sinon problème lors de la création
      alert("Un problème est survenue, veuillez réessayer");
    
    console.log(this.urlBase+urlAnnonceur);

    return this.http.put<User>(this.urlBase+urlAnnonceur, JSON.stringify(user), httpOptions);
  }

  getUserById(id : string) : Observable<User> {
    console.log(this.urlBase + "/UserById/" + id);
    return this.http.get<User>(this.urlBase +'/UserById/'+id);
  }

  getConnectedUser() : string | null{
    return localStorage.getItem("user");
  }

  isConnected(): boolean {
    return (localStorage.getItem("user") != null);
  }
}
