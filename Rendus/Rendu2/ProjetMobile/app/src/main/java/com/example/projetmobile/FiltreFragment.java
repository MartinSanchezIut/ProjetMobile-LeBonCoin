package com.example.projetmobile;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;
import com.example.projetmobile.BDD.Repository.AppDataBase;
import com.example.projetmobile.BDD.Repository.UserDao;
import com.example.projetmobile.Model.Annonce;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class FiltreFragment extends Fragment {

    private ListView list;
    private static final String ARG_PARAM1 = "Filtre";
    private static final String ARG_PARAM2 = "Categorie";
    // TODO: Rename and change types of parameters
    private ArrayList<String> mParam1;
    private String mParam2;

    public FiltreFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_filtre, container, false);
        if (getArguments() != null) {
            mParam1 = getArguments().getStringArrayList(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        System.out.println(mParam1);
        System.out.println(mParam2);

        list = (ListView)root.findViewById(R.id.listView);
        FiltreListAdaptateur adapter = new FiltreListAdaptateur(getActivity(),mParam1);
        list.setFooterDividersEnabled(true);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mParam1.get(position);
                //Gson gson = new Gson();
                //String json = gson.toJson(f);
                InputStream input = null;
                try {
                    input = getContext().openFileInput("Data.json");
                    byte[] buffer=new byte[input.available()];
                    input.read(buffer);
                    input.close();
                    String text=new String(buffer);
                    Gson gson = new Gson();
                    Annonce f = gson.fromJson(text, Annonce.class);
                    f.setFiltre(mParam1.get(position));
                    f.setCategories(mParam2);
                    FileOutputStream fOut = getContext().openFileOutput("Data.json",0);

                    String json = gson.toJson(f);
                    System.out.println("ICI   " + json);
                    fOut.write(json.getBytes());
                    fOut.close();

                    FragmentDeposer fragment = new FragmentDeposer();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                    transaction.replace(R.id.fragmentContainerView6, fragment);
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