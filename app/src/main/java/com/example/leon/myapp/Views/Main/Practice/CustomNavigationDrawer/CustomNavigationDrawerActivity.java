package com.example.leon.myapp.Views.Main.Practice.CustomNavigationDrawer;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.leon.myapp.*;
import com.example.leon.myapp.Views.Login.LoginActivity;
import com.example.leon.myapp.Views.Login.LoginFragment;
import com.example.leon.myapp.Views.Main.CustomList.CustomListFragment;
import com.example.leon.myapp.Views.Registration.RegistrationActivityView;
import com.example.leon.myapp.Views.Registration.RegistrationFragmentView;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class CustomNavigationDrawerActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private DrawerLayout drawerLayout;
    private ArrayList<CustomNavigationListItem> mItems = new ArrayList<>();
    private CustomNavigationListAdapter adapter;
    private ListView mMenuListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_navigation_drawer);

        drawerLayout = findViewById(R.id.custom_drawer_layout);

        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_launcher_round);

        ListView listView = findViewById(R.id.navigation_drawer_list_view);

        LinkedHashMap<String, Fragment> menuItems = new LinkedHashMap<>();
        menuItems.put("MenuItem1", new LoginFragment());
        menuItems.put("MenuItem2", new RegistrationFragmentView());
        menuItems.put("MenuItem3", new CustomListFragment());
        menuItems.put("MenuItem4", new DummyFragment());

        mItems.add(new CustomNavigationListItem("FirstItem", R.drawable.earth, menuItems, true));
        mItems.add(new CustomNavigationListItem("SecondItem", R.drawable.jupiter, menuItems, false));
        mItems.add(new CustomNavigationListItem("ThirdItem", R.drawable.neptune, menuItems, false));

        adapter = new CustomNavigationListAdapter(getLayoutInflater(), this, mItems);
        listView.setAdapter(adapter);
        adapter.loadMenuItems(0);

        mMenuListView = findViewById(R.id.navigation_drawer_menu_list_view);
        mMenuListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        LinkedHashMap<String, Fragment> menuItems = mItems.get(adapter.getSelectedPos()).getMenuItems();
        Fragment fragment = (Fragment)menuItems.values().toArray()[position];
        drawerLayout.closeDrawers();
        //TODO load fragments
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

                startActivity(new Intent(this, LoginActivity.class));

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
