package com.example.joseflores.historia;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.joseflores.historia.modelos.Niveles;
import com.example.joseflores.historia.modelos.ParqueArqueologico;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ParqueFragment extends Fragment {

    private DatabaseReference mReference;
    private FirebaseRecyclerAdapter<ParqueArqueologico, ParqueAdapter> adapter;
    private FirebaseRecyclerOptions<ParqueArqueologico> options;

    private RecyclerView mRecycler;
    private RecyclerView.LayoutManager mLayoutManager;

    public ParqueFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_parque, container, false);

        mRecycler = (RecyclerView) view.findViewById(R.id.recycler_parque);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecycler.setLayoutManager(mLayoutManager);
        mRecycler.setHasFixedSize(true);
        mReference = FirebaseDatabase.getInstance().getReference().child(Niveles.PARQUES);

        consulta(mReference);

        return view;
    }

    private void consulta(DatabaseReference reference) {
        options = new FirebaseRecyclerOptions.Builder<ParqueArqueologico>()
                .setQuery(mReference, ParqueArqueologico.class)
                .build();
        setupAdapter();
        mRecycler.setAdapter(adapter);
    }

    private void setupAdapter() {
        adapter = new FirebaseRecyclerAdapter<ParqueArqueologico, ParqueAdapter>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ParqueAdapter holder, int position, @NonNull ParqueArqueologico model) {

                Glide.with(ParqueFragment.this).load(model.getUrlParque()).error(R.drawable.carga).into(holder.urlImagen);

                holder.nombreParque.setText(model.getNomParque());

                holder.descripcionParque.setText(model.getDescParque());

                holder.urlImagen.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "Imagen", Toast.LENGTH_SHORT).show();
                    }
                });

            }

            @NonNull
            @Override
            public ParqueAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.parque_fragment_item, parent, false);

                return new ParqueAdapter(view1);
            }
        };
    }


    public static class ParqueAdapter extends RecyclerView.ViewHolder{

        ImageView urlImagen;
        TextView nombreParque;
        TextView descripcionParque;

        public ParqueAdapter(View view) {
            super(view);

            urlImagen = (ImageView) view.findViewById(R.id.urlParque);
            nombreParque = (TextView) view.findViewById(R.id.textTitParque);
            descripcionParque = (TextView) view.findViewById(R.id.textDesParque);
        }
    }
}
