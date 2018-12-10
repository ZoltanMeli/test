package com.ml.zszabo.segunda.Model;

import com.google.gson.annotations.SerializedName;

public class Picture {

    @SerializedName("id")
    private String id;
    @SerializedName("url")
    private String url;
    @SerializedName("secure_url")
    private String secureUrl;
    @SerializedName("size")
    private String size;
    @SerializedName("maxSize")
    private String max_size;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSecureUrl() {
        return secureUrl;
    }

    public void setSecureUrl(String secureUrl) {
        this.secureUrl = secureUrl;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMax_size() {
        return max_size;
    }

    public void setMax_size(String max_size) {
        this.max_size = max_size;
    }
}
