package com.innovagenesis.aplicaciones.android.cardviewconmysql.async_class;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by alexi on 03/03/2017.
 */

public class DeleteDonanteAsync extends AsyncTask<URL, Integer, String> {

    Activity activity;
    ProgressDialog dialog;

    public DeleteDonanteAsync(Activity activity) {
        this.activity = activity;
        dialog = new ProgressDialog(activity);

        try {
            listener = (OnRegistroEliminado) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("Interface no implmentada");
        }

    }

    public interface OnRegistroEliminado {
        void RegistroEliminado(Boolean donante);
    }

    private OnRegistroEliminado listener;

    @Override
    protected String doInBackground(URL... params) {

        HttpURLConnection connection = null;

        try {
            connection = (HttpURLConnection) params[0].openConnection();
            connection.setRequestMethod("DELETE");
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            return reader.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert connection != null;
            connection.disconnect();
        }

        return null;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog.setMessage("Por favor espere...");
        dialog.show();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        if (dialog.isShowing()){
            dialog.dismiss();
        }
        listener.RegistroEliminado(true);

        }
    }
