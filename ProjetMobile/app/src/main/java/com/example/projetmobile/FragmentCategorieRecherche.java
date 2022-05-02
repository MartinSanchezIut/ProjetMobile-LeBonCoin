package com.example.projetmobile;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentTransaction;
import com.example.projetmobile.Model.Catégorie;
import com.example.projetmobile.Model.Recherche;
import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;


public class FragmentCategorieRecherche extends Fragment {

    private ListView list;
    private ArrayList<Catégorie> listcategorie;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_categorie_recherche, container, false);
        list = (ListView)root.findViewById(R.id.listView);

        init();
        ListCategorieRechercheAdaptateur adapter = new ListCategorieRechercheAdaptateur(getActivity(),listcategorie);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    Bundle args = new Bundle();
                    args.putStringArrayList("Filtre", listcategorie.get(position).getFiltre());
                    args.putString("Categorie", listcategorie.get(position).getNomCategorie());
                    FragmentFiltreRecherche fragment = new FragmentFiltreRecherche();
                    fragment.setArguments(args);
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                    transaction.replace(R.id.fragmentContainerView5, fragment);
                    transaction.commit();
                } else {

                    String file_name = getActivity().getFilesDir() + "/" + "recherche.json";
                    File t = new File(file_name);
                    InputStream input = null;
                    com.example.projetmobile.Model.Recherche r = new com.example.projetmobile.Model.Recherche(null, null, null, null, null, null, null, null);
                    try {
                        Gson gson = new Gson();
                        if (t.exists()) {
                            try {
                                input = getContext().openFileInput("recherche.json");
                            } catch (FileNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                            byte[] buffer = new byte[0];
                            try {
                                buffer = new byte[input.available()];
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                input.read(buffer);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                input.close();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            String text = new String(buffer);
                            r = gson.fromJson(text, Recherche.class);
                        }
                        r.setFiltre(listcategorie.get(position).getFiltre().get(0));
                        r.setCategorie(listcategorie.get(position).getNomCategorie());
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
            }
        });
        return root;
    }
    public void init(){
        listcategorie = new ArrayList<>();
        ArrayList<String> vacance = new ArrayList<>();
        vacance.add("Tous filtres");
        vacance.add("Location et Gites");
        vacance.add("Chambres d'hôtes");
        vacance.add("Campings");
        vacance.add("Hébergement insolites");
        vacance.add("Hotels");
        vacance.add("Ventes privées vacances");
        vacance.add("Locations en épargne");
        ArrayList<String> all = new ArrayList<>();
        all.add("Tous filtres");
        listcategorie.add(new Catégorie("Toutes les catégories",R.drawable.baseline_wb_sunny_black_24dp,all));
        listcategorie.add(new Catégorie("Vacances",R.drawable.baseline_wb_sunny_black_24dp,vacance));
        ArrayList<String> test1 = new ArrayList<>();
        test1.add("Tous filtres");
        test1.add("TEST1");
        test1.add("TEST1");
        test1.add("TEST1");
        test1.add("TEST1");
        test1.add("TEST1");
        test1.add("TEST1");
        test1.add("TEST1");
        listcategorie.add(new Catégorie("TEST1",R.drawable.baseline_wb_sunny_black_24dp,test1));
        ArrayList<String> test2 = new ArrayList<>();
        test2.add("Tous filtres");
        test2.add("TEST2");
        test2.add("TEST2");
        test2.add("TEST2");
        test2.add("TEST2");
        test2.add("TEST2");
        test2.add("TEST2");
        test2.add("TEST2");
        listcategorie.add(new Catégorie("TEST2",R.drawable.baseline_wb_sunny_black_24dp,test2));
        ArrayList<String> test3 = new ArrayList<>();
        test3.add("Tous filtres");
        test3.add("TEST3");
        test3.add("TEST3");
        test3.add("TEST3");
        test3.add("TEST3");
        test3.add("TEST3");
        test3.add("TEST3");
        test3.add("TEST3");
        listcategorie.add(new Catégorie("TEST3",R.drawable.baseline_wb_sunny_black_24dp,test3));
        listcategorie.add(new Catégorie("Vacances",R.drawable.baseline_wb_sunny_black_24dp,vacance));
        listcategorie.add(new Catégorie("Vacances",R.drawable.baseline_wb_sunny_black_24dp,vacance));
        listcategorie.add(new Catégorie("Vacances",R.drawable.baseline_wb_sunny_black_24dp,vacance));
        listcategorie.add(new Catégorie("Vacances",R.drawable.baseline_wb_sunny_black_24dp,vacance));
        listcategorie.add(new Catégorie("Vacances",R.drawable.baseline_wb_sunny_black_24dp,vacance));
        listcategorie.add(new Catégorie("Vacances",R.drawable.baseline_wb_sunny_black_24dp,vacance));

    }
}