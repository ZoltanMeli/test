package com.ml.zszabo.segunda.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("price")
    @Expose
    private double price;
    @SerializedName("thumbnail")
    @Expose
    private String imageURL;

    public String getImageURL() {
        return imageURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPriceTag() {
        return String.valueOf(price);
    }

}
