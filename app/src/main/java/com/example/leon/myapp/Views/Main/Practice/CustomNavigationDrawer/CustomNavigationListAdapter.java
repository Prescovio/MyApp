package com.example.leon.myapp.Views.Main.Practice.CustomNavigationDrawer;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
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
    private ViewHolder mPrevViewHolder;
    private View mDefaultView;
    private boolean mFirstOpen;

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
        final ViewHolder viewHolder;

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.navigation_drawer_custom_list_item, null);

            viewHolder = new ViewHolder(convertView);

            viewHolder.imageButton.setImageDrawable(mContext.getDrawable(mItems.get(position).getImageId()));
            viewHolder.imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //some item has been highlighted before
                    if (mPrevViewHolder != null)
                        //reset hightlighting
                        mPrevViewHolder.imageView.setVisibility(View.INVISIBLE);

                    //highlight new item
                    viewHolder.imageView.setVisibility(View.VISIBLE);

                    //save position for activity
                    mSelectedPos = position;

                    //load menuitems for clicked item
                    loadMenuItems(mSelectedPos);

                    //save current viewholder
                    mPrevViewHolder = viewHolder;
                }
            });

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        CustomNavigationListItem currentItem = mItems.get(position);
        viewHolder.imageButton.setImageDrawable(mContext.getDrawable(currentItem.getImageId()));

        //first load
        if (!mFirstOpen) {
            mFirstOpen = true;
            //set highlighting visibility for first item
            viewHolder.imageView.setVisibility(View.VISIBLE);

            //set hightlighted item
            mPrevViewHolder = viewHolder;
        }

        return convertView;
    }

    public void loadMenuItems(int position) {
        final List<String> menuItemsList = new ArrayList<>();

        final CustomNavigationListItem currentItem = mItems.get(position);
        for (String s : currentItem.getMenuItems().keySet()) {
            menuItemsList.add(s);
        }

        mMenuListView.setAdapter(new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, menuItemsList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                //set background color for current selected item
                if (position == currentItem.getSelectedListItemPosId()) {
                    mDefaultView = view;
                    view.setBackgroundColor(mContext.getResources().getColor(R.color.listViewHighlighted));
                    mMenuListView.setSelection(position);
                }

                return view;
            }
        });
    }

    public View getDefaultView() {
        return mDefaultView;
    }

    public int getSelectedPos() {
        return mSelectedPos;
    }

    private class ViewHolder {
        ImageView imageView;
        ImageButton imageButton;

        public ViewHolder(View view) {
            imageView = view.findViewById(R.id.navigation_drawer_custom_list_item_image_view);
            imageButton = view.findViewById(R.id.navigation_drawer_custom_list_item_image_button);
        }
    }
}
