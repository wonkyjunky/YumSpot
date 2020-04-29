package com.codepath.yjoh.yumspot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class CategoryActivity extends AppCompatActivity {
    ImageView i1, i2, i3, i4, i5, i6;
    ImageView ibMenuChat;
    ImageView ibMenuFeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        i5 = findViewById(R.id.ib5);
        i6 = findViewById(R.id.ib6);
        i3 = findViewById(R.id.ib3);
        i4 = findViewById(R.id.ib4);
        i1 = findViewById(R.id.ib1);
        i2 = findViewById(R.id.ib2);
        ibMenuChat = findViewById(R.id.ibMenuChat);
        ibMenuFeed = findViewById(R.id.ibMenuFeed);

        i5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this, SearchActivity.class);
                SearchActivity.categories = "chinese";
                startActivity(intent);
            }
        });

        i6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this, SearchActivity.class);
                SearchActivity.categories = "burgers";
                startActivity(intent);
            }
        });
        i3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this, SearchActivity.class);
                SearchActivity.categories = "tacos";
                startActivity(intent);
            }
        });
        i4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this, SearchActivity.class);
                SearchActivity.categories = "chicken_wings";
                startActivity(intent);
            }
        });
        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this, SearchActivity.class);
                SearchActivity.categories = "pizza";
                startActivity(intent);
            }
        });
        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this, SearchActivity.class);
                SearchActivity.categories = "coffee";
                startActivity(intent);
            }
        });

        ibMenuChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this, ChatActivity.class);
                startActivity(intent);
            }
        });

        ibMenuFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryActivity.this, FeedActivity.class);
                startActivity(intent);
            }
        });
    }
}