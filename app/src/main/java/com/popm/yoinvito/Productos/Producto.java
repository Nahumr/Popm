package com.popm.yoinvito.Productos;

public class Producto {


    public Producto(String codigo_barras, String nombre, float precio, float vuelto) {
        this.codigo_barras = codigo_barras;
        this.nombre = nombre;
        this.precio = precio;
        this.vuelto = vuelto;
    }
    public Producto (){}

    public String getCodigo_barras() {
        return codigo_barras;
    }

    public String getNombre() {
        return nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public float getVuelto() {
        return vuelto;
    }


    String codigo_barras, nombre;
    float precio,vuelto;

}
