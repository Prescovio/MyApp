package com.example.leon.myapp.Views.Main.Practice.CustomNavigationDrawer;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Leon on 03.04.2018.
 */

public class CustomNavigationListAdapter extends BaseAdapter {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private ArrayList<CustomNavigationListItem> mItems;
    private FragmentManager mFragmentManager;
    private ListView mMenuListView;
    private int mSelectedPos;
    private ViewHolder mPrevViewHolder;
    private CustomNavigationListItem mDefaultItem;
    private View mDefaultView;
    private View mLoadedView;
    private boolean mFirstOpen;

    public CustomNavigationListAdapter(LayoutInflater layoutInflater, Context context, ArrayList<CustomNavigationListItem> items, FragmentManager fragmentManager) {
        this.mLayoutInflater = layoutInflater;
        this.mContext = context;
        this.mItems = items;
        this.mFragmentManager = fragmentManager;

        //get child list view in order to set adapter later
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
            //inflate layout for each list item
            convertView = mLayoutInflater.inflate(R.layout.navigation_drawer_custom_list_item, null);

            viewHolder = new ViewHolder(convertView);

            //set drawable
            viewHolder.imageButton.setImageDrawable(mContext.getDrawable(mItems.get(position).getImageId()));

            //set onclicklistener
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

            //set hightlighted

            mPrevViewHolder = viewHolder;
        }

        return convertView;
    }

    /**
     * load menu items for selected picture
     * @param position
     */
    public void loadMenuItems(int position) {
        final List<String> menuItemsList = new ArrayList<>();

        final CustomNavigationListItem currentItem = mItems.get(position);
        for (String s : currentItem.getMenuItems().keySet()) {
            menuItemsList.add(s);
        }

        //set custom array adapter
        mMenuListView.setAdapter(new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, menuItemsList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                //set background color for current selected item
                if (position == currentItem.getSelectedListItemPosId()) {
                    mDefaultItem = currentItem;
                    mDefaultView = view;

                    mLoadedView = view;
                    view.setBackgroundColor(mContext.getResources().getColor(R.color.listViewLoaded));

                    //get fragment to load and close drawer
                    LinkedHashMap<String, Fragment> menuItems = currentItem.getMenuItems();
                    Fragment fragment = (Fragment)menuItems.values().toArray()[position];

                    //switch to fragment
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, fragment);
                    //fragmentTransaction.addToBackStack(null); //destroys highlighting
                    fragmentTransaction.commit();

                    mMenuListView.setSelection(position);
                }

                return view;
            }
        });
    }

    public View getDefaultView() {
        return mDefaultView;
    }

    public CustomNavigationListItem getDefaultItem() {
        return mDefaultItem;
    }

    public View getLoadedView() {
        return mLoadedView;
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
