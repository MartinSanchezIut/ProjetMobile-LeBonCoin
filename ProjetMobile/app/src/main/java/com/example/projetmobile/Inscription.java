package com.example.projetmobile;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import com.example.projetmobile.Model.Annonceur_Particulier;
import com.example.projetmobile.Model.Annonceur_pro;
import com.google.gson.Gson;

import java.util.concurrent.ExecutionException;

public class Inscription extends AppCompatActivity {
    private EditText Eemail;
    private EditText Epseudo;
    private EditText Enom;
    private EditText Eprenom;
    private EditText Enumero;
    private EditText Epassword;
    private EditText Eentreprise;
    private Button valider;

    private EditText Eemail2;
    private EditText Epseudo2;
    private EditText Enom2;
    private EditText Eprenom2;
    private EditText Enumero2;
    private EditText Epassword2;
    Fragment fragmentInFrame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        fragmentInFrame = (Fragment)getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        Eemail = (EditText)findViewById(R.id.idemail);
        Epseudo = (EditText)findViewById(R.id.idpseudo);
        Enom = (EditText)findViewById(R.id.idnom);
        Eprenom = (EditText)findViewById(R.id.idprenom);
        Enumero = (EditText)findViewById(R.id.idnumero);
        Epassword = (EditText)findViewById(R.id.idpassword);
        Eentreprise = (EditText)findViewById(R.id.identreprise);

        Eemail2 = (EditText)findViewById(R.id.idemail2);
        Epseudo2 = (EditText)findViewById(R.id.idpseudo2);
        Enom2 = (EditText)findViewById(R.id.idnom2);
        Eprenom2 = (EditText)findViewById(R.id.idprenom2);
        Enumero2 = (EditText)findViewById(R.id.idnumero2);
        Epassword2 = (EditText)findViewById(R.id.idpassword2);
    }
    public void valider(View view) {
        if (fragmentInFrame instanceof FragmentInscriptionPro){
        String url = "http://192.168.1.25:8080/LeMauvaisCoin/api/User/InscriptionAnnonceurPro";
        MyAsyncInscription myAsyncTasks = new MyAsyncInscription();
        try {
            Gson gson = new Gson();
            System.out.println(Epseudo.getText().toString());
            System.out.println(Enom.getText().toString());
            System.out.println(Eemail.getText().toString());
            System.out.println(Enumero.getText().toString());
            System.out.println(Epassword.getText().toString());
            System.out.println(Eentreprise.getText().toString());
            Annonceur_pro pro = new Annonceur_pro("AnnonceurPro", Epseudo.getText().toString(), Enom.getText().toString(), Eprenom.getText().toString(), Eemail.getText().toString(), Enumero.getText().toString(), Epassword.getText().toString(), Eentreprise.getText().toString());
            String json = gson.toJson(pro);
            String reponse = myAsyncTasks.execute(url, json).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }else if(fragmentInFrame instanceof FragmentinscriptionPart){
            String url = "http://192.168.1.25:8080/LeMauvaisCoin/api/User/InscriptionAnnonceurPro";
            MyAsyncInscription myAsyncTasks = new MyAsyncInscription();
            try {
                Gson gson = new Gson();
                Annonceur_Particulier pro = new Annonceur_Particulier("AnnonceurPart", Epseudo2.getText().toString(), Enom2.getText().toString(), Eprenom2.getText().toString(), Eemail2.getText().toString(), Enumero2.getText().toString(), Epassword2.getText().toString());
                String json = gson.toJson(pro);
                String reponse = myAsyncTasks.execute(url, json).get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}