package com.uth.empleados;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.uth.empleados.db.Empleados;

public class ActivityNuevoEmpleado extends AppCompatActivity {

    String Id,Nombre,Apellido,Edad,Direccion,Puesto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_empleado);


        Button btnCancelar = findViewById(R.id.btnCancelar);
        Button btnGuardar = findViewById(R.id.btnGuardar);

        btnCancelar.setOnClickListener(view -> finish());

        EditText txtNombre, txtApellido, txtEdad, txtDireccion, txtPuesto;

        txtNombre = findViewById(R.id.txtNombre);
        txtApellido = findViewById(R.id.txtApellido);
        txtEdad = findViewById(R.id.txtEdad);
        txtDireccion = findViewById(R.id.txtDireccion);
        txtPuesto = findViewById(R.id.txtPuesto);

        Bundle bundle = getIntent().getExtras();

        if (!bundle.get("id").equals("")){

            Id = bundle.get("id").toString();
            Nombre = bundle.get("Nombre").toString();
            Apellido = bundle.get("Apellido").toString();
            Edad = bundle.get("Edad").toString();
            Direccion = bundle.get("Direccion").toString();
            Puesto = bundle.get("Puesto").toString();

            txtNombre.setText(Nombre);
            txtApellido.setText(Apellido);
            txtEdad.setText(Edad);
            txtDireccion.setText(Direccion);
            txtPuesto.setText(Puesto);

            setTitle("Información del Empleado");

        }else {
            setTitle("Nuevo Empleado");
            btnCancelar.setVisibility(View.GONE);
        }

        btnGuardar.setOnClickListener(view -> {
            if (bundle.get("id").equals("")){

                Empleados empleados = new Empleados(this);
                long id = empleados.insertEmpleado(
                        txtNombre.getText().toString(),
                        txtApellido.getText().toString(),
                        txtEdad.getText().toString(),
                        txtDireccion.getText().toString(),
                        txtPuesto.getText().toString()
                );

                if (id > 0){
                    Toast.makeText(this,"EL REGISTRO HA SIDO GUARDADO",Toast.LENGTH_LONG).show();
                    txtNombre.setText("");
                    txtApellido.setText("");
                    txtEdad.setText("");
                    txtDireccion.setText("");
                    txtPuesto.setText("");
                }else{
                    Toast.makeText(this,"EL REGISTRO NO SE HA PODIDO GUARDADO",Toast.LENGTH_LONG).show();
                }

            }else{

                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setMessage("CONFIRMA REALIZAR CAMBIOS EN LOS DATOS...?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "SI",
                        (dialog, id) -> {

                            Empleados empleados = new Empleados(this);

                            boolean res = empleados.updateEmpleados(
                                    Id,
                                    txtNombre.getText().toString(),
                                    txtApellido.getText().toString(),
                                    txtEdad.getText().toString(),
                                    txtDireccion.getText().toString(),
                                    txtPuesto.getText().toString()
                            );

                            if (res){
                                Toast.makeText(this,"EL DATO HA SIDO ACTUALIZADO",Toast.LENGTH_LONG).show();

                            }else{
                                Toast.makeText(this,"EL DATO NO SE HA PODIDO ACTUALIZAR",Toast.LENGTH_LONG).show();

                            }

                            dialog.cancel();

                        });

                builder1.setNegativeButton(
                        "NO",
                        (dialog, id) -> dialog.cancel());

                AlertDialog alert11 = builder1.create();
                alert11.show();

            }


        });

        btnCancelar.setOnClickListener(view -> {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("CONFIRMA ELIMINAR ESTE DATO...?");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "SI",
                    (dialog, id) -> {

                        Empleados empleados = new Empleados(this);

                        boolean res = empleados.eliminarEmpleados(Id);

                        if (res){
                            Toast.makeText(this,"EL DATO HA SIDO BORRADO",Toast.LENGTH_LONG).show();
                            txtNombre.setEnabled(false);
                            txtApellido.setEnabled(false);
                            txtEdad.setEnabled(false);
                            txtDireccion.setEnabled(false);
                            txtPuesto.setEnabled(false);

                            btnCancelar.setEnabled(false);
                            btnGuardar.setEnabled(false);

                        }else{
                            Toast.makeText(this,"EL DATO NO SE HA PODIDO BORRAR",Toast.LENGTH_LONG).show();
                        }

                        dialog.cancel();

                    });

            builder1.setNegativeButton(
                    "NO",
                    (dialog, id) -> dialog.cancel());

            AlertDialog alert11 = builder1.create();
            alert11.show();
        });



    }



}