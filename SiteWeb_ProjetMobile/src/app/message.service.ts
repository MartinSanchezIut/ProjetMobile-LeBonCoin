import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { apiUrl } from './_Models/Utils' ;

@Injectable({
  providedIn: 'root'
})
export class MessageService {
  private urlBase: string = apiUrl + '/message';

  constructor(private http: HttpClient) {}

  //Faut envoyer une ArrayList avec en premier : string message / en deuxième : string id du message
  putMessage(message : string[]): Observable<any> {
    console.log("Envoie message "+ message);
    return this.http.put<any>(this.urlBase + "/PutMessage", message);
  }

  conversationById(id : number): Observable<any> {
    console.log("Recherche de la conversion : "+ id);
    return this.http.post<any>(this.urlBase+"/ConversationById", id);
  }

  putConversation(conv : any) : Observable<any> {
    console.log("Envoie d'une conversation "+ conv);
    return this.http.put<any>(this.urlBase+"/PutConversation", conv);
  }

  messageByIdAnnonceur(id : number): Observable<any> {
    console.log("Recherche d'un message par l'id de l'annonceur : "+ id);
    return this.http.post<any>(this.urlBase+"/MessageByIdAnnonceur", id);
  }
}
