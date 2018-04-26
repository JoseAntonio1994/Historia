package com.example.joseflores.historia;

import android.support.annotation.NonNull;
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

import com.bumptech.glide.Glide;
import com.example.joseflores.historia.modelos.Contenido;
import com.example.joseflores.historia.modelos.Niveles;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ContenidoActivity extends AppCompatActivity {

    private ImageView imgContenido;
    private TextView ediContenido;

    private DatabaseReference mReference;

    private RecyclerView mRecycler;
    private FirebaseRecyclerAdapter<Contenido, ContentAdapter> adapter;
    private FirebaseRecyclerOptions<Contenido> options;
    private RecyclerView.LayoutManager layoutManager;

    private String temaUID, epoUID;
    private String imgTema;
    private String nomTema;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_content);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tooContent);
        setSupportActionBar(toolbar);

        mRecycler =(RecyclerView) findViewById(R.id.recycler_content);
        layoutManager = new LinearLayoutManager(this);
        mRecycler.setLayoutManager(layoutManager);
        mRecycler.setHasFixedSize(true);

        imgContenido = (ImageView) findViewById(R.id.imagenContenido);
        ediContenido = (TextView) findViewById(R.id.editcontenido);

        if (getIntent().getExtras() != null){
            temaUID = getIntent().getStringExtra("temUID");
            epoUID = getIntent().getStringExtra("epoUID");
            imgTema = getIntent().getStringExtra("imgTema");
            nomTema = getIntent().getStringExtra("nomTema");

            this.setTitle(nomTema);

            mReference = FirebaseDatabase.getInstance().getReference().child(Niveles.EPOCAS)
                    .child(epoUID).child(Niveles.TEMA).child(temaUID).child(Niveles.CONTENIDO);
        }



        inicialiceFirebaseOptions();


    }

    private void inicialiceFirebaseOptions(){
        options = new FirebaseRecyclerOptions.Builder<Contenido>()
                .setQuery(mReference, Contenido.class)
                .build();
        setupAdapter();
        mRecycler.setAdapter(adapter);
    }

    private void setupAdapter() {
        adapter = new FirebaseRecyclerAdapter<Contenido, ContentAdapter>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ContentAdapter holder, int position, @NonNull Contenido model) {
                Glide.with(ContenidoActivity.this).load(model.getImgContenido()).error(R.drawable.carga).into(holder.imgContent);
                holder.recContent.setText(getString(model.getRecContenido()));
            }

            @NonNull
            @Override
            public ContentAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_item, parent, false);
                return new ContentAdapter(view);
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

    private void newContent() {
        String conUID = getUID();

        Contenido con = new Contenido();
        con.setConUID(conUID);
        con.setImgContenido(imgTema);
        con.setRecContenido(R.string.aridoamerica);

        mReference.child(conUID).setValue(con);
    }

    private String getUID(){
        String urlArray[] = mReference.push().toString().split("/");
        return urlArray[urlArray.length - 1];
    }

    public static class ContentAdapter extends RecyclerView.ViewHolder{

        ImageView imgContent;
        TextView recContent;

        public ContentAdapter(View itemView) {
            super(itemView);
            imgContent = (ImageView) itemView.findViewById(R.id.imagenContenido);
            recContent = (TextView) itemView.findViewById(R.id.editcontenido);
        }
    }
}
