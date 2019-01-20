package com.popm.yoinvito;

public class Tienda {


    /*
            id_tienda INT NOT NULL UNIQUE,
            nombre VARCHAR (40) NOT NULL,
            latitud_x FLOAT(7) NOT NULL,
            latitud_y FLOAT(7) NOT NULL,
            calif INT NOT NULL,
            PRIMARY KEY (id_tienda)
    * */


    int id_tienda, calif;
    String nombre;
    float latitud_x ,latitud_y;


    Tienda(){

    }

    public Tienda(int id_tienda, int calif, String nombre, float latitud_x, float latitud_y) {
        this.id_tienda = id_tienda;
        this.calif = calif;
        this.nombre = nombre;
        this.latitud_x = latitud_x;
        this.latitud_y = latitud_y;
    }

    public float getLatitud_x() {
        return latitud_x;
    }

    public float getLatitud_y() {
        return latitud_y;
    }

    public int getId_tienda() {
        return id_tienda;
    }

    public String getNombre() {
        return nombre;
    }


}
