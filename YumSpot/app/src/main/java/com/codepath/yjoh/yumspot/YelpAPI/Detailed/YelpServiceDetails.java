package com.codepath.yjoh.yumspot.YelpAPI;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface YelpServiceDetails {

    @GET("businesses/{id}")
    Call<YelpSearchResult> searchRestaurant(@Header ("Authorization") String authHeader,
                                            @Query("id") String businessId);
}