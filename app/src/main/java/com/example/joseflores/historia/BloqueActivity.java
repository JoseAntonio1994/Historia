package com.example.joseflores.historia;

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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.joseflores.historia.Adaptadores.BloqueAdapter;
import com.example.joseflores.historia.modelos.Bloque;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class BloqueActivity extends AppCompatActivity implements RealmChangeListener<RealmResults<Bloque>>,
        AdapterView.OnItemClickListener, NavigationView.OnNavigationItemSelectedListener{

    private Realm realmBD;
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private RealmResults<Bloque> bloques;

    private List<Bloque> bloques1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navegacion);

        realmBD = Realm.getDefaultInstance();
        //nuevoBloque(crearBloques());
        bloques = realmBD.where(Bloque.class).findAll();

        Toolbar toolbar = (Toolbar) findViewById(R.id.barra_bloques);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        recycler = (RecyclerView) findViewById(R.id.bloques_inicio);
        recycler.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);

       adapter = new BloqueAdapter(this, bloques, R.layout.bloques_item);
       recycler.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        //Intent intent = new Intent(BloqueActivity.this, TemaActivity.class);
        //intent.putExtra("id", bloques.get(position).getId());
        //startActivity(intent);
        Toast.makeText(getApplicationContext(), "Click", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onChange(RealmResults<Bloque> bloques) {

    }


    private void nuevoBloque(List<Bloque> crearBloques) {
        realmBD.beginTransaction();
        realmBD.insert(crearBloques);
        realmBD.commitTransaction();
    }

    private List<Bloque> crearBloques(){
        bloques1 = new ArrayList<Bloque>(){{
            add(new Bloque("https://www.mexicodesconocido.com.mx/sites/default/files/styles/adaptive/public/nodes/4694/comercio-prehispanico.jpg",
                    "México prehispánico"));
            add(new Bloque("http://de10.com.mx/sites/default/files/styles/detalle_nota/public/2016/11/30/portada_cortes.jpg?itok=porYkDv7",
                    "La conquista de México y el virreinato"));
            add(new Bloque("http://1.bp.blogspot.com/-_sDlzONZjPQ/VEkrgk8XfbI/AAAAAAAABH0/qiPeneEKS5E/s1600/independencia-mexico-mural-fuerte-san-diego-acapulco.jpg",
                    "La independencia de México"));
            add(new Bloque("https://pbs.twimg.com/media/CzUwBTbUUAEXawB.jpg",
                    "México independiente"));
            add(new Bloque("https://lahistoriamexicana.mx/wp-content/uploads/revolucion_mexicana-660x330.jpg",
                    "La Revolucion Mexicana"));
        }};
        return bloques1;
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
}
