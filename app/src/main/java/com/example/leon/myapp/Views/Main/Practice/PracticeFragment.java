package com.example.leon.myapp.Views.Main.Practice;

import android.app.PictureInPictureParams;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.leon.myapp.R;
import com.example.leon.myapp.Views.Main.Practice.CustomNavigationDrawer.CustomNavigationDrawerActivity;
import com.example.leon.myapp.Views.Main.Practice.NavigationDrawer.NavigationDrawerActivity;
import com.example.leon.myapp.Views.Main.Practice.Notification.NotificationActivity;
import com.example.leon.myapp.Views.Main.Practice.Service.ServiceActivity;

public class PracticeFragment extends Fragment {
    public PracticeFragment() {
        // Required empty public constructor
    }

    public static PracticeFragment newInstance() {
        PracticeFragment fragment = new PracticeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_practice, container, false);

        //add button callbacks
        //LoadServiceActivity
        Button loadServiceActivity = view.findViewById(R.id.btnLoadServiceActivity);
        loadServiceActivity.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onLoadServiceActivityClick(v);
            }
        });

        //LoadNotificationActivity
        Button loadNotificationActivity = view.findViewById(R.id.btnLoadNotificationActivity);
        loadNotificationActivity.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onLoadNotificationActivityClick(v);
            }
        });

        //LoadNavigationDrawerActivity
        Button loadNavigationDrawerActivity = view.findViewById(R.id.btnLoadNavigationDrawerActivity);
        loadNavigationDrawerActivity.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onLoadNavigationDrawerActivityClick(v);
            }
        });

        //LoadCustomNavigationDrawerActivity
        Button loadCustomNavigationDrawerActivity = view.findViewById(R.id.btnLoadCustomNavigationDrawerActivity);
        loadCustomNavigationDrawerActivity.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onLoadCustomNavigationDrawerActivityClick(v);
            }
        });

        //EnterPIPMode
        Button enterPIPMode = view.findViewById(R.id.btnEnterPIPMode);
        enterPIPMode.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                enterPIPModeClick(v);
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void onLoadServiceActivityClick(View view) {
        startActivity(new Intent(getActivity(), ServiceActivity.class));
    }

    public void onLoadNotificationActivityClick(View view) {
        startActivity(new Intent(getActivity(), NotificationActivity.class));
    }

    public void onLoadNavigationDrawerActivityClick(View view) {
        startActivity(new Intent(getActivity(), NavigationDrawerActivity.class));
    }

    public void onLoadCustomNavigationDrawerActivityClick(View view) {
        startActivity(new Intent(getActivity(), CustomNavigationDrawerActivity.class));
    }

    public void enterPIPModeClick(View view) {
        if (Build.VERSION.SDK_INT >= 26) {
            PictureInPictureParams.Builder pictureInPictureParams = new PictureInPictureParams.Builder();
            getActivity().enterPictureInPictureMode(pictureInPictureParams.build());
        } else {
            Toast.makeText(getActivity(), "API Level below 26", Toast.LENGTH_SHORT).show();
        }
    }
}
