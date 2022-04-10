package com.example.projetmobile;

import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import com.example.projetmobile.BDD.Controllers.UserControlers;
import com.example.projetmobile.BDD.models.UserBDD;
import com.example.projetmobile.Model.Annonce;
import com.example.projetmobile.Model.Annonceur_Particulier;
import com.example.projetmobile.Model.Annonceur_pro;
import com.example.projetmobile.Model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Acceuille extends AppCompatActivity {
    private Bundle extras ;
    private ListView listView ;
    private TextView Tuser ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuil);
        extras = getIntent().getExtras();
        listView = (ListView) findViewById(R.id.listView);
        Tuser = (TextView) findViewById(R.id.user);
       User user =  extras.getParcelable("USER");
        System.out.println("utilisateur " + user.getEmail());
        MyAsyncConnexion myAsyncTasks = new MyAsyncConnexion();


        UserControlers planningModel = new ViewModelProvider(this).get(UserControlers.class);
        planningModel.init(getApplicationContext());
        List<UserBDD> d = planningModel.getPlanning();
        System.out.println(d.get(0).getPseudo());
        Tuser.setText(d.get(0).getPseudo());
        Gson gson = new Gson();
        String url = "http://192.168.1.25:8080/LeMauvaisCoin/api/annonce/Recent" ;
        String reponse = null;
        try {
            reponse = myAsyncTasks.execute(url).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ICI " + reponse);
        if(!reponse.equals("")) {
            List<Annonce> test = new ArrayList<Annonce>();
            List<Annonce> annonce = gson.fromJson(reponse,  new TypeToken<ArrayList<Annonce>>(){}.getType());
            listView.setAdapter(new AnnonceRecentAdaptateur(Acceuille.this,annonce));
        }


    }
}