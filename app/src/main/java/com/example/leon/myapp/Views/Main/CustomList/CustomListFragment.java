package com.example.leon.myapp.Views.Main.CustomList;

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

        listItems.clear();

        listItems.add(new ListItem(R.drawable.mercury, getString(R.string.mercury)));
        listItems.add(new ListItem(R.drawable.venus, getString(R.string.venus)));
        listItems.add(new ListItem(R.drawable.earth, getString(R.string.earth)));
        listItems.add(new ListItem(R.drawable.mars, getString(R.string.mars)));
        listItems.add(new ListItem(R.drawable.jupiter, getString(R.string.jupiter)));
        listItems.add(new ListItem(R.drawable.saturn, getString(R.string.saturn)));
        listItems.add(new ListItem(R.drawable.uranus, getString(R.string.uranus)));
        listItems.add(new ListItem(R.drawable.neptune, getString(R.string.neptune)));
        listItems.add(new ListItem(R.drawable.mercury, getString(R.string.mercury)));
        listItems.add(new ListItem(R.drawable.venus, getString(R.string.venus)));
        listItems.add(new ListItem(R.drawable.earth, getString(R.string.earth)));
        listItems.add(new ListItem(R.drawable.mars, getString(R.string.mars)));
        listItems.add(new ListItem(R.drawable.jupiter, getString(R.string.jupiter)));
        listItems.add(new ListItem(R.drawable.saturn, getString(R.string.saturn)));
        listItems.add(new ListItem(R.drawable.uranus, getString(R.string.uranus)));
        listItems.add(new ListItem(R.drawable.neptune, getString(R.string.neptune)));

        listView.setAdapter(new CustomListAdapter(inflater, getActivity(), listItems));

        return view;
    }
}
