package com.ml.zszabo.segunda.View;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.ParseException;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ml.zszabo.segunda.Model.Item;
import com.ml.zszabo.segunda.Presenter.ItemDetailPresenter;
import com.ml.zszabo.segunda.R;
import com.rbrooks.indefinitepagerindicator.IndefinitePagerIndicator;

public class ItemDetailActivity extends AppCompatActivity {

    public final static String DETAIL_INTENT_ITEM_ID_KEY = "detailkey";

    private Toolbar toolbar;
    private Item item;
    private RecyclerView recyclerView;
    private IndefinitePagerIndicator indicator;
    private TextView title, priceTag, description;
    private Button button;

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
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        final ItemDetailPresenter presenter = new ItemDetailPresenter();
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

    }

    private void findViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.item_detail_recyclerview);
        indicator = findViewById(R.id.item_detail_recyclerview_pager_indicator);
        title = findViewById(R.id.item_detail_title);
        priceTag = findViewById(R.id.item_detail_pricetag);
        description = findViewById(R.id.item_detail_description);
        button = findViewById(R.id.item_detail_button);
    }

    private void populateUI() {
        toolbar.setTitle(item.getTitle());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
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

}
