package com.example.projetmobile;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyAsyncRecherche extends AsyncTask<String, String, String> {

    String result = "";
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
            httpCon.setRequestMethod("POST");
            httpCon.setRequestProperty("Content-Type", "application/json");
            httpCon.setRequestProperty("Accept", "application/json");
            OutputStreamWriter out = new OutputStreamWriter (
                    httpCon.getOutputStream());
            out.write(params[1]);
            out.flush();
            String builtResponse = "";
            String line ="";
            BufferedReader reader = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
            while ((line = reader.readLine()) != null) {
                result += line;
            }
            out.close();
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
