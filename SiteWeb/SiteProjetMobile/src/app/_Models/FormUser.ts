export class FormUser {

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
    public setId_User(id: number): FormUser {
        this.id_user = id;
        return this;
    }
    public getPseudo(): string | null {
        return this.pseudo;
    }
    public setPseudo(pseudo: string): FormUser {
        this.pseudo = pseudo;
        return this;
    }
    public getNom(): string | null {
        return this.nom;
    }
    public setNom(nom: string): FormUser {
        this.nom = nom;
        return this;
    }
    public getPrenom(): string | null {
        return this.prenom;
    }
    public setPrenom(prenom: string): FormUser {
        this.prenom = prenom;
        return this;
    }
    public getEmail(): string | null {
        return this.email;
    }
    public setEmail(email: string): FormUser {
        this.email = email;
        return this;
    }
    public getNumero(): string | null {
        return this.numero;
    }
    public setNumero(numero: string): FormUser {
        this.numero = numero;
        return this;
    }
    public getMot_de_passe(): string | null {
        return this.mot_de_passe;
    }
    public setMot_de_passe(mot_de_passe: string): FormUser {
        this.mot_de_passe = mot_de_passe;
        return this;
    }
    public getStatu(): string | null {
        return this.statu;
    }
    public setStatu(statu: string): FormUser {
        this.statu = statu;
        return this;
    }
    public getNom_Societe(): string | null {
        return this.nom_societe;
    }
    public setNom_Societe(nomEntreprise: string): FormUser {
        this.nom_societe = nomEntreprise;
        return this;
    }
    public getImage(): string | null {
        return this.image;
    }
    public setImage(image: string): FormUser {
        this.image = image;
        return this;
    }
}