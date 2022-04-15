package com.example.projetmobile;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class navbarre extends Fragment {

     BottomNavigationView bottomNavigationView;
    public navbarre() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_navbarre, container, false);
        bottomNavigationView = root.findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> updateMainFragment(item.getItemId()));
        return root;
    }
    private Boolean updateMainFragment(Integer integer){
        switch (integer) {
            case R.id.navigation_home:
                Intent intent1 = new Intent(getActivity().getApplication(), Acceuille.class);
                startActivity(intent1);
                break;
            case R.id.navigate_inscription:
                Intent intent2 = new Intent(getActivity().getApplication(), Inscription.class);
                startActivity(intent2);
                break;
            case R.id.navigate_profile:
                Intent intent3 = new Intent(getActivity().getApplication(), Profil.class);
                startActivity(intent3);
                break;
            case R.id.navigation_connexion:
                Intent intent4 = new Intent(getActivity().getApplication(), Connexion.class);
                startActivity(intent4);
                break;
            case R.id.navigate_publication:
                Intent intent5 = new Intent(getActivity().getApplication(), AjouterAnnonce.class);
                startActivity(intent5);
                break;
        }
        return true;
    }
}
