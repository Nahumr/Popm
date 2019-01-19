package com.popm.yoinvito;

public class Usuario {

    private String nombre, apellido_p, apellido_m, fecha_nac,correo,telefono,rfc;
    private float saldo;

    public Usuario(String nombre, String apellido_p, String apellido_m, String fecha_nac, String correo, String telefono, String rfc, float saldo) {
        this.nombre = nombre;
        this.apellido_p = apellido_p;
        this.apellido_m = apellido_m;
        this.fecha_nac = fecha_nac;
        this.correo = correo;
        this.telefono = telefono;
        this.rfc = rfc;
        this.saldo = saldo;
    }

    public String getRfc() {
        return rfc;
    }

    public float getSaldo() {
        return saldo;
    }

    public String [] getDatosP (){
        String [] datos = new String [2];

        datos[0] = nombre;
        datos[1] = apellido_p;
        datos[2] = apellido_m;

        return datos;
    }

    public static String RFC (String nombre, String apellido_p, String apellido_m, String fecha){

        String rfc="";

        rfc= apellido_p.substring(0,1)+apellido_m.substring(0,0)+nombre.substring(0,0)+fecha.substring(2,3)+fecha.substring(5,6)+fecha.substring(8,9);

        return rfc;
    }
}
