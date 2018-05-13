package com.example.leon.myapp.Views.Main.Practice.DragAndDrop;

/**
 * Created by Leon on 13.05.2018.
 */

public class CustomRecyclerViewListItem {
    private int mDrawableId;
    private String mText;

    public CustomRecyclerViewListItem(int drawableId, String text) {
        this.mDrawableId = drawableId;
        this.mText = text;
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
}
