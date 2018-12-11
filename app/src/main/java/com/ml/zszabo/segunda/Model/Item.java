package com.ml.zszabo.segunda.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Item {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("price")
    @Expose
    private double price;
    @SerializedName("thumbnail")
    @Expose
    private String imageURL;
    @SerializedName("pictures")
    private List<Picture> pictures;
    @SerializedName("permalink")
    private String link;
    @SerializedName("seller_id")
    private String sellerId;

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    private String description;

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
        return "$" + String.valueOf(price).replaceAll("[//.][0]+$","");
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public String getId() {
        return id;
    }

    public String  getDescription() {
        return description == null ? "" : description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }
}
