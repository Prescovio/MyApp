package com.example.leon.myapp.Views.Main.Practice.DragAndDrop;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.leon.myapp.R;

import java.util.ArrayList;

/**
 * Created by Leon on 13.05.2018.
 */

public class CustomRecyclerViewListAdapter extends RecyclerView.Adapter<CustomRecyclerViewListAdapter.ViewHolder> {
    private ArrayList<CustomRecyclerViewListItem> mListItems;
    private Context mContext;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private TextView mTextView;
        private ViewHolder(View view) {
            super(view);
            mImageView = view.findViewById(R.id.custom_recycler_view_item_image_view);
            mTextView = view.findViewById(R.id.custom_recycler_view_item_text_view);
        }
    }

    public CustomRecyclerViewListAdapter(Context context, ArrayList<CustomRecyclerViewListItem> listItems) {
        this.mContext = context;
        this.mListItems = listItems;
    }

    @Override
    public CustomRecyclerViewListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_recycler_view_list_item,parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        viewHolder.mImageView.setImageDrawable(mContext.getDrawable(mListItems.get(position).getDrawableId()));
        viewHolder.mImageView.setTag(mListItems.get(position).getText());
        viewHolder.mTextView.setText(mListItems.get(position).getText());

        viewHolder.mImageView.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item(v.getTag().toString());
                ClipData dragData = new ClipData(v.getTag().toString(), new String[] { ClipDescription.MIMETYPE_TEXT_PLAIN }, item);

                View.DragShadowBuilder myShadow = new CustomDragShadowBuilder(viewHolder.mImageView, mContext, mListItems.get(position).getDrawableId());

                if (Build.VERSION.SDK_INT >= 24)
                    return v.startDragAndDrop(dragData, myShadow, mListItems.get(position).getDrawableId(), 0);
                else
                    return v.startDrag(dragData, myShadow, mListItems.get(position).getDrawableId(), 0);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListItems.size();
    }
}
