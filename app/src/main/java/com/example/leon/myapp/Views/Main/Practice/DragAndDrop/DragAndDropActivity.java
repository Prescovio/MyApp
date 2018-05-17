package com.example.leon.myapp.Views.Main.Practice.DragAndDrop;

import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.leon.myapp.R;
import com.example.leon.myapp.Views.Login.LoginFragment;
import com.example.leon.myapp.Views.Main.DefaultList.ListFragment;
import com.example.leon.myapp.Views.Registration.RegistrationFragment;

import java.util.ArrayList;

public class DragAndDropActivity extends AppCompatActivity implements View.OnDragListener{

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<CustomRecyclerViewListItem> mItems = new ArrayList<>();
    private FragmentManager fragmentManager;

    private LinearLayout mLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_and_drop);

        fragmentManager = getSupportFragmentManager();

        mRecyclerView = findViewById(R.id.drag_drop_recycler_view);
        mLinearLayout = findViewById(R.id.drag_drop_linear_layout);
        mLinearLayout.setOnDragListener(this);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


        mItems.add(new CustomRecyclerViewListItem(R.drawable.mercury, getString(R.string.mercury), new LoginFragment()));
        mItems.add(new CustomRecyclerViewListItem(R.drawable.venus, getString(R.string.venus), new RegistrationFragment()));
        mItems.add(new CustomRecyclerViewListItem(R.drawable.earth, getString(R.string.earth), new ListFragment()));

        mAdapter = new CustomRecyclerViewListAdapter(this, mItems);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                break;
            case DragEvent.ACTION_DROP:
                // Dropped, reassign View to ViewGroup

                for (CustomRecyclerViewListItem listItem : mItems) {
                    if (listItem.getDrawableId() == (int)event.getLocalState())
                    {
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.drag_drop_linear_layout, listItem.getFragment());
                        fragmentTransaction.commit();
                    }
                }

                break;
            case DragEvent.ACTION_DRAG_ENDED:
                break;
            default:
                break;
        }
        return true;
    }
}
