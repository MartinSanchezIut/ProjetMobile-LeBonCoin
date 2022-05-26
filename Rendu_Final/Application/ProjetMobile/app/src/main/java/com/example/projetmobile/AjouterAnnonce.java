package com.example.projetmobile;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.google.android.material.divider.MaterialDividerItemDecoration;

import static java.security.AccessController.getContext;

public class AjouterAnnonce extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_ajouterannonce);

    }
}