package com.example.leon.myapp.Views.Main.Practice.NavigationDrawer;

import android.content.Intent;
import android.support.design.internal.NavigationMenuItemView;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.leon.myapp.*;
import com.example.leon.myapp.Views.Login.LoginActivityView;
import com.example.leon.myapp.Views.Registration.RegistrationActivityView;

public class CustomNavigationDrawerActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_navigation_drawer);

        drawerLayout = findViewById(R.id.custom_drawer_layout);

        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_launcher_round);

        ListView listView = findViewById(R.id.navigation_drawer_list_view);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.planets, android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        ListView menuListView = findViewById(R.id.navigation_drawer_menu_list_view);
        // menuListAdapter = ArrayAdapter.createFromResource(this, R.menu.drawer_menu, android.R.layout.simple_list_item_1);
        menuListView.setAdapter(adapter);
        menuListView.setOnItemClickListener(this);
    }

    //AdapterView.OnItemClickListener implementation
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "Item: " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.custom_drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.logout:
                Intent broadcastIntent = new Intent();
                broadcastIntent.setAction("com.package.ACTION_LOGOUT");
                sendBroadcast(broadcastIntent);

                startActivity(new Intent(this, LoginActivityView.class));

                break;
            case R.id.mika:
                startActivity(new Intent(this, RegistrationActivityView.class));

                break;
            default:
                super.onOptionsItemSelected(item);
        }

        return true;
    }
}
