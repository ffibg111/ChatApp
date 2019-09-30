package com.example.androideatit;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.androideatit.Common.Common;
import com.example.androideatit.Common.Firebase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    TextView textViewFullName;

    private NavController navController;
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Initialize firebase
        Firebase firebase = new Firebase();
        firebase.initFirebase();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        // Set name for user
        View headerView = navigationView.getHeaderView(0);
        textViewFullName = headerView.findViewById(R.id.textViewFullName);
        textViewFullName.setText(Common.currentUser.getName());

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_cart, R.id.nav_order, R.id.nav_log_out)
                .setDrawerLayout(drawer)
                .build();

        navController = Navigation.findNavController(
                this, R.id.nav_host_fragment);

        NavigationUI.setupActionBarWithNavController(
                this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        final FloatingActionButton fab = findViewById(R.id.fab);
        // Register fab to navController
        Navigation.setViewNavController(fab, navController);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Lets navigate to cart destination using fab clickable
                navController.navigate(R.id.nav_cart);

                /*
                // Lets not start a new intent
                Intent cartIntent = new Intent(Home.this, Cart.class);
                startActivity(cartIntent);

                // Lets not loading cart fragment into destination
                CartFragment cart = new CartFragment();
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.nav_host_fragment, cart);
                ft.commit();
                */
            }
        });

        // Hide shopping-cart icon for Cart fragment screen
        navController.addOnDestinationChangedListener(
                new NavController.OnDestinationChangedListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onDestinationChanged(@NonNull NavController controller,
                     @NonNull NavDestination destination,
                         @Nullable Bundle arguments) {
                if(destination.getId() == R.id.nav_cart) {
                    fab.setVisibility(View.INVISIBLE);
                } else {
                    fab.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
