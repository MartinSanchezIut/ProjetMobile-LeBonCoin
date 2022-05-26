import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ConnexionComponent } from './connexion/connexion.component';
import { HomepageComponent } from './homepage/homepage.component';
import { InscriptionComponent } from './inscription/inscription.component';
import { RechercheComponent } from './recherche/recherche.component';
import { AjouterannonceComponent } from './ajouterannonce/ajouterannonce.component';
import { ProfilComponent } from './profil/profil.component';
import { MessagesComponent } from './messages/messages.component';
import { Error404Component } from './error404/error404.component' ;

import { NavbarComponent } from './navbar/navbar.component';
import { MesannoncesComponent } from './mesannonces/mesannonces.component';
import { ListeannonceComponent } from './listeannonce/listeannonce.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { EditerannonceComponent } from './editerannonce/editerannonce.component';
import { DetailannonceComponent } from './detailannonce/detailannonce.component';
import { AnnoncessauvegardeesComponent } from './annoncessauvegardees/annoncessauvegardees.component';
import { StatistiquesComponent } from './statistiques/statistiques.component' ;

const routes: Routes = [
  { path: '', component: HomepageComponent },
  { path: 'home', component: HomepageComponent },
  { path: 'recherche', component: RechercheComponent },
  { path: 'ajouterAnnonce', component: AjouterannonceComponent },
  { path: 'profil', component: ProfilComponent },
  { path: 'messages', component: MessagesComponent },
  { path: 'inscription', component: InscriptionComponent },
  { path: 'connexion', component: ConnexionComponent },
  { path: 'statistiques', component: StatistiquesComponent },
  { path: 'listeannonce', component: ListeannonceComponent },
  { path: 'listeannonce/:type', component: ListeannonceComponent },
  { path: 'detailannonce/:id', component: DetailannonceComponent },
  { path: 'detailannonce', component: DetailannonceComponent },
  
  { path: 'annoncesauvegardes', component: AnnoncessauvegardeesComponent },
  { path: 'mesannonces', component: MesannoncesComponent },
  { path: 'editerannonce/:id', component: EditerannonceComponent },

  
  { path: 'test/navbar', component: NavbarComponent },
  { path: 'test/header', component: HeaderComponent },
  { path: 'test/footer', component: FooterComponent },

  
  { path: '**', component: Error404Component },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
