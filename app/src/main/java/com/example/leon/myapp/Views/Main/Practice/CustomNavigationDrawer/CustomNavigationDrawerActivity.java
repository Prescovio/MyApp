package com.example.leon.myapp.Views.Main.Practice.CustomNavigationDrawer;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import com.example.leon.myapp.Views.Registration.RegistrationActivity;
import com.example.leon.myapp.Views.Registration.RegistrationFragment;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class CustomNavigationDrawerActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private DrawerLayout drawerLayout;
    private ArrayList<CustomNavigationListItem> mItems = new ArrayList<>();
    private CustomNavigationListAdapter adapter;
    private ListView mMenuListView;
    private View mPrevView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_navigation_drawer);

        drawerLayout = findViewById(R.id.custom_drawer_layout);

        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_launcher_round);

        ListView listView = findViewById(R.id.navigation_drawer_list_view);

        LinkedHashMap<String, Fragment> menuLoginItems = new LinkedHashMap<>();
        menuLoginItems.put("LoginFragment", new LoginFragment());
        menuLoginItems.put("DummyFragment", new DummyFragment());

        mItems.add(new CustomNavigationListItem("Login", R.drawable.earth, menuLoginItems));

        LinkedHashMap<String, Fragment> menuRegistrationItems = new LinkedHashMap<>();
        menuRegistrationItems.put("RegistrationFragment", new RegistrationFragment());
        menuRegistrationItems.put("DummyFragment", new DummyFragment());

        mItems.add(new CustomNavigationListItem("Registration", R.drawable.jupiter, menuRegistrationItems));

        LinkedHashMap<String, Fragment> menuListItems = new LinkedHashMap<>();
        menuListItems.put("ListFragment", new CustomListFragment());
        menuListItems.put("DummyFragment", new DummyFragment());

        mItems.add(new CustomNavigationListItem("List", R.drawable.neptune, menuListItems));

        adapter = new CustomNavigationListAdapter(getLayoutInflater(), this, mItems);
        listView.setAdapter(adapter);
        adapter.loadMenuItems(0);

        mMenuListView = findViewById(R.id.navigation_drawer_menu_list_view);
        mMenuListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        CustomNavigationListItem currentItem = mItems.get(adapter.getSelectedPos());
        currentItem.setSelectedListItemPosId(position);

        if (mPrevView == null)
            mPrevView = adapter.getDefaultView();

        mPrevView.setBackgroundColor(getResources().getColor(android.R.color.transparent));

        view.setBackgroundColor(getResources().getColor(R.color.listViewHighlighted));
        mMenuListView.setSelection(position);

        mPrevView = view;

        LinkedHashMap<String, Fragment> menuItems = currentItem.getMenuItems();
        Fragment fragment = (Fragment)menuItems.values().toArray()[position];
        drawerLayout.closeDrawers();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
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
                startActivity(new Intent(this, RegistrationActivity.class));

                break;
            default:
                super.onOptionsItemSelected(item);
        }

        return true;
    }
}
