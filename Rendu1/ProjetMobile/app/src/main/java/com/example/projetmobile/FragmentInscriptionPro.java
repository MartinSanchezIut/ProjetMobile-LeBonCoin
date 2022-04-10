package com.example.projetmobile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.projetmobile.Model.Annonceur_pro;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class FragmentInscriptionPro extends Fragment {
    private EditText Eemail;
    private EditText Epseudo;
    private EditText Enom;
    private EditText Enumero;
    private EditText Eprenom;
    private EditText Epassword;
    private EditText Eentreprise;
    private Button Pro;
    private Button Part;
    private Button valider;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inscription_pro, container, false);
        Eemail = (EditText)view.findViewById(R.id.idemail);
        Epseudo = (EditText)view.findViewById(R.id.idpseudo);
        Enom = (EditText)view.findViewById(R.id.idnom);
        Eprenom = (EditText)view.findViewById(R.id.idprenom);
        Enumero = (EditText)view.findViewById(R.id.idnumero);
        Epassword = (EditText)view.findViewById(R.id.idpassword);
        Eentreprise = (EditText)view.findViewById(R.id.identreprise);
        Pro = (Button)view.findViewById(R.id.Probutton);
        Pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentPro();

            }
        });

        Part = (Button)view.findViewById(R.id.Partbutton);
        Part.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentPart();

            }
        });

        valider = (Button)view.findViewById(R.id.valider);
        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url ="http://192.168.1.25:8080/LeMauvaisCoin/api/User/InscriptionAnnonceurPro";
                MyAsyncInscription myAsyncTasks = new MyAsyncInscription();
                try {
                    Gson gson = new Gson();
                    Annonceur_pro pro = new Annonceur_pro("AnnonceurPro",Epseudo.getText().toString(),Enom.getText().toString(),Eprenom.getText().toString(),Eemail.getText().toString(),Enumero.getText().toString(),Epassword.getText().toString(),Eentreprise.getText().toString());
                    String json = gson.toJson(pro);
                    String reponse = myAsyncTasks.execute(url,json).get();
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(getActivity(),Connexion.class);
                startActivity(intent);

            }
        });

        return view;
    }
    public void FragmentPart(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentinscriptionPart fragment  = new FragmentinscriptionPart();
        fragmentTransaction.replace(R.id.fragmentContainerView , fragment);
        fragmentTransaction.commit();
    }

    public void FragmentPro(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentInscriptionPro fragment  = new FragmentInscriptionPro();
        fragmentTransaction.replace(R.id.fragmentContainerView , fragment);
        fragmentTransaction.commit();
    }
}