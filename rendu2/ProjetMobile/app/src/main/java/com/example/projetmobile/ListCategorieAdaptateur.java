package com.example.projetmobile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.projetmobile.Model.Catégorie;

import java.util.List;
import java.util.Locale;

public class ListCategorieAdaptateur extends BaseAdapter {

    private Context context;
    private List<Catégorie> list;
    private LayoutInflater inflater;

    public ListCategorieAdaptateur(Context context, List<Catégorie> list) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Catégorie getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.list_categorie,null);

        Catégorie e = getItem(i);
        TextView categorie = view.findViewById(R.id.textcategorie);
        ImageView imacategorie = view.findViewById(R.id.imagecategorie);
        imacategorie.setImageResource(e.getIcon());
        categorie.setText(e.getNomCategorie());
        return view;
    }
}
