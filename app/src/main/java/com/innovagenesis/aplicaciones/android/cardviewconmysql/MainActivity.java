package com.innovagenesis.aplicaciones.android.cardviewconmysql;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.innovagenesis.aplicaciones.android.cardviewconmysql.adapter.RecyclerViewAdapter;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ConsultaGetAsync.OnConsultaFinaliza {

    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.text);

        try {
            new ConsultaGetAsync(this).execute(
                    new URL ("http://192.168.100.3:8080/WebServiceExamenSiete/webapi/Donantes"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void ConsultaFinalizada(List<Donantes> resultado) {


        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        adapter = new RecyclerViewAdapter(this,resultado);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }
}
