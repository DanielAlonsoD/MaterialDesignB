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

public class MainActivity extends AppCompatActivity implements NavHost {
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Esta parte declara el Top App Bar
        MaterialToolbar topAppBar = findViewById(R.id.topAppBar);
        setSupportActionBar(topAppBar);

        //Aquí es dónde se debe insertar el Navigation Drawer, ya que es el icono izquierdo de la Top App Bar
        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Has pulsado las 3 barras ", Snackbar.LENGTH_SHORT).show();
            }
        });

        //Toda esta parte hasta el final del método es del Navigation Bar
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

    //Parte del Top App Bar, aquí se declara el menú de las opciones del Top App Bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.menu_top_app_bar, menu);
       return true;
    }

    /*Parte del Top App Bar, en esta parte dependiendo del item que el usuario seleccione el programa hará
    una u otra acción*/
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem menuItem) {
        boolean realizado = false;
        if (menuItem.getItemId()==R.id.itemMenuSelected) {
            //Toda esta parte hasta el final del if es el Menu de Selected
            View view = findViewById(R.id.itemMenuSelected);
            PopupMenu menu = new PopupMenu(this, view);
            menu.getMenuInflater().inflate(R.menu.menu_selection, menu.getMenu());

            menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    if (item.getItemId()==R.id.itemOpciones) {
                        Intent actividadOpciones = new Intent(MainActivity.this, OpcionesActivity.class);
                        startActivity(actividadOpciones);
                        return true;
                    } else {
                        Intent actividadAcercaDe = new Intent(MainActivity.this, AcercaDeActivity.class);
                        startActivity(actividadAcercaDe);
                        return false;
                    }
                }
            });

            menu.show();
            realizado = true;
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
}