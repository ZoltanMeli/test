
package com.ml.zszabo.sellerdetail.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ratings {

    @SerializedName("negative")
    @Expose
    private Integer negative;
    @SerializedName("neutral")
    @Expose
    private Double neutral;
    @SerializedName("positive")
    @Expose
    private Double positive;

    public Integer getNegative() {
        return negative;
    }

    public void setNegative(Integer negative) {
        this.negative = negative;
    }

    public Double getNeutral() {
        return neutral;
    }

    public void setNeutral(Double neutral) {
        this.neutral = neutral;
    }

    public Double getPositive() {
        return positive;
    }

    public void setPositive(Double positive) {
        this.positive = positive;
    }

}
