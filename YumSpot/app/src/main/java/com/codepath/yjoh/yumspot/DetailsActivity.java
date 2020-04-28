//package com.codepath.yjoh.yumspot;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageButton;
//import android.widget.Toast;
//
//import com.codepath.yjoh.yumspot.YelpAPI.RestaurantsAdapter;
//import com.codepath.yjoh.yumspot.YelpAPI.YelpRestaurant;
//import com.codepath.yjoh.yumspot.YelpAPI.YelpSearchResult;
//import com.codepath.yjoh.yumspot.YelpAPI.YelpServiceDetails;
//import com.codepath.yjoh.yumspot.YelpAPI.YelpServiceSearch;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class DetailsActivity extends AppCompatActivity {
//
//    private static final String TAG = "DetailsActivity";
//    private static final String BASE_URL = "https://api.yelp.com/v3/";
//    public static final String  apiKey = "Zn_-XRQAXGwGDOexuinCZkofR-0ZGYDVr-90rF8vwEkLe257suMGUrtQazkrA_YD6c3VV5Q8CdTfhjKWQAGExRxtKJ7BQUrNkPjupz7ANviqerfmNPRgmMihurSPXnYx";
//
//    private static final String[] paths = {"Name", "Location"};
//
//
//    YelpRestaurant restaurant;
//    RestaurantsAdapter adapter;
//    RecyclerView rvRestaurant;
////    public static String categories;
////    public static String location = "San Jose";
////    public static String term = categories;
////    public static String searchSelect = "Name";
//    public static String businessId = "id";
//    ImageButton btBackDetails;
//    YelpServiceDetails yelpServiceDetails;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_search);
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        defaultvalues();
//        yelpServiceDetails = retrofit.create(YelpServiceDetails.class);
//
//        btBackDetails = findViewById(R.id.btBackDetails);
//        rvRestaurant = findViewById(R.id.rvRestaurants);
//        restaurant = new YelpRestaurant();
//        adapter = new RestaurantsAdapter(this, restaruants);
//
//        rvRestaurant.setLayoutManager(new LinearLayoutManager(this));
//        rvRestaurant.setAdapter((RecyclerView.Adapter) adapter);
//
//        btBackSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(SearchActivity.this, CategoryActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//
//        btSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                if (inputName.equals("") && inputLocation.equals("")) {
//                    Toast.makeText(DetailsActivity.this, "You entered nothing.", Toast.LENGTH_SHORT).show();
//                }
//                else if (inputName.equals("")) {
//                    location = inputLocation;
//                }
//                else if (inputLocation.equals("")) {
//                    term = inputName;
//                }
//                else {
//                    location = inputLocation;
//                    term = inputName;
//                }
//                restaruants.clear();
//                search(term, location);
//            }
//        });
//        search(term, location);
//    }
//
//    public void search(String term, String location){
//        yelpService.searchRestaurant("Bearer "+ apiKey,categories, location, term).enqueue(new Callback<YelpSearchResult>() {
//            @Override
//            public void onResponse(Call<YelpSearchResult> call, Response<YelpSearchResult> response) {
//                Log.i(TAG, "onResponse" + response);
//                YelpSearchResult body = response.body();
//                if (body == null){
//                    Toast.makeText(DetailsActivity.this, "No results. Try others!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                restaruants.addAll(body.restaurants);
//                adapter.notifyDataSetChanged();
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onFailure(Call<YelpSearchResult> call, Throwable t) {
//                Log.i(TAG, "onFailure" + t);
//            }
//        });
//    }
//
//    public void defaultvalues(){
//        location = "San Jose";
//        term = categories;
//        searchSelect = "Name";
//    }
//
//}