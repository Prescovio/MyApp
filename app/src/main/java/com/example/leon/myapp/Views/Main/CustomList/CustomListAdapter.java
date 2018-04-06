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
    public int getViewTypeCount() {

        return mItems.size();
    }

    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.fragment_custom_list_item, null);

            viewHolder = new ViewHolder(convertView);

            viewHolder.imageView.setImageDrawable(mContext.getDrawable(mItems.get(position).getImageId()));
            viewHolder.textView.setText(mItems.get(position).getText());

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, mItems.get(position).getText(), Toast.LENGTH_SHORT).show();
                }
            });

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        ListItem currentItem = (ListItem)mItems.get(position);

        viewHolder.imageView.setImageDrawable(mContext.getDrawable(currentItem.getImageId()));
        viewHolder.textView.setText(currentItem.getText());

        return convertView;
    }

    private class ViewHolder {
        ImageView imageView;
        TextView textView;

        public ViewHolder(View view) {
            imageView = view.findViewById(R.id.list_item_image_view);
            textView = view.findViewById(R.id.list_item_text_view);
        }
    }
}
