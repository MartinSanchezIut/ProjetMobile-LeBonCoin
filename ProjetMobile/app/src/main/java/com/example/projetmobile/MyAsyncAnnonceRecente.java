package com.example.projetmobile;

import android.os.AsyncTask;
import android.widget.Toast;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;


;import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
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
        try {
            return IOUtils.toString(new InputStreamReader(new BufferedInputStream(new URL(params[0]).openConnection().getInputStream()), Charsets.UTF_8));

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
/*
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

 */

    }
    @Override
    protected void onPostExecute(String s) {
        this.result = s;


    }
}
