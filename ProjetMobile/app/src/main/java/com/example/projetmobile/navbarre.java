package com.example.projetmobile;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
<<<<<<< HEAD
import androidx.lifecycle.ViewModelProvider;
import com.example.projetmobile.BDD.models.Controllers.UserControlers;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class navbarreDeconnecter extends Fragment {

     BottomNavigationView bottomNavigationView;
    public navbarreDeconnecter() {
=======
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class navbarre extends Fragment {

     BottomNavigationView bottomNavigationView;
    public navbarre() {
>>>>>>> 1e32d3dff781e1059356dd69c93d6e30fecd5ce0
        // Required empty public constructor
    }

    @Override
<<<<<<< HEAD
    public void onResume(){
        super.onResume();
        // put your code here...

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



=======
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
>>>>>>> 1e32d3dff781e1059356dd69c93d6e30fecd5ce0
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
<<<<<<< HEAD
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
        if (Acceuille.class.getName().equals(c) || Recherche.class.getName().equals(c) || DetailAnnonce.class.getName().equals(c)) {
            bottomNavigationView.getMenu().findItem(R.id.navigation_home).setChecked(true);
        } else if (Inscription.class.getName().equals(c)) {
            bottomNavigationView.getMenu().findItem(R.id.navigate_inscription).setChecked(true);
        } else if (Profil.class.getName().equals(c) || ActivityMessage.class.getName().equals(c) ||ActivityConversation.class.getName().equals(c) ) {
            bottomNavigationView.getMenu().findItem(R.id.navigate_profile).setChecked(true);
        } else if (Connexion.class.getName().equals(c)) {
            bottomNavigationView.getMenu().findItem(R.id.navigation_connexion).setChecked(true);
        } else if (AjouterAnnonce.class.getName().equals(c)) {
            bottomNavigationView.getMenu().findItem(R.id.navigate_publication).setChecked(true);
        }
=======
        View root = inflater.inflate(R.layout.fragment_navbarre, container, false);
        bottomNavigationView = root.findViewById(R.id.bottomNavigationView);
>>>>>>> 1e32d3dff781e1059356dd69c93d6e30fecd5ce0
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> updateMainFragment(item.getItemId()));
        return root;
    }
    private Boolean updateMainFragment(Integer integer){
        switch (integer) {
            case R.id.navigation_home:
<<<<<<< HEAD
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
=======
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
>>>>>>> 1e32d3dff781e1059356dd69c93d6e30fecd5ce0
                break;
        }
        return true;
    }
}
