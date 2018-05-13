package com.example.leon.myapp.Views.Main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.leon.myapp.R;
import com.example.leon.myapp.Views.Login.LoginActivity;
import com.example.leon.myapp.Views.Main.CustomList.CustomListFragment;
import com.example.leon.myapp.Views.Main.DefaultList.ListFragment;
import com.example.leon.myapp.Views.Main.Practice.PracticeFragment;
import com.example.leon.myapp.Views.Registration.RegistrationActivity;

public class MainActivity extends AppCompatActivity {
    private IntentFilter intentFilter;
    private BroadcastReceiver broadcastReceiver;
    private FragmentManager fragmentManager;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private int currentTab = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PracticeFragment practiceFragment = PracticeFragment.newInstance();
        ListFragment listFragment = ListFragment.newInstance();
        CustomListFragment customListFragment = CustomListFragment.newInstance();

        //Initializing viewPager
        viewPager = findViewById(R.id.main_viewpager);
        viewPager.setOffscreenPageLimit(3);

        fragmentManager = getSupportFragmentManager();
        adapter = new ViewPagerAdapter(fragmentManager);
        adapter.addFragment(practiceFragment, getString(R.string.practice_fragment));
        adapter.addFragment(listFragment, getString(R.string.default_list));
        adapter.addFragment(customListFragment, getString(R.string.custom_list));
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentTab = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tabLayout = findViewById(R.id.tablayout);

        //if api lvl is at least 26, check if PIP mode is active
        if (Build.VERSION.SDK_INT < 26 || (Build.VERSION.SDK_INT >= 26 && !isInPictureInPictureMode())) {
            tabLayout.setupWithViewPager(viewPager);

            //Initializing the tablayout
            tabLayout = findViewById(R.id.tablayout);
            tabLayout.setupWithViewPager(viewPager);

            setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setLogo(R.mipmap.ic_launcher);
            getSupportActionBar().setDisplayUseLogoEnabled(true);
        }
    }

    @Override
    public void onPictureInPictureModeChanged (boolean isInPictureInPictureMode, Configuration newConfig) {
        if (isInPictureInPictureMode) {
            tabLayout.setVisibility(View.GONE);
        } else {

        }
    }

    @Override
    public void onUserLeaveHint() {

    }

    @Override
    public void onBackPressed() {
        if (currentTab > 0) {
            currentTab = 0;
            viewPager.setCurrentItem(currentTab, true);
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
    protected void onResume() {
        super.onResume();

        if (currentTab > 0)
            viewPager.setCurrentItem(currentTab);

        intentFilter = new IntentFilter();
        intentFilter.addAction("com.package.ACTION_LOGOUT");

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                finish();
            }
        };

        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {
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
