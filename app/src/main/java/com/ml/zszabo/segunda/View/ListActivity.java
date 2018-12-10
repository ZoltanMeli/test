package com.ml.zszabo.segunda.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ml.zszabo.segunda.Model.Item;
import com.ml.zszabo.segunda.Model.SearchResponse;
import com.ml.zszabo.segunda.Presenter.ListPresenter;
import com.ml.zszabo.segunda.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListActivity extends AppCompatActivity implements ItemAdapter.ItemClickListener {

    private static final String RECYCLER_STATE_KEY = "reckey";
    private static final String SAVED_BUNDLE_ITEMS_KEY = "itemskey";
    private static final String SAVED_QUERY_KEY = "savedquerykey";
    private ItemAdapter adapter;
    private RecyclerView recyclerView;
    private Bundle mBundleRecyclerViewState;
    private List<Item> items;
    private EditText editText;
    private ListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        try {
            String action = getIntent().getData().toString();
            String query = action.substring(action.indexOf("/search/")+8);
            if (query.length()>0) saveQuery(query);
        } catch (Exception e) {}

        presenter = new ListPresenter();

        adapter = new ItemAdapter(this);
        recyclerView = findViewById(R.id.activity_list_recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        editText = findViewById(R.id.activity_list_edittext);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    saveQuery(editText.getText().toString());
                    getItems(presenter);
                    hideKeyboard();
                    return true;
                }
                return false;
            }
        });

        if (savedInstanceState == null) {
            getItems(presenter);
            editText.setText(getSavedQuery());
        } else {
            String itemsJson = savedInstanceState.getString(SAVED_BUNDLE_ITEMS_KEY);
            ListActivity.this.items = new Gson().fromJson(itemsJson, new TypeToken<List<Item>>(){}.getType());
            adapter.setItems(items);
        }

        ImageView searchButton = findViewById(R.id.activity_list_search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveQuery(editText.getText().toString());
                getItems(presenter);
            }
        });

    }

    private void getItems(ListPresenter presenter) {
        presenter.getItems(getSavedQuery(), new ListPresenter.OnItemsReceivedListener() {
            @Override
            public void onItemsReceived(List<Item> items) {
                ListActivity.this.items = items;
                adapter.setItems(items);
            }
        });
    }

    private String getSavedQuery() {
        return PreferenceManager.getDefaultSharedPreferences(this).getString(SAVED_QUERY_KEY, "Barny");
    }

    private void saveQuery(String query) {
        PreferenceManager.getDefaultSharedPreferences(this).edit().putString(SAVED_QUERY_KEY, query).apply();
    }

    @Override
    public void onItemClick(Item item) {
        Intent intent = new Intent(this, ItemDetailActivity.class);
        intent.putExtra(ItemDetailActivity.DETAIL_INTENT_ITEM_ID_KEY, item.getId());
        startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(SAVED_BUNDLE_ITEMS_KEY, new Gson().toJson(items));
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

    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(this);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
