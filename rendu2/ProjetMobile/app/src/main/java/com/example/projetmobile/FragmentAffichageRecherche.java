package com.example.projetmobile;

import android.os.Bundle;
import android.widget.GridView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.projetmobile.Model.Annonce;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class FragmentAffichageRecherche extends Fragment {
    private GridView listView ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String mParam2 = "";
        View root = inflater.inflate(R.layout.fragment_affichage_recherche, container, false);

        listView = root.findViewById(R.id.gridView);
        if (getArguments() != null) {
            mParam2 = getArguments().getString("ANNONCES");
        }
        System.out.println(mParam2);
        Gson gson = new Gson();
        ArrayList<Annonce> annonce = gson.fromJson(mParam2,  new TypeToken<ArrayList<Annonce>>(){}.getType());

        AnnonceRecentAdaptateur myAdapter=new AnnonceRecentAdaptateur(getContext(),annonce);
        listView.setAdapter(myAdapter);
        return root;
    }
}