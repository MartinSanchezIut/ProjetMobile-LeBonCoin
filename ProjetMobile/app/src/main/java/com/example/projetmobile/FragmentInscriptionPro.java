package com.example.projetmobile;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
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
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.concurrent.ExecutionException;

import static android.app.Activity.RESULT_OK;


public class FragmentInscriptionPro extends Fragment {
    private TextInputLayout Eemail;
    private TextInputLayout Epseudo;
    private TextInputLayout Enom;
    private TextInputLayout Enumero;
    private TextInputLayout Eprenom;
    private TextInputLayout Epassword;
    private TextInputLayout Eentreprise;
    private Button Pro;
    private Button Part;
    private Button valider;

    private String Image = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inscription_pro, container, false);
        Eemail = (TextInputLayout)view.findViewById(R.id.idemail);
        Epseudo = (TextInputLayout)view.findViewById(R.id.idpseudo);
        Enom = (TextInputLayout)view.findViewById(R.id.idNom);
        Eprenom = (TextInputLayout)view.findViewById(R.id.idPrenom);
        Enumero = (TextInputLayout)view.findViewById(R.id.idnum);
        Epassword = (TextInputLayout)view.findViewById(R.id.idpassword);
        Eentreprise = (TextInputLayout)view.findViewById(R.id.identreprise);

        valider = (Button)view.findViewById(R.id.valider);
        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boitedialogue();
            }
        });

        return view;
    }

    public void inscription(){
        String url ="http://192.168.1.25:8080/LeMauvaisCoin/api/User/InscriptionAnnonceurPro";
        MyAsyncInscription myAsyncTasks = new MyAsyncInscription();
        try {
            Gson gson = new Gson();
            Annonceur_pro pro = new Annonceur_pro("AnnonceurPro",Epseudo.getEditText().getText().toString(),Image,Enom.getEditText().getText().toString(),Eprenom.getEditText().getText().toString(),Eemail.getEditText().getText().toString(),Enumero.getEditText().getText().toString(),Epassword.getEditText().getText().toString(),Eentreprise.getEditText().getText().toString());
            String json = gson.toJson(pro);
            String reponse = myAsyncTasks.execute(url,json).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(getActivity(),Connexion.class);
        startActivity(intent);

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
            byte[] b = baos.toByteArray();
            this.Image = Base64.getEncoder().encodeToString(baos.toByteArray());
            inscription();
        }
    }

}