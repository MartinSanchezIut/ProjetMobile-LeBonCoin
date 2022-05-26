package com.example.projetmobile;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;
import com.example.projetmobile.BDD.Repository.AppDataBase;
import com.example.projetmobile.BDD.Repository.UserDao;
import com.example.projetmobile.BDD.models.Controllers.UserControlers;
import com.example.projetmobile.Model.Annonce;
import com.example.projetmobile.Model.serveur;
import com.google.android.gms.ads.*;
import com.google.android.gms.ads.admanager.AdManagerAdRequest;
import com.google.android.gms.ads.admanager.AdManagerInterstitialAd;
import com.google.android.gms.ads.admanager.AdManagerInterstitialAdLoadCallback;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Acceuille extends AppCompatActivity {
    private Bundle extras;
    private GridView listView;
    private TextView Tuser;
    private TextInputLayout recherche;

    private TextInputLayout rechercheTitre;
    private ArrayList<Annonce> annonce;
    AnnonceRecentAdaptateur myAdapter;
    private static final long GAME_LENGTH_MILLISECONDS = 3000;
    private static final String AD_UNIT_ID = "/6499/example/interstitial";
    private static final String TAG = "MyActivity";
    private AdView mAdView;
    static InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_acceuil);
        recherche = (TextInputLayout) findViewById(R.id.idrecherche);
        extras = getIntent().getExtras();
        listView = (GridView) findViewById(R.id.gridView);
        Gson gson = new Gson();
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        serveur s = new serveur("annonce/Recent");
        String reponse = null;
        try {
            reponse = s.getRequest();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (!reponse.equals("")) {
            annonce = gson.fromJson(reponse, new TypeToken<ArrayList<Annonce>>() {
            }.getType());
            myAdapter = new AnnonceRecentAdaptateur(Acceuille.this, annonce);
            listView.setAdapter(myAdapter);
        }
        recherche.getEditText().setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    System.out.println("ICI");
                    serveur s = new serveur("annonce/RechercheTitre");
                    String reponse = null;
                    try {
                        reponse = s.PostRequest(recherche.getEditText().getText().toString());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    if (!reponse.equals("")) {
                        annonce = gson.fromJson(reponse, new TypeToken<ArrayList<Annonce>>() {
                        }.getType());
                        myAdapter = new AnnonceRecentAdaptateur(Acceuille.this, annonce);
                        listView.setAdapter(myAdapter);
                    }

                    return true;
                }
                return false;
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                final int REQUEST_CODE = 20;
                Intent intention = new Intent(Acceuille.this, DetailAnnonce.class);
                Gson gson = new Gson();
                FileOutputStream fOut = null;
                String myJson = gson.toJson(annonce.get(position));
                intention.putExtra("Annonce", myJson);
                intention.putExtra("FAV", myAdapter.getFav().get(position));
                startActivity(intention);
            }
        });
        Random random = new Random();

        int value = random.nextInt(0 + 4) + 0;

        if(value == 0){
            generateAds();
        }
    }


    public void filtre(View view) {
        Intent intention = new Intent(Acceuille.this, Recherche.class);
        startActivity(intention);
    }
    public void generateAds(){

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });


        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {

                        Toast.makeText(Acceuille.this,"Ad Loaded", Toast.LENGTH_SHORT).show();
                        interstitialAd.show(Acceuille.this);
                        interstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                                super.onAdFailedToShowFullScreenContent(adError);
                            }

                            @Override
                            public void onAdShowedFullScreenContent() {
                                super.onAdShowedFullScreenContent();
                            }

                            @Override
                            public void onAdDismissedFullScreenContent() {
                                super.onAdDismissedFullScreenContent();
                            }

                            @Override
                            public void onAdImpression() {
                                super.onAdImpression();
                            }

                            @Override
                            public void onAdClicked() {
                                super.onAdClicked();
                            }
                        });
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    }
                });

    }

}
