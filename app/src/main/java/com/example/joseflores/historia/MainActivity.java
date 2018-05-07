package com.example.joseflores.historia;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnMenu;
    private Button btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMenu = (Button) findViewById(R.id.btnMenu);
        btnSalir = (Button) findViewById(R.id.btnSalir);

        btnMenu.setOnClickListener(this);
        btnSalir.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnMenu){
            startActivity(new Intent(MainActivity.this, BloqueActivity.class));
        }else if (v.getId() == R.id.btnSalir){

            System.exit(0);

            finish();

        }
    }
}
