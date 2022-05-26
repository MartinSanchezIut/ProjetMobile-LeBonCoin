package com.example.projetmobile;

import android.content.Intent;
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
import com.example.projetmobile.Model.Conversation;
import com.example.projetmobile.Model.User;
import java.text.SimpleDateFormat;

import com.example.projetmobile.Model.serveur;
import com.github.mikephil.charting.components.Legend;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.*;
import java.util.stream.Collectors;


public class DetailAnnoncePro extends Fragment {

    private ViewPager2 viewPager2image;
    private TextView titre;
    private TextView description;
    private TextView prix;
    private TextView département;
    private TextView ville;

    private TextView total;

    private TextView aujourdhui;

    private TextView pseudo;
    private ImageView profileAnnonceur;
    private ImageView iconFavoris;

    private ImageView supprimer;
    private ImageView modifier;

    private LinearLayout annonceur;

    private LinearLayout linearglobal;

    private LinearLayout fraude;
    private TextView fraudemotif;
    private Annonce annonce;

    private BarChart chart;

    public DetailAnnoncePro() {

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_detail_annonce_pro, container, false);
        this.viewPager2image = root.findViewById(R.id.viewpager2);
        this.titre = root.findViewById(R.id.Titre);
        this.description = root.findViewById(R.id.description);
        this.prix = root.findViewById(R.id.Prix);
        this.département = root.findViewById(R.id.departement);
        this.ville = root.findViewById(R.id.ville);
        this.pseudo = root.findViewById(R.id.pseudo);
        this.profileAnnonceur = root.findViewById(R.id.profil);
        this.annonceur = root.findViewById(R.id.messageAnnoneur);

        this.total = root.findViewById(R.id.total);

        this.aujourdhui = root.findViewById(R.id.aujourdhui);

        this.linearglobal = root.findViewById(R.id.linearglobal);
        this.fraude = root.findViewById(R.id.linearfraude);
        this.fraudemotif = root.findViewById(R.id.fraudemotif);




        this.iconFavoris = root.findViewById(R.id.iconfavoris);
        this.supprimer = root.findViewById(R.id.Suprimer);
        this.modifier = root.findViewById(R.id.Modifier);
        BarChart barChart = (BarChart) root.findViewById(R.id.barchart);


        Gson gson = new Gson();
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String reponse = bundle.getString("Annonce", null);
            annonce = gson.fromJson(reponse,Annonce.class);
        }

        if (bundle.getBoolean("FAV")) {
            this.iconFavoris.setImageResource(R.drawable.baseline_favorite_black_24dp);
        }

        if(!annonce.isIsfraude()){
            fraude.removeAllViews();
        }else{
            fraudemotif.setText(annonce.getMotifFraude());
            linearglobal.setBackgroundColor(Color.RED);
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
                String url = "http://172.16.5.209:8080/LeMauvaisCoin/api/annonce/deleteannonce/" + userControlers.getPlanning().get(0).getId_user()+"/"+annonce.getId_annonce();

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

        String reponse = "";
        Map<String,Integer> res = null;
        try {
            serveur s = new serveur("annonce/NbVuAnnonce/"+annonce.getId_annonce());
            reponse=s.getRequest();
            System.out.println(reponse);
             res = gson.fromJson(reponse,new TypeToken<Map<String,Integer>>(){}.getType());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Map<String,Integer> x
                = res.entrySet()
                .stream()
                .sorted((i1, i2)
                        -> i1.getKey().compareTo(
                        i2.getKey()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (z1, z2) -> z1, LinkedHashMap::new));


        ArrayList<BarEntry> NoOfEmp = new ArrayList();

        int i = 0;
        int toto = 0;
        for (Integer value : x.values()) {
            toto = toto + Integer.valueOf(value);
        }


        total.setText(String.valueOf(toto));
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String date = dtf.format(LocalDateTime.now());
        Set<String> phoneNumbers = x.keySet();

        for (String phoneNumber : phoneNumbers) {
            if(phoneNumber.equals(date)){
                aujourdhui.setText(String.valueOf(x.get(phoneNumber)));


            }else{
                aujourdhui.setText("0");
            }

        }
        /*
        ArrayList<String> year = new ArrayList();
        String d = "";
        int m = 0;
        for (String key : x.keySet()) {
            if(!d.equals("")){

                do{
                    System.out.println("VAL1" + d);
                    System.out.println("VAL2" + key);
                    if(!d.equals(key)){
                        year.add(d);
                        NoOfEmp.add(new BarEntry(0, m));
                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                        //create instance of the Calendar class and set the date to the given date
                        Calendar cal = Calendar.getInstance();
                        try{
                            cal.setTime(formatter.parse(d));
                        }catch(ParseException e){
                            e.printStackTrace();
                        }

                        // use add() method to add the days to the given date
                        cal.add(Calendar.DAY_OF_MONTH, 1);
                         d = formatter.format(cal.getTime());

                    }else{
                        year.add(d);
                        NoOfEmp.add(new BarEntry(Float.valueOf(x.get(key)), m));
                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                        //create instance of the Calendar class and set the date to the given date
                        Calendar cal = Calendar.getInstance();
                        try{
                            cal.setTime(formatter.parse(d));
                        }catch(ParseException e){
                            e.printStackTrace();
                        }

                        // use add() method to add the days to the given date
                        cal.add(Calendar.DAY_OF_MONTH, 1);
                        d = formatter.format(cal.getTime());
                    }
                    ++m;

                   // year.add(key);
                }
                while(!key.equals(d));

            }
            else{
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                //create instance of the Calendar class and set the date to the given date
                Calendar cal = Calendar.getInstance();
                try{
                    cal.setTime(formatter.parse(key));
                }catch(ParseException e){
                    e.printStackTrace();
                }

                // use add() method to add the days to the given date
                cal.add(Calendar.DAY_OF_MONTH, 1);
                d = formatter.format(cal.getTime());
            }

        }

         */
        ArrayList<String> year = new ArrayList();
        String d = "";
        int m = 0;
        for (String key : x.keySet()) {
            System.out.println("VAL 1 = " + key);
            System.out.println("VAL 2 = " + d);
            if(!d.equals("")){
                   while(!d.equals(key)){
                       year.add(d);
                       NoOfEmp.add(new BarEntry(0, m));
                       ++m;
                       SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                       Calendar cal = Calendar.getInstance();
                       try{
                           cal.setTime(formatter.parse(d));
                       }catch(ParseException e){
                           e.printStackTrace();
                       }
                       cal.add(Calendar.DAY_OF_MONTH, 1);
                       d = formatter.format(cal.getTime());
                       System.out.println("VALEUR d = " + d );

                   }
                year.add(key);
                NoOfEmp.add(new BarEntry(x.get(d), m));
                ++m;
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                Calendar cal = Calendar.getInstance();
                try{
                    cal.setTime(formatter.parse(key));
                }catch(ParseException e){
                    e.printStackTrace();
                }
                cal.add(Calendar.DAY_OF_MONTH, 1);
                d = formatter.format(cal.getTime());


            }
            else{
                year.add(key);
                NoOfEmp.add(new BarEntry(x.get(key), m));
                ++m;
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                Calendar cal = Calendar.getInstance();
                try{
                    cal.setTime(formatter.parse(key));
                }catch(ParseException e){
                    e.printStackTrace();
                }
                cal.add(Calendar.DAY_OF_MONTH, 1);
                d = formatter.format(cal.getTime());
            }

        }
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        DateTimeFormatter l = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            while(!year.get(year.size()-1).equals(l.format(LocalDateTime.now()))){
                year.add(d);
                NoOfEmp.add(new BarEntry(0, m));
                ++m;
                 formatter = new SimpleDateFormat("dd/MM/yyyy");
                Calendar cal = Calendar.getInstance();
                try{
                    cal.setTime(formatter.parse(d));
                }catch(ParseException e){
                    e.printStackTrace();
                }
                cal.add(Calendar.DAY_OF_MONTH, 1);
                d = formatter.format(cal.getTime());
                System.out.println("VALEUR d = " + d );

            }



        BarDataSet bardataset = new BarDataSet(NoOfEmp, "");
        barChart.animateY(500);
        barChart.getAxisLeft().setTextColor(Color.rgb(255, 255, 83));
        barChart.getAxisRight().setEnabled(false);
        barChart.getXAxis().setTextColor(Color.rgb(241, 13, 13 ));
        BarData data = new BarData(year, bardataset);
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        bardataset.setValueTextColor(Color.rgb(255, 255, 83));
        bardataset.setValueTextSize(10);
        barChart.getLegend().setEnabled(false);
        barChart.setDescription("");

        barChart.setData(data);





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