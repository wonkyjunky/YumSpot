package com.codepath.yjoh.yumspot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.codepath.yjoh.yumspot.fragments.CategoryActivity;
import com.codepath.yjoh.yumspot.fragments.ChatActivity;
import com.codepath.yjoh.yumspot.fragments.ComposeFragment;
import com.codepath.yjoh.yumspot.fragments.PostsFragment;
import com.codepath.yjoh.yumspot.fragments.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "FeedActivity";


    final FragmentManager fragmentManager = getSupportFragmentManager();
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment;
                switch (menuItem.getItemId()) {
                    case R.id.action_category:
                        fragment = new CategoryActivity();
                        break;
                    case R.id.action_chat:
                        fragment = new ChatActivity();
                        break;
                    case R.id.action_feed:
                        fragment = new PostsFragment();
                        break;
                    case R.id.action_profile:
                    default:
                        fragment = new UserFragment();
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
        bottomNavigationView.setSelectedItemId(R.id.action_category);
    }
}
