package com.example.projetmobile;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import com.example.projetmobile.BDD.models.Controllers.UserControlers;
import com.example.projetmobile.BDD.models.UserBDD;
import com.example.projetmobile.Model.Annonceur_Particulier;
import com.example.projetmobile.Model.Annonceur_pro;
import com.example.projetmobile.Model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class Connexion extends AppCompatActivity {
    private TextInputLayout email;

    private TextInputLayout password;
    private TextView erreur;
    private Button connexion;
    private User user;
    BottomNavigationView bottomNavigationView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_connexion);
        email = (TextInputLayout) findViewById(R.id.idemail);
        password = (TextInputLayout) findViewById(R.id.idpassword);
        this.erreur = (TextView) findViewById(R.id.iderreur);
        this.erreur.setVisibility(View.INVISIBLE);
        this.connexion = (Button) findViewById(R.id.idbutttonconnexion);

        bottomNavigationView =  findViewById (R.id.bottomNavigationView);




    }

    public void Connexion(View view) throws ExecutionException, InterruptedException, IOException {
        final int REQUEST_CODE = 20;

        Gson gson = new Gson();
        System.out.println(this.email.getEditText().getText().toString());
        String url = "http://172.16.5.209:8080/LeMauvaisCoin/api/User/Connexion/" + this.email.getEditText().getText().toString() + "/" + this.password.getEditText().getText().toString();
        String reponse = getRequest(url);
        System.out.println("ICI " + reponse);
        if(!reponse.equals("")) {
            User user = gson.fromJson(reponse, User.class);
            Intent intention = new Intent(Connexion.this, Acceuille.class);
            if (user.getStatu().equals("AnnonceurPro")) {
                Annonceur_pro annonceur = gson.fromJson(reponse, Annonceur_pro.class);
                intention.putExtra("USER", annonceur);
                UserControlers planningModel = new ViewModelProvider(this).get(UserControlers.class);
                //(long id_user, String pseudo, String nom, String prenom, String email, String numero, String mot_de_passe, String statu, String entreprise)
                UserBDD t = new UserBDD(annonceur.getId_user(),annonceur.getPseudo(),annonceur.getImage(),annonceur.getNom(),annonceur.getPrenom(),annonceur.getEmail(),annonceur.getNumero(),annonceur.getMot_de_passe(),annonceur.getStatu(),annonceur.getNom_societe());
                planningModel.init(getApplicationContext());
                planningModel.insert(t);
            } else if (user.getStatu().equals("AnnonceurPart")) {
                Annonceur_Particulier annonceur = gson.fromJson(reponse, Annonceur_Particulier.class);
                UserControlers planningModel = new ViewModelProvider(this).get(UserControlers.class);
                intention.putExtra("USER", annonceur);
                UserBDD t = new UserBDD(annonceur.getId_user(),annonceur.getPseudo(),annonceur.getImage(),annonceur.getNom(),annonceur.getPrenom(),annonceur.getEmail(),annonceur.getNumero(),annonceur.getMot_de_passe(),annonceur.getStatu(),null);
                planningModel.init(getApplicationContext());
                planningModel.insert(t);

            }
            startActivityForResult(intention, REQUEST_CODE);
        }else{
            this.erreur.setVisibility(View.VISIBLE);

        }


    }

    public String getRequest(String url) throws IOException {
        final String[] result = {""};
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try  {
                    result[0]= IOUtils.toString(new InputStreamReader(new BufferedInputStream(new URL(url).openConnection().getInputStream()), Charsets.UTF_8));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return result[0];
    }


}
