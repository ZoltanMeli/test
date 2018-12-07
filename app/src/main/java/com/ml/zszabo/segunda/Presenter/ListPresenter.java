package com.ml.zszabo.segunda.Presenter;

import com.ml.zszabo.segunda.Model.Item;
import com.ml.zszabo.segunda.Model.SearchResponse;
import com.ml.zszabo.segunda.Util.API;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListPresenter {

    public void getItems(final OnItemsReceivedListener onItemsReceivedListener) {
        API.getApi().searchItem("Barny").enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                onItemsReceivedListener.onItemsReceived(response.body().getResults());
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {

            }
        });
    }

    public interface OnItemsReceivedListener {
        void onItemsReceived(List<Item> items);
    }

}
