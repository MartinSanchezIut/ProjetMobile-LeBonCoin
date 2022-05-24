import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { apiUrl } from './_Models/Utils' ;

@Injectable({
  providedIn: 'root'
})
export class AnnonceService {
/*
	Liste des methodes du web service: 
		"/AnnonceById/{id}"  		 OK
		"/getAll"			 		 OK
		"/Getsauvegarde/{id_user}"   OK
		"/GetMesAnnonces/{id_user}"  OK
		"/Recent"					 OK

		"/Favoris"									OK
		"/deleteannonce/{id_user}/{id_annonce}"	    OK

		"/PutAnnonce" 		OK
		"/PostAnnonce		OK

		"/vu/{id_annonce}"  OK


		"/RechercheTitre"   OK
		"/Recherche"		OK

		"/Getsauvegardeid/{id_user}"   
		"/NbVuAnnonce/{id_annonce}"    OK
		"/fraude"					   OK


*/

  constructor(private http: HttpClient) {}

  private urlBase: string = apiUrl + '/annonce';

  // Récupère une liste d'annonces ("/Recent", 
  // 								"/GetMesAnnonces/{id_user}"
  //								"/Getsauvegarde/{id_user}"
  //								"/getAll")
  getListAnnonce(url : string) : Observable<any> {
      console.log("Récupération d'annonces ("+ url +").");
      return this.http.get<any>(this.urlBase + url);
    }
  // Récupère une annonce par son id
  getAnnonceById(id : number) : Observable<any> {
    console.log("Récupération de l'annonces "+ id +".");
    return this.http.get<any>(this.urlBase + "/AnnonceById/"+id);
  }
  // Récupère une annonce par son id
  getNombreVueAnnonceById(id : number) : Observable<any> {
    console.log("Récupération du nombre de vue de l'annonce "+ id +".");
    return this.http.get<any>(this.urlBase + "/NbVuAnnonce/"+id);
  }

   // Ajouter une annonce au favoris d'un utilisateur 
   ajouterAnnonceAuFavoris(userANDannoncelist : any) : Observable<any> {
	console.log("Favori add user:"+ userANDannoncelist[0] + " annonce: " + userANDannoncelist[1]);
	return this.http.post<any>(this.urlBase + "/Favoris", userANDannoncelist);
   }
   // Supprimer une annonce au favoris d'un utilisateur 
   supprimerAnnonceAuFavoris(id_user : number, id_annonce : number) : Observable<any> {
	console.log("Favori rm user:"+ id_user + " annonce: " + id_annonce);
	return this.http.delete<any>(this.urlBase + "/deleteannonce/"+id_user+"/"+id_annonce)
   }


  // Ajouter une vue a l'annonce :id
  ajouterVueAnnonce(id : number) : Observable<any> {
      console.log("Ajout d'une vue à l'annonce "+ id +".");
      return this.http.get<any>(this.urlBase + "/vu/"+id);
   }

   // Ajouter une annonce
   ajouterAnnonce(annonce : any) : Observable<any> {
	console.log("Ajout annonce "+ annonce );
    return this.http.put<any>(this.urlBase + "/PutAnnonce", annonce);
   }
	// Editer une annonce
	editerAnnonce(annonce : any) : Observable<any> {
		console.log("editer annonce "+ annonce );
		return this.http.put<any>(this.urlBase + "/PostAnnonce", annonce);
	}



	// Retourne une liste d'annonces qui on le titre :titre
	rechercherParTitre(titre : string) : Observable<any>{
		console.log("Recherche annonce :"+ titre );
		return this.http.post<any>(this.urlBase + "/RechercheTitre", titre);
	}
	// Retourne une liste d'annonces qui collent avec recherche
	rechercher(recherche : any) : Observable<any>{
		console.log("Recherche annonce :"+ recherche );
		return this.http.post<any>(this.urlBase + "/RechercheTitre", recherche);
	}


	// Signale une fraude
	fraude(fraude : any) : Observable<any>{
		console.log("Signaler fraude :"+ fraude );
		return this.http.post<any>(this.urlBase + "/RechercheTitre", fraude);
	}


  // Récupère la liste d'identifiant d'annonce sauvegarder par :id
  getIdOfSavedAnnonces(id : number) : Observable<any> {
    console.log("Annonces sauvegardés de "+ id +".");
    return this.http.get<any>(this.urlBase + "/Getsauvegardeid/"+id);
  }
}
