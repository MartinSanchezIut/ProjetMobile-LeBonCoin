package com.example.projetmobile;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.projetmobile.Model.Annonce;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class AnnonceRecentAdaptateur extends BaseAdapter  {

    private List<Annonce> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public AnnonceRecentAdaptateur(Context aContext,  List<Annonce> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }
    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Annonce getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.list_view_adapteur_annonce_recent, null);
        getItem(i).getPrix();
        byte[] myImage = Base64.getDecoder().decode(getItem(i).getimage().getBytes(StandardCharsets.UTF_8));
        Bitmap bmp= BitmapFactory.decodeByteArray(myImage,0,myImage.length);
        ImageView myView = (ImageView)view.findViewById(R.id.photo_annonce);
        myView.setImageBitmap(bmp);
        TextView Titre = (TextView)view.findViewById(R.id.Titre);
        TextView Description = (TextView)view.findViewById(R.id.Descriptions);
        TextView Date = (TextView)view.findViewById(R.id.Date);
        TextView Lieux = (TextView)view.findViewById(R.id.Lieux);
        Titre.setText(getItem(i).getTitre());
        Description.setText(getItem(i).getDescription());
        Date.setText(getItem(i).getDate_publication());
        Lieux.setText(getItem(i).getLieux());
        return view;
    }
}
