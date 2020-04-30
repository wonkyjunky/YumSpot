package com.codepath.yjoh.yumspot.YelpAPI.Detailed;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class YelpDetailedRestaurant {
    String name;
    Double rating;
    String price;
    boolean is_closed;
    String display_phone;
    String url;
    YelpDetailedLocation location;
    @SerializedName("review_count") Integer numReviews;
    @SerializedName("image_url") String imageUrl;
    @SerializedName("id") String businessId;
    public List<String> photos;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isIs_closed() {
        return is_closed;
    }

    public void setIs_closed(boolean is_closed) {
        this.is_closed = is_closed;
    }

    public String getDisplay_phone() {
        return display_phone;
    }

    public void setDisplay_phone(String display_phone) {
        this.display_phone = display_phone;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public YelpDetailedLocation getLocation() {
        return location;
    }

    public void setLocation(YelpDetailedLocation location) {
        this.location = location;
    }

    public Integer getNumReviews() {
        return numReviews;
    }

    public void setNumReviews(Integer numReviews) {
        this.numReviews = numReviews;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public List<String> getPhotos() {
        return photos;
    }
}
