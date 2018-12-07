package com.ml.zszabo.segunda.Util;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.ml.zszabo.segunda.Model.Item;
import com.ml.zszabo.segunda.Model.SearchResponse;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class API {

    private static API api;
    private static Meli service;

    private API() {}

    public static API getApi() {
        if (api == null) {
            api = new API();
        }
        if (service == null) {

            OkHttpClient client = new OkHttpClient.Builder()
                    .addNetworkInterceptor(new StethoInterceptor())
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.mercadolibre.com/sites/MLA/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();

            service = retrofit.create(Meli.class);
        }
        return api;
    }

    public Call<SearchResponse> searchItem(String q) {
        return service.searchItem(q);
    }

    public interface Meli {
        @GET("search")
        Call<SearchResponse> searchItem(@Query("q") String item);
    }

}
