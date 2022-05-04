package com.example.projetmobile;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentTransaction;
import com.example.projetmobile.Model.Recherche;
import com.example.projetmobile.Model.Departement;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class FragmentDepartementRecherche extends Fragment {

    private ListView dep ;
    private Button valider;
    private ArrayList<Departement> Departement;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_departement_recherche, container, false);
        dep = root.findViewById(R.id.listViewdepartement);
        valider = (Button) root.findViewById(R.id.Valider);
        init();
        ListDepartementRechercheAdaptateur adapter = new ListDepartementRechercheAdaptateur(getActivity(),Departement);

        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    InputStream input = null;
                    input = getContext().openFileInput("recherche.json");
                    byte[] buffer = new byte[input.available()];
                    input.read(buffer);
                    input.close();
                    String text = new String(buffer);
                    Gson gson = new Gson();
                    Recherche recherche = gson.fromJson(text, Recherche.class);
                    ArrayList<String> q = new ArrayList<>();
                    for (int i = 0; i < dep.getAdapter().getCount(); ++i) {
                        Departement d = (Departement) dep.getAdapter().getItem(i);
                        System.out.println(d.getNom() + " = " + d.isIschecked());
                        if(d.isIschecked()) {
                            q.add(d.getNom());

                        }
                    }
                    FileOutputStream fOut = getContext().openFileOutput("recherche.json", 0);
                    recherche.setDepartement(q);
                    String json = gson.toJson(recherche);
                    System.out.println(json);
                    fOut.write(json.getBytes());
                    fOut.close();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                FragmentLocalisationRecherche fragment= new FragmentLocalisationRecherche();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainerView5, fragment);
                transaction.commit();

            }

        });



        dep.setAdapter(adapter);
        return root;
    }

    public void init(){
        Departement = new ArrayList<>();
        Departement.add(new Departement("Toute la France",false));
        Departement.add(new Departement("Bouche du Rhone",false));
        Departement.add(new Departement("Var",false));
        Departement.add(new Departement("Hérault",false));
        Departement.add(new Departement("Ile est Vilaine",false));
        Departement.add(new Departement("Ile de france",false));
        Departement.add(new Departement("Finistère",false));
        Departement.add(new Departement("Bouche du Rhone",false));
        Departement.add(new Departement("Var",false));
        Departement.add(new Departement("Hérault",false));
        Departement.add(new Departement("Ile est Vilaine",false));
        Departement.add(new Departement("Ile de france",false));
        Departement.add(new Departement("Finistère",false));
        Departement.add(new Departement("Bouche du Rhone",false));
        Departement.add(new Departement("Var",false));
        Departement.add(new Departement("Hérault",false));
        Departement.add(new Departement("Ile est Vilaine",false));
        Departement.add(new Departement("Ile de france",false));
        Departement.add(new Departement("Finistère",false));
        Departement.add(new Departement("Bouche du Rhone",false));
        Departement.add(new Departement("Var",false));
        Departement.add(new Departement("Hérault",false));
        Departement.add(new Departement("Ile est Vilaine",false));
        Departement.add(new Departement("Ile de france",false));
        Departement.add(new Departement("Finistère",false));
        Departement.add(new Departement("Bouche du Rhone",false));
        Departement.add(new Departement("Var",false));
        Departement.add(new Departement("Hérault",false));
        Departement.add(new Departement("Ile est Vilaine",false));
        Departement.add(new Departement("Ile de france",false));
        Departement.add(new Departement("Finistère",false));
        Departement.add(new Departement("Bouche du Rhone",false));
        Departement.add(new Departement("Var",false));
        Departement.add(new Departement("Hérault",false));
        Departement.add(new Departement("Ile est Vilaine",false));
        Departement.add(new Departement("Ile de france",false));
        Departement.add(new Departement("Finistère",false));


    }
}