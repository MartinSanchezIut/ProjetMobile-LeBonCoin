package com.example.projetmobile;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.example.projetmobile.BDD.Controllers.UserControlers;
import com.example.projetmobile.BDD.Repository.AppDataBase;
import com.example.projetmobile.BDD.Repository.UserDao;
import com.example.projetmobile.BDD.models.UserBDD;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;


public class navbarreDeconnecter extends Fragment {

     BottomNavigationView bottomNavigationView;
    public navbarreDeconnecter() {
        // Required empty public constructor
    }

    @Override
    public void onResume(){
        super.onResume();
        // put your code here...

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_navbarre_deconnecter, container, false);
        bottomNavigationView = root.findViewById(R.id.bottomNavigationView);
        UserControlers userControlers = new ViewModelProvider(this).get(UserControlers.class);
        userControlers.init(getContext());
        if(userControlers.Count()==0) {
            bottomNavigationView.inflateMenu(R.menu.bottom_navigation_view);
        }else{
            bottomNavigationView.inflateMenu(R.menu.bottom_navigation_view_connecter);
        }
        String c = getContext().getClass().getName();
        if (Acceuille.class.getName().equals(c)) {
            System.out.println("ICI1");
            bottomNavigationView.getMenu().findItem(R.id.navigation_home).setChecked(true);
        } else if (Inscription.class.getName().equals(c)) {
            System.out.println("ICI2");
            bottomNavigationView.getMenu().findItem(R.id.navigate_inscription).setChecked(true);
        } else if (Profil.class.getName().equals(c)) {
            System.out.println("ICI3");
            bottomNavigationView.getMenu().findItem(R.id.navigate_profile).setChecked(true);
        } else if (Connexion.class.getName().equals(c)) {
            System.out.println("ICI4");
            bottomNavigationView.getMenu().findItem(R.id.navigation_connexion).setChecked(true);
        } else if (AjouterAnnonce.class.getName().equals(c)) {
            System.out.println("ICI5");
            bottomNavigationView.getMenu().findItem(R.id.navigate_publication).setChecked(true);
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> updateMainFragment(item.getItemId()));
        return root;
    }
    private Boolean updateMainFragment(Integer integer){
        switch (integer) {
            case R.id.navigation_home:
                startActivity(new Intent(getActivity().getApplication(), Acceuille.class));
                getActivity().overridePendingTransition(0, 0);
                break;
            case R.id.navigate_inscription:
                startActivity(new Intent(getActivity().getApplication(), Inscription.class));
                getActivity().overridePendingTransition(0, 0);
                break;
            case R.id.navigate_profile:
                startActivity(new Intent(getActivity().getApplication(), Profil.class));
                getActivity().overridePendingTransition(0, 0);
                break;
            case R.id.navigation_connexion:
                startActivity(new Intent(getActivity().getApplication(), Connexion.class));
                getActivity().overridePendingTransition(0, 0);
                break;
            case R.id.navigate_publication:
                startActivity( new Intent(getActivity().getApplication(), AjouterAnnonce.class));
                getActivity().overridePendingTransition(0, 0);
                break;
        }
        return true;
    }
}
