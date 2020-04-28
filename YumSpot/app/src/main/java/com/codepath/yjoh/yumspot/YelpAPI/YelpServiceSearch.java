package com.codepath.yjoh.yumspot.YelpAPI;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface YelpServiceSearch {

    @GET("businesses/search")
    Call<YelpSearchResult> searchRestaurant(@Header ("Authorization") String authHeader,
                                            @Query("categories") String searchCategories,
                                            @Query("location") String location,
                                            @Query("term") String searchTerm);
}