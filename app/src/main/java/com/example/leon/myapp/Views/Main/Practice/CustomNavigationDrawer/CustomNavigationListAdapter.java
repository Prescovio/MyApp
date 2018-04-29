package com.example.leon.myapp.Views.Main.Practice.CustomNavigationDrawer;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.leon.myapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leon on 03.04.2018.
 */

public class CustomNavigationListAdapter extends BaseAdapter {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private ArrayList<CustomNavigationListItem> mItems;
    private ListView mMenuListView;
    private int mSelectedPos;

    public CustomNavigationListAdapter(LayoutInflater layoutInflater, Context context, ArrayList<CustomNavigationListItem> items) {
        this.mLayoutInflater = layoutInflater;
        this.mContext = context;
        this.mItems = items;

        mMenuListView = ((Activity)mContext).findViewById(R.id.navigation_drawer_menu_list_view);
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return mItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.navigation_drawer_custom_list_item, null);

            viewHolder = new ViewHolder(convertView);

            viewHolder.imageButton.setImageDrawable(mContext.getDrawable(mItems.get(position).getImageId()));
            viewHolder.imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSelectedPos = position;
                    loadMenuItems(position);
                }
            });

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        CustomNavigationListItem currentItem = mItems.get(position);

        viewHolder.imageButton.setImageDrawable(mContext.getDrawable(currentItem.getImageId()));

        return convertView;
    }

    public void loadMenuItems(int position) {
        List<String> menuItemsList = new ArrayList<>();

        for (String s : mItems.get(position).getMenuItems().keySet()) {
            menuItemsList.add(s);
        }

        mMenuListView.setAdapter(new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, menuItemsList));
    }

    public int getSelectedPos() {
        return mSelectedPos;
    }

    private class ViewHolder {
        ImageButton imageButton;

        public ViewHolder(View view) {
            imageButton = view.findViewById(R.id.navigation_drawer_custom_list_item_image_button);
        }
    }
}
