package com.codepath.yjoh.yumspot.YelpAPI;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface YelpService {

    @GET("businesses/search")
    Call<YelpSearchResult> searchRestaurant(@Header ("Authorization") String authHeader, @Query("term") String searchTerm, @Query("location") String location);
}