package com.example.joseflores.historia.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.joseflores.historia.R;
import com.example.joseflores.historia.modelos.Tema;

import java.util.List;

/**
 * Created by JoseFlores on 14/03/2018.
 */

public class TemaAdapter extends BaseAdapter {

    private Context context;
    private List<Tema> listTemas;
    private int layout;

    public TemaAdapter(Context context, List<Tema> listTemas, int layout) {
        this.context = context;
        this.listTemas = listTemas;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return listTemas.size();
    }

    @Override
    public Object getItem(int i) {
        return listTemas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder vh;

        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(layout, null);
            vh = new ViewHolder();
            vh.caratura = (ImageView) convertView.findViewById(R.id.caratura);
            vh.tema = (TextView) convertView.findViewById(R.id.tema);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }

        Tema tema = listTemas.get(position);
        Glide.with(context).load(tema.getUrlImagen()).error(R.mipmap.caratura).into(vh.caratura);
        vh.tema.setText(tema.getNombre());

        return convertView;
    }

    public class ViewHolder{
        ImageView caratura;
        TextView tema;
    }
}
