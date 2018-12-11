
package com.ml.zszabo.sellerdetail.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Transactions {

    @SerializedName("canceled")
    @Expose
    private Integer canceled;
    @SerializedName("completed")
    @Expose
    private Integer completed;
    @SerializedName("period")
    @Expose
    private String period;
    @SerializedName("ratings")
    @Expose
    private Ratings ratings;
    @SerializedName("total")
    @Expose
    private Integer total;

    public Integer getCanceled() {
        return canceled;
    }

    public void setCanceled(Integer canceled) {
        this.canceled = canceled;
    }

    public Integer getCompleted() {
        return completed;
    }

    public void setCompleted(Integer completed) {
        this.completed = completed;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Ratings getRatings() {
        return ratings;
    }

    public void setRatings(Ratings ratings) {
        this.ratings = ratings;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

}
