package com.example.projetmobile;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentTransaction;
import com.example.projetmobile.Model.Recherche;
import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;

public class FragmentFiltreRecherche extends Fragment {



    private ListView list;
    private static final String ARG_PARAM1 = "Filtre";
    private static final String ARG_PARAM2 = "Categorie";
    // TODO: Rename and change types of parameters
    private ArrayList<String> mParam1;
    private String mParam2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_filtre_recherche, container, false);
        if (getArguments() != null) {
            mParam1 = getArguments().getStringArrayList(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        System.out.println(mParam1);
        System.out.println(mParam2);

        list = (ListView)root.findViewById(R.id.listView);
        ListFiltreRechercheAdaptateur adapter = new ListFiltreRechercheAdaptateur(getActivity(),mParam1);
        list.setFooterDividersEnabled(true);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mParam1.get(position);

                String file_name = getActivity().getFilesDir() + "/" + "recherche.json";
                File t = new File(file_name);
                InputStream input = null;
                Recherche r = new Recherche(null,null,null,null,null,null,null,null);
                try {
                    Gson gson = new Gson();
                if (t.exists()) {
                        input = getContext().openFileInput("recherche.json");
                        byte[] buffer = new byte[input.available()];
                        input.read(buffer);
                        input.close();
                        String text = new String(buffer);
                         r = gson.fromJson(text, Recherche.class);
                    }
                        r.setFiltre(mParam1.get(position));
                        r.setCategorie(mParam2);
                        FileOutputStream fOut = getContext().openFileOutput("recherche.json", 0);

                        String json = gson.toJson(r);
                        fOut.write(json.getBytes());
                        fOut.close();


                        FragmentRechercheAcceuil fragment = new FragmentRechercheAcceuil();
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                        transaction.replace(R.id.fragmentContainerView5, fragment);
                        transaction.commit();

                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }





            }
        });

        return root;
    }
}