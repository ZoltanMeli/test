package com.ml.zszabo.sellerdetail.Util;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.ml.zszabo.sellerdetail.Model.Seller;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

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
                    .baseUrl("https://api.mercadolibre.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();

            service = retrofit.create(Meli.class);
        }
        return api;
    }

    public Call<Seller> getSeller(String id) { return service.getSeller(id); }

    public interface Meli {
        @GET("users/{id}")
        Call<Seller> getSeller(@Path("id") String id);
    }

}
