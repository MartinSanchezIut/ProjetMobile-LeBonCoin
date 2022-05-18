package com.example.projetmobile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.GridView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;
import com.example.projetmobile.BDD.Repository.AppDataBase;
import com.example.projetmobile.BDD.Repository.UserDao;
import com.example.projetmobile.BDD.models.Controllers.UserControlers;
import com.example.projetmobile.Model.Annonce;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;


public class FragmentAnnonceSauvegarde extends Fragment {
    private GridView listView ;
    private ArrayList<Annonce> annonces;
    AnnonceRecentAdaptateur myAdapter;

    public FragmentAnnonceSauvegarde() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_annonce_sauvegarde, container, false);
        listView = (GridView ) root.findViewById(R.id.gridView);
        Gson gson = new Gson();
        AppDataBase db = Room.databaseBuilder(getContext(), AppDataBase.class,"database-name").allowMainThreadQueries().build();
        UserDao userDao = db.userDao();
        String url = "http://172.16.5.209:8080/LeMauvaisCoin/api/annonce/Getsauvegarde/" + userDao.getAll().get(0).getId_user();
        String reponse = null;
        try {
            reponse = getRequest(url);
            // reponse = myAsyncTasks.execute(url).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(!reponse.equals("")) {
            annonces = gson.fromJson(reponse,  new TypeToken<ArrayList<Annonce>>(){}.getType());
            myAdapter=new AnnonceRecentAdaptateur(getContext(),annonces);
            listView.setAdapter(myAdapter);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3)
            {
                final int REQUEST_CODE = 20;
                Intent intention= new Intent(getContext(), DetailAnnonce.class);
                Gson gson = new Gson();
                FileOutputStream fOut = null;
                String myJson = gson.toJson(annonces.get(position));
                intention.putExtra("Annonce",myJson);
                intention.putExtra("FAV",myAdapter.getFav().get(position));
                startActivity(intention);
            }
        });


        return root;
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