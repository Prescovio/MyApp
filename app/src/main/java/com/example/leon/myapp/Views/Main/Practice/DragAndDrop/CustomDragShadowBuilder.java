package com.example.leon.myapp.Views.Main.Practice.DragAndDrop;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
/**
 * Created by Leon on 13.05.2018.
 */

public class CustomDragShadowBuilder extends View.DragShadowBuilder {
    private static Drawable shadow;
    private Drawable mImage;

    public CustomDragShadowBuilder(View view, Context context, int drawableId) {
        super(view);

        shadow = new ColorDrawable(Color.LTGRAY);
        mImage = context.getDrawable(drawableId);
    }

    @Override
    public void onProvideShadowMetrics(Point size, Point touch) {
        int width, height;

        width = getView().getWidth() / 2;

        height = getView().getHeight() / 2;

        mImage.setBounds(0,0,width, height);

        size.set(width, height);

        touch.set(width / 2, height / 2);
    }

    @Override
    public void onDrawShadow(Canvas canvas) {
        mImage.draw(canvas);
    }
}
