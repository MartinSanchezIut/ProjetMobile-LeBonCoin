export class FormUser {

    private type : String | null = null;

    private id : Number | null = null;
    private pseudo : String | null = null;
    private nom : String | null = null;
    private prenom : String | null = null;
    private email : String | null = null;
    private numero : String | null = null;
    private mot_de_passe : String | null = null;
    //private statu : String | null = null;
    
    
    private nomEntreprise : String | null = null;


    constructor() {
/*
                pseudo : String, nom : String, prenom : String,
                email : String, numero : String, mot_de_passe : String,
                statu : String

        this.pseudo = pseudo;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.numero = numero;
        this.mot_de_passe = mot_de_passe;
        this.statu = statu;
*/
    }

    public toString() : String {
        return " Type: " + this.type + 
               " Id: " + this.id + 
               " Pseudo: " + this.pseudo + 
               " Nom: " + this.nom + 
               " Prenom: " + this.prenom + 
               " Email: " + this.email + 
               " Numero: " + this.numero + 
               " Mdp: " + this.mot_de_passe + 
               //"Statu: " + this.statu + 
               " NomEntreprise: " + this.nomEntreprise;
    }



    public getNomEntreprise(): String | null {
        return this.nomEntreprise;
    }
    public setNomEntreprise(nomEntreprise: String): FormUser {
        this.nomEntreprise = nomEntreprise;
        return this;
    }
    public getType(): String | null {
        return this.type;
    }
    public setType(type: String): FormUser {
        this.type = type;
        return this;
    }
    public getId(): Number | null {
        return this.id;
    }
    public setId(id: Number): FormUser {
        this.id = id;
        return this;
    }
    public getPseudo(): String | null {
        return this.pseudo;
    }
    public setPseudo(pseudo: String): FormUser {
        this.pseudo = pseudo;
        return this;
    }
    public getNom(): String | null {
        return this.nom;
    }
    public setNom(nom: String): FormUser {
        this.nom = nom;
        return this;
    }
    public getPrenom(): String | null {
        return this.prenom;
    }
    public setPrenom(prenom: String): FormUser {
        this.prenom = prenom;
        return this;
    }
    public getEmail(): String | null {
        return this.email;
    }
    public setEmail(email: String): FormUser {
        this.email = email;
        return this;
    }
    public getNumero(): String | null {
        return this.numero;
    }
    public setNumero(numero: String): FormUser {
        this.numero = numero;
        return this;
    }
    public getMot_de_passe(): String | null {
        return this.mot_de_passe;
    }
    public setMot_de_passe(mot_de_passe: String): FormUser {
        this.mot_de_passe = mot_de_passe;
        return this;
    }
}