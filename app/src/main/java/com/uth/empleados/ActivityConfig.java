package com.uth.empleados;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.uth.empleados.db.Empleados;
import com.uth.empleados.db.Helper;

public class ActivityConfig extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        setTitle("Actualizar Y Limpiar Datos");

        Button btnBorrar = findViewById(R.id.btnBorrar);

        btnBorrar.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("RESTAURAR LA LISTA DE DATOS...?");
            builder.setCancelable(true);

            builder.setPositiveButton(
                    "SI",
                    (dialog, id) -> {
                        Empleados empleados = new Empleados(this);
                        boolean res = empleados.EliminarDB();
                        if (res) {
                            Toast.makeText(this, "LA LISTA HA SIDO RESTAURADA", Toast.LENGTH_LONG).show();
                            dialog.cancel();
                        }else {
                            Toast.makeText(this, "LA LISTA NO SE PUDO RESTAURAR", Toast.LENGTH_LONG).show();
                        }
                    });

            builder.setNegativeButton(
                    "NO",
                    (dialog, id) -> dialog.cancel());

            AlertDialog alert = builder.create();
            alert.show();


        });



    }



}