package com.example.materialdesignb;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;

public class Fragment2 extends Fragment {

    Chip Sistemas, Contabilidad, RRHH;
    TextView txtArea;

    List<Trabajador> listaTrabajadores = new ArrayList<>();
    List<Trabajador> listaTemporal = new ArrayList<>();

    public Fragment2() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2, container, false);

        Sistemas = view.findViewById(R.id.Sistemas);
        Contabilidad = view.findViewById(R.id.Contabilidad);
        RRHH = view.findViewById(R.id.RRHH);
        txtArea = view.findViewById(R.id.txtTrabajadores);

        listaTrabajadores.add(new Trabajador("Sergio", "Sistemas"));
        listaTrabajadores.add(new Trabajador("Mario", "Sistemas"));
        listaTrabajadores.add(new Trabajador("Ana", "Contabilidad"));
        listaTrabajadores.add(new Trabajador("Cesar", "Contabilidad"));
        listaTrabajadores.add(new Trabajador("Alejandro", "RRHH"));
        listaTrabajadores.add(new Trabajador("Laura", "Contabilidad"));
        listaTrabajadores.add(new Trabajador("Ramiro", "RRHH"));
        listaTrabajadores.add(new Trabajador("Cristian", "RRHH"));
        listaTrabajadores.add(new Trabajador("Juan", "Contabilidad"));

        listaTemporal = listaTrabajadores;
        mostrarTrabajadores();


        Sistemas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Sistemas.isSelected()) {
                    // Si el botón ya está seleccionado, mostrar todos los trabajadores
                    listaTemporal = listaTrabajadores;
                } else {
                    // Si el botón no está seleccionado, aplicar el filtro
                    filtrarLista(Sistemas.getText().toString());
                }
                // Cambiar el estado seleccionado del botón
                Sistemas.setSelected(!Sistemas.isSelected());
                mostrarTrabajadores();
            }
        });

        Contabilidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Contabilidad.isSelected()) {
                    // Si el botón ya está seleccionado, mostrar todos los trabajadores
                    listaTemporal = listaTrabajadores;
                } else {
                    // Si el botón no está seleccionado, aplicar el filtro
                    filtrarLista(Contabilidad.getText().toString());
                }
                // Cambiar el estado seleccionado del botón
                Contabilidad.setSelected(!Contabilidad.isSelected());
                mostrarTrabajadores();
            }
        });

        RRHH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (RRHH.isSelected()) {
                    // Si el botón ya está seleccionado, mostrar todos los trabajadores
                    listaTemporal = listaTrabajadores;
                } else {
                    // Si el botón no está seleccionado, aplicar el filtro
                    filtrarLista(RRHH.getText().toString());
                }
                // Cambiar el estado seleccionado del botón
                RRHH.setSelected(!RRHH.isSelected());
                mostrarTrabajadores();
            }
        });


        return view;
    }

    public void filtrarLista(String area) {
        listaTemporal = new ArrayList<>();
        for (int i = 0; i < listaTrabajadores.size(); i++) {
            if (listaTrabajadores.get(i).area.equals(area)) {
                listaTemporal.add(listaTrabajadores.get(i));
            }
        }
        mostrarTrabajadores();
    }

    public void mostrarTrabajadores() {
        StringBuilder cadena = new StringBuilder();
        for (int i = 0; i < listaTemporal.size(); i++) {
            cadena.append(listaTemporal.get(i).nombre).append(", ").append(listaTemporal.get(i).area).append("\n");
        }
        txtArea.setText(cadena.toString());
    }
}
