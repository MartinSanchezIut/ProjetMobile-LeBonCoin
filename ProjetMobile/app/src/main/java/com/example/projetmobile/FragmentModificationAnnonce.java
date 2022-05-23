package com.example.projetmobile;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;
import com.example.projetmobile.BDD.Repository.AppDataBase;
import com.example.projetmobile.BDD.Repository.UserDao;
import com.example.projetmobile.BDD.models.Controllers.UserControlers;
import com.example.projetmobile.Model.Annonce;
import com.example.projetmobile.Model.Message;
import com.example.projetmobile.Model.serveur;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;


public class FragmentModificationAnnonce extends Fragment {

    private Annonce annonce;

    private TextInputLayout Titre;

    private TextInputLayout Description;

    private TextInputLayout Prix;

    private ImageView image1;

    private ImageView image2;


    private ImageView image3;

    private ImageView image4;
    private Intent intent=new Intent(Intent.ACTION_PICK);

    private ImageView image5;

    private ImageView image6;

    private TextInputLayout Ville;

    private ArrayList<String> image = new ArrayList<>();

    private TextInputLayout Contact;

    private Button Modifier;
    private LinearLayout lin;

    private LinearLayout departement;

    public FragmentModificationAnnonce() {
        // Required empty public constructor
    }


    @SuppressLint("WrongThread")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_modification_annonce, container, false);

        Gson gson = new Gson();
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String reponse = bundle.getString("Annonce", null);
            annonce = gson.fromJson(reponse, Annonce.class);
        }
        Titre = (TextInputLayout)root.findViewById(R.id.Titre);
        Description = (TextInputLayout)root.findViewById(R.id.Description);
        Prix = (TextInputLayout)root.findViewById(R.id.Prix);
        Ville = (TextInputLayout)root.findViewById(R.id.Ville);
        Contact = (TextInputLayout)root.findViewById(R.id.Contact);

        intent.setType("image/*");
        String[] mimeTypes = {"image/jpeg", "image/png","image/jpg"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES,mimeTypes);
        image1 = (ImageView) root.findViewById(R.id.image1);
        image1();
        image2 = (ImageView) root.findViewById(R.id.image2);
        image2();
        image3 = (ImageView) root.findViewById(R.id.image3);
        image3();
        image4 = (ImageView) root.findViewById(R.id.image4);
        image4();
        image5 = (ImageView) root.findViewById(R.id.image5);
        image5();
        image6 = (ImageView) root.findViewById(R.id.image6);
        image6();



        Modifier = (Button)root.findViewById(R.id.Deposer);
        Modifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String file_name = getActivity().getFilesDir() + "/" + "Modification.json";
                File t = new File(file_name);
                InputStream input = null;
                if (t.exists()) {
                    try {
                        input = getContext().openFileInput("Modification.json");
                        byte[] buffer = new byte[input.available()];
                        input.read(buffer);
                        input.close();
                        String text = new String(buffer);
                        Gson gson = new Gson();
                        annonce = gson.fromJson(text, Annonce.class);
                        annonce.setTitre(Titre.getEditText().getText().toString());
                        annonce.setDescription(Description.getEditText().getText().toString());
                        annonce.setPrix(Float.parseFloat(Prix.getEditText().getText().toString()));
                        annonce.setVille(Ville.getEditText().getText().toString());
                        annonce.setContact(Contact.getEditText().getText().toString());
                        UserControlers userControlers = new ViewModelProvider(getActivity()).get(UserControlers.class);
                        userControlers.init(getContext());
                        annonce.setId_annonceur(userControlers.getPlanning().get(0).getId_user());
                        Map<String, Integer> d = new HashMap<>();
                        annonce.setNbvues(d);
                        List<Message> m = new ArrayList<>();
                        annonce.setList_messages(m);
                        String json = gson.toJson(annonce);
                        serveur s = new serveur("annonce/PostAnnonce");
                        s.PutRequest(json);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    t.delete();
                }
                Intent intent = new Intent(getActivity(),Acceuille.class);
                startActivity(intent);
            }
        });


        String file_name = getActivity().getFilesDir() + "/" + "Modification.json";
        File t = new File(file_name);
        InputStream input = null;
        if (t.exists()) {
            try {
                input = getContext().openFileInput("Modification.json");
                byte[] buffer = new byte[input.available()];
                input.read(buffer);
                input.close();
                String text = new String(buffer);
                System.out.println("TEXTE "+text);
                annonce = gson.fromJson(text, Annonce.class);
                this.image = annonce.getimage();
                if(annonce.getTitre()!=null)
                    Titre.getEditText().setText(annonce.getTitre());
                if(annonce.getDescription()!=null)
                    Description.getEditText().setText(annonce.getDescription());
                if(annonce.getPrix()!= (Float)null)
                    Prix.getEditText().setText(String.valueOf(annonce.getPrix()));
                if(annonce.getville()!=null)
                    Ville.getEditText().setText(annonce.getville());
                if(annonce.getContact()!=null)
                    Contact.getEditText().setText(annonce.getContact());
                if(annonce.getContact()!=null)
                    Contact.getEditText().setText(annonce.getContact());
                if(annonce.getimage()!=null) {
                    ArrayList<String> images = new ArrayList<>();
                    for (int j = 0; j < annonce.getimage().size(); ++j) {
                        byte[] myImage = Base64.getDecoder().decode(annonce.getimage().get(j).getBytes(StandardCharsets.UTF_8));
                        Bitmap bmp = BitmapFactory.decodeByteArray(myImage, 0, myImage.length);
                        ByteArrayOutputStream baos=new ByteArrayOutputStream();
                        bmp.compress(Bitmap.CompressFormat.PNG,100, baos);
                        byte [] b=baos.toByteArray();
                        images.add(Base64.getEncoder().encodeToString(b));
                    }
                    annonce.setPic_bytes(images);
                    for (int i = 0; i < annonce.getimage().size(); ++i) {
                        byte[] myImage = Base64.getDecoder().decode(annonce.getimage().get(i).getBytes(StandardCharsets.UTF_8));
                        Bitmap bmp = BitmapFactory.decodeByteArray(myImage, 0, myImage.length);
                        switch (i) {
                            case 0:
                                image1.setImageBitmap(bmp);
                                break;
                            case 1:
                                image2.setImageBitmap(bmp);
                                break;
                            case 2:
                                image3.setImageBitmap(bmp);
                                break;
                            case 3:
                                image4.setImageBitmap(bmp);
                                break;
                            case 4:
                                image5.setImageBitmap(bmp);
                                break;
                            case 5:
                                image6.setImageBitmap(bmp);
                                break;
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        lin = (LinearLayout)root.findViewById(R.id.linearLayoutcatégorie);
        this.Titre.getEditText().getText().toString();

        lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create new fragment and transaction
                Fragment newFragment = new FragmentCategorie();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                Gson gson = new Gson();
                FileOutputStream fOut = null;
                try {
                    fOut = getContext().openFileOutput("Modification.json", 0);
                    AppDataBase db = Room.databaseBuilder(getContext(), AppDataBase.class, "UserBDD").build();
                    UserDao userDao = db.userDao();
//                    List<UserBDD> b = userDao.getAll();

                    if (!Prix.getEditText().getText().toString().equals("")) {
                        annonce = new Annonce( annonce.getId_annonce(),Titre.getEditText().getText().toString(), Description.getEditText().getText().toString(), Float.parseFloat(Prix.getEditText().getText().toString()), null, Ville.getEditText().getText().toString(), annonce.getDepartement(), 0L, annonce.getimage(), null, Contact.getEditText().getText().toString(), annonce.getCategories(), annonce.getFiltre(), null);
                    } else {
                        annonce = new Annonce( annonce.getId_annonce(),Titre.getEditText().getText().toString(), Description.getEditText().getText().toString(), null, null, Ville.getEditText().getText().toString(), annonce.getDepartement(), 0L, annonce.getimage(), null, Contact.getEditText().getText().toString(), annonce.getCategories(), annonce.getFiltre(), null);
                    }
                    String json = gson.toJson(annonce);
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

        departement = (LinearLayout)root.findViewById(R.id.linearLayoutdepartement);
        departement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create new fragment and transaction
                Fragment newFragment = new FragmentDepartement();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                Gson gson = new Gson();
                FileOutputStream fOut = null;
                try {
                    fOut = getContext().openFileOutput("Modification.json", 0);
                    AppDataBase db = Room.databaseBuilder(getContext(), AppDataBase.class, "UserBDD").build();
                    UserDao userDao = db.userDao();
//                    List<UserBDD> b = userDao.getAll();

                    if (!Prix.getEditText().getText().toString().equals("")) {
                        annonce = new Annonce(annonce.getId_annonce(), Titre.getEditText().getText().toString(), Description.getEditText().getText().toString(), Float.parseFloat(Prix.getEditText().getText().toString()), null, Ville.getEditText().getText().toString(), annonce.getDepartement(), 0L, annonce.getimage(), null, Contact.getEditText().getText().toString(), annonce.getCategories(), annonce.getFiltre(), null);
                    } else {
                        annonce = new Annonce(annonce.getId_annonce(),Titre.getEditText().getText().toString(), Description.getEditText().getText().toString(), null, null, Ville.getEditText().getText().toString(), annonce.getDepartement(), 0L, annonce.getimage(), null, Contact.getEditText().getText().toString(), annonce.getCategories(), annonce.getFiltre(), null);
                    }
                    String json = gson.toJson(annonce);
                    System.out.println("IMAGE : " + annonce.getimage());
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

    void image1(){
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(image1.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.baseline_add_photo_alternate_black_24dp).getConstantState())) {
                    UserControlers userControlers = new ViewModelProvider(getActivity()).get(UserControlers.class);
                    userControlers.init(getContext());
                    if (getAnnonce().getimage().size() <= 2 || userControlers.getPlanning().get(0).getStatu().equals("AnnonceurPro")) {
                        startActivityForResult(intent, 1);
                    } else {
                        boitedialogue();
                    }
                }else{
                    Annonce annonce = getAnnonce();
                    Bitmap bm=((BitmapDrawable)image1.getDrawable()).getBitmap();
                    ByteArrayOutputStream baos=new ByteArrayOutputStream();
                    bm.compress(Bitmap.CompressFormat.PNG,100, baos);
                    byte [] b=baos.toByteArray();
                    String temp = Base64.getEncoder().encodeToString(b);
                    annonce.remove(temp);
                    setAnnonce(annonce);
                    image1.setImageDrawable(getResources().getDrawable(R.drawable.baseline_add_photo_alternate_black_24dp));

                }
            }
        });
    }


    void image2(){
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(image2.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.baseline_add_photo_alternate_black_24dp).getConstantState())) {
                    UserControlers userControlers = new ViewModelProvider(getActivity()).get(UserControlers.class);
                    userControlers.init(getContext());
                    if (getAnnonce().getimage().size() <= 2 || userControlers.getPlanning().get(0).getStatu().equals("AnnonceurPro")) {
                        startActivityForResult(intent, 2);
                    } else {
                        boitedialogue();
                    }
                }else{
                    Annonce annonce = getAnnonce();
                    Bitmap bm=((BitmapDrawable)image2.getDrawable()).getBitmap();
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bm.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    String temp = Base64.getEncoder().encodeToString(stream.toByteArray());
                    annonce.remove(temp);
                    setAnnonce(annonce);
                    image2.setImageDrawable(getResources().getDrawable(R.drawable.baseline_add_photo_alternate_black_24dp));

                }
            }
        });
    }

    void image3(){
        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(image3.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.baseline_add_photo_alternate_black_24dp).getConstantState())) {
                    UserControlers userControlers = new ViewModelProvider(getActivity()).get(UserControlers.class);
                    userControlers.init(getContext());
                    if (getAnnonce().getimage().size() <= 2 || userControlers.getPlanning().get(0).getStatu().equals("AnnonceurPro")) {
                        startActivityForResult(intent, 3);
                    } else {
                        boitedialogue();
                    }
                }else{
                    Annonce annonce = getAnnonce();
                    Bitmap bm=((BitmapDrawable)image3.getDrawable()).getBitmap();
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bm.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    String temp = Base64.getEncoder().encodeToString(stream.toByteArray());
                    annonce.remove(temp);
                    setAnnonce(annonce);
                    image3.setImageDrawable(getResources().getDrawable(R.drawable.baseline_add_photo_alternate_black_24dp));

                }
            }
        });
    }

    void image4(){
        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(image4.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.baseline_add_photo_alternate_black_24dp).getConstantState())) {
                    UserControlers userControlers = new ViewModelProvider(getActivity()).get(UserControlers.class);
                    userControlers.init(getContext());
                    if (getAnnonce().getimage().size() <= 2 || userControlers.getPlanning().get(0).getStatu().equals("AnnonceurPro")) {
                        startActivityForResult(intent, 4);
                    } else {
                        boitedialogue();
                    }
                }else{
                    Annonce annonce = getAnnonce();
                    Bitmap bm=((BitmapDrawable)image4.getDrawable()).getBitmap();
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bm.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    String temp = Base64.getEncoder().encodeToString(stream.toByteArray());
                    annonce.remove(temp);
                    setAnnonce(annonce);
                    image4.setImageDrawable(getResources().getDrawable(R.drawable.baseline_add_photo_alternate_black_24dp));

                }
            }
        });
    }

    void image5(){
        image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(image5.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.baseline_add_photo_alternate_black_24dp).getConstantState())) {
                    UserControlers userControlers = new ViewModelProvider(getActivity()).get(UserControlers.class);
                    userControlers.init(getContext());
                    if (getAnnonce().getimage().size() <= 2 || userControlers.getPlanning().get(0).getStatu().equals("AnnonceurPro")) {
                        startActivityForResult(intent, 5);
                    } else {
                        boitedialogue();
                    }
                }else{
                    Annonce annonce = getAnnonce();
                    Bitmap bm=((BitmapDrawable)image5.getDrawable()).getBitmap();
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bm.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    String temp = Base64.getEncoder().encodeToString(stream.toByteArray());
                    annonce.remove(temp);
                    setAnnonce(annonce);
                    image5.setImageDrawable(getResources().getDrawable(R.drawable.baseline_add_photo_alternate_black_24dp));

                }
            }
        });
    }

    void image6(){
        image6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(image6.getDrawable().getConstantState().equals(getResources().getDrawable(R.drawable.baseline_add_photo_alternate_black_24dp).getConstantState())) {
                    UserControlers userControlers = new ViewModelProvider(getActivity()).get(UserControlers.class);
                    userControlers.init(getContext());
                    if (getAnnonce().getimage().size() <= 2 || userControlers.getPlanning().get(0).getStatu().equals("AnnonceurPro")) {
                        startActivityForResult(intent, 6);
                    } else {
                        boitedialogue();
                    }
                }else{
                    Annonce annonce = getAnnonce();
                    Bitmap bm=((BitmapDrawable)image6.getDrawable()).getBitmap();
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bm.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    String temp = Base64.getEncoder().encodeToString(stream.toByteArray());
                    annonce.remove(temp);
                    setAnnonce(annonce);
                    image6.setImageDrawable(getResources().getDrawable(R.drawable.baseline_add_photo_alternate_black_24dp));

                }
            }
        });
    }
    public Annonce getAnnonce() {
        return this.annonce;
    }
    public void setAnnonce(Annonce annonce){
        this.annonce = annonce;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        BufferedInputStream inputStream  = null;
        try {
            inputStream = new BufferedInputStream(getActivity().getContentResolver().openInputStream(data.getData()));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Bitmap i = BitmapFactory.decodeStream(inputStream);
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        i.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp = Base64.getEncoder().encodeToString(b);
        this.annonce.addImage(temp);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case 1:
                    image1.setImageBitmap(i);
                    break;
                case 2:
                    image2.setImageBitmap(i);
                    break;
                case 3:
                    image3.setImageBitmap(i);
                    break;
                case 4:
                    image4.setImageBitmap(i);
                    break;
                case 5:
                    image5.setImageBitmap(i);
                    break;
                case 6:
                    image6.setImageBitmap(i);
                    break;

            }

        }

    }
    public void boitedialogue(){
        // Create the object of
        // AlertDialog Builder class
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setMessage("Vous ne pouvez pas déposer plus de 3 images");

        builder.setTitle("Erreur dépo image");

        builder.setCancelable(false);

        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });


        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}