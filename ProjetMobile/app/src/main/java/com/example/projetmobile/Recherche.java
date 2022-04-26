package com.example.projetmobile;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Recherche extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_recherche);
    }
}