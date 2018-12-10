package com.ml.zszabo.segunda.View;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ml.zszabo.segunda.Model.Item;
import com.ml.zszabo.segunda.Presenter.ItemDetailPresenter;
import com.ml.zszabo.segunda.R;
import com.rbrooks.indefinitepagerindicator.IndefinitePagerIndicator;

public class ItemDetailActivity extends AppCompatActivity {

    public final static String DETAIL_INTENT_ITEM_ID_KEY = "detailkey";
    private static final String RECYCLER_STATE_KEY = "recyclerposition";
    private static final String SAVED_BUNDLE_ITEM_KEY = "savedbundle";

    private Item item;
    private RecyclerView recyclerView;
    private IndefinitePagerIndicator indicator;
    private TextView title, priceTag, description;
    private Button button;
    private LinearLayoutManager linearLayoutManager;
    private Bundle mBundleRecyclerViewState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        final String itemID = getIntent().getStringExtra(DETAIL_INTENT_ITEM_ID_KEY);
        if (itemID == null) {
            Toast.makeText(this.getApplicationContext(), "Item ID invalid", Toast.LENGTH_SHORT).show();
            finish();
        }

        findViews();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        final ItemDetailPresenter presenter = new ItemDetailPresenter();
        if (savedInstanceState == null) {
            presenter.getItem(itemID, new ItemDetailPresenter.OnItemReceivedListener() {
                @Override
                public void onItemReceived(Item item) {
                    ItemDetailActivity.this.item = item;
                    populateUI();
                    presenter.getItemDescription(itemID, new ItemDetailPresenter.OnItemDescriptionReceivedListener() {

                        @Override
                        public void onItemDescriptionReceived(String description) {
                            ItemDetailActivity.this.item.setDescription(description);
                            populateUI();
                        }
                    });
                }
            });
        } else {
            String itemJson = savedInstanceState.getString(SAVED_BUNDLE_ITEM_KEY);
            ItemDetailActivity.this.item = new Gson().fromJson(itemJson, Item.class);
            populateUI();
        }
    }

    private void findViews() {
        recyclerView = findViewById(R.id.item_detail_recyclerview);
        indicator = findViewById(R.id.item_detail_recyclerview_pager_indicator);
        title = findViewById(R.id.item_detail_title);
        priceTag = findViewById(R.id.item_detail_pricetag);
        description = findViewById(R.id.item_detail_description);
        button = findViewById(R.id.item_detail_button);
    }

    private void populateUI() {
        getSupportActionBar().setTitle(item.getTitle());
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        PicturesRecyclerAdapter adapter = new PicturesRecyclerAdapter(this, item.getPictures());
        recyclerView.setAdapter(adapter);
        try {
            SnapHelper snapHelper = new LinearSnapHelper();
            snapHelper.attachToRecyclerView(recyclerView);
        } catch (IllegalStateException e) {
            // Do nothing
        }
        indicator.attachToRecyclerView(recyclerView);

        title.setText(item.getTitle());
        priceTag.setText(item.getPriceTag());
        description.setText(item.getDescription());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMeliIntent();
            }
        });
    }

    private void startMeliIntent() {
        try {
            Uri uri = Uri.parse(item.getLink());
            try {
                Intent myIntent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(myIntent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(this, "oops", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "oops", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(SAVED_BUNDLE_ITEM_KEY, new Gson().toJson(item));
        super.onSaveInstanceState(outState);
    }


    @Override
    protected void onPause()
    {
        super.onPause();

        // save RecyclerView state
        mBundleRecyclerViewState = new Bundle();
        Parcelable listState = recyclerView.getLayoutManager().onSaveInstanceState();
        mBundleRecyclerViewState.putParcelable(RECYCLER_STATE_KEY, listState);
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        // restore RecyclerView state
        if (mBundleRecyclerViewState != null) {
            Parcelable listState = mBundleRecyclerViewState.getParcelable(RECYCLER_STATE_KEY);
            recyclerView.getLayoutManager().onRestoreInstanceState(listState);
        }
    }
}
