package com.example.projetmobile;

import android.os.AsyncTask;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyAsyncAnnonceRecente extends AsyncTask<String, String, String> {


    String result;
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // display a progress dialog to show the user what is happening
    }


    @Override
    protected String doInBackground (String...params){

        String result = "";
        try{
            URL url;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL(params[0]);
                //open a URL coonnection

                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = urlConnection.getInputStream();

                InputStreamReader isw = new InputStreamReader(in);

                int data = isw.read();

                while (data != -1) {
                    result += (char) data;
                    data = isw.read();

                }

                return result;

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "Exception: " + e.getMessage();
        }
        return result;

    }
    @Override
    protected void onPostExecute(String s) {
        this.result = s;


    }
}
