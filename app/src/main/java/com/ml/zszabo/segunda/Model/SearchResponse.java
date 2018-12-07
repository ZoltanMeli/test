package com.ml.zszabo.segunda.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResponse {

    @SerializedName("results")
    @Expose
    private List<Item> results = null;

    public List<Item> getResults() {
        return results;
    }

    public void setResults(List<Item> results) {
        this.results = results;
    }
}
