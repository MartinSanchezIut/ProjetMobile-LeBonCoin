package com.example.projetmobile;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.*;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentTransaction;
import com.example.projetmobile.Model.Recherche;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class FragmentLocalisationRecherche extends Fragment implements EasyPermissions.PermissionCallbacks, LocationListener{


   private LinearLayout dep;
    private TextInputLayout Ville;
    private Recherche r;

    private int READ_STORAGE_PERMISSION_REQUEST = 123;
    private Button valider;

    private SwitchMaterial loc;

    private LocationManager locationManager;

    private List<Address> adresses = null;

    @Override
    public void onResume() {
        super.onResume();
        locationManager = (LocationManager) getContext().getSystemService(getActivity().LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000L, 10F, this);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_localisation_recherche, container, false);

        dep = root.findViewById(R.id.linearLayoutdepartement);
        Ville = root.findViewById(R.id.idVille);
        loc = root.findViewById(R.id.localisation);
        String permission1 = Manifest.permission.ACCESS_FINE_LOCATION;
        String permission2 = Manifest.permission.ACCESS_COARSE_LOCATION;
        if (!EasyPermissions.hasPermissions(getContext(), permission1)) {
            EasyPermissions.requestPermissions(this, "Our App Requires a permission to access your storage", READ_STORAGE_PERMISSION_REQUEST
                    , permission1);
        }

        if (!EasyPermissions.hasPermissions(getContext(), permission2)) {
            EasyPermissions.requestPermissions(this, "Our App Requires a permission to access your storage", READ_STORAGE_PERMISSION_REQUEST
                    , permission2);
        }
        loc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
                    Location loc = null;
                    if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        try
                        {
                            adresses = geocoder.getFromLocation(loc.getLatitude(), loc.getLongitude(), 1);
                        }
                        catch (IOException ioException)
                        {
                            Log.e("GPS", "erreur", ioException);
                        }
                        Address adresse = adresses.get(0);
                        //System.out.println(adresse.getLocality());
                        Ville.getEditText().setText(adresse.getLocality());
                    }

                }else{
                    Ville.getEditText().setText("");
                }
              //  System.out.println(b);

            }
        });






        String file_name = getActivity().getFilesDir() + "/" + "recherche.json";
        File t = new File(file_name);
        InputStream input = null;
         r = new Recherche(null, null, null, null, new ArrayList<>(), null, null, null);
        try {
            Gson gson = new Gson();
            if (t.exists()) {
                input = getContext().openFileInput("recherche.json");
                byte[] buffer = new byte[input.available()];
                input.read(buffer);
                input.close();
                String text = new String(buffer);
                r = gson.fromJson(text, Recherche.class);
                Ville.getEditText().setText(r.getVille());

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if( r.getDepartement() != null) {
            for (String s : r.getDepartement()) {
                System.out.println(s);
            }
        }
        dep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileOutputStream fOut = getContext().openFileOutput("recherche.json", 0);
                    Gson gson = new Gson();
                    r.setVille(Ville.getEditText().getText().toString());
                    String json = gson.toJson(r);
                    fOut.write(json.getBytes());
                    fOut.close();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                FragmentDepartementRecherche fragment = new FragmentDepartementRecherche();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                transaction.replace(R.id.fragmentContainerView5, fragment);
                transaction.commit();
            }
            });

        valider = root.findViewById(R.id.idValider);
        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileOutputStream fOut = getContext().openFileOutput("recherche.json", 0);
                    Gson gson = new Gson();
                    r.setVille(Ville.getEditText().getText().toString());
                    String json = gson.toJson(r);
                    fOut.write(json.getBytes());
                    fOut.close();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                FragmentRechercheAcceuil fragment = new FragmentRechercheAcceuil();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                transaction.replace(R.id.fragmentContainerView5, fragment);
                transaction.commit();

            }
        });
        return root;
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if(EasyPermissions.somePermissionPermanentlyDenied(this, perms)){

            new AppSettingsDialog.Builder(this).build().show();
        }

    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }
}