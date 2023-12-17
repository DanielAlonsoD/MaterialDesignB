package com.example.materialdesignb;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;
import com.google.android.material.chip.Chip;
import com.google.android.material.tabs.TabLayout;


public class HomeFragment extends Fragment {

    private TabLayout tabLayout;
    private FrameLayout frameLayout;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View elemento = inflater.inflate(R.layout.fragment_home, container, false);

        tabLayout = elemento.findViewById(R.id.tabLayout);
        frameLayout = elemento.findViewById(R.id.frameLayout);

        // Agrega las pestañas
        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));

        // Establece un listener para cambiar los fragmentos cuando se selecciona una pestaña
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        loadFragment(new Fragment1());
                        break;
                    case 1:
                        loadFragment(new Fragment2());
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        // Carga el primer fragmento por defecto
        loadFragment(new Fragment1());

        return elemento;
    }

    private void loadFragment(androidx.fragment.app.Fragment fragment) {
        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout, fragment)
                .commit();
    }


}