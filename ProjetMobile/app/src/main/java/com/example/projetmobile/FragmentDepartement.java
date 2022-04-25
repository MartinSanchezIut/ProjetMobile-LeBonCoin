package com.example.projetmobile;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentTransaction;
import com.example.projetmobile.Model.Annonce;
import com.example.projetmobile.Model.Catégorie;
import com.example.projetmobile.Model.Departement;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class FragmentDepartement extends Fragment {

    private Button valider;
    private ListView list;
    private ArrayList<Departement> Departement;

    public FragmentDepartement() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_departement, container, false);
        list = (ListView)root.findViewById(R.id.listViewdepartement);
        valider = (Button) root.findViewById(R.id.Valider);

        init();
        ListdepartementAdaptateur adapter = new ListdepartementAdaptateur(getActivity(),Departement);
        list.setAdapter(adapter);

        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    for (int i = 0; i < list.getAdapter().getCount(); ++i) {
                        Departement d = (Departement) list.getAdapter().getItem(i);
                        if(d.isIschecked()) {
                            InputStream input = null;
                            input = getContext().openFileInput("Data.json");
                            byte[] buffer = new byte[input.available()];
                            input.read(buffer);
                            input.close();
                            String text = new String(buffer);
                            Gson gson = new Gson();
                            Annonce f = gson.fromJson(text, Annonce.class);
                            f.setDepartement(d.getNom());
                            FileOutputStream fOut = getContext().openFileOutput("Data.json", 0);
                            String json = gson.toJson(f);
                            System.out.println(json);
                            fOut.write(json.getBytes());
                            fOut.close();
                            FragmentDeposer fragment = new FragmentDeposer();
                            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.fragmentContainerView6, fragment);
                            transaction.commit();
                        }
                    }
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }

        });
        return root;
    }
    public void init(){
        Departement = new ArrayList<>();

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