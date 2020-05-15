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

public class PostsAdapter extends RecyclerView.Adapter {

    private static final String TAG = "PostsAdapter";

    private Context context;
    private List<Post> posts;

    public PostsAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
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

//        if (viewType == 0) {
//            view = layoutInflater.inflate(R.layout.item_compose, parent, false);
//            return new ViewHolderOne(view);
//        }
//        if (viewType == 1) {
//            if (Post.KEY_IMAGE != null) {
//                view = layoutInflater.inflate(R.layout.item_post, parent, false);
//            } else {
//                view = layoutInflater.inflate(R.layout.item_post_text, parent, false);
//            }
//            return new ViewHolderTwo(view);
//        } else {
//            throw IllegalArgumentException("Invalid view type")
//        }

        switch (viewType) {
            case 0: {
                view = layoutInflater.inflate(R.layout.item_compose, parent, false);
                return new ViewHolderOne(view);
            }
            case 1: {
                view = layoutInflater.inflate(R.layout.item_post, parent, false);
                return new ViewHolderTwo(view);
            }
            default:
                throw new IllegalArgumentException("Invalid view type");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == 0) {
            ViewHolderOne viewHolderOne = (ViewHolderOne) holder;
            viewHolderOne.ibPost.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    ComposeFragment composeFragment = new ComposeFragment();
                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.flContainer, composeFragment).addToBackStack(null).commit();
                }
            });
        }
        else {
            Post post = posts.get(position);
            ViewHolderTwo viewHolderTwo = (ViewHolderTwo) holder;
            viewHolderTwo.tvUsername.setText("[" + post.getUser().getUsername() + "]");
            viewHolderTwo.tvDescription.setText(post.getDescription());

            Date date = post.getUpdatedAt();
            DateFormat dateFormat = new SimpleDateFormat("MMM dd yyyy");
            String strDate = dateFormat.format(date);
            viewHolderTwo.tvUpdatedAt.setText("updated at " + strDate);

            ParseFile image = post.getImage();
            if (image != null) {
                Glide.with(context).load(image.getUrl()).into(viewHolderTwo.ivImage);
            } else {
                viewHolderTwo.ivImage.setVisibility(View.GONE);
            }
        }
    }


    @Override
    public int getItemCount() {
        return posts.size();
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

    class ViewHolderTwo extends RecyclerView.ViewHolder {
        TextView tvUsername;
        ImageView ivImage;
        TextView tvDescription;
        TextView tvUpdatedAt;

        public ViewHolderTwo(@NonNull View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tvName);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvUpdatedAt = itemView.findViewById(R.id.tvUpdatedAt);
        }
    }
}