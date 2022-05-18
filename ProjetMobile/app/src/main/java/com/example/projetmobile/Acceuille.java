package com.example.projetmobile;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;
import com.example.projetmobile.BDD.Repository.AppDataBase;
import com.example.projetmobile.BDD.Repository.UserDao;
import com.example.projetmobile.BDD.models.Controllers.UserControlers;
import com.example.projetmobile.Model.Annonce;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Acceuille extends AppCompatActivity {
    private Bundle extras ;
    private GridView  listView ;
    private TextView Tuser ;
    private TextInputLayout recherche;
    private ArrayList<Annonce> annonce;
    AnnonceRecentAdaptateur myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_acceuil);
        recherche = (TextInputLayout) findViewById(R.id.idrecherche);
        extras = getIntent().getExtras();
        listView = (GridView ) findViewById(R.id.gridView);
        Gson gson = new Gson();
        String url = "http://172.16.5.209:8080/LeMauvaisCoin/api/annonce/Recent" ;
        String reponse = null;
        try {
            reponse = getRequest(url);
           // reponse = myAsyncTasks.execute(url).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(!reponse.equals("")) {
             annonce = gson.fromJson(reponse,  new TypeToken<ArrayList<Annonce>>(){}.getType());
             myAdapter=new AnnonceRecentAdaptateur(this,annonce);
            listView.setAdapter(myAdapter);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3)
            {
                final int REQUEST_CODE = 20;
                Intent intention= new Intent(Acceuille.this, DetailAnnonce.class);
                Gson gson = new Gson();
                FileOutputStream fOut = null;
                String myJson = gson.toJson(annonce.get(position));
                intention.putExtra("Annonce",myJson);
                intention.putExtra("FAV",myAdapter.getFav().get(position));
                startActivity(intention);
            }
        });
    }

    public void filtre(View view){
        Intent intention= new Intent(Acceuille.this, Recherche.class);
        startActivity(intention);
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
