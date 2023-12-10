package com.edu.ue.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {

    HomeFragment homeFragment = new HomeFragment();
    ProgressFragment progressFragment = new ProgressFragment();
    PracticeFragment practiceFragment = new PracticeFragment();
    UserFragment userFragment = new UserFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        selecc(navigation);
        loadFragment(homeFragment);
    }
    public void loadFragment(Fragment fr){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container,fr);
        transaction.commit();

    }
    private void selecc(BottomNavigationView navigation){
        navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.homeFragment){
                    loadFragment(homeFragment);
                    return true;
                } else if (id == R.id.progresoFragment) {
                    loadFragment(progressFragment);
                    return true;
                } else if (id == R.id.practicaFragment) {
                    loadFragment(practiceFragment);
                    return true;
                } else if (id == R.id.perfilFragment) {
                    loadFragment(userFragment);
                    return true;
                }
                return false;
            }
        });
    }
}