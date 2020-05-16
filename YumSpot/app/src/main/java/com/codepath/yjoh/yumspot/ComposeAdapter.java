package com.codepath.yjoh.yumspot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codepath.yjoh.yumspot.fragments.ComposeFragment;
import com.parse.ParseFile;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ComposeAdapter extends RecyclerView.Adapter {

    private static final String TAG = "PostsAdapter";

    private Context context;

    public ComposeAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        int viewType = 1; //Default is 1
        if (position == 0) viewType = 0; //if zero, it will be a header view
        return viewType;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view;


        switch (viewType) {
            case 0: {
                view = layoutInflater.inflate(R.layout.item_compose, parent, false);
                return new ComposeAdapter.ViewHolderOne(view);
            }
            default:
                throw new IllegalArgumentException("Invalid view type");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == 0) {
            ComposeAdapter.ViewHolderOne viewHolderOne = (ComposeAdapter.ViewHolderOne) holder;
            viewHolderOne.ibPost.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    ComposeFragment composeFragment = new ComposeFragment();
                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.flContainer, composeFragment).addToBackStack(null).commit();
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return 1;
    }

    class ViewHolderOne extends RecyclerView.ViewHolder {

        //        EditText etPost;
        Button ibPost;

        public ViewHolderOne(@NonNull View itemView) {
            super(itemView);
//            etPost = itemView.findViewById(R.id.etPost);
            ibPost = itemView.findViewById(R.id.ibPost);
        }
    }

}

