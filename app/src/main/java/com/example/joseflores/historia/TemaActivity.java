package com.example.joseflores.historia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.joseflores.historia.modelos.Niveles;
import com.example.joseflores.historia.modelos.Tema;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class TemaActivity extends AppCompatActivity {

    private ListView listView;
    private FirebaseListAdapter<Tema> adapter;
    private DatabaseReference dbRef;
    private Query query;
    private FirebaseListOptions<Tema> options;

    private String nodoEpoca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tema);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbarPre);
        setSupportActionBar(toolbar);

        if (getIntent().getExtras() != null){
            nodoEpoca = getIntent().getStringExtra("clave");
            Toast.makeText(getApplicationContext(), nodoEpoca, Toast.LENGTH_SHORT).show();
        }



        inicialiceScreen();
        inicialiceQuery();
        inicialiceFirebaseOptions();
    }

    private void inicialiceScreen() {
        listView = (ListView) findViewById(R.id.lista_temas2);
        dbRef = FirebaseDatabase.getInstance().getReference(Niveles.EPOCAS);
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
                .setQuery(query, Tema.class)
                .build();
        setupAdapter();
        listView.setAdapter(adapter);
    }

    private void inicialiceQuery() {
        query = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Niveles.EPOCAS)
                .child(nodoEpoca)
                .child(Niveles.TEMA)
                .limitToLast(50);
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

}
