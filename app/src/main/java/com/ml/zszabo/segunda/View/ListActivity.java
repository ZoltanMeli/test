package com.ml.zszabo.segunda.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ml.zszabo.segunda.Model.Item;
import com.ml.zszabo.segunda.Model.SearchResponse;
import com.ml.zszabo.segunda.Presenter.ListPresenter;
import com.ml.zszabo.segunda.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListActivity extends AppCompatActivity implements ItemAdapter.ItemClickListener {

    private ListPresenter presenter;
    private ItemAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        presenter = new ListPresenter();

        adapter = new ItemAdapter(this);
        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        presenter.getItems(new ListPresenter.OnItemsReceivedListener() {
            @Override
            public void onItemsReceived(List<Item> items) {
                adapter.addItems(items);
            }
        });

    }

    @Override
    public void onItemClick(Item item) {

    }
}
