package com.example.joseflores.historia;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class SeccionActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seccion_drawer);

        toolbar = (Toolbar) findViewById(R.id.tooParque);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.seccion_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_seccion);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_layout, new ParqueFragment());
        transaction.commit();

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.seccion_view);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        Fragment fragmentSelected = null;

        if (id == R.id.home) {

            startActivity(new Intent(SeccionActivity.this, BloqueActivity.class));

        } else if (id == R.id.linea_tiempo) {
            fragmentSelected = new LineaFragment();
            toolbar.setTitle("Linea del Tiempo");

        } else if (id == R.id.ruinas) {
            fragmentSelected = new ParqueFragment();
            toolbar.setTitle("Parques Arqueologicos");

        } else if (id == R.id.fechas) {

            fragmentSelected = new FechasFragment();
            toolbar.setTitle("Fechas importantes");

        } else if (id == R.id.himno) {

            fragmentSelected = new HimnoFragment();
            toolbar.setTitle("Himno Nacional");

        } else if (id == R.id.salir) {

            finish();

        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_parque, fragmentSelected);
        transaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.seccion_view);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
