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

public class FiltreListAdaptateur extends BaseAdapter {

    private Context context;
    private List<String> list;
    private LayoutInflater inflater;

    public FiltreListAdaptateur(Context context, List<String> list) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public String getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.list_filtre,null);

        String e = getItem(i);
        System.out.println(e);
        TextView filtre = view.findViewById(R.id.filtre);
        filtre.setText(e);
        return view;
    }
}
