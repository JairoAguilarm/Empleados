package com.uth.empleados;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.uth.empleados.db.AdaptadorEmpleados;
import com.uth.empleados.db.ConstructorEmpleado;
import com.uth.empleados.db.Empleados;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView listaEmpleados;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Funciones para la gestion de la base de datos
        Empleados empleados = new Empleados(this);
//        empleados.

        //Envia los datos al adaptador
        AdaptadorEmpleados adaptadorEmpleados = new AdaptadorEmpleados(empleados.getEmpleados(),this);


        //ReciclerView
        listaEmpleados = findViewById(R.id.listaEmpleados);
        listaEmpleados.setLayoutManager(new LinearLayoutManager(this));
        listaEmpleados.setAdapter(adaptadorEmpleados);

        TextView count = findViewById(R.id.count);
        count.setText("Item: "+empleados.getEmpleados().size());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_config) {
            Intent intent = new Intent(this, ActivityConfig.class);
            startActivity(intent);
        }

        if (id == R.id.addEmpleado) {
            Intent intent = new Intent(this, ActivityNuevoEmpleado.class);
            intent.putExtra("id","");
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }


}