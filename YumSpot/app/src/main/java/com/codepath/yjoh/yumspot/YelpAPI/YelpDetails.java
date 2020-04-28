package com.codepath.yjoh.yumspot.YelpAPI;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class YelpDetails {

    @SerializedName("id") String businessId;
    @SerializedName("businesses")
    public YelpRestaurant restaurant;

}