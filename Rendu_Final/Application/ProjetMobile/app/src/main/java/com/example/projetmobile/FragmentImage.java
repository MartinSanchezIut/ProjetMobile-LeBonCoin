package com.example.projetmobile;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.projetmobile.Model.Annonce;


public class FragmentImage extends Fragment {
    private static final String LOG_TAG = "AndroidExample";
    Bitmap image;

    ImageView photo;


    FragmentImage() {
    }
    FragmentImage(Bitmap image) {
        this.image = image;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_image, container, false);
        photo = root.findViewById(R.id.photo);
        return root;
    }


    // Called when configuration change.
    // For example: User rotate the Phone,
    //  Android will create new Fragment (EmployeePageFragment) object via default Constructor
    //  so this.employee will be null.
    /*
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        Log.i(LOG_TAG, "onSaveInstanceState: save employee data to Bundle");
        // Convert employee object to Bundle.
        Bundle dataBundle = this.employeeToBundle(image);
        outState.putAll(dataBundle);

        super.onSaveInstanceState(outState);
    }

     */


    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        Log.i(LOG_TAG, "onViewStateRestored");

        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.i(LOG_TAG, "onViewCreated");
        super.onViewCreated(view, savedInstanceState);

        if(this.image == null)  {
            Log.i(LOG_TAG, "Get employee data from savedInstanceState");
            // The state was saved by onSaveInstanceState(Bundle outState) method.
            this.image = this.bundleToEmployee(savedInstanceState);
        }
        this.showInGUI(this.image);
    }

    // Call where View ready.
    private void showInGUI(Bitmap image)  {
        this.photo.setImageBitmap(image);
    }

    private Bundle employeeToBundle(Bitmap image)  {
        Bundle bundle = new Bundle();
        bundle.putParcelable("Image", image);

        return bundle;
    }

    private Bitmap bundleToEmployee(Bundle savedInstanceState) {
        Bitmap image = savedInstanceState.getParcelable("Image");
        return image;
    }
}