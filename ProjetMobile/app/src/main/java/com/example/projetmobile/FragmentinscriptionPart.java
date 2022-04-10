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
import com.google.gson.Gson;

import java.util.concurrent.ExecutionException;


public class FragmentinscriptionPart extends Fragment {
    private EditText Eemail;
    private EditText Epseudo;
    private EditText Enom;
    private EditText Enumero;
    private EditText Eprenom;
    private EditText Epassword;
    private Button Pro;
    private Button Part;
    private Button valider;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rooter = inflater.inflate(R.layout.fragment_inscription_part, container, false);

        Eemail = (EditText)rooter.findViewById(R.id.idemail2);
        Epseudo = (EditText)rooter.findViewById(R.id.idpseudo2);
        Enom = (EditText)rooter.findViewById(R.id.idnom2);
        Eprenom = (EditText)rooter.findViewById(R.id.idprenom2);
        Enumero = (EditText)rooter.findViewById(R.id.idnumero2);
        Epassword = (EditText)rooter.findViewById(R.id.idpassword2);
        Pro = (Button)rooter.findViewById(R.id.probutton2);
        Part = (Button)rooter.findViewById(R.id.partbutton2);
        Pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentPro();
            }
        });

        Part.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentPart();
            }
        });

        valider = (Button)rooter.findViewById(R.id.valider2);
        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url ="http://192.168.1.25:8080/LeMauvaisCoin/api/User/InscriptionAnnonceurPart";
                MyAsyncInscription myAsyncTasks = new MyAsyncInscription();
                try {
                    Gson gson = new Gson();
                    Annonceur_Particulier pro = new Annonceur_Particulier("AnnonceurPart",Epseudo.getText().toString(),Enom.getText().toString(),Eprenom.getText().toString(),Eemail.getText().toString(),Enumero.getText().toString(),Epassword.getText().toString());
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

    public void FragmentPro(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentInscriptionPro fragment  = new FragmentInscriptionPro();
        fragmentTransaction.replace(R.id.fragmentContainerView , fragment);
        fragmentTransaction.commit();
    }


    public void FragmentPart(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentinscriptionPart fragment  = new FragmentinscriptionPart();
        fragmentTransaction.replace(R.id.fragmentContainerView , fragment);
        fragmentTransaction.commit();
    }
}