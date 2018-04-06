package com.example.joseflores.historia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.ImageView;

public class ContenidoActivity extends AppCompatActivity {

    private ImageView imgContenido;
    private EditText ediContenido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenido);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tooContenido);
        setSupportActionBar(toolbar);

        imgContenido = (ImageView) findViewById(R.id.imagenContenido);
        ediContenido = (EditText) findViewById(R.id.editcontenido);
    }
}
