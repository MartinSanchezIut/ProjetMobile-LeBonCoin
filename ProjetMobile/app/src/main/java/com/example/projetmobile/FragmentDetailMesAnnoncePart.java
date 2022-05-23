package com.example.projetmobile;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;
import com.example.projetmobile.BDD.models.Controllers.UserControlers;
import com.example.projetmobile.Model.Annonce;
import com.example.projetmobile.Model.serveur;
import com.google.gson.Gson;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;


public class FragmentDetailMesAnnoncePart extends Fragment {

    private ViewPager2 viewPager2image;
    private TextView titre;
    private TextView description;
    private TextView prix;
    private TextView département;
    private TextView ville;
    private TextView pseudo;
    private ImageView profileAnnonceur;
    private ImageView iconFavoris;

    private ImageView supprimer;
    private ImageView modifier;

    private LinearLayout annonceur;


    private LinearLayout fraude;

    private LinearLayout global;
    private TextView motiffraude;

    private Annonce annonce;
    public FragmentDetailMesAnnoncePart() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_detail_mes_annonce_part, container, false);
        this.viewPager2image = root.findViewById(R.id.viewpager2);
        this.titre = root.findViewById(R.id.Titre);
        this.description = root.findViewById(R.id.description);
        this.prix = root.findViewById(R.id.Prix);
        this.département = root.findViewById(R.id.departement);
        this.ville = root.findViewById(R.id.ville);
        this.pseudo = root.findViewById(R.id.pseudo);
        this.profileAnnonceur = root.findViewById(R.id.profil);
        this.annonceur = root.findViewById(R.id.messageAnnoneur);
        this.iconFavoris = root.findViewById(R.id.iconfavoris);
        this.supprimer = root.findViewById(R.id.Suprimer);
        this.modifier = root.findViewById(R.id.Modifier);
        this.fraude = root.findViewById(R.id.linearfraude);
        this.motiffraude = root.findViewById(R.id.fraudemotif);
        this.global = root.findViewById(R.id.linearglobal);



        Gson gson = new Gson();
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String reponse = bundle.getString("Annonce", null);
            annonce = gson.fromJson(reponse,Annonce.class);
        }

        if (bundle.getBoolean("FAV")) {
            this.iconFavoris.setImageResource(R.drawable.baseline_favorite_black_24dp);
            global.setBackgroundColor(Color.RED);
        }

        if(!annonce.isIsfraude()){
            fraude.removeAllViews();
        }else{
            motiffraude.setText(annonce.getMotifFraude());
        }

        titre.setText(annonce.getTitre());
        description.setText(annonce.getDescription());
        prix.setText(String.valueOf(annonce.getPrix()));
        département.setText(annonce.getDepartement());
        ville.setText(annonce.getVille());
        ArrayList<Bitmap> images = new ArrayList<>();
        for(String s : annonce.getimage()) {
            byte[] myImage = Base64.getDecoder().decode(s.getBytes(StandardCharsets.UTF_8));
            Bitmap bmp = BitmapFactory.decodeByteArray(myImage, 0, myImage.length);
            images.add(bmp);
        }
        ImageFragmentStateAdaptater adapter = new ImageFragmentStateAdaptater(getActivity(),images);
        this.viewPager2image.setAdapter(adapter);
        this.iconFavoris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                iconFavoris();

            }
        });

        this.supprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserControlers userControlers = new ViewModelProvider(getActivity()).get(UserControlers.class);
                userControlers.init(getContext());

                try {
                    serveur s = new serveur("annonce/deleteannonce/" + userControlers.getPlanning().get(0).getId_user()+"/"+annonce.getId_annonce());
                    s.DeleteRequest();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Fragment newFragment = new FragmentListMesAnnonce();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainerView2, newFragment);
                transaction.commit();

            }
        });

        this.modifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment newFragment = new FragmentModificationAnnonce();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                FileOutputStream fOut = null;
                try {
                    fOut = getContext().openFileOutput("Modification.json", 0);
                    String json = gson.toJson(annonce);
                    System.out.println(json);
                    fOut.write(json.getBytes());
                    fOut.close();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                transaction.replace(R.id.fragmentContainerView2, newFragment);
                transaction.commit();

            }
        });

        return root;
    }
    public void iconFavoris(){
        UserControlers userControlers = new ViewModelProvider(getActivity()).get(UserControlers.class);
        userControlers.init(getContext());
        ArrayList<String> params = new ArrayList<>();
        params.add(String.valueOf(userControlers.getPlanning().get(0).getId_user()));
        params.add(String.valueOf(annonce.getId_annonce()));
        Gson gson = new Gson();
        String query = gson.toJson(params);
        try {
            serveur s = new serveur("annonce/Favoris");
            s.PostRequest(query);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(this.iconFavoris.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.baseline_favorite_black_24dp).getConstantState())){
            this.iconFavoris.setImageResource(R.drawable.baseline_favorite_border_black_24dp);
        }else {
            this.iconFavoris.setImageResource(R.drawable.baseline_favorite_black_24dp);
        }

    }

}