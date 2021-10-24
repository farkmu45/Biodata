package com.example.biodata;

import static androidx.navigation.ui.NavigationUI.setupActionBarWithNavController;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavHost;
import androidx.room.Room;

import com.example.biodata.model.Database;

public class MainActivity extends AppCompatActivity {

    private NavController navController;
    public static Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = Room.databaseBuilder(getApplicationContext(),
                Database.class, "database-name").allowMainThreadQueries().build();

        NavHost navHost = (NavHost) getSupportFragmentManager().findFragmentById(R.id.nav_host);
        navController = navHost.getNavController();

        setupActionBarWithNavController(this, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp() || super.onSupportNavigateUp();
    }
}