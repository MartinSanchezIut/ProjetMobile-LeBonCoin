package com.example.projetmobile;

import android.content.Intent;
import android.view.View;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.projetmobile.BDD.Controllers.UserControlers;
import com.example.projetmobile.BDD.models.UserBDD;
import com.example.projetmobile.Model.Annonce;
import com.example.projetmobile.Model.Annonceur_Particulier;
import com.example.projetmobile.Model.Annonceur_pro;
import com.example.projetmobile.Model.User;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Acceuille extends AppCompatActivity {
    private Bundle extras ;
    private GridView  listView ;
    private TextView Tuser ;
    private TextInputLayout recherche;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_acceuil);
        recherche = (TextInputLayout) findViewById(R.id.idrecherche);
        extras = getIntent().getExtras();
        listView = (GridView ) findViewById(R.id.gridView);

//       User user =  extras.getParcelable("USER");
  //      System.out.println("utilisateur " + user.getEmail());
        MyAsyncConnexion myAsyncTasks = new MyAsyncConnexion();

/*
        UserControlers planningModel = new ViewModelProvider(this).get(UserControlers.class);
        planningModel.init(getApplicationContext());
        List<UserBDD> d = planningModel.getPlanning();
        System.out.println(d.get(0).getPseudo());
        Tuser.setText(d.get(0).getPseudo());

 */
        Gson gson = new Gson();
        String url = "http://192.168.1.25:8080/LeMauvaisCoin/api/annonce/Recent" ;
        String reponse = null;
        try {
            Toast.makeText(Acceuille.this, "ICI1", Toast.LENGTH_LONG).show();
            reponse = myAsyncTasks.execute(url).get();
            Toast.makeText(Acceuille.this, "ICI2", Toast.LENGTH_LONG).show();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        if(!reponse.equals("")) {
            List<Annonce> test = new ArrayList<Annonce>();
            ArrayList<Annonce> annonce = gson.fromJson(reponse,  new TypeToken<ArrayList<Annonce>>(){}.getType());
            AnnonceRecentAdaptateur myAdapter=new AnnonceRecentAdaptateur(this,annonce);
            listView.setAdapter(myAdapter);
        }


    }
}