
export var apiUrl : string = "http://192.168.0.13:8080/LeMauvaisCoin/api" ;


export class Departements {
    // Liste des départements francais : INCOMPLETTE
    public static list : string[] = [
    "Ain (01)","Aisne (02)","Allier (03)","Alpes-de-Haute-Provence (04)","Alpes-Maritimes (06)",
    "Ardèche (07)","Ardennes (08)","Ariège (09)","Aube (10)","Aude (11)","Aveyron (12)","Bas-Rhin (67)","Bouches-du-Rhône (13)",
    "Calvados (14)","Cantal (15)","Charente (16)","Charente-Maritime (17)","Cher (18)","Corrèze (19)","Corse-du-Sud (2A)",
    "Côte-d'Or (21)","Côtes-d'Armor (22)","Creuse (23)","Deux-Sèvres (79)","Dordogne (24)","Doubs (25)","Drôme (26)","Essonne (91)",
    "Eure (27)","Eure-et-Loir (28)","Finistère (29)","Gard (30)","Gers (32)","Gironde (33)","Guadeloupe (971)","Guyane (973)","Haut-Rhin (68)",
    "Haute-Corse (2B)","Haute-Garonne (31)","Haute-Loire (43)","Haute-Marne (52)","Haute-Saône (70)","Haute-Savoie (74)","Haute-Vienne (87)",
    "Hautes-Alpes (05)","Hautes-Pyrénées (65)","Hauts-de-Seine (92)","Hérault (34)","Ille-et-Vilaine (35)","Indre (36)"] ;
  
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