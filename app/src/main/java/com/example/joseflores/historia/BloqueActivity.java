package com.example.joseflores.historia;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


public class BloqueActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private RecyclerView recycler;
    private FirebaseRecyclerAdapter<Bloque, BloqueAdapter> adapter;
    private DatabaseReference dbRef;
    private FirebaseRecyclerOptions<Bloque> options;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navegacion);

        Toolbar toolbar = (Toolbar) findViewById(R.id.barra_bloques);
        setSupportActionBar(toolbar);

        inicialiceSreen();
        inicialiceFirebaseOptions();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (!isNetworkConnected(getApplicationContext()))
            Toast.makeText(getApplicationContext(), "No hay conexión a internet", Toast.LENGTH_SHORT).show();



    }

    //Comprobar si tenemos conexión a internet
    private boolean isNetworkConnected(Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context
                .CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info == null || !info.isConnected() || !info.isAvailable()) {
            return false;
        }
        return true;
    }

    private void inicialiceSreen() {
        recycler = (RecyclerView) findViewById(R.id.bloques_inicio);
        layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);
        recycler.setHasFixedSize(true);
        dbRef = FirebaseDatabase.getInstance().getReference().child(Niveles.EPOCAS);
    }

    private void inicialiceFirebaseOptions(){
        options = new FirebaseRecyclerOptions.Builder<Bloque>()
                .setQuery(dbRef, Bloque.class)
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

                holder.actSiguiente.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Aqui se debe ir a una actividad en donde se elaboraran preguntas dinamicas en la aplicacion por cada etapa.

                        Toast.makeText(getApplicationContext(), "Proximamente", Toast.LENGTH_SHORT).show();
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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.home) {
            // Handle the camera action
        } else if (id == R.id.bandera_mexico) {

            Intent i = new Intent(BloqueActivity.this, SeccionActivity.class);
            i.putExtra("title", "Bandera de México");
            i.putExtra("id", 0);
            startActivity(i);

        } else if (id == R.id.parque_arqueologico) {

            Intent i = new Intent(BloqueActivity.this, SeccionActivity.class);
            i.putExtra("title", "Parques Arqueologicos");
            i.putExtra("id", 1);
            startActivity(i);

        } else if (id == R.id.fechas) {

            Intent i = new Intent(BloqueActivity.this, SeccionActivity.class);
            i.putExtra("title", "Fechas importantes");
            i.putExtra("id", 2);
            startActivity(i);

        } else if (id == R.id.himno) {

            Intent i = new Intent(BloqueActivity.this, SeccionActivity.class);
            i.putExtra("title", "Hinmo Nacional");
            i.putExtra("id", 3);
            startActivity(i);

        } else if (id == R.id.salir) {

            startActivity(new Intent(BloqueActivity.this, MainActivity.class));

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
