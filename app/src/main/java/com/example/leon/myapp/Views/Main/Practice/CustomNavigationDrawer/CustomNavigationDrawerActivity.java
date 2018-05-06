package com.example.leon.myapp.Views.Main.Practice.CustomNavigationDrawer;

import android.content.Intent;
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
    private FragmentManager fragmentManager;
    private ListView mMenuListView;
    private View mPrevView;
    private CustomNavigationListItem mPrevItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_navigation_drawer);

        drawerLayout = findViewById(R.id.custom_drawer_layout);

        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_launcher_round);

        fragmentManager = getSupportFragmentManager();

        ListView listView = findViewById(R.id.navigation_drawer_list_view);

        //create map for menu items (menuLogin, menuRegistration, menuListItems)
        LinkedHashMap<String, Fragment> menuLoginItems = new LinkedHashMap<>();
        menuLoginItems.put("LoginFragment", new LoginFragment());
        menuLoginItems.put("DummyFragment", new DummyFragment());

        //add new item (picture) each has its own menu list
        mItems.add(new CustomNavigationListItem("Login", R.drawable.earth, menuLoginItems));

        LinkedHashMap<String, Fragment> menuRegistrationItems = new LinkedHashMap<>();
        menuRegistrationItems.put("RegistrationFragment", new RegistrationFragment());
        menuRegistrationItems.put("DummyFragment", new DummyFragment());

        mItems.add(new CustomNavigationListItem("Registration", R.drawable.jupiter, menuRegistrationItems));

        LinkedHashMap<String, Fragment> menuListItems = new LinkedHashMap<>();
        menuListItems.put("ListFragment", new CustomListFragment());
        menuListItems.put("DummyFragment", new DummyFragment());

        mItems.add(new CustomNavigationListItem("List", R.drawable.neptune, menuListItems));

        //set custom adapter
        adapter = new CustomNavigationListAdapter(getLayoutInflater(), this, mItems, fragmentManager);
        listView.setAdapter(adapter);

        //load first item of picture listview
        adapter.loadMenuItems(0);

        //get menulist
        mMenuListView = findViewById(R.id.navigation_drawer_menu_list_view);
        mMenuListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //get current picture item from adapter
        CustomNavigationListItem currentItem = mItems.get(adapter.getSelectedPos());

        //get default highlighting
        if (mPrevItem == null) {
            mPrevItem = adapter.getDefaultItem();
            mPrevView = adapter.getDefaultView();
        }

        //remove highlighting
        if (mPrevItem != null && mPrevView != null) {
            mPrevItem.setLoaded(false);
            mPrevView.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        }

        //remove loaded highlighting
        View loadedView = adapter.getLoadedView();
        if (loadedView != null)
            loadedView.setBackgroundColor(getResources().getColor(android.R.color.transparent));

        //set current item loaded
        currentItem.setSelectedListItemPosId(position);
        currentItem.setLoaded(true);

        //view loaded color
        view.setBackgroundColor(getResources().getColor(R.color.listViewLoaded));
        mMenuListView.setSelection(position);

        //set current objects so they can be reset on next selection
        mPrevView = view;
        mPrevItem = currentItem;

        //get fragment to load and close drawer
        LinkedHashMap<String, Fragment> menuItems = currentItem.getMenuItems();
        Fragment fragment = (Fragment)menuItems.values().toArray()[position];
        drawerLayout.closeDrawers();

        //switch to fragment
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        //fragmentTransaction.addToBackStack(null); //destroys highlighting
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
