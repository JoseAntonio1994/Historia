package com.example.joseflores.historia.fragmentos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.joseflores.historia.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class HimnoFragment extends Fragment {

    private TextView textCancion;


    public HimnoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_himno, container, false);

        textCancion = (TextView) view.findViewById(R.id.textCancion);
        textCancion.setText(Html.fromHtml(getString(R.string.himno_nacional)));

        return view;
    }

}
