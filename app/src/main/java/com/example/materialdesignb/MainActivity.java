package com.example.materialdesignb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.NavHost;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ActionMenuView;
import android.widget.ImageButton;
import android.widget.PopupMenu;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener ,NavHost {
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialToolbar topAppBar = findViewById(R.id.topAppBar);
        setSupportActionBar(topAppBar);

        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Has pulsado las 3 barras", Snackbar.LENGTH_SHORT).show();
            }
        });

        NavHostFragment fragmentos = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentos);
        navController = fragmentos.getNavController();

        BottomNavigationView navigationBar = findViewById(R.id.navigationBar);
        navigationBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                boolean realizado = false;
                Fragment fragmentoSeleccionado = null;
                if (item.getItemId() == R.id.itemHome) {
                    fragmentoSeleccionado = new HomeFragment();
                    realizado = true;
                } else if (item.getItemId() == R.id.itemTextInputs) {
                    fragmentoSeleccionado = new TextInputFragment();
                    realizado = true;
                } else if (item.getItemId() == R.id.itemSearch) {
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
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.menu_top_app_bar, menu);
       return true;
    }

    // Maneja las acciones del menú
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem menuItem) {
        boolean realizado = false;
        if (menuItem.getItemId()==R.id.itemMenuSelected) {
            menuItem.setOnMenuItemClickListener((MenuItem.OnMenuItemClickListener) this);
            return true;
        }
        return realizado;
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

    @Override
    public void onClick(View v) {
        PopupMenu menuOpciones = new PopupMenu(MainActivity.this, getCurrentFocus());
        menuOpciones.getMenuInflater().inflate(R.menu.menu_selection, menuOpciones.getMenu());

        menuOpciones.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                boolean realizado = false;
                if (item.getItemId()==R.id.itemOpciones) {
                    Intent actividadOpciones = new Intent(MainActivity.this, OpcionesActivity.class);
                    startActivity(actividadOpciones);
                    realizado = true;
                } else if (item.getItemId()==R.id.itemAcercaDe) {
                    Intent actividadAcercaDe = new Intent(MainActivity.this, AcercaDeActivity.class);
                    startActivity(actividadAcercaDe);
                    realizado = true;
                }
                return realizado;
            }
        });

        menuOpciones.show();
    }
}