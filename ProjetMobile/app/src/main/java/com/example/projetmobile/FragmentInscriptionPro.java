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
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class FragmentInscriptionPro extends Fragment {
    private TextInputLayout Eemail;
    private TextInputLayout Epseudo;
    private TextInputLayout Enom;
    private TextInputLayout Enumero;
    private TextInputLayout Eprenom;
    private TextInputLayout Epassword;
    private TextInputLayout Eentreprise;
    private Button Pro;
    private Button Part;
    private Button valider;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inscription_pro, container, false);
        Eemail = (TextInputLayout)view.findViewById(R.id.idemail);
        Epseudo = (TextInputLayout)view.findViewById(R.id.idpseudo);
        Enom = (TextInputLayout)view.findViewById(R.id.idNom);
        Eprenom = (TextInputLayout)view.findViewById(R.id.idPrenom);
        Enumero = (TextInputLayout)view.findViewById(R.id.idnum);
        Epassword = (TextInputLayout)view.findViewById(R.id.idpassword);
        Eentreprise = (TextInputLayout)view.findViewById(R.id.identreprise);

        valider = (Button)view.findViewById(R.id.valider);
        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url ="http://172.16.5.209:8080/LeMauvaisCoin/api/User/InscriptionAnnonceurPro";
                MyAsyncInscription myAsyncTasks = new MyAsyncInscription();
                try {
                    Gson gson = new Gson();
                    Annonceur_pro pro = new Annonceur_pro("AnnonceurPro",Epseudo.getEditText().getText().toString(),Enom.getEditText().getText().toString(),Eprenom.getEditText().getText().toString(),Eemail.getEditText().getText().toString(),Enumero.getEditText().getText().toString(),Epassword.getEditText().getText().toString(),Eentreprise.getEditText().getText().toString());
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

}