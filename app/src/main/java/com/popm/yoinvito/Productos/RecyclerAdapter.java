package com.popm.yoinvito.Productos;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.popm.yoinvito.R;

import java.util.List;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.PersonViewHolder>{


    public static class PersonViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView nombre;
        TextView precio;
        TextView vuelto;



        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.card_view);
            nombre = (TextView)itemView.findViewById(R.id.CvNombre);
            precio = (TextView)itemView.findViewById(R.id.CvPrecio);
            vuelto = (TextView)itemView.findViewById(R.id.CvVuelto);

        }
    }

    List<Producto> productos;

    public RecyclerAdapter(List<Producto> productos){
        this.productos = productos;
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
        personViewHolder.nombre.setText(productos.get(i).getNombre());
        personViewHolder.precio.setText("Precio: $"+String.valueOf(productos.get(i).getPrecio()));
        personViewHolder.vuelto.setText("Te devolvemos: $"+String.valueOf(productos.get(i).getVuelto()));
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

}