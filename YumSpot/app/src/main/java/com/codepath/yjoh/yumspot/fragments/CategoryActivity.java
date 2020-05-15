package com.codepath.yjoh.yumspot.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

//import com.codepath.yjoh.yumspot.ChatActivity;
import com.codepath.yjoh.yumspot.R;

public class CategoryActivity extends Fragment {
    ImageView i1, i2, i3, i4, i5, i6;

        public CategoryActivity() {
            // Required empty public constructor
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.activity_category, container, false);
        }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            i5 = view.findViewById(R.id.ib5);
            i6 = view.findViewById(R.id.ib6);
            i3 = view.findViewById(R.id.ib3);
            i4 = view.findViewById(R.id.ib4);
            i1 = view.findViewById(R.id.ib1);
            i2 = view.findViewById(R.id.ib2);

            i5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SearchActivity.categories = "chinese";
                    SearchActivity search= new SearchActivity();
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.flContainer, search)
                            .addToBackStack(null)
                            .commit();
                }
            });

            i6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SearchActivity.categories = "burgers";
                    SearchActivity search= new SearchActivity();
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.flContainer, search)
                            .addToBackStack(null)
                            .commit();
                }
            });
            i3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SearchActivity.categories = "tacos";
                    SearchActivity search= new SearchActivity();
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.flContainer, search)
                            .addToBackStack(null)
                            .commit();
                }
            });
            i4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SearchActivity.categories = "chicken_wings";
                    SearchActivity search= new SearchActivity();
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.flContainer, search)
                            .addToBackStack(null)
                            .commit();
                }
            });
            i1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SearchActivity.categories = "pizza";
                    SearchActivity search= new SearchActivity();
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.flContainer, search)
                            .addToBackStack(null)
                            .commit();
                }
            });
            i2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SearchActivity.categories = "coffee";
                    SearchActivity search= new SearchActivity();
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.flContainer, search)
                            .addToBackStack(null)
                            .commit();
                }
            });

//            ibMenuChat.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(getActivity(), ChatActivity.class);
//                    startActivity(intent);
//                }
//            });

//            ibMenuFeed.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(getActivity(), FeedActivity.class);
//                    startActivity(intent);
//                }
//            });
        }

    }
