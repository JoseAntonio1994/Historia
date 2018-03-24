package com.example.joseflores.historia.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.joseflores.historia.BloqueActivity;
import com.example.joseflores.historia.R;
import com.example.joseflores.historia.TemaActivity;
import com.example.joseflores.historia.modelos.Bloque;

import java.util.List;

import io.realm.Realm;

/**
 * Created by JoseFlores on 14/03/2018.
 */

public class BloqueAdapter extends RecyclerView.Adapter<BloqueAdapter.ViewHolder> {

    private Context context;
    private List<Bloque> listBloque;
    private int layout;

    public BloqueAdapter(Context context, List<Bloque> listBloque, int layout) {
        this.context = context;
        this.listBloque = listBloque;
        this.layout = layout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewGroup) {
        View v = LayoutInflater.from(context)
                .inflate(layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Glide.with(context).load(listBloque.get(position).getUrlImagen()).error(R.drawable.bering).into(holder.urlImagenBloque);
        holder.nombreBloque.setText(listBloque.get(position).getNombre());
        holder.imageActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, String.valueOf(listBloque.get(position).getId()), Toast.LENGTH_SHORT).show();
            }
        });
        holder.imageSig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.contextBloque.getApplicationContext(), TemaActivity.class);
                intent.putExtra("id", listBloque.get(position).getId());
                holder.contextBloque.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listBloque.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView urlImagenBloque;
        TextView nombreBloque;
        Context contextBloque;
        ImageView imageSig, imageActividad;

        public ViewHolder(View v) {
            super(v);
            contextBloque = v.getContext();
            urlImagenBloque = (ImageView) v.findViewById(R.id.imagenBloque);
            nombreBloque = (TextView) v.findViewById(R.id.nombreBloque);
            imageSig = (ImageView) v.findViewById(R.id.imageSiguiente);
            imageActividad = (ImageView) v.findViewById(R.id.imageActividad);
        }
    }
    
}
