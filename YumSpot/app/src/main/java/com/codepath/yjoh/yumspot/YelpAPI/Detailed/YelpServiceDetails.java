package com.codepath.yjoh.yumspot.YelpAPI.Detailed;

import com.codepath.yjoh.yumspot.YelpAPI.YelpRestaurant;
import com.codepath.yjoh.yumspot.YelpAPI.YelpSearchResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface YelpServiceDetails {

    @GET("businesses/{id}")
    Call<YelpDetailedRestaurant> searchRestaurant(@Header ("Authorization") String authHeader, @Path("id") String restaurantId);
}