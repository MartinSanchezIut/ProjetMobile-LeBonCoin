package com.example.projetmobile;

import android.os.AsyncTask;
import com.example.projetmobile.Model.Annonceur_pro;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;

public class MyAsyncInscription  extends AsyncTask<String, String, String> {

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
            OutputStreamWriter  out = new OutputStreamWriter (
                    httpCon.getOutputStream());

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
