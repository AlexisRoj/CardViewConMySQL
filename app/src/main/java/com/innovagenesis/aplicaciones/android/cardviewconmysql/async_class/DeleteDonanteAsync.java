package com.innovagenesis.aplicaciones.android.cardviewconmysql.async_class;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * Created by alexi on 03/03/2017.
 */

public class DeleteDonanteAsync extends AsyncTask<URL,Integer, String> {

    Activity activity;
    ProgressDialog dialog;

    public DeleteDonanteAsync(Activity activity) {
        this.activity = activity;
        dialog = new ProgressDialog(activity);
    }

    @Override
    protected String doInBackground(URL... params) {

        HttpURLConnection connection = null;

        try {
            connection = (HttpURLConnection)params[0].openConnection();
            connection.setRequestMethod("DELETE");
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            return reader.readLine();


        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            assert connection != null;
            connection.disconnect();
        }

        return null;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
