package com.example.joseflores.historia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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

    private String uidEpoca;
    private String nomEpoca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tema);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbarPre);
        setSupportActionBar(toolbar);

        if (getIntent().getExtras() != null){
            uidEpoca = getIntent().getStringExtra("clave");
            nomEpoca = getIntent().getStringExtra("nombre");
        }

        this.setTitle(nomEpoca);

        inicialiceScreen();
        inicialiceQuery();
        inicialiceFirebaseOptions();
    }

    private void inicialiceScreen() {
        listView = (ListView) findViewById(R.id.lista_temas2);
        dbRef = FirebaseDatabase.getInstance().getReference(Niveles.EPOCAS).child(uidEpoca).child(Niveles.TEMA);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navegacion, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add:
                //nuevoTema();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }

    private void nuevoTema() {
        String UID = getUID();
        Tema tema = new Tema(UID, "https://img.elcomercio.pe/files/article_content_ec_fotos/uploads/2017/11/07/5a01e0d1ceb7d.jpeg",
                "Migrantes de Asia a América");
        dbRef.child(UID).setValue(tema);
    }

    private String getUID(){
        String urlArray[] = dbRef.push().toString().split("/");
        return urlArray[urlArray.length - 1];
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
                .child(uidEpoca)
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
