package com.example.joseflores.historia.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.joseflores.historia.R;
import com.example.joseflores.historia.modelos.Contenido;

import java.util.List;

/**
 * Created by JoseFlores on 06/04/2018.
 */

public class ContenidoAdapter extends BaseAdapter {

    private Context context;
    private List<Contenido> listContenido;
    private int layout;

    public ContenidoAdapter(Context context, List<Contenido> listContenido, int layout) {
        this.context = context;
        this.listContenido = listContenido;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return listContenido.size();
    }

    @Override
    public Object getItem(int position) {
        return listContenido.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder vh;

        if (convertView != null){
            convertView = LayoutInflater.from(context).inflate(layout, null);
            vh = new ViewHolder();
            vh.imgContenido = (ImageView) convertView.findViewById(R.id.imagenContenido);
            vh.ediContenido = (EditText) convertView.findViewById(R.id.editcontenido);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }

        Contenido contenido = listContenido.get(position);
        Glide.with(context).load(contenido.getUrlContenido()).error(R.mipmap.caratura).into(vh.imgContenido);
        vh.ediContenido.setText(context.getString(contenido.getRecContenido()));

        return convertView;
    }

    public class ViewHolder{
        ImageView imgContenido;
        EditText ediContenido;
    }
}
