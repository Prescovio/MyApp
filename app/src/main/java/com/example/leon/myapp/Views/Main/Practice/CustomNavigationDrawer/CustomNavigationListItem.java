package com.example.leon.myapp.Views.Main.Practice.CustomNavigationDrawer;

import android.support.v4.app.Fragment;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Leon on 28.04.2018.
 */

public class CustomNavigationListItem {
    private String mName;
    private int mImageId;
    private LinkedHashMap<String, Fragment> mMenuItems;
    private boolean mIsActive;

    public CustomNavigationListItem(String name, int imageId, LinkedHashMap<String, Fragment> menuItems, boolean isActive) {
        this.mName = name;
        this.mImageId = imageId;
        this.mMenuItems = menuItems;
        this.mIsActive = isActive;
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

    public void setImageId(int imageId) {
        this.mImageId = imageId;
    }

    public LinkedHashMap<String, Fragment> getMenuItems() {
        return mMenuItems;
    }

    public void setMenuItems(LinkedHashMap<String, Fragment> menuItems) {
        this.mMenuItems = menuItems;
    }

    public boolean isActive() {
        return mIsActive;
    }

    public void setActive(boolean active) {
        mIsActive = active;
    }
}
