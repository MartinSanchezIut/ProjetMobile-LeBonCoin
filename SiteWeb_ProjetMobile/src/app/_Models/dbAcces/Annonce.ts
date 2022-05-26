export class Annonce {

    //ATTRIBUTS
    public id_annonce : number | null = null;
    public titre : string | null = "";
    public description : string | null = "";
    public prix : string | null = "";
    public date_publication : string | null = "";
    public id_annonceur : number | null = null;
    public departement : string | null = "";
    public ville : string | null = "";
    public adresse_image : number | null = null;
    public image : any[] | null = [];
    public nbvues : number | null = null;
    public contact : number | null = null;
    public categories : string | null = "";
    public filtre : string | null = "";
    public list_messages : any[] | null = [];
}
