package com.codepath.yjoh.yumspot.YelpAPI;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class YelpSearchResult {

    @SerializedName("total") int total;
    @SerializedName("businesses")
    public List<YelpRestaurant> restaurants;

}