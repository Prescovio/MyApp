package com.example.leon.myapp.Views.Main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.leon.myapp.R;

public class MainFragmentViewTwo extends Fragment {
    public MainFragmentViewTwo() {
        // Required empty public constructor
    }

    public static MainFragmentViewTwo newInstance() {
        MainFragmentViewTwo fragment = new MainFragmentViewTwo();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main_two, container, false);
    }
}
