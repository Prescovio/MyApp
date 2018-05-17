package com.example.leon.myapp.Views.Main.Practice.DragAndDrop;

import android.support.v4.app.Fragment;

/**
 * Created by Leon on 13.05.2018.
 */

public class CustomRecyclerViewListItem {
    private int mDrawableId;
    private String mText;
    private Fragment mFragment;

    public CustomRecyclerViewListItem(int drawableId, String text, Fragment fragment) {
        this.mDrawableId = drawableId;
        this.mText = text;
        this.mFragment = fragment;
    }

    public int getDrawableId() {
        return mDrawableId;
    }

    public void setDrawableId(int drawableId) {
        this.mDrawableId = drawableId;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        this.mText = text;
    }

    public Fragment getFragment() {
        return this.mFragment;
    }

    public void setFragment(Fragment fragment) {
        this.mFragment = fragment;
    }
}
