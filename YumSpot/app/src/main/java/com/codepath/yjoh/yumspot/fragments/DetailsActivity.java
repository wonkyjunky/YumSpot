package com.codepath.yjoh.yumspot.fragments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.codepath.yjoh.yumspot.MainActivity;
import com.codepath.yjoh.yumspot.R;
import com.codepath.yjoh.yumspot.YelpAPI.Detailed.YelpDetailedRestaurant;
import com.codepath.yjoh.yumspot.YelpAPI.Detailed.YelpServiceDetails;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailsActivity extends AppCompatActivity {

    private static final String TAG = "DetailsActivity";
    public static final String BASE_URL = "https://api.yelp.com/v3/";
    public static String id;
    public static final String  apiKey = "Zn_-XRQAXGwGDOexuinCZkofR-0ZGYDVr-90rF8vwEkLe257suMGUrtQazkrA_YD6c3VV5Q8CdTfhjKWQAGExRxtKJ7BQUrNkPjupz7ANviqerfmNPRgmMihurSPXnYx";

    Context context;
    private YelpServiceDetails yelpService;
    YelpDetailedRestaurant restaurant;
    TextView tvTitle;
    TextView tvPhone;
    RatingBar ratingBar2;
    TextView tvPrice;
    TextView tvAddress;
    TextView isClosed;
    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    TextView tvNumReviews;
    TextView tvUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        tvTitle = findViewById(R.id.tvDetailedTitle);
        tvPhone = findViewById(R.id.tvPhone);
        ratingBar2 = findViewById(R.id.ratingBar2);
        tvPrice = findViewById(R.id.tvPrice2);
        tvAddress = findViewById(R.id.tvAdresses);
        isClosed = findViewById(R.id.tvOpen);
        imageView = findViewById(R.id.ivImage);
        imageView2 = findViewById(R.id.ivImage2);
        imageView3 = findViewById(R.id.ivImage3);
        tvNumReviews = findViewById(R.id.tvNumReviews);
        tvUrl = findViewById(R.id.tvUrl);



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        yelpService = retrofit.create(YelpServiceDetails.class);

        yelpService.searchRestaurant("Bearer "+ apiKey, id).enqueue(new Callback<YelpDetailedRestaurant>() {
            @Override
            public void onResponse(Call<YelpDetailedRestaurant> call, Response<YelpDetailedRestaurant> response) {
                Log.i(TAG, "onResponse" + response);
                restaurant = response.body();
                tvTitle.setText(restaurant.getName());
                tvPhone.setText(restaurant.getDisplay_phone());
                ratingBar2.setRating(Float.valueOf(String.valueOf(restaurant.getRating())));
                tvPrice.setText(restaurant.getPrice());
                if (restaurant.getLocation().displayAddress.size() == 3) {
                    tvAddress.setText(restaurant.getLocation().displayAddress.get(0) + "\n" +
                            restaurant.getLocation().displayAddress.get(1) + "\n" +
                            restaurant.getLocation().displayAddress.get(2) + "\n");
                }
                if (restaurant.getLocation().displayAddress.size() == 2) {
                    tvAddress.setText(restaurant.getLocation().displayAddress.get(0) + "\n" +
                            restaurant.getLocation().displayAddress.get(1) + "\n");
                }
                if (restaurant.getLocation().displayAddress.size() == 1) {
                    tvAddress.setText(restaurant.getLocation().displayAddress.get(0) + "\n");
                }

                if (restaurant.isIs_closed()){
                    isClosed.setText("Closed :(");
                    isClosed.setTextColor(Color.parseColor("#F44336"));
                }
                else{
                    isClosed.setText("Open Now!");
                    isClosed.setTextColor(Color.parseColor("#4CAF50"));
                }
                if (restaurant.photos.size() == 3) {
                    Glide.with(DetailsActivity.this).load(restaurant.getPhotos().get(0)).apply(new RequestOptions().transform(
                            new CenterCrop(), new RoundedCorners(20)
                    )).into(imageView);
                    Glide.with(DetailsActivity.this).load(restaurant.getPhotos().get(1)).apply(new RequestOptions().transform(
                            new CenterCrop(), new RoundedCorners(20)
                    )).into(imageView2);
                    Glide.with(DetailsActivity.this).load(restaurant.getPhotos().get(2)).apply(new RequestOptions().transform(
                            new CenterCrop(), new RoundedCorners(20)
                    )).into(imageView3);
                }
                if (restaurant.photos.size() == 2) {
                    Glide.with(DetailsActivity.this).load(restaurant.getPhotos().get(0)).apply(new RequestOptions().transform(
                            new CenterCrop(), new RoundedCorners(20)
                    )).into(imageView);
                    Glide.with(DetailsActivity.this).load(restaurant.getPhotos().get(1)).apply(new RequestOptions().transform(
                            new CenterCrop(), new RoundedCorners(20)
                    )).into(imageView2);
                    Glide.with(DetailsActivity.this).load(restaurant.getPhotos().get(2)).apply(new RequestOptions().transform(
                            new CenterCrop(), new RoundedCorners(20)
                    )).into(imageView3);
                }

                if (restaurant.photos.size() == 1) {
                    Glide.with(DetailsActivity.this).load(restaurant.getPhotos().get(0)).apply(new RequestOptions().transform(
                            new CenterCrop(), new RoundedCorners(20)
                    )).into(imageView);
                }
                tvNumReviews.setText(restaurant.getNumReviews() + " Reviews");
                tvUrl.setText("Webpage");
                tvUrl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Uri uri = Uri.parse(restaurant.getUrl()); // missing 'http://' will cause crashed
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                });

                tvPhone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:"+restaurant.getDisplay_phone()));
                        startActivity(intent);
                    }
                });
            }



            @Override
            public void onFailure(Call<YelpDetailedRestaurant> call, Throwable t) {
                Log.i(TAG, "onFailure" + t);
            }
        });


    }
}
