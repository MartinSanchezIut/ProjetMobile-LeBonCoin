import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ChatComponent } from './chat/chat.component';
import { MesannoncesComponent } from './mesannonces/mesannonces.component';
import { AnnoncessauvegardeesComponent } from './annoncessauvegardees/annoncessauvegardees.component';
import { AjouterannonceComponent } from './ajouterannonce/ajouterannonce.component';
import { EditerannonceComponent } from './editerannonce/editerannonce.component';
import { HomepageComponent } from './homepage/homepage.component';
import { ListeannonceComponent } from './listeannonce/listeannonce.component';
import { DetailannonceComponent } from './detailannonce/detailannonce.component';
import { InscriptionComponent } from './inscription/inscription.component';
import { ConnexionComponent } from './connexion/connexion.component';
import { RechercheComponent } from './recherche/recherche.component';
import { ProfilComponent } from './profil/profil.component';
import { MessagesComponent } from './messages/messages.component';
import { NavbarComponent } from './navbar/navbar.component';
import { FooterComponent } from './footer/footer.component';
import { HeaderComponent } from './header/header.component';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    ChatComponent,
    MesannoncesComponent,
    AnnoncessauvegardeesComponent,
    AjouterannonceComponent,
    EditerannonceComponent,
    HomepageComponent,
    ListeannonceComponent,
    DetailannonceComponent,
    InscriptionComponent,
    ConnexionComponent,
    RechercheComponent,
    ProfilComponent,
    MessagesComponent,
    NavbarComponent,
    FooterComponent,
    HeaderComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
