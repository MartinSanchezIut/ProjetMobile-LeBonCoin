package com.example.projetmobile;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentTransaction;
import com.example.projetmobile.Model.Catégorie;

import java.util.ArrayList;
import java.util.List;


public class FragmentCategorie extends Fragment {
    private ListView list;
    private ArrayList<Catégorie> listcategorie;

    public FragmentCategorie() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_categorie, container, false);
        list = (ListView)root.findViewById(R.id.listView);

        init();
        ListCategorieAdaptateur adapter = new ListCategorieAdaptateur(getActivity(),listcategorie);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle args = new Bundle();
                args.putStringArrayList("Filtre", listcategorie.get(position).getFiltre());
                args.putString("Categorie", listcategorie.get(position).getNomCategorie());
                System.out.println(listcategorie.get(position).getFiltre().get(0));
                FiltreFragment fragment = new FiltreFragment();
                fragment.setArguments(args);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                transaction.replace(R.id.fragmentContainerView2, fragment);
                transaction.commit();
            }
        });
        return root;
    }
    public void init(){
        listcategorie = new ArrayList<>();
        ArrayList<String> vacance = new ArrayList<>();
        vacance.add("Location et Gites");
        vacance.add("Chambres d'hôtes");
        vacance.add("Campings");
        vacance.add("Hébergement insolites");
        vacance.add("Hotels");
        vacance.add("Ventes privées vacances");
        vacance.add("Locations en épargne");
        listcategorie.add(new Catégorie("Vacances",R.drawable.baseline_wb_sunny_black_24dp,vacance));
        ArrayList<String> test1 = new ArrayList<>();
        test1.add("Jeux de société");
        test1.add("Jeux en famille");
        test1.add("jeux 0-5 ans");
        test1.add("jeux 5-10 ans");
        test1.add("jeux 10-18 ans");
        test1.add("jeux +18");
        test1.add("jeux tout publique");
        listcategorie.add(new Catégorie("Jeux",R.drawable.baseline_wb_sunny_black_24dp,test1));
        ArrayList<String> test2 = new ArrayList<>();
        test2.add("10m2");
        test2.add("20m2");
        test2.add("30m2");
        test2.add("40m2");
        test2.add("50m2");
        test2.add("60m2");
        test2.add("+60m2");
        listcategorie.add(new Catégorie("Appartement",R.drawable.baseline_wb_sunny_black_24dp,test2));
        ArrayList<String> test3 = new ArrayList<>();
        test3.add("50m2");
        test3.add("100m2");
        test3.add("150m2");
        test3.add("200m2");
        test3.add("+200m2");
        listcategorie.add(new Catégorie("Maison",R.drawable.baseline_wb_sunny_black_24dp,test3));
        ArrayList<String> test4 = new ArrayList<>();
        test4.add("Marteau");
        test4.add("Tourne vise");
        test4.add("vise");
        test4.add("echelle");
        test4.add("clous");
        listcategorie.add(new Catégorie("Bricolage",R.drawable.baseline_wb_sunny_black_24dp,test4));

        ArrayList<String> test5 = new ArrayList<>();
        test5.add("Velo");
        test5.add("Tapis de course");
        test5.add("vetement de sport");
        test5.add("raquette");
        test5.add("ballon");
        listcategorie.add(new Catégorie("Sport",R.drawable.baseline_wb_sunny_black_24dp,test5));

        ArrayList<String> test6 = new ArrayList<>();
        test6.add("Telephone");
        test6.add("Ordinateur");
        test6.add("montre connecté");
        test6.add("écouteur");
        test6.add("Périphérique");
        listcategorie.add(new Catégorie("Multimedia",R.drawable.baseline_wb_sunny_black_24dp,test6));

        ArrayList<String> test7 = new ArrayList<>();
        test6.add("Lit");
        test6.add("Cafetière");
        test6.add("cuisine");
        listcategorie.add(new Catégorie("Autre",R.drawable.baseline_wb_sunny_black_24dp,test7));
    }

}