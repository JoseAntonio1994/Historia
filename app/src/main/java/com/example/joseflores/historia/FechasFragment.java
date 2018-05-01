package com.example.joseflores.historia;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fechas, container, false);

        listView = (ListView) view.findViewById(R.id.list_fechas);

        mReference = FirebaseDatabase.getInstance().getReference().child(Niveles.FECHAS);

        inicialiceFirebaseOptions(view);

        return view;
    }

    private void inicialiceFirebaseOptions(View view) {



    }

}
