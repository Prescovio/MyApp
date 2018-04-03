package com.example.leon.myapp.Views.Main;

/**
 * Created by Leon on 03.04.2018.
 */

public class ListItem {
    private int mImageId;
    private String mText;

    public ListItem(int imageId, String text) {
        this.mImageId = imageId;
        this.mText = text;
    }

    public int getImageId() {
        return mImageId;
    }

    public void setImageId(int mImageId) {
        this.mImageId = mImageId;
    }

    public String getText() {
        return mText;
    }

    public void setText(String mText) {
        this.mText = mText;
    }
}
