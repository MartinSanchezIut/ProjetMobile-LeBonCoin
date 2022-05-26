
export var apiUrl : string = "http://localhost:8080/LeMauvaisCoin/api" ;


export class Departements {
    // Liste des départements francais : INCOMPLETTE
    public static list : string[] = [
    "Ain","Aisne","Allier","Alpes-de-Haute-Provence","Alpes-Maritimes",
    "Ardèche","Ardennes","Ariège","Aube","Aude","Aveyron","Bas-Rhin","Bouches-du-Rhône",
    "Calvados","Cantal","Charente","Charente-Maritime","Cher","Corrèze","Corse-du-Sud",
    "Côte-d'Or","Côtes-d'Armor","Creuse","Deux-Sèvres","Dordogne","Doubs","Drôme","Essonne",
    "Eure","Eure-et-Loir","Finistère","Gard","Gers","Gironde","Guadeloupe","Guyane","Haut-Rhin",
    "Haute-Corse","Haute-Garonne","Haute-Loire","Haute-Marne","Haute-Saône","Haute-Savoie","Haute-Vienne",
    "Hautes-Alpes","Hautes-Pyrénées","Hauts-de-Seine","Hérault","Ille-et-Vilaine","Indre"] ;
  
}

export class Categories {
    // Liste des catégories : INCOMPLETTE
    public static list : { nomCat : string, listeSousCat : string[]}[] = [
        {nomCat: "Matériel Pro.", listeSousCat:["..."]},
        {nomCat:"Mode", listeSousCat:["Pantalon", "T-Shirt", "Robes", "Chapeau", "Sous-vetements", "Chaussures"]}, 
        {nomCat: "Maison", listeSousCat:["..."]},
        {nomCat: "Multimédia", listeSousCat:["..."]},
        {nomCat: "Loisir", listeSousCat:["..."]},
        {nomCat: "Divers", listeSousCat:["..."]},
    ]; 

    public static getSousCategorieOf(categorie : string) : string[] {
        for (let cat of Categories.list) {
            if (cat.nomCat == categorie) {
                return cat.listeSousCat;
            }
        }
        return [] ;
    }
}