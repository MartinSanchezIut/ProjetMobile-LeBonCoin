package com.example.projetmobile;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;
import com.example.projetmobile.BDD.Controllers.UserControlers;
import com.example.projetmobile.BDD.Repository.AppDataBase;
import com.example.projetmobile.BDD.Repository.UserDao;
import com.example.projetmobile.BDD.models.UserBDD;
import com.example.projetmobile.Model.Annonce;
import com.example.projetmobile.Model.Message;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ExecutionException;


public class FragmentDeposer extends Fragment {
    private final int STORAGE_PERMISSION_CODE = 23;
    private final int GALLERY_REQUEST_CODE=24;
    private TextInputLayout Titre;

    private TextInputLayout Description;

    private TextInputLayout Prix;

    private ImageView image1;

    private ImageView image2;


    private ImageView image3;
    Annonce f;

    private ImageView image4;

    private ImageView image5;

    private ImageView image6;

    private Intent intent=new Intent(Intent.ACTION_PICK);
    private TextInputLayout Ville;

    private ArrayList<String> image = new ArrayList<>();

    private TextInputLayout Contact;

    private Button Deposer;
    private LinearLayout lin;

    private LinearLayout departement;
    public FragmentDeposer() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_deposer, container, false);
        Titre = (TextInputLayout)root.findViewById(R.id.Titre);
        Description = (TextInputLayout)root.findViewById(R.id.Description);
        Prix = (TextInputLayout)root.findViewById(R.id.Prix);
        Ville = (TextInputLayout)root.findViewById(R.id.Ville);
        Contact = (TextInputLayout)root.findViewById(R.id.Contact);


        //Définissez le type comme image/*.
        //Cela garantit que seuls les composants de type image sont sélectionnés
        intent.setType("image/*");
        //Nous passons un tableau supplémentaire avec les types MIME acceptés.
        //Cela garantira que seuls les composants avec ces types MIME sont ciblés.
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



        Deposer = (Button)root.findViewById(R.id.Deposer);
        Deposer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String file_name = getActivity().getFilesDir() + "/" + "Data.json";
                File t = new File(file_name);
                InputStream input = null;
                if (t.exists()) {
                    try {
                        input = getContext().openFileInput("Data.json");
                        byte[] buffer = new byte[input.available()];
                        input.read(buffer);
                        input.close();
                        String text = new String(buffer);
                        Gson gson = new Gson();
                        Annonce f = gson.fromJson(text, Annonce.class);
                        f.setTitre(Titre.getEditText().getText().toString());
                        f.setDescription(Description.getEditText().getText().toString());
                        f.setPrix(Float.parseFloat(Prix.getEditText().getText().toString()));
                        f.setVille(Ville.getEditText().getText().toString());
                        f.setContact(Contact.getEditText().getText().toString());
                        UserControlers userControlers = new ViewModelProvider(getActivity()).get(UserControlers.class);
                        userControlers.init(getContext());
                        f.setId_annonceur(userControlers.getPlanning().get(0).getId_user());
                        Map<String, Integer> d = new HashMap<>();
                        f.setNbvues(d);
                        List<Message> m = new ArrayList<>();
                        f.setList_messages(m);
                        MyAsyncPutAnnonce myAsyncTasks = new MyAsyncPutAnnonce();
                        String url = "http://192.168.1.25:8080/LeMauvaisCoin/api/annonce/PutAnnonce";
                        String json = gson.toJson(f);
                        String reponse = myAsyncTasks.execute(url,json).get();
                        System.out.println(f);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (ExecutionException e) {
                        throw new RuntimeException(e);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    t.delete();
                }
            }
        });


        String file_name = getActivity().getFilesDir() + "/" + "Data.json";
        File t = new File(file_name);
        InputStream input = null;
        if (t.exists()) {
            try {
                input = getContext().openFileInput("Data.json");
                byte[] buffer = new byte[input.available()];
                input.read(buffer);
                input.close();
                String text = new String(buffer);
                System.out.println("TEXTE "+text);
                Gson gson = new Gson();
               Annonce f = gson.fromJson(text, Annonce.class);
               if(f.getTitre()!=null)
                   Titre.getEditText().setText(f.getTitre());
                if(f.getDescription()!=null)
                    Description.getEditText().setText(f.getDescription());
                if(f.getPrix()!= (Float)null)
                    Prix.getEditText().setText(String.valueOf(f.getPrix()));
                if(f.getville()!=null)
                    Ville.getEditText().setText(f.getville());
                if(f.getContact()!=null)
                    Contact.getEditText().setText(f.getContact());
                if(f.getContact()!=null)
                    Contact.getEditText().setText(f.getContact());
                if(f.getimage()!=null) {
                    for (int i = 0; i < f.getimage().size(); ++i) {
                        byte[] myImage = Base64.getDecoder().decode(f.getimage().get(i).getBytes(StandardCharsets.UTF_8));
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

                String file_name = getActivity().getFilesDir() + "/" + "Data.json";
                File t = new File(file_name);
                InputStream input = null;
                if (t.exists()) {
                    try {
                        input = getContext().openFileInput("Data.json");
                        byte[] buffer = new byte[input.available()];
                        input.read(buffer);
                        input.close();
                        String text = new String(buffer);
                        Gson gson = new Gson();
                        Annonce p = gson.fromJson(text, Annonce.class);

                        FileOutputStream fOut = getContext().openFileOutput("Data.json", 0);
                        AppDataBase db = Room.databaseBuilder(getContext(), AppDataBase.class, "UserBDD").build();
                        UserDao userDao = db.userDao();
//                    List<UserBDD> b = userDao.getAll();

                        if (!Prix.getEditText().getText().toString().equals("")) {
                            f = new Annonce(0L, Titre.getEditText().getText().toString(), Description.getEditText().getText().toString(), Float.parseFloat(Prix.getEditText().getText().toString()), null, Ville.getEditText().getText().toString(), p.getDepartement(), 0L, image, null, Contact.getEditText().getText().toString(), p.getCategories(), p.getFiltre(), null);
                        } else {
                            f = new Annonce(0L, Titre.getEditText().getText().toString(), Description.getEditText().getText().toString(), null, null, Ville.getEditText().getText().toString(), p.getDepartement(), 0L, image, null, Contact.getEditText().getText().toString(), p.getCategories(), p.getFiltre(), null);
                        }
                        String json = gson.toJson(f);
                        System.out.println(json);
                        fOut.write(json.getBytes());
                        fOut.close();
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }else {
                    try {
                        FileOutputStream fOut = getContext().openFileOutput("Data.json", 0);
                        Gson gson = new Gson();
                        AppDataBase db = Room.databaseBuilder(getContext(), AppDataBase.class, "UserBDD").build();
                        UserDao userDao = db.userDao();
//                    List<UserBDD> b = userDao.getAll();

                        if (!Prix.getEditText().getText().toString().equals("")) {
                            f = new Annonce(0L, Titre.getEditText().getText().toString(), Description.getEditText().getText().toString(), Float.parseFloat(Prix.getEditText().getText().toString()), null, Ville.getEditText().getText().toString(), null, 0L, image, null, Contact.getEditText().getText().toString(), null, null, null);
                        } else {
                            f = new Annonce(0L, Titre.getEditText().getText().toString(), Description.getEditText().getText().toString(), null, null, Ville.getEditText().getText().toString(), null, 0L, image, null, Contact.getEditText().getText().toString(), null, null, null);
                        }
                        String json = gson.toJson(f);
                        System.out.println(json);
                        fOut.write(json.getBytes());
                        fOut.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                transaction.replace(R.id.fragmentContainerView6, newFragment);
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

                String file_name = getActivity().getFilesDir() + "/" + "Data.json";
                File t = new File(file_name);
                InputStream input = null;
                    if (t.exists()) {
                        try {
                            input = getContext().openFileInput("Data.json");
                            byte[] buffer = new byte[input.available()];
                            input.read(buffer);
                            input.close();
                            String text = new String(buffer);
                            Gson gson = new Gson();
                            Annonce p = gson.fromJson(text, Annonce.class);
                            FileOutputStream fOut = getContext().openFileOutput("Data.json", 0);
                            AppDataBase db = Room.databaseBuilder(getContext(), AppDataBase.class, "UserBDD").build();
                            UserDao userDao = db.userDao();
//                    List<UserBDD> b = userDao.getAll();
                            if (!Prix.getEditText().getText().toString().equals("")) {
                                f = new Annonce(0L, Titre.getEditText().getText().toString(), Description.getEditText().getText().toString(), Float.parseFloat(Prix.getEditText().getText().toString()), null, Ville.getEditText().getText().toString(), p.getDepartement(), 0L, image, null, Contact.getEditText().getText().toString(), p.getCategories(), p.getFiltre(), null);
                            } else {
                                f = new Annonce(0L, Titre.getEditText().getText().toString(), Description.getEditText().getText().toString(), null, null, Ville.getEditText().getText().toString(), p.getDepartement(), 0L, image, null, Contact.getEditText().getText().toString(), p.getCategories(), p.getFiltre(), null);
                            }
                            String json = gson.toJson(f);
                            System.out.println(json);
                            fOut.write(json.getBytes());
                            fOut.close();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }else {
                        try {
                            FileOutputStream fOut = getContext().openFileOutput("Data.json", 0);
                            Gson gson = new Gson();
                            AppDataBase db = Room.databaseBuilder(getContext(), AppDataBase.class, "UserBDD").build();
                            UserDao userDao = db.userDao();
//                    List<UserBDD> b = userDao.getAll();

                            if (!Prix.getEditText().getText().toString().equals("")) {
                                f = new Annonce(0L, Titre.getEditText().getText().toString(), Description.getEditText().getText().toString(), Float.parseFloat(Prix.getEditText().getText().toString()), null, Ville.getEditText().getText().toString(), null, 0L, image, null, Contact.getEditText().getText().toString(), null, null, null);
                            } else {
                                f = new Annonce(0L, Titre.getEditText().getText().toString(), Description.getEditText().getText().toString(), null, null, Ville.getEditText().getText().toString(), null, 0L, image, null, Contact.getEditText().getText().toString(), null, null, null);
                            }
                            String json = gson.toJson(f);
                            System.out.println(json);
                            fOut.write(json.getBytes());
                            fOut.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                transaction.replace(R.id.fragmentContainerView6, newFragment);
                transaction.commit();
            }
        });


        return root;
    }

    void image1(){
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(intent,1);
            }
        });
    }

    void image2(){
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(intent,2);
            }
        });
    }

    void image3(){
        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(intent,3);
            }
        });
    }

    void image4(){
        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(intent,4);
            }
        });
    }

    void image5(){
        image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(intent,5);
            }
        });
    }

    void image6(){
        image6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(intent,6);
            }
        });
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
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        i.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        byte[] b = baos.toByteArray();
        String temp = Base64.getEncoder().encodeToString(baos.toByteArray());
        image.add(temp);
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


}