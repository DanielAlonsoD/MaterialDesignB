package com.example.materialdesignb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.NavHost;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NavHost {
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton botonMenuSelection = findViewById(R.id.botonMenuSelection);
        botonMenuSelection.setOnClickListener(this);

        NavHostFragment fragmentos =(NavHostFragment)getSupportFragmentManager().findFragmentById(R.id.fragmentos);
        navController = fragmentos.getNavController();

        BottomNavigationView navigationBar = findViewById(R.id.navigationBar);
        navigationBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                boolean realizado = false;
                Fragment fragmentoSeleccionado = null;
                if (item.getItemId()==R.id.itemHome) {
                    fragmentoSeleccionado = new HomeFragment();
                    realizado = true;
                } else if (item.getItemId()==R.id.itemTextInputs) {
                    fragmentoSeleccionado = new TextInputFragment();
                    realizado = true;
                } else if (item.getItemId()==R.id.itemSearch) {
                    fragmentoSeleccionado = new SearchFragment();
                    realizado = true;
                }
                mostrarFragmento(fragmentoSeleccionado);
                return realizado;
            }
        });

        mostrarFragmento(new HomeFragment());
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.botonMenuSelection) {
            PopupMenu menuSelection = new PopupMenu(MainActivity.this, view);
            menuSelection.getMenuInflater().inflate(R.menu.menu_selection, menuSelection.getMenu());

            menuSelection.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    boolean realizado = false;
                    if (menuItem.getItemId() == R.id.itemOpciones) {
                        Intent actividadOpciones = new Intent(MainActivity.this, OpcionesActivity.class);
                        startActivity(actividadOpciones);
                        realizado = true;
                    } else if (menuItem.getItemId() == R.id.itemAcercaDe) {
                        Intent actividadAcercaDe = new Intent(MainActivity.this, AcercaDeActivity.class);
                        startActivity(actividadAcercaDe);
                        realizado = true;
                    }
                    return realizado;
                }
            });
            menuSelection.show();
        }
    }

    private void mostrarFragmento(Fragment fragmento) {
        if (fragmento != null) {
            FragmentTransaction transaccion = getSupportFragmentManager().beginTransaction();
            transaccion.replace(R.id.contenedor, fragmento);
            transaccion.commit();
        }
    }

    @NonNull
    @Override
    public NavController getNavController() {
        return navController;
    }
}