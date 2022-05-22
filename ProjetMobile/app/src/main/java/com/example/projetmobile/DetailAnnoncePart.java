package com.example.projetmobile;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;
import com.example.projetmobile.BDD.models.Controllers.UserControlers;
import com.example.projetmobile.Model.Annonce;
import com.example.projetmobile.Model.User;
import com.example.projetmobile.Model.Conversation;
import com.google.gson.Gson;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;


public class DetailAnnoncePart extends Fragment {

    private ViewPager2 viewPager2image;
    private TextView titre;
    private TextView description;
    private TextView prix;
    private TextView département;
    private TextView ville;
    private TextView pseudo;
    private ImageView profileAnnonceur;
    private ImageView iconFavoris;

    private LinearLayout annonceur;

    private Annonce annonce;
    public DetailAnnoncePart() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_detail_annonce, container, false);
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

        Gson gson = new Gson();
        annonce = gson.fromJson(getActivity().getIntent().getStringExtra("Annonce"), Annonce.class);

        if (getActivity().getIntent().getBooleanExtra("FAV",false)) {
            this.iconFavoris.setImageResource(R.drawable.baseline_favorite_black_24dp);
        }


        titre.setText(annonce.getTitre());
        description.setText(annonce.getDescription());
        prix.setText(String.valueOf(annonce.getPrix()));
        département.setText(annonce.getDepartement());
        ville.setText(annonce.getVille());
        String url = "http://172.16.5.209:8080/LeMauvaisCoin/api/User/UserById/" + annonce.getAnnonceur();
         String reponse = null;
        try {
            reponse = getRequest(url);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        User user = gson.fromJson(reponse, User.class);
        if(user.getImage() !=null) {
            byte[] myImage = Base64.getDecoder().decode(user.getImage().getBytes(StandardCharsets.UTF_8));
            Bitmap bmp = BitmapFactory.decodeByteArray(myImage, 0, myImage.length);
            profileAnnonceur.setImageBitmap(bmp);
        }

        ArrayList<Bitmap> images = new ArrayList<>();
        for(String s : annonce.getimage()) {
            byte[] myImage = Base64.getDecoder().decode(s.getBytes(StandardCharsets.UTF_8));
            Bitmap bmp = BitmapFactory.decodeByteArray(myImage, 0, myImage.length);
            images.add(bmp);
        }
        ImageFragmentStateAdaptater adapter = new ImageFragmentStateAdaptater(getActivity(),images);
        this.viewPager2image.setAdapter(adapter);


         url = "http://172.16.5.209:8080/LeMauvaisCoin/api/annonce/vu/" + annonce.getId_annonce();
        try {
            System.out.println("ICI");
            getRequest(url);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        annonceur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://172.16.5.209:8080/LeMauvaisCoin/api/message/PutConversation";
                UserControlers userControlers = new ViewModelProvider(getActivity()).get(UserControlers.class);
                userControlers.init(getContext());
                Conversation c = new Conversation(annonce.getId_annonce(),userControlers.getPlanning().get(0).getId_user(),annonce.getAnnonceur());
                Gson gson = new Gson();
                String json = gson.toJson(c);
                String result = null;
                try {
                    result = PutRequest(url,json);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Intent intention= new Intent(getContext(), ActivityConversation.class);
                intention.putExtra("Conversation",result);
                startActivity(intention);

            }
        });



        this.iconFavoris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                iconFavoris();

            }
        });


        return root;
    }

    public void iconFavoris(){
        String url = "http://172.16.5.209:8080/LeMauvaisCoin/api/annonce/Favoris";
        UserControlers userControlers = new ViewModelProvider(getActivity()).get(UserControlers.class);
        userControlers.init(getContext());
        ArrayList<String> params = new ArrayList<>();
        params.add(String.valueOf(userControlers.getPlanning().get(0).getId_user()));
        params.add(String.valueOf(annonce.getId_annonce()));
        Gson gson = new Gson();
        String query = gson.toJson(params);
        try {
            PostRequest(url,query);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(this.iconFavoris.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.baseline_favorite_black_24dp).getConstantState())){
            this.iconFavoris.setImageResource(R.drawable.baseline_favorite_border_black_24dp);
        }else {
            this.iconFavoris.setImageResource(R.drawable.baseline_favorite_black_24dp);
        }

    }

    public String getRequest(String url) throws IOException {
        final String[] result = {""};
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try  {
                    result[0]= IOUtils.toString(new InputStreamReader(new BufferedInputStream(new URL(url).openConnection().getInputStream()), Charsets.UTF_8));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return result[0];
    }

    public void PostRequest(String url,String param) throws IOException {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try  {
                    URL adress = null;
                    try {
                        adress = new URL(url);
                        HttpURLConnection httpCon = (HttpURLConnection) adress.openConnection();
                        httpCon.setDoOutput(true);
                        httpCon.setRequestMethod("POST");
                        httpCon.setRequestProperty("Content-Type", "application/json");
                        httpCon.setRequestProperty("Accept", "application/json");
                        OutputStreamWriter out = new OutputStreamWriter (
                                httpCon.getOutputStream());
                        out.write(param);
                        out.close();
                        httpCon.getInputStream();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException{
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }

    public String PutRequest(String url,String json) throws IOException {
        final String[] result = {""};
        Thread thread = new Thread(new Runnable() {
            URL adresse = null;

            @Override
            public void run() {
                try  {
                    try {
                        adresse = new URL(url);
                        HttpURLConnection httpCon = (HttpURLConnection) adresse.openConnection();
                        httpCon.setDoOutput(true);
                        httpCon.setRequestMethod("PUT");
                        httpCon.setRequestProperty("Content-Type", "application/json");
                        httpCon.setRequestProperty("Accept", "application/json");
                        OutputStreamWriter out = new OutputStreamWriter (
                                httpCon.getOutputStream());
                        out.write(json);
                        out.flush();
                        String builtResponse = "";
                        String line ="";
                        BufferedReader reader = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
                        while ((line = reader.readLine()) != null) {
                            result[0] += line;
                        }
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return result[0];
    }
}