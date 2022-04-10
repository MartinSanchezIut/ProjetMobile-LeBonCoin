package com.example.projetmobile;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import com.example.projetmobile.BDD.Controllers.UserControlers;
import com.example.projetmobile.BDD.models.UserBDD;
import com.example.projetmobile.Model.Annonceur_Particulier;
import com.example.projetmobile.Model.Annonceur_pro;
import com.example.projetmobile.Model.User;
import com.google.gson.Gson;

import java.util.concurrent.ExecutionException;

public class Connexion extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private TextView erreur;
    private Button connexion;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        this.email = (EditText) findViewById(R.id.idemail);
        this.password = (EditText) findViewById(R.id.idpassword);
        this.erreur = (TextView) findViewById(R.id.iderreur);
        this.erreur.setVisibility(View.INVISIBLE);
        this.connexion = (Button) findViewById(R.id.idbutttonconnexion);




    }

    public void Connexion(View view) throws ExecutionException, InterruptedException {
        final int REQUEST_CODE = 20;
        MyAsyncConnexion myAsyncTasks = new MyAsyncConnexion();

        Gson gson = new Gson();
        String url = "http://192.168.1.25:8080/LeMauvaisCoin/api/User/Connexion/" + this.email.getText() + "/" + this.password.getText() ;
        String reponse = myAsyncTasks.execute(url).get();
        System.out.println("ICI " + reponse);
        if(!reponse.equals("")) {
            User user = gson.fromJson(reponse, User.class);
            Intent intention = new Intent(Connexion.this, Acceuille.class);
            if (user.getStatu().equals("AnnonceurPro")) {
                Annonceur_pro annonceur = gson.fromJson(reponse, Annonceur_pro.class);
                intention.putExtra("USER", annonceur);
                UserControlers planningModel = new ViewModelProvider(this).get(UserControlers.class);
                //(long id_user, String pseudo, String nom, String prenom, String email, String numero, String mot_de_passe, String statu, String entreprise)
                UserBDD t = new UserBDD(annonceur.getId_user(),annonceur.getPseudo(),annonceur.getNom(),annonceur.getPrenom(),annonceur.getEmail(),annonceur.getNumero(),annonceur.getMot_de_passe(),annonceur.getStatu(),annonceur.getNom_societe());
                planningModel.init(getApplicationContext());
                planningModel.insert(t);
            } else if (user.getStatu().equals("AnnonceurPart")) {
                Annonceur_Particulier annonceur = gson.fromJson(reponse, Annonceur_Particulier.class);
                UserControlers planningModel = new ViewModelProvider(this).get(UserControlers.class);
                intention.putExtra("USER", annonceur);
                UserBDD t = new UserBDD(annonceur.getId_user(),annonceur.getPseudo(),annonceur.getNom(),annonceur.getPrenom(),annonceur.getEmail(),annonceur.getNumero(),annonceur.getMot_de_passe(),annonceur.getStatu(),null);
                planningModel.init(getApplicationContext());
                planningModel.insert(t);

            }
            startActivityForResult(intention, REQUEST_CODE);
        }else{
            this.erreur.setVisibility(View.VISIBLE);

        }


    }

    public void Inscription(View view) {
        Intent intention= new Intent(Connexion.this, Inscription.class);
        startActivity(intention);
    }
}
