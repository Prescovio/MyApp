package com.example.leon.myapp.Views.Main.Practice.CustomNavigationDrawer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.leon.myapp.R;

public class DummyFragment extends Fragment {
    public DummyFragment() {
        // Required empty public constructor
    }

    public static DummyFragment newInstance() {
        return new DummyFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dummy, container, false);
    }
}
