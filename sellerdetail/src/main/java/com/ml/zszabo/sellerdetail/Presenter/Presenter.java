package com.ml.zszabo.sellerdetail.Presenter;

import com.ml.zszabo.sellerdetail.Model.Seller;
import com.ml.zszabo.sellerdetail.Util.API;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Presenter {

    public void getSeller(String id, final SellerListener listener) {
        API.getApi().getSeller(id).enqueue(new Callback<Seller>() {
            @Override
            public void onResponse(Call<Seller> call, Response<Seller> response) {
                listener.onReceivedSeller(response.body());
            }

            @Override
            public void onFailure(Call<Seller> call, Throwable t) {

            }
        });
    }

    public interface SellerListener{
        void onReceivedSeller(Seller seller);
    }
}
