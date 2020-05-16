package com.codepath.yjoh.yumspot.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codepath.yjoh.yumspot.ChatAdapter;
import com.codepath.yjoh.yumspot.Post;
import com.codepath.yjoh.yumspot.PostsAdapter;
import com.codepath.yjoh.yumspot.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends PostsFragment {

//    @Override
//    protected void queryPosts() {
//        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
//        query.include(Post.KEY_USER);
//        query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());
//        query.setLimit(20);
//        query.addDescendingOrder(Post.KEY_CREATED_KEY);
//        query.findInBackground(new FindCallback<Post>() {
//            @Override
//            public void done(List<Post> posts, ParseException e) {
//                if (e != null) {
//                    Log.e(TAG, "Issue with getting posts", e);
//                    return;
//                }
//                for (Post post : posts) {
//                    Log.i(TAG, "Post: " + post.getDescription() + ", username: " + post.getUser().getUsername());
//                }
//                allPosts.addAll(posts);
//                adapter.notifyDataSetChanged();
//            }
//        });
//    }

    TextView tvUsername;
    TextView tvEmail;
    TextView tvName;
    RecyclerView rvPosts;
    ImageView ivProfile;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvUsername = view.findViewById(R.id.tvUserName);
        tvEmail = view.findViewById(R.id.tvEmail);
        tvName = view.findViewById(R.id.tvName);
        rvPosts = view.findViewById(R.id.rvPosts);
        ivProfile = view.findViewById(R.id.ivProfile);

        tvUsername.setText(ParseUser.getCurrentUser().getUsername());
        tvName.setText(ParseUser.getCurrentUser().getString("Name"));
        tvEmail.setText(ParseUser.getCurrentUser().getEmail());

        allPosts = new ArrayList<>();
        adapter = new PostsAdapter(getContext(), allPosts);

        rvPosts.setAdapter(adapter);
        rvPosts.setLayoutManager(new LinearLayoutManager(getContext()));

        queryPosts();

        Glide.with(this).load(getProfileUrl(ParseUser.getCurrentUser().getInt("number"))).override(300,300).circleCrop().into(ivProfile);
    }
    @Override
    protected void queryPosts() {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USER);
        query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());
        query.setLimit(20);
        query.addDescendingOrder(Post.KEY_CREATED_KEY);
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting posts", e);
                    return;
                }
                for (Post post : posts) {
                    Log.i(TAG, "Post: " + post.getDescription() + ", username: " + post.getUser().getUsername());
                }
                allPosts.addAll(posts);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private static String getProfileUrl(final int userNumber) {
        Log.d("Number: ", ""+userNumber);
        return "https://i.pravatar.cc/200?img=" + userNumber;
    }
}
