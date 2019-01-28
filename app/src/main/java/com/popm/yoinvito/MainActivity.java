

package com.popm.yoinvito;

import android.content.Intent;
import android.os.StrictMode;
import android.service.autofill.RegexValidator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {


    private EditText nombre_ed, apellido_p_ed, apellido_m_ed, fecha_nac_ed,correo_ed,telefono_ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);


        nombre_ed = (EditText) findViewById(R.id.registro_Nombre);
        apellido_p_ed = (EditText) findViewById(R.id.registro_apellidoP);
        apellido_m_ed = (EditText) findViewById(R.id.registro_apellidoM);
        fecha_nac_ed = (EditText) findViewById(R.id.registro_fnacimiento);
        correo_ed = (EditText) findViewById(R.id.registro_correo);
        telefono_ed = (EditText) findViewById(R.id.registro_telefono);

        Button mapa = (Button) findViewById(R.id.registrar);

        mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //registrarUsuario();

                Intent ListSong = new Intent(getApplicationContext(), lista_productos.class);
                startActivity(ListSong);



            }
        });

    }

    public Connection conexionBD(){
         Connection conexion= null;

        try{

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();


           /* conexion = DriverManager.getConnection(""
                                + "jdbc:jtds:sqlserver://200.38.35.62:1433/XLR8;"
                                + "user=popmobile2;password=R3c4rg4f4c1l2.16#;");*/

          conexion = DriverManager.getConnection(""
                    + "jdbc:jtds:sqlserver://192.168.100.5/XLR8;"
                    + "user=SA;password=Rodriguez1$;");


            Toast.makeText(getApplicationContext(), "Conexion exitosa", Toast.LENGTH_LONG ).show();

        }catch (Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG ).show();
        }


        return conexion;
    }


    public void registrarUsuario(){

        try{

            //PreparedStatement pst = conexionBD().prepareStatement("INSERT INTO USUARIO (nombre, apellido_p, apellido_m, fecha_nac, correo, sexo, saldo, rfc, telefono) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            PreparedStatement pst = conexionBD().prepareStatement("INSERT INTO USUARIO VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

            pst.setString(1 , datosPersonales(nombre_ed));
            pst.setString(2 , datosPersonales(apellido_p_ed));
            pst.setString(3 , datosPersonales(apellido_m_ed) );
            pst.setDate(4 , datoFecha(fecha_nac_ed) );
            pst.setString(5 , datoCorreo(correo_ed));
            pst.setString(6 , "I");
            pst.setFloat(7 , 0);
            pst.setString(8, Usuario.RFC(validaDatos(datosPersonales(apellido_p_ed)),validaDatos(datosPersonales(apellido_m_ed)), validaDatos(datosPersonales(nombre_ed)),datoFechaS(fecha_nac_ed)));
            pst.setString(9 , datoTelefono(telefono_ed));

            pst.executeUpdate();

            Toast.makeText(getApplicationContext(),"Usuario creado exitosamente",Toast.LENGTH_LONG).show();

        }catch (SQLException e) {

            Toast.makeText(getApplicationContext(),"ERROR",Toast.LENGTH_LONG).show();
            Log.e("ERROR", e.getMessage());
        }

    }


    public String datosPersonales (EditText campo){

            String data = campo.getText().toString().toUpperCase();

            if (data == ""){
                Toast.makeText(getApplicationContext(), "Hay un campo vacio!", Toast.LENGTH_LONG).show();
                return "#1";
            }

            if(Pattern.matches("[A-Za-zäÄëËïÏöÖüÜáéíóúáéíóúÁÉÍÓÚÂÊÎÔÛâêîôûàèìòùÀÈÌÒÙ.-]+",data.trim())){
                return data;
            }else{
                return "#1";
            }
    }


    public Date datoFecha (EditText campo){

        Integer [] fechas = new Integer [3];

        String dato = campo.getText().toString().trim();



        if( Pattern.matches("\\d{4}-[01]\\d-[0-3]\\d",dato) ){

            String [] dta = dato.split("-");
            fechas[0] = Integer.valueOf(dta[0]);
            fechas[1] = Integer.valueOf(dta[1]);
            fechas[2] = Integer.valueOf(dta[2]);

            Date fecha = new Date(fechas[0],fechas[1],fechas[2]);

            return fecha;

        }else {

            java.util.Date fecha = new java.util.Date();
            return new Date(fecha.getTime());
        }

    }

    public String datoFechaS (EditText campo){

        String dato = campo.getText().toString().trim();

        if( Pattern.matches("\\d{4}-[01]\\d-[0-3]\\d",dato) ){
            return dato;

        }else{
            java.util.Date fecha = new java.util.Date();
            return new Date(fecha.getTime()).toString();
        }

    }
    public String datoTelefono (EditText campo){

        String telefono = "";

        telefono = campo.getText().toString().trim();

        if (telefono == ""){
            return "#1";
        }


        if( Pattern.matches("[0-9]{10}",telefono) ){
            return telefono;
        }

        return telefono;
    }

    public String datoCorreo(EditText campo){
        String correo = campo.getText().toString();

        if(correo == ""){
            return "#1";
        }

        if( Pattern.matches("[A-Za-z0-9]+@[A-Za-z0-9]+\\.[A-Za-z0-9]+",correo) ){
            return correo;
        }else return "#1";
    }

    public String validaDatos (String dato){

        String [] datos = dato.split(" ");

        if (datos.length>1)
            return datos[0];
        else
            return dato;
    }

}
