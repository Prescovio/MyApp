package com.example.leon.myapp.Views.Main.Practice.CustomNavigationDrawer;

import android.support.v4.app.Fragment;

import java.util.LinkedHashMap;

/**
 * Created by Leon on 28.04.2018.
 */

public class CustomNavigationListItem {
    private String mName;
    private int mImageId;
    private LinkedHashMap<String, Fragment> mMenuItems;
    private int mSelectedListItemPos;
    private boolean mLoaded;

    public CustomNavigationListItem(String name, int imageId, LinkedHashMap<String, Fragment> menuItems) {
        this.mName = name;
        this.mImageId = imageId;
        this.mMenuItems = menuItems;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public int getImageId() {
        return mImageId;
    }

    public LinkedHashMap<String, Fragment> getMenuItems() {
        return mMenuItems;
    }

    public int getSelectedListItemPosId() {
        return mSelectedListItemPos;
    }

    public void setSelectedListItemPosId(int selectedListItemPosId) {
        this.mSelectedListItemPos = selectedListItemPosId;
    }

    public boolean getLoaded() {
        return mLoaded;
    }

    public void setLoaded(boolean loaded) {
        this.mLoaded = loaded;
    }
}
