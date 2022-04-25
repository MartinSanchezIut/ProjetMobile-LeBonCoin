package com.example.projetmobile;

import android.os.AsyncTask;
import com.example.projetmobile.Model.Annonce;
import com.example.projetmobile.Model.Annonceur_pro;
import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyAsyncPutAnnonce  extends AsyncTask<String, String, String> {
    String result;
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // display a progress dialog to show the user what is happening
    }


    @Override
    protected String doInBackground (String...params){


        URL url = null;
        try {
            url = new URL(params[0]);
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setRequestMethod("PUT");
            httpCon.setRequestProperty("Content-Type", "application/json");
            httpCon.setRequestProperty("Accept", "application/json");
            OutputStreamWriter out = new OutputStreamWriter (
                    httpCon.getOutputStream());

            Gson gson = new Gson();
            Annonce f = gson.fromJson(params[1], Annonce.class);
            out.write(params[1]);
            out.close();
            httpCon.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;

    }
    @Override
    protected void onPostExecute(String s) {
        this.result = s;


    }
}
