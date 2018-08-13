package com.example.joseflores.historia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.joseflores.historia.Constantes.Util;
import com.example.joseflores.historia.modelos.Niveles;
import com.example.joseflores.historia.modelos.Tema;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class TemaActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listView;
    private FirebaseListAdapter<Tema> adapter;
    private DatabaseReference dbRef;
    private FirebaseListOptions<Tema> options;

    private String uidEpoca;
    private String nomEpoca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tema);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbarPre);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        if (getIntent().getExtras() != null){
            uidEpoca = getIntent().getStringExtra(Util.CLAVE);
            nomEpoca = getIntent().getStringExtra(Util.NOMBRE);
        }

        this.setTitle(nomEpoca);

        inicialiceScreen();
        inicialiceFirebaseOptions();
    }

    private void inicialiceScreen() {
        listView = (ListView) findViewById(R.id.lista_temas2);
        dbRef = FirebaseDatabase.getInstance().getReference(Niveles.EPOCAS).child(uidEpoca).child(Niveles.TEMA);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    private void inicialiceFirebaseOptions() {
        options = new FirebaseListOptions.Builder<Tema>()
                .setLayout(R.layout.temas_item)
                .setQuery(dbRef, Tema.class)
                .build();
        setupAdapter();
        listView.setOnItemClickListener(this);
        listView.setAdapter(adapter);
    }


    private void setupAdapter() {
        adapter = new FirebaseListAdapter<Tema>(options) {
            @Override
            protected void populateView(View v, Tema model, int position) {
                ImageView imagenTema = (ImageView) v.findViewById(R.id.imagenTema);
                Glide.with(TemaActivity.this).load(model.getImagenTema())
                        .error(R.drawable.carga).into(imagenTema);

                TextView nombreTema = (TextView)v.findViewById(R.id.nombreTema);
                nombreTema.setText(model.getNombreTema());
            }
        };
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Tema tema = (Tema) listView.getItemAtPosition(position);
        Intent intent = new Intent(TemaActivity.this, ContenidoActivity.class);
        intent.putExtra("temUID", tema.getUID());
        intent.putExtra("epoUID", uidEpoca);
        intent.putExtra("nomTema", tema.getNombreTema());
        intent.putExtra("imgTema", tema.getImagenTema());
        startActivity(intent);
    }
}
