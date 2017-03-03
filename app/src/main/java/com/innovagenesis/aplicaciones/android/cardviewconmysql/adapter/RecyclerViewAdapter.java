package com.innovagenesis.aplicaciones.android.cardviewconmysql.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.innovagenesis.aplicaciones.android.cardviewconmysql.Donantes;
import com.innovagenesis.aplicaciones.android.cardviewconmysql.R;
import com.innovagenesis.aplicaciones.android.cardviewconmysql.RecyclerViewHolder;


import java.util.Collections;
import java.util.List;

/**
 * Created by alexi on 02/03/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private Context context;
    LayoutInflater inflater;
    List<Donantes> data = Collections.emptyList();

    public RecyclerViewAdapter(Context context, List<Donantes> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.template_item_donante, parent, false);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        Donantes current = data.get(position);

        int a = current.donante_ced;
        String b = current.donante_nombre;

        Toast.makeText(context,b,Toast.LENGTH_SHORT).show();

        holder.idCedula.setText(String.valueOf(current.donante_ced));
        holder.nombre.setText(current.donante_nombre);
        holder.apellido.setText(current.donante_apellido);
        holder.edad.setText(String.valueOf(current.donante_edad));
        holder.tipoSangre.setText(current.donante_factor);
        holder.peso.setText(String.valueOf(current.donante_peso));
        holder.estatura.setText(String.valueOf(current.donante_estatura));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
