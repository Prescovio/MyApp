package com.example.leon.myapp.Views.Main.CustomList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.leon.myapp.R;

import java.util.ArrayList;

/**
 * Created by Leon on 03.04.2018.
 */

public class CustomListAdapter extends BaseAdapter {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private ArrayList<ListItem> mItems;

    public CustomListAdapter(LayoutInflater layoutInflater, Context context, ArrayList<ListItem> items) {
        this.mLayoutInflater = layoutInflater;
        this.mContext = context;
        this.mItems = items;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView;
        rowView = mLayoutInflater.inflate(R.layout.fragment_custom_list_item, null);

        ImageView imageView = rowView.findViewById(R.id.list_item_image_view);
        TextView textView = rowView.findViewById(R.id.list_item_text_view);

        imageView.setImageDrawable(mContext.getDrawable(mItems.get(position).getImageId()));
        textView.setText(mItems.get(position).getText());

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, mItems.get(position).getText(), Toast.LENGTH_SHORT).show();
            }
        });
        return rowView;
    }
}
