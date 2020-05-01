package com.example.cs425.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.example.cs425.R;
import com.example.cs425.fragments.DashbordFragment;
import com.example.cs425.fragments.EditorFragment;
import com.example.cs425.fragments.ProfileFragment;
import com.example.cs425.fragments.StatsFragment;
import com.example.cs425.fragments.TodoFragment;
import com.google.android.material.navigation.NavigationView;

public class SideBarActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    String fullName;
    String email;
    String moodleToken;


    @Override
    protected void onCreate(Bundle  savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sidebar_dashboard);
        getWindow().getDecorView().setBackgroundColor(Color.WHITE);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        ProfileFragment profile = new ProfileFragment();

        //Get user information from the login
         fullName = getIntent().getStringExtra("fullName");
         email = getIntent().getStringExtra("email");
         moodleToken = getIntent().getStringExtra("moodleToken");
        //Log.d("TAG", "fullName is : "+ fullName);
        //Log.d("TAG", "email is : "+ email);
        //Log.d("TAG", "moodleToken is : "+ moodleToken);

        //Create bundle to send user info to profileFragment


        //Drawer layout
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.colordashbord));
        toggle.syncState();


        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DashbordFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_dashbord);
        }
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
        super.onBackPressed();
    }
    }


    //Navigate through the side bar
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_dashbord:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new DashbordFragment()).commit();
                break;
            case R.id.nav_stats:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new StatsFragment()).commit();
                break;
            case R.id.nav_todo:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new TodoFragment()).commit();
                break;
            case R.id.nav_editor:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new EditorFragment()).commit();
                break;
            case R.id.nav_account:
                Bundle bundle = new Bundle();
                bundle.putString("fullName",fullName);
                bundle.putString("email",email);
                bundle.putString("moodleToken",moodleToken);
                ProfileFragment myObj = new ProfileFragment();
                myObj.setArguments(bundle);

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, myObj).commit();;
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
