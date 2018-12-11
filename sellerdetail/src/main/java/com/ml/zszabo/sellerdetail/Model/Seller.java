
package com.ml.zszabo.sellerdetail.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Seller {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nickname")
    @Expose
    private String nickname;
    @SerializedName("registration_date")
    @Expose
    private String registrationDate;
    @SerializedName("country_id")
    @Expose
    private String countryId;
    @SerializedName("address")
    @Expose
    private Address address;
    @SerializedName("user_type")
    @Expose
    private String userType;
    @SerializedName("tags")
    @Expose
    private List<String> tags = null;
    @SerializedName("logo")
    @Expose
    private Object logo;
    @SerializedName("points")
    @Expose
    private Integer points;
    @SerializedName("site_id")
    @Expose
    private String siteId;
    @SerializedName("permalink")
    @Expose
    private String permalink;
    @SerializedName("seller_reputation")
    @Expose
    private SellerReputation sellerReputation;
    @SerializedName("buyer_reputation")
    @Expose
    private BuyerReputation buyerReputation;
    @SerializedName("status")
    @Expose
    private Status status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Object getLogo() {
        return logo;
    }

    public void setLogo(Object logo) {
        this.logo = logo;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public SellerReputation getSellerReputation() {
        return sellerReputation;
    }

    public void setSellerReputation(SellerReputation sellerReputation) {
        this.sellerReputation = sellerReputation;
    }

    public BuyerReputation getBuyerReputation() {
        return buyerReputation;
    }

    public void setBuyerReputation(BuyerReputation buyerReputation) {
        this.buyerReputation = buyerReputation;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
