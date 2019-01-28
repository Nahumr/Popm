package com.popm.yoinvito;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.popm.yoinvito.Productos.Producto;
import com.popm.yoinvito.Productos.RecyclerAdapter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class lista_productos extends AppCompatActivity {

    RecyclerView recycle;
    List<Producto> list = new ArrayList<Producto>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_productos);
        recycle=findViewById(R.id.recycle);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recycle.setLayoutManager(llm);
        recycle.setHasFixedSize(true);
        Productos();
        RecyclerAdapter adapter = new RecyclerAdapter(list);
        recycle.setAdapter(adapter);
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


        }catch (Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG ).show();
        }


        return conexion;
    }

    public void Productos (){

        try {

            Statement statement = conexionBD().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCTOS");

            while (resultSet.next()){
                list.add(new Producto(

                        resultSet.getString("codigo_b"),
                        resultSet.getString("nombre"),
                        resultSet.getFloat("precio"),
                        resultSet.getFloat("vuelto")

                ));

            }

        }catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

    }
}
