package com.example.projetmobile;

import android.os.AsyncTask;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


;import java.io.IOException;

public class MyAsyncAnnonceRecente extends AsyncTask<String, String, String> {


    String result;
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // display a progress dialog to show the user what is happening
    }


    @Override
    protected String doInBackground (String...params){
/*
        String result = "";
        try{
            URL url;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL(params[0]);
                //open a URL coonnection
                System.out.println("00000000000000000000000000");
                urlConnection = (HttpURLConnection) url.openConnection();
                System.out.println("5555555555555555555555555555555555");
                InputStream in = urlConnection.getInputStream();
                System.out.println("6666666666666666666666666");
                InputStreamReader isw = new InputStreamReader(in);
                System.out.println("77777777777777777777777777");
                int data = isw.read();
                System.out.println("88888888888888888888888888888888888888888");

                while (data != -1) {
                    result += (char) data;
                    data = isw.read();
                    System.out.println("2");

                }
                System.out.println("333333333333333333333333333333");

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
        }*/
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(params[0])
                .get()
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            result =  response.body().string();
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
