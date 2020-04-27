package com.codepath.yjoh.yumspot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;

import com.codepath.yjoh.yumspot.R;
import com.codepath.yjoh.yumspot.YelpAPI.RestaurantsAdapter;
import com.codepath.yjoh.yumspot.YelpAPI.YelpRestaurant;
import com.codepath.yjoh.yumspot.YelpAPI.YelpSearchResult;
import com.codepath.yjoh.yumspot.YelpAPI.YelpService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity {

    private static final String TAG = "SearchActivity";
    private static final String BASE_URL = "https://api.yelp.com/v3/";
    public static final String  apiKey = "Zn_-XRQAXGwGDOexuinCZkofR-0ZGYDVr-90rF8vwEkLe257suMGUrtQazkrA_YD6c3VV5Q8CdTfhjKWQAGExRxtKJ7BQUrNkPjupz7ANviqerfmNPRgmMihurSPXnYx";

    List<YelpRestaurant> restaruants;
    RestaurantsAdapter adapter;
    RecyclerView rvRestaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Retrofit retrofit = new Retrofit.Builder()

                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        YelpService yelpService = retrofit.create(YelpService.class);

        rvRestaurant = findViewById(R.id.rvRestaurants);
        restaruants = new ArrayList<>();
        adapter = new RestaurantsAdapter(this, restaruants);

        rvRestaurant.setLayoutManager(new LinearLayoutManager(this));
        rvRestaurant.setAdapter((RecyclerView.Adapter) adapter);


        yelpService.searchRestaurant("Bearer "+ apiKey,"Chicken", "San Jose").enqueue(new Callback<YelpSearchResult>() {
            @Override
            public void onResponse(Call<YelpSearchResult> call, Response<YelpSearchResult> response) {
                Log.i(TAG, "onResponse" + response);
                YelpSearchResult body = response.body();
                restaruants.addAll(body.restaurants);
                ((RecyclerView.Adapter) adapter).notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<YelpSearchResult> call, Throwable t) {
                Log.i(TAG, "onFailure" + t);
            }
        });

    }
}