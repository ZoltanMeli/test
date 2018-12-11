
package com.ml.zszabo.sellerdetail.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BuyerReputation {

    @SerializedName("tags")
    @Expose
    private List<Object> tags = null;

    public List<Object> getTags() {
        return tags;
    }

    public void setTags(List<Object> tags) {
        this.tags = tags;
    }

}
