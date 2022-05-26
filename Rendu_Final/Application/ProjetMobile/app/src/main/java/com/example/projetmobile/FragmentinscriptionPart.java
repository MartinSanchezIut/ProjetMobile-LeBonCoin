package com.example.projetmobile;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
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
import com.example.projetmobile.Model.serveur;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.concurrent.ExecutionException;

import static android.app.Activity.RESULT_OK;


public class FragmentinscriptionPart extends Fragment {
    private TextInputLayout Eemail;
    private TextInputLayout Epseudo;
    private TextInputLayout Enom;
    private TextInputLayout Enumero;
    private TextInputLayout Eprenom;
    private TextInputLayout Epassword;

    private String Image = null;
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

                boitedialogue();


            }
        });

        return rooter;
    }

    public void boitedialogue(){
        // Create the object of
        // AlertDialog Builder class
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setMessage("Voulez vous une photo de profil ?");

        builder.setTitle("Photo de profil");

        builder.setCancelable(false);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                takePhoto();
                            }
                        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                inscription();
                            }
                        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    public void takePhoto(){
        final int REQUEST_IMAGE_CAPTURE = 1;
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        } catch (ActivityNotFoundException e) {
            // display error state to the user
        }
    }

    public void inscription(){
        Gson gson = new Gson();
        Annonceur_Particulier pro = new Annonceur_Particulier("AnnonceurPart",Epseudo.getEditText().getText().toString(),this.Image,Enom.getEditText().getText().toString(),Eprenom.getEditText().getText().toString(),Eemail.getEditText().getText().toString(),Enumero.getEditText().getText().toString(),Epassword.getEditText().getText().toString());
        String json = gson.toJson(pro);
        serveur s = new serveur("User/InscriptionAnnonceurPart");
        s.PutRequest(json);

        Intent intent = new Intent(getActivity(),Connexion.class);
        startActivity(intent);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
            this.Image = Base64.getEncoder().encodeToString(baos.toByteArray());
            inscription();
        }
    }




}