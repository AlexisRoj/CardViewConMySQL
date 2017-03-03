package com.innovagenesis.aplicaciones.android.cardviewconmysql;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.innovagenesis.aplicaciones.android.cardviewconmysql.adapter.RecyclerViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by alexi on 02/03/2017.
 */

public class ConsultaGetAsync extends AsyncTask<URL, Integer, String>{


    private Activity activity;
    private ProgressDialog dialog;



    public interface OnConsultaFinaliza{
      void ConsultaFinalizada(List<Donantes> resultado);
    }

    private OnConsultaFinaliza listener;

    public ConsultaGetAsync(Activity activity) {
        this.activity = activity;
        dialog = new ProgressDialog(activity);

        try {
            listener = (OnConsultaFinaliza)activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("La interface no ha sido implementada");
        }
    }

    @Override
    protected String doInBackground(URL... params) {
        HttpURLConnection connection = null;

        try {
            connection = (HttpURLConnection) params[0].openConnection();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            return reader.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
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

        List<Donantes> data = new ArrayList<>();


        try {
            JSONArray jsonArray = new JSONArray(s);
            for (int i = 0; i < jsonArray.length(); i++) {
                Donantes donantes = new Donantes();
                donantes.donante_ced = jsonArray.getJSONObject(i).getInt("donante_ced");
                donantes.donante_nombre = jsonArray.getJSONObject(i).getString("donante_nombre");
                donantes.donante_apellido = jsonArray.getJSONObject(i).getString("donante_apellido");
                donantes.donante_edad = jsonArray.getJSONObject(i).getInt("donante_edad");
                donantes.donante_factor = jsonArray.getJSONObject(i).getString("donante_tipo_sangre");
                donantes.donante_peso = jsonArray.getJSONObject(i).getInt("donante_peso");
                donantes.donante_estatura = jsonArray.getJSONObject(i).getInt("donante_estatura");
                data.add(donantes);
            }



        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(activity, "Error al decodificar json respuesta", Toast.LENGTH_SHORT).show();
        }

        listener.ConsultaFinalizada(data);

        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }

}
