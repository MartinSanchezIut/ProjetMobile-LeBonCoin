package com.example.projetmobile;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.example.projetmobile.Model.Annonce;

import java.util.ArrayList;
import java.util.List;

public class ImageFragmentStateAdaptater extends FragmentStateAdapter {

    private List<Annonce> annonce;

    List<Bitmap> image;


    public ImageFragmentStateAdaptater(@NonNull FragmentActivity fragmentActivity , ArrayList<Bitmap> image) {
        super(fragmentActivity);
        this.image = image;

    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Bitmap photo = this.image.get(position);
        return new FragmentImage(photo);
    }

    @Override
    public int getItemCount() {
            return this.image.size();
    }
}
