package com.example.projetmobile;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import com.example.projetmobile.BDD.models.Controllers.UserControlers;

import java.nio.charset.StandardCharsets;
import java.util.Base64;


public class AcceuilProfile extends Fragment {

    private TextView Tpseudo ;
    private TextView TNom ;
    private TextView TPrenom ;
    private TextView TTel ;
    private TextView Temail ;
    private TextView Tentreprise ;

    private ImageView profil ;
    private Button Annoncesauv;

    private Button Messages;
    private Button Mesannonces;
    private Button Deconnexion;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_acceuil_profile, container, false);
        Tpseudo = (TextView)root.findViewById(R.id.idpseudo);
        TNom = (TextView)root.findViewById(R.id.idNom);
        TPrenom = (TextView)root.findViewById(R.id.idPrenom);
        TTel = (TextView)root.findViewById(R.id.idNum);
        Temail = (TextView)root.findViewById(R.id.idEmail);
        Tentreprise = (TextView)root.findViewById(R.id.idEntreprise);
        profil = (ImageView)root.findViewById(R.id.profil);



        UserControlers userControlers = new ViewModelProvider(getActivity()).get(UserControlers.class);
        userControlers.init(getContext());
        Tpseudo.setText(userControlers.getPlanning().get(0).getPseudo());
        TNom.setText(userControlers.getPlanning().get(0).getNom());
        TPrenom.setText(userControlers.getPlanning().get(0).getPrenom());
        TTel.setText(userControlers.getPlanning().get(0).getNumero());
        Temail.setText(userControlers.getPlanning().get(0).getEmail());

        if(userControlers.getPlanning().get(0).getEntreprise() != null){
            Tentreprise.setText(userControlers.getPlanning().get(0).getEntreprise());
        }else{
            Tentreprise.setText("");
        }
        if(userControlers.getPlanning().get(0).getImage() != null) {
            byte[] myImage = Base64.getDecoder().decode(userControlers.getPlanning().get(0).getImage().getBytes(StandardCharsets.UTF_8));
            Bitmap bmp = BitmapFactory.decodeByteArray(myImage, 0, myImage.length);
            profil.setImageBitmap(bmp);
        }

        Tentreprise = (TextView)root.findViewById(R.id.identreprise);
        Annoncesauv = (Button)root.findViewById(R.id.btnAnnoncesauv);
        Annoncesauv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment newFragment = new FragmentAnnonceSauvegarde();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainerView2, newFragment);
                transaction.commit();

            }
        });
        Mesannonces = (Button)root.findViewById(R.id.btnMesAnnonces);
        Mesannonces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment newFragment = new FragmentListMesAnnonce();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainerView2, newFragment);
                transaction.commit();

            }
        });

        Messages = (Button)root.findViewById(R.id.btnMessages);
        Messages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intention= new Intent(getContext(), ActivityMessage.class);
                startActivity(intention);

            }
        });
        Deconnexion = (Button)root.findViewById(R.id.btnDeconnexion);
        Deconnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userControlers.Delete(userControlers.getPlanning().get(0));
                Intent intention= new Intent(getContext(), Connexion.class);
                startActivity(intention);

            }
        });

        return root;
    }
}