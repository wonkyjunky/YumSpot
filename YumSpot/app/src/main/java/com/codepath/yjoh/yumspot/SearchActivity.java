package com.codepath.yjoh.yumspot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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

    private Spinner spinner;
    private static final String[] paths = {"Name", "Location"};
    EditText etSearchRestaurants;

    List<YelpRestaurant> restaruants;
    RestaurantsAdapter adapter;
    RecyclerView rvRestaurant;
    public static String categories;
    public static String location = "San Jose";
    public static String term = categories;
    public static String searchSelect = "Name";
    Button btSearch;
    YelpService yelpService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        defaultvalues();
        yelpService = retrofit.create(YelpService.class);
        etSearchRestaurants = findViewById(R.id.etSearchRestaurants);
        btSearch = findViewById(R.id.btSearch);
        rvRestaurant = findViewById(R.id.rvRestaurants);
        restaruants = new ArrayList<>();
        adapter = new RestaurantsAdapter(this, restaruants);

        rvRestaurant.setLayoutManager(new LinearLayoutManager(this));
        rvRestaurant.setAdapter((RecyclerView.Adapter) adapter);

        spinner = findViewById(R.id.spinner1);
        ArrayAdapter<String> SpinAdapter = new ArrayAdapter(SearchActivity.this, android.R.layout.simple_spinner_item,paths);

        SpinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(SpinAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                switch (position){
                    case 0:
                        searchSelect = "Name";
                        break;
                    case 1:
                        searchSelect = "Location";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etSearchRestaurants.getText().toString().equals("")) {
                    Toast.makeText(SearchActivity.this, "You Entered nothing!", Toast.LENGTH_SHORT).show();
                } else {
                    if (searchSelect.equals("Location")) {
                        location = etSearchRestaurants.getText().toString();
                    }
                    else if (searchSelect.equals("Name")){
                        term = etSearchRestaurants.getText().toString();
                    }
                    restaruants.clear();
                    search(term, location);

                }
            }
        });


        search(term, location);

    }


    public void search(String term, String location){
        yelpService.searchRestaurant("Bearer "+ apiKey,categories, location, term).enqueue(new Callback<YelpSearchResult>() {
            @Override
            public void onResponse(Call<YelpSearchResult> call, Response<YelpSearchResult> response) {
                Log.i(TAG, "onResponse" + response);
                YelpSearchResult body = response.body();
                if (body == null){
                    Toast.makeText(SearchActivity.this, "No results! Try other one!", Toast.LENGTH_SHORT).show();
                    return;
                }
                restaruants.addAll(body.restaurants);
                adapter.notifyDataSetChanged();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<YelpSearchResult> call, Throwable t) {
                Log.i(TAG, "onFailure" + t);
            }
        });
    }

    public void defaultvalues(){
        location = "San Jose";
        term = categories;
        searchSelect = "Name";
    }

}