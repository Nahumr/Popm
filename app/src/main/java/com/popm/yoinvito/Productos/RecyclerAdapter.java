package com.popm.yoinvito.Productos;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.popm.yoinvito.R;

import java.util.List;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.PersonViewHolder> implements AdapterView.OnItemSelectedListener {


    public static class PersonViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView nombre;
        TextView precio;
        TextView vuelto;
        Spinner cantidad;
        Button agregar;

        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.card_view);
            nombre = (TextView)itemView.findViewById(R.id.CvNombre);
            precio = (TextView)itemView.findViewById(R.id.CvPrecio);
            vuelto = (TextView)itemView.findViewById(R.id.CvVuelto);
            cantidad = (Spinner)itemView.findViewById(R.id.CvCantidad);

        }
    }

    Context context;
    List<Producto> productos;

    public RecyclerAdapter(List<Producto> productos, Context context){
        this.productos = productos;
        this.context = context;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.producto, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, R.array.cantidad_array,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        personViewHolder.cantidad.setAdapter(adapter);
        personViewHolder.nombre.setText(productos.get(i).getNombre());
        personViewHolder.precio.setText("Precio: $"+String.valueOf(productos.get(i).getPrecio()));
        personViewHolder.vuelto.setText("Te devolvemos: $"+String.valueOf(productos.get(i).getVuelto()));

    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

}