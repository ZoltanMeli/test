package com.ml.zszabo.segunda.Presenter;

import com.ml.zszabo.segunda.Model.Item;
import com.ml.zszabo.segunda.Model.SearchResponse;
import com.ml.zszabo.segunda.Util.API;

import java.util.List;

import retrofit2.Call;

public class ListPresenter {

    public Call<SearchResponse> getItems() {
        return API.getApi().searchItem("Barny");
    }


}
