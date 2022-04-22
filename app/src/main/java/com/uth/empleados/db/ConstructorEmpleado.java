package com.uth.empleados.db;

public class ConstructorEmpleado {

    private int id;
    private String tb_nombres,tb_apellidos,tb_edad,tb_direccion,tb_puesto;

    public ConstructorEmpleado() {
    }

    public ConstructorEmpleado(int id, String tb_nombres, String tb_apellidos, String tb_edad, String tb_direccion, String tb_puesto) {
        this.id = id;
        this.tb_nombres = tb_nombres;
        this.tb_apellidos = tb_apellidos;
        this.tb_edad = tb_edad;
        this.tb_direccion = tb_direccion;
        this.tb_puesto = tb_puesto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTb_nombres() {
        return tb_nombres;
    }

    public void setTb_nombres(String tb_nombres) {
        this.tb_nombres = tb_nombres;
    }

    public String getTb_apellidos() {
        return tb_apellidos;
    }

    public void setTb_apellidos(String tb_apellidos) {
        this.tb_apellidos = tb_apellidos;
    }

    public String getTb_edad() {
        return tb_edad;
    }

    public void setTb_edad(String tb_edad) {
        this.tb_edad = tb_edad;
    }

    public String getTb_direccion() {
        return tb_direccion;
    }

    public void setTb_direccion(String tb_direccion) {
        this.tb_direccion = tb_direccion;
    }

    public String getTb_puesto() {
        return tb_puesto;
    }

    public void setTb_puesto(String tb_puesto) {
        this.tb_puesto = tb_puesto;
    }
}
