package com.example.leon.myapp.Views.Main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.leon.myapp.R;

import java.util.ArrayList;

public class CustomListFragment extends Fragment {
    ArrayList<ListItem> listItems = new ArrayList<>();

    public CustomListFragment() {
        // Required empty public constructor
    }

    public static CustomListFragment newInstance() {
        CustomListFragment fragment = new CustomListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_custom_list, container, false);

        ListView listView = view.findViewById(R.id.custom_list_list_view);


        listItems.add(new ListItem(R.drawable.mercury, "Merkur"));
        listItems.add(new ListItem(R.drawable.venus, "Venus"));
        listItems.add(new ListItem(R.drawable.earth, "Erde"));
        listItems.add(new ListItem(R.drawable.mars, "Mars"));
        listItems.add(new ListItem(R.drawable.jupiter, "Jupiter"));
        listItems.add(new ListItem(R.drawable.saturn, "Saturn"));
        listItems.add(new ListItem(R.drawable.uranus, "Uranus"));
        listItems.add(new ListItem(R.drawable.neptune, "Neptun"));

        listView.setAdapter(new CustomListAdapter(inflater, getActivity(), listItems));

        return view;
    }
}
