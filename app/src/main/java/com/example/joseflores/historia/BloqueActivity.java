package com.example.joseflores.historia;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.joseflores.historia.modelos.Bloque;
import com.example.joseflores.historia.modelos.Niveles;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


public class BloqueActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private RecyclerView recycler;
    private FirebaseRecyclerAdapter<Bloque, BloqueAdapter> adapter;
    private DatabaseReference dbRef;
    private Query query;
    private FirebaseRecyclerOptions<Bloque> options;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navegacion);

        Toolbar toolbar = (Toolbar) findViewById(R.id.barra_bloques);
        setSupportActionBar(toolbar);

        inicialiceSreen();
        inicialiceQuery();
        inicialiceFirebaseOptions();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);





    }

    private void inicialiceSreen() {
        recycler = (RecyclerView) findViewById(R.id.bloques_inicio);
        layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);
        recycler.setHasFixedSize(true);
        dbRef = FirebaseDatabase.getInstance().getReference().child(Niveles.EPOCAS);
    }

    private void inicialiceQuery() {
        query = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Niveles.EPOCAS);
    }

    private void inicialiceFirebaseOptions(){
        options = new FirebaseRecyclerOptions.Builder<Bloque>()
                .setQuery(query, Bloque.class)
                .build();
        setupAdapter();
        recycler.setAdapter(adapter);
    }

    private void setupAdapter() {
        adapter = new FirebaseRecyclerAdapter<Bloque, BloqueAdapter>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final BloqueAdapter holder, final int position, @NonNull final Bloque model) {
                Glide.with(BloqueActivity.this).load(model.getImagenEpoca())
                        .error(R.drawable.carga)
                        .into(holder.imagenBloque);
                holder.nombreBloque.setText(model.getNombreEpoca());

                holder.temaSiguiente.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String clave = model.getUID();
                        Intent i = new Intent(BloqueActivity.this, TemaActivity.class);
                        i.putExtra("clave", clave);
                        i.putExtra("nombre", model.getNombreEpoca());
                        startActivity(i);
                    }
                });
            }

            @NonNull
            @Override
            public BloqueAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bloques_item, parent, false);

                return new BloqueAdapter(view);
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
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_settings:
                return true;
            case R.id.action_add:
               //newBlock();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }

    private void newBlock() {
        String UID = getUID();
        Bloque bloque = new Bloque(UID, "http://indicepolitico.com/wp-content/uploads/2016/10/agricultura-prehispanica-01.png","Epoca prehispanica");

        dbRef.child(UID).setValue(bloque);
    }

    private String getUID(){
        String urlArray[] = dbRef.push().toString().split("/");
        return urlArray[urlArray.length - 1];
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navegacion, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static class BloqueAdapter extends RecyclerView.ViewHolder{
        ImageView imagenBloque;
        TextView nombreBloque;
        ImageView temaSiguiente;
        ImageView actSiguiente;

        public BloqueAdapter(View view) {
            super(view);
            imagenBloque = (ImageView) view.findViewById(R.id.imagenBloque);
            nombreBloque = (TextView) view.findViewById(R.id.nombreBloque);
            temaSiguiente = (ImageView) view.findViewById(R.id.imageSiguiente);
            actSiguiente = (ImageView) view.findViewById(R.id.imageActividad);
        }
    }
}
