package com.example.projetmobile;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.projetmobile.Model.Annonce;
import com.example.projetmobile.Model.MessageAffichage;
import com.example.projetmobile.Model.User;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

public class ListMessageAdaptater  extends BaseAdapter {

    private Context context;
    private List<MessageAffichage> listMessage;
    private LayoutInflater inflater;

    public ListMessageAdaptater(Context context, List<MessageAffichage> list) {
        this.context = context;
        this.listMessage = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listMessage.size();
    }

    @Override
    public MessageAffichage getItem(int i) {
        return listMessage.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.list_message,null);
        if(getItem(i).getProfil() != null) {
            byte[] myImage = Base64.getDecoder().decode(getItem(i).getProfil().getBytes(StandardCharsets.UTF_8));
            Bitmap bmp = BitmapFactory.decodeByteArray(myImage, 0, myImage.length);
            ImageView myView = (ImageView) view.findViewById(R.id.profil);
            myView.setImageBitmap(bmp);
        }

        TextView pseudo =  view.findViewById(R.id.Pseudo);
        pseudo.setText(getItem(i).getPseudo());

        System.out.println(getItem(i).getTitreAnnonce());
        TextView titre =  view.findViewById(R.id.Titre);
        titre.setText(getItem(i).getTitreAnnonce());


        return view;
    }
}
