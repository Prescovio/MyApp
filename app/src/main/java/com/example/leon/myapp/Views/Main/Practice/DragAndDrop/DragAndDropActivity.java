package com.example.leon.myapp.Views.Main.Practice.DragAndDrop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.example.leon.myapp.R;

public class DragAndDropActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private LinearLayout mLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_and_drop);

        mRecyclerView = findViewById(R.id.drag_drop_recycler_view);
        mLinearLayout = findViewById(R.id.drag_drop_linear_layout);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


        CustomRecyclerViewListItem listItems[] = {
                new CustomRecyclerViewListItem(R.drawable.mercury, getString(R.string.mercury)),
                new CustomRecyclerViewListItem(R.drawable.venus, getString(R.string.venus)),
                new CustomRecyclerViewListItem(R.drawable.earth, getString(R.string.earth)),
                new CustomRecyclerViewListItem(R.drawable.mars, getString(R.string.mars))
        };


        mAdapter = new CustomRecyclerViewListAdapter(this, listItems);
        mRecyclerView.setAdapter(mAdapter);
    }
}
