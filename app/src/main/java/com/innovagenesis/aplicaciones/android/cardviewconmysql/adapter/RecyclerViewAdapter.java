package com.innovagenesis.aplicaciones.android.cardviewconmysql.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.innovagenesis.aplicaciones.android.cardviewconmysql.Donantes;
import com.innovagenesis.aplicaciones.android.cardviewconmysql.R;
import com.innovagenesis.aplicaciones.android.cardviewconmysql.RecyclerViewHolder;
import com.innovagenesis.aplicaciones.android.cardviewconmysql.async_class.DeleteDonanteAsync;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.List;

/**
 * Created by alexi on 02/03/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private Context context;
    private Activity activity;
    LayoutInflater inflater;
    List<Donantes> data = Collections.emptyList();



    public RecyclerViewAdapter(Context context, Activity activity, List<Donantes> data) {
        this.context = context;
        this.activity = activity;
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
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {

        final Donantes current = data.get(position);
        final int idDonanteBorrar = current.donante_ced;

        /** Carga los datos en el recyclerView */
        holder.idCedula.setText(String.valueOf(current.donante_ced));
        holder.nombre.setText(current.donante_nombre);
        holder.apellido.setText(current.donante_apellido);
        holder.edad.setText(String.valueOf(current.donante_edad));
        holder.tipoSangre.setText(current.donante_tipo);
        holder.factorSangre.setText(current.donante_factor);
        holder.peso.setText(String.valueOf(current.donante_peso));
        holder.estatura.setText(String.valueOf(current.donante_estatura));


        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context,"Delete",Toast.LENGTH_SHORT).show();
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int item = holder.getAdapterPosition();
                try {
                    /** Ejecuta tarea asincronica de borrado*/
                    new DeleteDonanteAsync(activity).execute(new URL("http://192.168.100.3:8080/WebServiceExamenSiete/webapi/Donantes/" +
                            idDonanteBorrar));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                removeAt(item);
            }
        });

    }

    public void removeAt(int position) {
        //Elimina los itemsd el recyclerView
        data.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, data.size());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


}
