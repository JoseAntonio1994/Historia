package com.example.joseflores.historia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.joseflores.historia.Adaptadores.TemaAdapter;
import com.example.joseflores.historia.modelos.Bloque;
import com.example.joseflores.historia.modelos.Tema;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmList;

public class TemaActivity extends AppCompatActivity implements RealmChangeListener<Bloque>, AdapterView.OnItemClickListener {

    private ListView listView;
    private TemaAdapter adapter;
    private RealmList<Tema> temas;
    private Realm realmBD;
    private List<Tema> temaList;

    private int bloqueId;
    private Bloque bloque;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tema);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbarPre);
        setSupportActionBar(toolbar);

        realmBD = Realm.getDefaultInstance();

        if (getIntent().getExtras() != null){
            bloqueId = getIntent().getExtras().getInt("id");
        }

        bloque = realmBD.where(Bloque.class).equalTo("id",bloqueId).findFirst();

        //nuevosTemas(crearTemas());
        //bloque.addChangeListener(this);
        if (bloque.getListTemas() != null)
        temas = bloque.getListTemas();

        //Toast.makeText(getApplicationContext(), String.valueOf(bloque.getListTemas()), Toast.LENGTH_SHORT).show();

        this.setTitle(bloque.getNombre());

        listView = (ListView) findViewById(R.id.lista_temas2);
        listView.setOnItemClickListener(this);
        adapter = new TemaAdapter(this, temas, R.layout.temas_item);
        listView.setAdapter(adapter);
    }



    @Override
    public void onChange(Bloque bloque) {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Toast.makeText(getApplicationContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();
    }

    private void nuevosTemas(List<Tema> crearTemas) {
        realmBD.beginTransaction();
        realmBD.copyToRealm(crearTemas);
        bloque.getListTemas().addAll(crearTemas);
        realmBD.commitTransaction();
    }

    private List<Tema> crearTemas(){
        temaList = new ArrayList<Tema>(){{
            add(new Tema("http://www.asisucedio.co/wp-content/uploads/2017/06/Porfiriato.jpg",
                    "El porfiriato"));
            add(new Tema("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRN4K9ztcEGyMojedbxD1n4pnVgqJlnxybeICW5dMUIbKtI67Fixg",
                    "La revolución mexicana"));
            add(new Tema("http://radio-corporacion.com/wp-content/uploads/2016/02/portada2.jpg",
                    "La constitución de 1917"));
            add(new Tema("https://4.bp.blogspot.com/-gdYQ8WINo9w/Uo1Fhk4DguI/AAAAAAAAF64/yTJpF3p9Cyg/s1600/REVOLUCI%C3%93N_MEXICANA_VIAJA_BONITO.jpg",
                    "La cultura revolucionaria"));
        }};
        return temaList;
    }


}
