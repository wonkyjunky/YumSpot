package com.codepath.yjoh.yumspot.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.codepath.yjoh.yumspot.R;
import com.codepath.yjoh.yumspot.YelpAPI.RestaurantsAdapter;
import com.codepath.yjoh.yumspot.YelpAPI.YelpRestaurant;
import com.codepath.yjoh.yumspot.YelpAPI.YelpSearchResult;
import com.codepath.yjoh.yumspot.YelpAPI.YelpServiceSearch;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends Fragment {

    private static final String TAG = "SearchActivity";
    private static final String BASE_URL = "https://api.yelp.com/v3/";
    public static final String  apiKey = "Zn_-XRQAXGwGDOexuinCZkofR-0ZGYDVr-90rF8vwEkLe257suMGUrtQazkrA_YD6c3VV5Q8CdTfhjKWQAGExRxtKJ7BQUrNkPjupz7ANviqerfmNPRgmMihurSPXnYx";

    EditText etSearchName;
    EditText etSearchLocation;

    List<YelpRestaurant> restaurants;
    RestaurantsAdapter adapter;
    RecyclerView rvRestaurant;
    public static String categories;
    public static String location = "San Jose";
    public static String term = categories;
    public static String searchSelect = "Name";
    ImageButton btBackSearch;
    Button btSearch;
    YelpServiceSearch yelpService;

    public SearchActivity() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_search, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        defaultvalues();
        yelpService = retrofit.create(YelpServiceSearch.class);
        etSearchName = view.findViewById(R.id.etSearchName);
        etSearchLocation = view.findViewById(R.id.etSearchLocation);
        btBackSearch = view.findViewById(R.id.btBackSearch);
        btSearch = view.findViewById(R.id.btSearch);
        rvRestaurant = view.findViewById(R.id.rvRestaurants);
        Button btnBackSearch;
        restaurants = new ArrayList<>();
        adapter = new RestaurantsAdapter(getActivity(), restaurants);

        rvRestaurant.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvRestaurant.setAdapter(adapter);

        btBackSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CategoryActivity category = new CategoryActivity();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.flContainer, category)
                        .addToBackStack(null)
                        .commit();
            }
        });

        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputName = etSearchName.getText().toString();
                String inputLocation = etSearchLocation.getText().toString();

                if (inputName.equals("") && inputLocation.equals("")) {
                    Toast.makeText(getActivity(), "You entered nothing.", Toast.LENGTH_SHORT).show();
                }
                else if (inputName.equals("")) {
                    location = inputLocation;
                }
                else if (inputLocation.equals("")) {
                    term = inputName;
                }
                else {
                    location = inputLocation;
                    term = inputName;
                }
                restaurants.clear();
                search(term, location);
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
                    Toast.makeText(getActivity(), "No results. Try others!", Toast.LENGTH_SHORT).show();
                    return;
                }
                restaurants.addAll(body.restaurants);
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