export class Recherche {
    
    //ATTRIBUTS
    public categorie : string | null = null;
    public filtre : string | null = "";
    public Prix1 : number | null = null;
    public Prix2 : number | null = null;
    public departement : string[] | null = [];
    public ville : string | null = "";
    public particulier : boolean | null = true;
    public professionel : boolean | null = true;
}