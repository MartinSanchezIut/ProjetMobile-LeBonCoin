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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.github.mikephil.charting.charts.BarChart;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;


public class DetailAnnoncePro extends Fragment {

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
        this.iconFavoris = root.findViewById(R.id.iconfavoris);
        this.supprimer = root.findViewById(R.id.Suprimer);
        this.modifier = root.findViewById(R.id.Modifier);
        BarChart barChart = (BarChart) findViewById(R.id.barchart);


        Gson gson = new Gson();
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String reponse = bundle.getString("Annonce", null);
            System.out.println("REPONSE " + reponse);
            annonce = gson.fromJson(reponse,Annonce.class);
            System.out.println(annonce.getTitre());
        }

        if (bundle.getBoolean("FAV")) {
            this.iconFavoris.setImageResource(R.drawable.baseline_favorite_black_24dp);
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
                    DeleteRequest(url);
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

        String url = "http://172.16.5.209:8080/LeMauvaisCoin/api/annonce/NbVuAnnonce/"+annonce.getId_annonce();
        String reponse = "";
        Map<String,Integer> res = null;
        try {
            reponse=getRequest(url);
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


        float start = 1f;
        ArrayList<BarEntry> values = new ArrayList<>();
        for (int i = (int) start; i < start + count; i++) {
            float val = (float) (Math.random() * (range + 1));
            if (Math.random() * 100 < 25) {
                values.add(new BarEntry(i, val, getResources().getDrawable(R.drawable.star)));
            } else {
                values.add(new BarEntry(i, val));
            }
        }
        chart.getDescription().setEnabled(false);
        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        chart.setMaxVisibleValueCount(60);
        // scaling can now only be done on x- and y-axis separately
        chart.setPinchZoom(false);
        chart.setDrawGridBackground(false);
        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setFormSize(9f);
        l.setTextSize(11f);
        l.setXEntrySpace(4f)
        BarDataSet set1;
        if (chart.getData() != null &&
                chart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) chart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(values, "The year 2017");
            set1.setDrawIcons(false);
            int startColor1 = ContextCompat.getColor(this, android.R.color.holo_orange_light);
            int startColor2 = ContextCompat.getColor(this, android.R.color.holo_blue_light);
            int startColor3 = ContextCompat.getColor(this, android.R.color.holo_orange_light);
            int startColor4 = ContextCompat.getColor(this, android.R.color.holo_green_light);
            int startColor5 = ContextCompat.getColor(this, android.R.color.holo_red_light);
            int endColor1 = ContextCompat.getColor(this, android.R.color.holo_blue_dark);
            int endColor2 = ContextCompat.getColor(this, android.R.color.holo_purple);
            int endColor3 = ContextCompat.getColor(this, android.R.color.holo_green_dark);
            int endColor4 = ContextCompat.getColor(this, android.R.color.holo_red_dark);
            int endColor5 = ContextCompat.getColor(this, android.R.color.holo_orange_dark);
            List<GradientColor> gradientFills = new ArrayList<>();
            gradientFills.add(new GradientColor(startColor1, endColor1));
            gradientFills.add(new GradientColor(startColor2, endColor2));
            gradientFills.add(new GradientColor(startColor3, endColor3));
            gradientFills.add(new GradientColor(startColor4, endColor4));
            gradientFills.add(new GradientColor(startColor5, endColor5));
            set1.setGradientColors(gradientFills);
            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);
            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setBarWidth(0.9f);
            chart.setData(data);
        return root;
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

    public void DeleteRequest(String url) throws IOException {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try  {
                    URL adress = null;
                    try {
                        adress = new URL(url);
                        HttpURLConnection httpCon = (HttpURLConnection) adress.openConnection();
                        httpCon.setDoOutput(true);
                        httpCon.setRequestMethod("DELETE");
                        httpCon.setRequestProperty("Content-Type", "application/json");
                        httpCon.setRequestProperty("Accept", "application/json");
                        OutputStreamWriter out = new OutputStreamWriter (
                                httpCon.getOutputStream());
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
}