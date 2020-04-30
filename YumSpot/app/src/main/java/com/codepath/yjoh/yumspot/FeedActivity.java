package com.codepath.yjoh.yumspot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.codepath.yjoh.yumspot.fragments.ComposeFragment;
import com.codepath.yjoh.yumspot.fragments.PostsFragment;
import com.codepath.yjoh.yumspot.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FeedActivity extends AppCompatActivity {

    public static final String TAG = "FeedActivity";


    final FragmentManager fragmentManager = getSupportFragmentManager();
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment;
                switch (menuItem.getItemId()) {
                    case R.id.action_category:
                    case R.id.action_chat:
                        fragment = new ComposeFragment();
                        break;
                    case R.id.action_feed:
                        fragment = new PostsFragment();
                        break;
                    case R.id.action_profile:
                    default:
                        fragment = new ProfileFragment();
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }
        });



//        ImageButton ibPost = (ImageButton) findViewById(R.id.ibPost);
//        ibPost.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                ComposeFragment compose = new ComposeFragment();
//                fragmentTransaction.replace(R.id.flContainer, compose);
//                fragmentTransaction.commit();
//            }
//        });



        // Set default selection
        bottomNavigationView.setSelectedItemId(R.id.action_feed);
    }
}
