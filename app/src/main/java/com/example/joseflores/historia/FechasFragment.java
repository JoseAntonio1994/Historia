package com.example.joseflores.historia;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.joseflores.historia.modelos.Fechas;
import com.example.joseflores.historia.modelos.Niveles;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class FechasFragment extends Fragment {

    private ListView listView;

    private FirebaseListAdapter<Fechas> adapter;
    private DatabaseReference mReference;
    private FirebaseListOptions<Fechas> options;


    public FechasFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fechas, container, false);

        listView = (ListView) view.findViewById(R.id.list_fechas);

        mReference = FirebaseDatabase.getInstance().getReference().child(Niveles.FECHAS);

        inicialiceFirebaseOptions(view);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }


    private void inicialiceFirebaseOptions(View view) {

        options = new FirebaseListOptions.Builder<Fechas>()
                .setLayout(R.layout.fragment_fecha_item)
                .setQuery(mReference, Fechas.class)
                .build();

        setupAdapter(view);

        listView.setAdapter(adapter);

    }

    private void setupAdapter(View view) {

        adapter = new FirebaseListAdapter<Fechas>(options) {
            @Override
            protected void populateView(View v, Fechas model, int position) {

                ImageView imageFecha = (ImageView) v.findViewById(R.id.imageFecha);
                Glide.with(FechasFragment.this).load(model.getImageFecha()).error(R.drawable.carga).into(imageFecha);

                TextView textNom = (TextView)v.findViewById(R.id.nomFecha);
                textNom.setText(model.getNomFecha());

                TextView textDesc = (TextView) v.findViewById(R.id.descFecha);
                textDesc.setText(model.getDescFecha());

            }
        };

    }

}
