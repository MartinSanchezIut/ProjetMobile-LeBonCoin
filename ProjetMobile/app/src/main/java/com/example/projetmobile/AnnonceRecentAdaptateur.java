package com.example.projetmobile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.projetmobile.Model.Annonce;

import java.util.List;

public class AnnonceRecentAdaptateur extends BaseAdapter {

    private Context context;
    private List<Annonce> list;
    private LayoutInflater inflater;

    public AnnonceRecentAdaptateur(Context context, List<Annonce> list) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    /*
    Getter
     */
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Annonce getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.list_view_adapteur_annonce_recent,null);

        Annonce e = getItem(i);
        TextView Titre = (TextView) view.findViewById(R.id.idTitre);
        TextView Descr = (TextView) view.findViewById(R.id.idDescr);
        TextView Prix = (TextView) view.findViewById(R.id.idPrix);

        Titre.setText(e.getTitre());
        Descr.setText(e.getDescription());
        Prix.setText(String.valueOf(e.getPrix()));
        return view;
    }
}
