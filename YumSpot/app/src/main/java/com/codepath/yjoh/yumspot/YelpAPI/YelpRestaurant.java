
package com.codepath.yjoh.yumspot.YelpAPI;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class YelpRestaurant {
    String name;
    Double rating;
    String price;
    @SerializedName("review_count") Integer numReviews;
    @SerializedName("distance")
    private Double distanceInMeters;
    @SerializedName("image_url") String imageUrl;
    @SerializedName("id") String businessId;
    List<YelpCategory> categories;
    YelpLocation location;

    String displayDistance(){
        Double milesPerMeter = 0.000621371;
        String distanceInMiles = String.format("%.2f", distanceInMeters * milesPerMeter);
        return distanceInMiles + " mi";
    }

}