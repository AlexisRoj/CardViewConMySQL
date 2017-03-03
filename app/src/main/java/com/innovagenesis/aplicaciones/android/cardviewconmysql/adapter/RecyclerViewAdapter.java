package com.innovagenesis.aplicaciones.android.cardviewconmysql.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

        holder.idCedula.setText(current.getDonante_ced());
        holder.apellido.setText(current.getDonante_apellido());
        holder.edad.setText(current.getDonante_edad());
        holder.tipoSangre.setText(current.getDonante_factor());
        holder.peso.setText(current.getDonante_peso());
        holder.estatura.setText(current.getDonante_estatura());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
