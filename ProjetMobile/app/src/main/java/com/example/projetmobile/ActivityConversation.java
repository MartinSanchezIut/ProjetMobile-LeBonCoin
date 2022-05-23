package com.example.projetmobile;

import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.projetmobile.BDD.models.Controllers.UserControlers;
import com.example.projetmobile.Model.Conversation;
import com.example.projetmobile.Model.Message;
import com.example.projetmobile.Model.serveur;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class ActivityConversation extends AppCompatActivity {
    private RecyclerView mMessageRecycler;
    private MessageListAdapter mMessageAdapter;

    private Button send;

    private TextView envoie;
    Conversation c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);
        envoie = findViewById(R.id.edit_gchat_message);
        send = findViewById(R.id.button_gchat_send);
        Gson gson = new Gson();
        System.out.println("ICI1");
        String param = getIntent().getExtras().getString("Conversation");
         c = gson.fromJson(param, Conversation.class);
        System.out.println(param.getBytes(StandardCharsets.UTF_8).length);
        if(c.getList_messages() == null) {
            c.setList_messages(new ArrayList<>());
        }
        mMessageRecycler = (RecyclerView) findViewById(R.id.recycler_gchat);
        ArrayList<Message> list = c.getList_messages();
        Collections.reverse(list);
        mMessageAdapter = new MessageListAdapter(this, this,list);
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setReverseLayout(true);
        mMessageRecycler.setLayoutManager(linearLayoutManager);


        mMessageRecycler.setAdapter(mMessageAdapter);
    }

    public void Envoyer(View view){
        String url = "http://172.16.5.209:8080/LeMauvaisCoin/api/message/PutMessage";
        Gson gson = new Gson();
        UserControlers userControlers = new ViewModelProvider(this).get(UserControlers.class);
        userControlers.init(this);
        System.out.println(userControlers.getPlanning().get(0).getId_user());
        Message mss = new Message(userControlers.getPlanning().get(0).getId_user(),envoie.getText().toString());
        String json = gson.toJson(mss);
        System.out.println(json);
        ArrayList<String> p = new ArrayList<>();
        p.add(String.valueOf(c.getId_conversation()));
        p.add(json);
        String query = gson.toJson(p);
        System.out.println(query);
        serveur s = new serveur("message/PutMessage");
        s.PutRequest(query);
         gson = new Gson();
        String result = null;
        try {
             s = new serveur("message/ConversationById");
            result = s.PostRequest(String.valueOf(c.getId_conversation()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        c = gson.fromJson(result, Conversation.class);
        mMessageRecycler = (RecyclerView) findViewById(R.id.recycler_gchat);
        ArrayList<Message> list = c.getList_messages();
        Collections.reverse(list);
        mMessageAdapter = new MessageListAdapter(this, this,list);
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setReverseLayout(true);
        mMessageRecycler.setLayoutManager(linearLayoutManager);



        mMessageRecycler.setAdapter(mMessageAdapter);
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