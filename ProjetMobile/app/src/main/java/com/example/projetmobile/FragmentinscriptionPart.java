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
import com.example.projetmobile.Model.Annonceur_Particulier;
import com.example.projetmobile.Model.Annonceur_pro;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import java.util.concurrent.ExecutionException;


public class FragmentinscriptionPart extends Fragment {
    private TextInputLayout Eemail;
    private TextInputLayout Epseudo;
    private TextInputLayout Enom;
    private TextInputLayout Enumero;
    private TextInputLayout Eprenom;
    private TextInputLayout Epassword;
    private Button Pro;
    private Button Part;
    private Button valider;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rooter = inflater.inflate(R.layout.fragment_inscription_part, container, false);

        Eemail = (TextInputLayout)rooter.findViewById(R.id.idemail);
        Epseudo = (TextInputLayout)rooter.findViewById(R.id.idpseudo);
        Enom = (TextInputLayout)rooter.findViewById(R.id.idNom);
        Eprenom = (TextInputLayout)rooter.findViewById(R.id.idPrenom);
        Enumero = (TextInputLayout)rooter.findViewById(R.id.idnum);
        Epassword = (TextInputLayout)rooter.findViewById(R.id.idpassword);

        valider = (Button)rooter.findViewById(R.id.valider2);
        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url ="http://192.168.1.25:8080/LeMauvaisCoin/api/User/InscriptionAnnonceurPart";
                MyAsyncInscription myAsyncTasks = new MyAsyncInscription();
                try {
                    Gson gson = new Gson();
                    Annonceur_Particulier pro = new Annonceur_Particulier("AnnonceurPart",Epseudo.getEditText().getText().toString(),Enom.getEditText().getText().toString(),Eprenom.getEditText().getText().toString(),Eemail.getEditText().getText().toString(),Enumero.getEditText().getText().toString(),Epassword.getEditText().getText().toString());
                    String json = gson.toJson(pro);
                    String reponse = myAsyncTasks.execute(url,json).get();
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent(getActivity(),Connexion.class);
                startActivity(intent);

            }
        });

        return rooter;
    }


}