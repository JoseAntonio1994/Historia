package com.example.joseflores.historia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.example.joseflores.historia.modelos.Bloque;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tema);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbarPre);
        setSupportActionBar(toolbar);

        if (getIntent().getExtras() != null){

        }

        listView = (ListView) findViewById(R.id.lista_temas2);

        inicialiceQuery();
        inicialiceFirebaseOptions();
    }

    private void inicialiceFirebaseOptions() {
        options = new FirebaseListOptions.Builder<Tema>()
                .setQuery(query, Tema.class)
                .build();
        setupAdapter();
    }

    private void inicialiceQuery() {
        query = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Niveles.EPOCAS)
                .limitToLast(50);
    }

    private void setupAdapter() {
        adapter = new FirebaseListAdapter<Tema>(options) {
            @Override
            protected void populateView(View v, Tema model, int position) {

            }
        };
    }
}
