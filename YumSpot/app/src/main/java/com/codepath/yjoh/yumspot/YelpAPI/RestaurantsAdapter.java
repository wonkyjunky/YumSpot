package com.codepath.yjoh.yumspot.YelpAPI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.codepath.yjoh.yumspot.R;

import java.util.List;

public class RestaurantsAdapter extends RecyclerView.Adapter<RestaurantsAdapter.ViewHolder> {

    private Context context;
    private List<YelpRestaurant> restaurants;

    public RestaurantsAdapter(Context context, List<YelpRestaurant> restaurants) {
        this.context = context;
        this.restaurants = restaurants;
    }

    @NonNull
    @Override
    public RestaurantsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_restaurant, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantsAdapter.ViewHolder holder, int position) {
        YelpRestaurant restaurant = restaurants.get(position);
        holder.bind(restaurant);
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        RatingBar ratingBar;
        TextView tvNumReviews;
        TextView tvAddress;
        TextView tvCategory;
        TextView tvDistance;
        TextView tvPrice;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            tvNumReviews = itemView.findViewById(R.id.tvNumReviews);
            tvAddress = itemView.findViewById(R.id.tvAddress);
            tvCategory = itemView.findViewById(R.id.tvCategory);
            tvDistance = itemView.findViewById(R.id.tvDistance);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            imageView = itemView.findViewById(R.id.imageView);

        }

        public void bind(YelpRestaurant restaurant) {
            tvName.setText(restaurant.name);
            ratingBar.setRating(Float.valueOf(String.valueOf(restaurant.rating)));
            tvNumReviews.setText(restaurant.numReviews + " Reviews");
            tvAddress.setText(restaurant.location.address);
            tvCategory.setText(restaurant.categories.get(0).title);
            tvDistance.setText(restaurant.displayDistance());
            tvPrice.setText(restaurant.price);
            Glide.with(context).load(restaurant.imageUrl).apply(new RequestOptions().transform(
                    new CenterCrop(), new RoundedCorners(20)
            )).into(imageView);

        }
    }


}