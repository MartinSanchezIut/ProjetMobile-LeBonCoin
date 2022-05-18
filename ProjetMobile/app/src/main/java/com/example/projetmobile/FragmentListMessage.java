package com.example.projetmobile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.GridView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;
import com.example.projetmobile.BDD.models.Controllers.UserControlers;
import com.example.projetmobile.Model.Annonce;
import com.example.projetmobile.Model.Conversation;
import com.example.projetmobile.Model.MessageAffichage;
import com.example.projetmobile.Model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class FragmentListMessage extends Fragment {


    private GridView listView ;
    private ArrayList<MessageAffichage> message;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_list_message, container, false);
        listView = (GridView ) root.findViewById(R.id.gridView);
        String url = "http://172.16.5.209:8080/LeMauvaisCoin/api/message/MessageByIdAnnonceur";
        UserControlers userControlers = new ViewModelProvider(getActivity()).get(UserControlers.class);
        userControlers.init(getContext());
        long id = userControlers.getPlanning().get(0).getId_user();
        String result = null;
        try {
             result = PostRequest(url,id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(result);
        Gson gson = new Gson();
        message = gson.fromJson(result, new TypeToken<ArrayList<MessageAffichage>>(){}.getType());
        /*
        for(MessageAffichage m : message) {
            System.out.println(m.getTitreAnnonce());
        }

         */

        ListMessageAdaptater myAdapter=new ListMessageAdaptater(getContext(),message);
        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3)
        {

            Gson gson = new Gson();
            String url = "http://172.16.5.209:8080/LeMauvaisCoin/api/message/ConversationById";
            String result = null;
            try {
                result = GetConversation(url,message.get(position).getId_conversation());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Intent intention= new Intent(getContext(), ActivityConversation.class);
            intention.putExtra("Conversation",result);
            startActivity(intention);

        }
        });
        return root;
    }

    public String PostRequest(String url,long id) throws IOException {
        final String[] result = {""};
        Thread thread = new Thread(new Runnable() {
            URL adresse = null;
            @Override
            public void run() {
                try  {
                    try {
                        adresse = new URL(url);
                        HttpURLConnection httpCon = (HttpURLConnection) adresse.openConnection();
                        httpCon.setDoOutput(true);
                        httpCon.setRequestMethod("POST");
                        httpCon.setRequestProperty("Content-Type", "application/json");
                        httpCon.setRequestProperty("Accept", "application/json");
                        OutputStreamWriter out = new OutputStreamWriter (
                                httpCon.getOutputStream());
                        out.write(String.valueOf(id));
                        out.flush();
                        String builtResponse = "";
                        String line ="";
                        BufferedReader reader = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
                        while ((line = reader.readLine()) != null) {
                            result[0] += line;
                        }
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return result[0];
    }

    public String GetConversation(String url,long id) throws IOException {
        final String[] result = {""};
        Thread thread = new Thread(new Runnable() {
            URL adresse = null;
            @Override
            public void run() {
                try  {
                    try {
                        adresse = new URL(url);
                        HttpURLConnection httpCon = (HttpURLConnection) adresse.openConnection();
                        httpCon.setDoOutput(true);
                        httpCon.setRequestMethod("POST");
                        httpCon.setRequestProperty("Content-Type", "application/json");
                        httpCon.setRequestProperty("Accept", "application/json");
                        OutputStreamWriter out = new OutputStreamWriter (
                                httpCon.getOutputStream());
                        out.write(String.valueOf(id));
                        out.flush();
                        String builtResponse = "";
                        String line ="";
                        BufferedReader reader = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
                        while ((line = reader.readLine()) != null) {
                            result[0] += line;
                        }
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return result[0];
    }
}
