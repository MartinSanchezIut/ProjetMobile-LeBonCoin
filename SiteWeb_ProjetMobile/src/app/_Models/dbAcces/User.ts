export class User {

    //ATTRIBUTS
    private id_user : number | null = null;
    private pseudo : string | null = null;
    private image : string | null = null;
    private nom : string | null = null;
    private prenom : string | null = null;
    private email : string | null = null;
    private numero : string | null = null;
    private mot_de_passe : string | null = null;
    private statu : string | null = null;
    private nom_societe : string | null = null;


    //CONSTRUCTOR
    constructor() {}

    //METHODS
    public tostring() : void {
        console.log( " Id: " + this.id_user + 
               " Pseudo: " + this.pseudo + 
               " Nom: " + this.nom + 
               " Prenom: " + this.prenom + 
               " Email: " + this.email + 
               " Numero: " + this.numero + 
               " Mdp: " + this.mot_de_passe + 
               " Statu: " + this.statu + 
               " NomEntreprise: " + this.nom_societe
        );
    }

    //GETTERS AND SETTERS
    public getId_User(): number | null {
        return this.id_user;
    }
    public setId_User(id: number): User {
        this.id_user = id;
        return this;
    }
    public getPseudo(): string | null {
        return this.pseudo;
    }
    public setPseudo(pseudo: string): User {
        this.pseudo = pseudo;
        return this;
    }
    public getNom(): string | null {
        return this.nom;
    }
    public setNom(nom: string): User {
        this.nom = nom;
        return this;
    }
    public getPrenom(): string | null {
        return this.prenom;
    }
    public setPrenom(prenom: string): User {
        this.prenom = prenom;
        return this;
    }
    public getEmail(): string | null {
        return this.email;
    }
    public setEmail(email: string): User {
        this.email = email;
        return this;
    }
    public getNumero(): string | null {
        return this.numero;
    }
    public setNumero(numero: string): User {
        this.numero = numero;
        return this;
    }
    public getMot_de_passe(): string | null {
        return this.mot_de_passe;
    }
    public setMot_de_passe(mot_de_passe: string): User {
        this.mot_de_passe = mot_de_passe;
        return this;
    }
    public getStatu(): string | null {
        return this.statu;
    }
    public setStatu(statu: string): User {
        this.statu = statu;
        return this;
    }
    public getNom_Societe(): string | null {
        return this.nom_societe;
    }
    public setNom_Societe(nomEntreprise: string): User {
        this.nom_societe = nomEntreprise;
        return this;
    }
    public getImage(): string | null {
        return this.image;
    }
    public setImage(image: string): User {
        this.image = image;
        return this;
    }
}