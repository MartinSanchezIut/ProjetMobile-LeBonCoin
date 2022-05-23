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
import com.example.projetmobile.Model.serveur;
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


public class FragmentListMesAnnonce extends Fragment {

    private GridView listView;
    private ArrayList<Annonce> annonces;
    AnnonceRecentAdaptateur myAdapter;
    public FragmentListMesAnnonce() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_list_mes_annonces, container, false);
        Gson gson = new Gson();
        listView = (GridView) root.findViewById(R.id.gridView);
        AppDataBase db = Room.databaseBuilder(getContext(), AppDataBase.class,"database-name").allowMainThreadQueries().build();
        UserDao userDao = db.userDao();
        String reponse = "";
        try {
            serveur s = new serveur("annonce/GetMesAnnonces/" + userDao.getAll().get(0).getId_user());
            reponse= s.getRequest();
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
                UserControlers userControlers = new ViewModelProvider(getActivity()).get(UserControlers.class);
                userControlers.init(getContext());
                if(userControlers.getPlanning().get(0).getStatu().equals("AnnonceurPro")) {
                    Fragment newFragment = new DetailAnnoncePro();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragmentContainerView2, newFragment);
                    Bundle bundle = new Bundle();
                    String myJson = gson.toJson(annonces.get(position));
                    bundle.putString("Annonce", myJson);
                    bundle.putBoolean("FAV", myAdapter.getFav().get(position));
                    newFragment.setArguments(bundle);
                    transaction.commit();
                }else{
                    Fragment newFragment = new FragmentDetailMesAnnoncePart();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragmentContainerView2, newFragment);
                    Bundle bundle = new Bundle();
                    String myJson = gson.toJson(annonces.get(position));
                    bundle.putString("Annonce", myJson);
                    bundle.putBoolean("FAV", myAdapter.getFav().get(position));
                    newFragment.setArguments(bundle);
                    transaction.commit();
                }
            }
        });

        return root;
    }
}