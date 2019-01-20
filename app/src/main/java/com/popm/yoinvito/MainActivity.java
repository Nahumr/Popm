

package com.popm.yoinvito;

import android.content.Intent;
import android.database.SQLException;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mapa = (Button) findViewById(R.id.mapa);

        mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), localiza_tienda.class);
                startActivityForResult(intent, 0);
            }
        });

    }

    public Connection conexionBD(){
        Connection conexion = null;

        try{

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();

            conexion = DriverManager.getConnection("jdbc:jtds:sqlserver://200.38.35.62; databaseName = XLR8: user = popmobile2: password = R3c4rg4f4c1l2.16#");

            Toast.makeText(getApplicationContext(), "Conexion exitosa", Toast.LENGTH_LONG ).show();

        }catch (Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG ).show();
        }


        return conexion;
    }



    public void agregarUsuario(){

        try{

            PreparedStatement pst = conexionBD().prepareStatement("INSERT INTO ");
            pst.setString(1, "Dato");
            pst.executeUpdate();
        }catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

    }
}
