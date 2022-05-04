package com.example.projetmobile;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentTransaction;
import com.example.projetmobile.Model.Recherche;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;


public class FragmentRechercheAcceuil extends Fragment {


    private LinearLayout categorie;
    private LinearLayout localisation;

    private TextInputLayout Prix1;

    private TextInputLayout Prix2;

    private CheckBox particulier;

    private CheckBox profesionnel;

    private Button rechercher;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_recherche_acceuil, container, false);

        categorie = root.findViewById(R.id.linearLayoutcatégorie);
        localisation = root.findViewById(R.id.linearLayoutlocalisation);
        Prix1 = root.findViewById(R.id.Prix1);
        Prix2 = root.findViewById(R.id.Prix2);
        particulier = root.findViewById(R.id.checkboxparticulier);
        profesionnel = root.findViewById(R.id.checkboxpro);
        rechercher = root.findViewById(R.id.idRechercher);

        String file_name = getActivity().getFilesDir() + "/" + "recherche.json";
        File t = new File(file_name);
        InputStream input = null;
        Recherche r = new Recherche(null, null, null, null, null, null, null, null);
        try {
            Gson gson = new Gson();
            if (t.exists()) {
                input = getContext().openFileInput("recherche.json");
                byte[] buffer = new byte[input.available()];
                input.read(buffer);
                input.close();
                String text = new String(buffer);
                r = gson.fromJson(text, Recherche.class);
                if(r.getPrix1() != null) {
                    Prix1.getEditText().setText(String.valueOf(r.getPrix1()));
                }else{
                    Prix1.getEditText().setText("");
                }
                if(r.getPrix1() != null) {
                    Prix2.getEditText().setText(String.valueOf(r.getPrix2()));
                }else{
                    Prix2.getEditText().setText("");
                }


                if(r.isParticulier() == null) {
                    particulier.setChecked(false);
                }else{
                    particulier.setChecked(r.isParticulier());
                }
                if(r.isProfessionel() == null) {
                    profesionnel.setChecked(false);
                }else{
                    profesionnel.setChecked(r.isProfessionel());
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(r.getCategorie());
        System.out.println(r.getFiltre());
        System.out.println(r.getDepartement());
        System.out.println(r.getPrix1());
        System.out.println(r.getPrix2());


        categorie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String file_name = getActivity().getFilesDir() + "/" + "recherche.json";
                File t = new File(file_name);
                InputStream input = null;
                Recherche r = new Recherche(null, null, null, null, null, null, null, null);
                try {
                    Gson gson = new Gson();
                    if (t.exists()) {
                        input = getContext().openFileInput("recherche.json");
                        byte[] buffer = new byte[input.available()];
                        input.read(buffer);
                        input.close();
                        String text = new String(buffer);
                        r = gson.fromJson(text, Recherche.class);
                    }
                    if(!Prix1.getEditText().getText().toString().equals("")) {
                        r.setPrix1(Float.parseFloat(Prix1.getEditText().getText().toString()));
                    }else{
                        r.setPrix1(null);
                    }
                    if(!Prix2.getEditText().getText().toString().equals("")) {
                    r.setPrix2(Float.parseFloat(Prix2.getEditText().getText().toString()));
                    }else{
                        r.setPrix1(null);
                    }
                    r.setParticulier(particulier.isChecked());
                    r.setProfessionel(profesionnel.isChecked());
                    FileOutputStream fOut = getContext().openFileOutput("recherche.json", 0);

                    String json = gson.toJson(r);
                    System.out.println("ICI   " + json);
                    fOut.write(json.getBytes());
                    fOut.close();
                    FragmentCategorieRecherche fragment = new FragmentCategorieRecherche();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                    transaction.replace(R.id.fragmentContainerView5, fragment);
                    transaction.commit();

                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        localisation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String file_name = getActivity().getFilesDir() + "/" + "recherche.json";
                File t = new File(file_name);
                InputStream input = null;
                Recherche r = new Recherche(null, null, null, null, null, null, null, null);
                try {
                    Gson gson = new Gson();
                    if (t.exists()) {
                        input = getContext().openFileInput("recherche.json");
                        byte[] buffer = new byte[input.available()];
                        input.read(buffer);
                        input.close();
                        String text = new String(buffer);
                        r = gson.fromJson(text, Recherche.class);
                    }
                    if(!Prix1.getEditText().getText().toString().equals("")) {
                        r.setPrix1(Float.parseFloat(Prix1.getEditText().getText().toString()));
                    }else{
                        r.setPrix1(null);
                    }
                    if(!Prix2.getEditText().getText().toString().equals("")) {
                        r.setPrix2(Float.parseFloat(Prix2.getEditText().getText().toString()));
                    }else{
                        r.setPrix1(null);
                    }
                    r.setParticulier(particulier.isChecked());
                    r.setProfessionel(profesionnel.isChecked());
                    FileOutputStream fOut = getContext().openFileOutput("recherche.json", 0);

                    String json = gson.toJson(r);
                    System.out.println("ICI   " + json);
                    fOut.write(json.getBytes());
                    fOut.close();
                    Bundle args = new Bundle();
                    FragmentLocalisationRecherche fragment = new FragmentLocalisationRecherche();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                    transaction.replace(R.id.fragmentContainerView5, fragment);
                    transaction.commit();

                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
            });


        rechercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String file_name = getActivity().getFilesDir() + "/" + "recherche.json";
                File t = new File(file_name);
                InputStream input = null;
                Recherche r = new Recherche(null, null, null, null, null, null, null, null);
                try {
                    Gson gson = new Gson();
                    if (t.exists()) {
                        input = getContext().openFileInput("recherche.json");
                        byte[] buffer = new byte[input.available()];
                        input.read(buffer);
                        input.close();
                        String text = new String(buffer);
                        r = gson.fromJson(text, Recherche.class);
                    }
                    if(!Prix1.getEditText().getText().toString().equals("")) {
                        r.setPrix1(Float.parseFloat(Prix1.getEditText().getText().toString()));
                    }else{
                        r.setPrix1(null);
                    }
                    if(!Prix2.getEditText().getText().toString().equals("")) {
                        r.setPrix2(Float.parseFloat(Prix2.getEditText().getText().toString()));
                    }else{
                        r.setPrix1(null);
                    }
                    r.setParticulier(particulier.isChecked());
                    r.setProfessionel(profesionnel.isChecked());

                    String json = gson.toJson(r);
                    System.out.println("ICI   " + json);
                    t.delete();



                    String url = "http://172.16.5.209:8080/LeMauvaisCoin/api/annonce/Recherche" ;
                    String reponse = null;
                        reponse = PostRequest(url,json);

                    System.out.println(reponse);

                    Bundle args = new Bundle();
                    FragmentAffichageRecherche fragment = new FragmentAffichageRecherche();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    args.putString("ANNONCES", reponse);
                    fragment.setArguments(args);
                    transaction.replace(R.id.fragmentContainerView5, fragment);
                    transaction.commit();



                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });

        return root;

    }

    public String PostRequest(String url,String json) throws IOException {
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
                        httpCon.setRequestMethod("POST");
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