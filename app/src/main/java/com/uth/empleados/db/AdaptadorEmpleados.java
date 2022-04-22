package com.uth.empleados.db;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uth.empleados.ActivityNuevoEmpleado;
import com.uth.empleados.R;

import java.util.ArrayList;

public class AdaptadorEmpleados extends RecyclerView.Adapter<AdaptadorEmpleados.ViewHolder> {

    ArrayList<ConstructorEmpleado> list;
    Context context;

    public AdaptadorEmpleados(ArrayList<ConstructorEmpleado> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista,parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.txtNombre.setText(list.get(position).getTb_nombres() + " " +list.get(position).getTb_apellidos());
        holder.txtEdad.setText(list.get(position).getTb_edad()+ " Años");
        holder.txtDireccion.setText(list.get(position).getTb_direccion());
        holder.txtPuesto.setText(list.get(position).getTb_puesto());

        holder.itemClick.setOnClickListener(view -> {
            Intent intent = new Intent(context, ActivityNuevoEmpleado.class);
            intent.putExtra("id",list.get(position).getId());
            intent.putExtra("Nombre",list.get(position).getTb_nombres());
            intent.putExtra("Apellido",list.get(position).getTb_apellidos());
            intent.putExtra("Edad",list.get(position).getTb_edad());
            intent.putExtra("Direccion",list.get(position).getTb_direccion());
            intent.putExtra("Puesto",list.get(position).getTb_puesto());
            context.startActivity(intent);

        });

    }

    @Override
    public int getItemCount() {
        return list.size() ;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombre, txtEdad, txtDireccion, txtPuesto;
        LinearLayout itemClick;

        @SuppressLint("CutPasteId")
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemClick = itemView.findViewById(R.id.itemClick);
            txtNombre = itemView.findViewById(R.id.txtNombres);
            txtEdad = itemView.findViewById(R.id.txtEdad);
            txtDireccion = itemView.findViewById(R.id.txtDireccion);
            txtPuesto = itemView.findViewById(R.id.txtPuesto);

        }
    }


}
