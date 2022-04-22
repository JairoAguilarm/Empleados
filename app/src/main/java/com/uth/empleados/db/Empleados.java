package com.uth.empleados.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Map;

public class Empleados extends Helper {
    Context context;

    public Empleados(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    /** INSERTAR
     * INSERTA LOS REGISTROS A LA TABLA
     * @param Nombres
     * @param Apellidos
     * @param Edad
     * @param Direccion
     * @param Puesto
     * @return
     */
    public long insertEmpleado(String Nombres, String Apellidos, String Edad, String Direccion, String Puesto){
        long id = 0;
        try {
            Helper helper = new Helper(context);
            SQLiteDatabase db = helper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("tb_nombres", Nombres);
            values.put("tb_apellidos", Apellidos);
            values.put("tb_edad", Edad);
            values.put("tb_direccion", Direccion);
            values.put("tb_puesto", Puesto);

            id = db.insert(DATA_TABLE,null,values);

        }catch (Exception e){
            Log.d("TAG Exception", "insertEmpleado: "+ e);
        }

        return id;
    }


    /** CONSULTAR
     * CONSULTA EL LISTADO DE EMPLEADOS DE LA BASE DE DATOS Y LOS MUESTRA EN EL RECYCLER VIEW
     * @return
     */
    @SuppressLint("Recycle")
    public ArrayList<ConstructorEmpleado> getEmpleados(){

        ArrayList<ConstructorEmpleado> list = new ArrayList<>();
        ConstructorEmpleado constructorEmpleado;
        Cursor cursorEmpleados;

        try {
            Helper helper = new Helper(context);
            SQLiteDatabase db = helper.getReadableDatabase();

            cursorEmpleados = db.rawQuery("SELECT * FROM " + DATA_TABLE, null);

            if (cursorEmpleados.moveToFirst()) {
                do {

                    constructorEmpleado = new ConstructorEmpleado();
                    constructorEmpleado.setId(cursorEmpleados.getInt(0));
                    constructorEmpleado.setTb_nombres(cursorEmpleados.getString(1));
                    constructorEmpleado.setTb_apellidos(cursorEmpleados.getString(2));
                    constructorEmpleado.setTb_edad(cursorEmpleados.getString(3));
                    constructorEmpleado.setTb_direccion(cursorEmpleados.getString(4));
                    constructorEmpleado.setTb_puesto(cursorEmpleados.getString(5));
                    list.add(constructorEmpleado);

                } while (cursorEmpleados.moveToNext());
            }

            cursorEmpleados.close();

        }catch (Exception e){
            Log.d("TAG", "getEmpleados: "+e);
        }

        return list;
    }

    /** ACTUALIZAR
     * ACTUALIZA LOS VALORES DE LOS EMPLEADOS
     * @param id
     * @param Nombre
     * @param Apellido
     * @param Edad
     * @param Direccion
     * @param Puesto
     * @return
     */
    public boolean updateEmpleados(String id, String Nombre, String Apellido, String Edad, String Direccion, String Puesto){
        boolean res = false;

        Helper helper = new Helper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        try{

            db.execSQL("UPDATE " + DATA_TABLE +
                    " SET tb_nombres = '"+ Nombre +"', " +
                    " tb_apellidos = '" + Apellido + "', " +
                    " tb_edad = '" + Edad + "'," +
                    " tb_direccion = '" + Direccion + "', " +
                    " tb_puesto = '" + Puesto + "' " +
                    " WHERE id = '" + Integer.parseInt(id) + "'" );

            res = true;

        }catch (Exception e){
            Log.d("TAG", "updateEmpleados: "+ e);
        }finally {
            db.close();
        }

        return res;
    }

    /** ELIMINAR ENPLEADOS
     * eLIMINA LOS EMPLEADOSPOR ID
     * @param id
     * @return
     */

    public boolean eliminarEmpleados(String id){
        boolean res = false;

        Helper helper = new Helper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        try{

            db.execSQL("DELETE FROM " + DATA_TABLE + " WHERE id = '" + Integer.parseInt(id) + "'" );

            res = true;

        }catch (Exception e){
            Log.d("TAG", "eliminarEmpleados: "+ e);
        }finally {
            db.close();
        }

        return res;
    }


    /** ELIMINAR BASE DE DATOS
     * ELIMINA LA BASE DE DATOS Y RESTAURA LOS VALORES
     * @return
     */
    public  boolean EliminarDB(){
        boolean res = false;

        Helper helper = new Helper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        try{
            context.deleteDatabase(DATABASE_NAME);
            res = true;

        }catch (Exception e){
            Log.d("TAG", "eliminarEmpleados: "+ e);
        }finally {
            db.close();
        }

        return res;



    }




}
