package com.ml.zszabo.segunda.Presenter;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.ml.zszabo.segunda.Model.Item;
import com.ml.zszabo.segunda.Util.API;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemDetailPresenter {

    public void getItem(String id, final OnItemReceivedListener onItemReceivedListener) {
        API.getApi().itemDetails(id).enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                onItemReceivedListener.onItemReceived(response.body());
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {

            }
        });
    }

    public void getItemDescription(String id, final OnItemDescriptionReceivedListener onItemDescriptionReceivedListener) {
        API.getApi().itemDescription(id).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    try {
                        String description = response.body().get("plain_text").getAsString();
                        onItemDescriptionReceivedListener.onItemDescriptionReceived(description);
                    } catch (JsonParseException e) {
                        e.printStackTrace();
                        onItemDescriptionReceivedListener.onItemDescriptionReceived("");
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }

    public interface OnItemReceivedListener {
        void onItemReceived(Item item);
    }

    public interface OnItemDescriptionReceivedListener {
        void onItemDescriptionReceived(String description);
    }
}
