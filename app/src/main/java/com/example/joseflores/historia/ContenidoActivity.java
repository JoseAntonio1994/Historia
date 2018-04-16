package com.example.joseflores.historia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.joseflores.historia.modelos.Contenido;
import com.example.joseflores.historia.modelos.Niveles;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ContenidoActivity extends AppCompatActivity {

    private ImageView imgContenido;
    private TextView ediContenido;

    private DatabaseReference mReference;
    private FirebaseListAdapter<Contenido> adapter;
    private FirebaseListOptions<Contenido> options;

    private String temaUID, epoUID;
    private String imgTema;
    private String nomTema;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenido);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tooContenido);
        setSupportActionBar(toolbar);

        imgContenido = (ImageView) findViewById(R.id.imagenContenido);
        ediContenido = (TextView) findViewById(R.id.editcontenido);

        if (getIntent().getExtras() != null){
            temaUID = getIntent().getStringExtra("temUID");
            epoUID = getIntent().getStringExtra("epoUID");
            imgTema = getIntent().getStringExtra("imgTema");
            nomTema = getIntent().getStringExtra("nomTema");

            Glide.with(ContenidoActivity.this).load(imgTema).error(R.drawable.carga).into(imgContenido);

            this.setTitle(nomTema);
        }

        mReference = FirebaseDatabase.getInstance().getReference().child(Niveles.EPOCAS)
                     .child(epoUID).child(Niveles.TEMA).child(temaUID).child(Niveles.CONTENIDO);

        inicialiceFirebaseOptions();


    }

    private void inicialiceFirebaseOptions(){
        options = new FirebaseListOptions.Builder<Contenido>()
                .setLayout(R.layout.activity_contenido)
                .setQuery(mReference, Contenido.class)
                .build();
        setupAdapter();
    }

    private void setupAdapter() {
        adapter = new FirebaseListAdapter<Contenido>(options) {
            @Override
            protected void populateView(View v, Contenido model, int position) {
                Toast.makeText(getApplicationContext(), String.valueOf(model.getRecContenido()), Toast.LENGTH_SHORT).show();
            }
        };
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navegacion, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add:
                newContent();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void newContent() {
        String conUID = getUID();

        Contenido con = new Contenido();
        con.setConUID(conUID);
        con.setRecContenido(R.string.migrantes);

        mReference.child(conUID).setValue(con);
    }

    private String getUID(){
        String urlArray[] = mReference.push().toString().split("/");
        return urlArray[urlArray.length - 1];
    }
}
