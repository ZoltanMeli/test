package com.ml.zszabo.sellerdetail.View;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ml.zszabo.sellerdetail.Model.Seller;
import com.ml.zszabo.sellerdetail.Presenter.Presenter;
import com.ml.zszabo.sellerdetail.R;

public class SellerActivity extends AppCompatActivity implements Presenter.SellerListener {

    private String sellerId;
    private TextView sellerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller);

        try {
            String action = getIntent().getData().toString();
            sellerId = action.substring(action.indexOf("vervendedor/")+12);
        } catch (Exception e) {
            finish();
        }

        setupViews();

        Presenter presenter = new Presenter();
        presenter.getSeller(sellerId, this);

    }

    private void setupViews() {
        sellerName = findViewById(R.id.seller_name);
    }

    @Override
    public void onReceivedSeller(Seller seller) {
        sellerName.setText(seller.getNickname());
        Toast.makeText(this, seller.getNickname(), Toast.LENGTH_SHORT).show();
    }
}
