package com.example.joseflores.historia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.ImageView;
import com.example.joseflores.historia.modelos.Contenido;


public class ContenidoActivity extends AppCompatActivity {

    private int bloqueId;
    private int temaId;
    private String urlImagen, nombreTema;
    private Contenido contenido;

    private ImageView imgContenido;
    private EditText ediContenido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenido);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tooContenido);
        setSupportActionBar(toolbar);

        if (getIntent().getExtras() != null){
            bloqueId = getIntent().getExtras().getInt("bloqueId");
            temaId = getIntent().getExtras().getInt("temaId");
            urlImagen = getIntent().getExtras().getString("urlImagen");
            nombreTema = getIntent().getExtras().getString("titulo");
        }

    }

}
