package com.example.assignment.screen;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.assignment.adapter.PostListAdapter;
import com.example.assignment.viewmodel.PostViewModel;
import com.example.assignment.R;
import com.example.assignment.adapter.PostListPageAdapter;
import com.example.assignment.databinding.ActivityHomeScreenBinding;

public class HomeScreen extends AppCompatActivity {

    ActivityHomeScreenBinding binding;
    PostListAdapter adapter;
    private PostViewModel postViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home_screen);
        binding.setLifecycleOwner(this);

        binding.postList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PostListAdapter();
        PostListPageAdapter postListPageAdapter = new PostListPageAdapter();
        binding.postList.setAdapter(adapter);
        //binding.postList.setAdapter(postListPageAdapter);
        postViewModel = new ViewModelProvider(this).get(PostViewModel.class);
        postViewModel.getAllPost().observe(this, posts -> {
            if (posts != null && !posts.isEmpty()) {
                adapter.submitList(posts);
                adapter.setOnItemClickListener((view, post, position) -> {
                    Intent detailVewClick = new Intent(this, DetailScreen.class);
                    detailVewClick.putExtra("data",post);
                    startActivity(detailVewClick);
                });
                //adapter.notifyDataSetChanged();
            }
        });




//        postViewModel.getPagedList().observe(this, posts -> {
//            if (posts != null && !posts.isEmpty()) {
//                postListPageAdapter.submitList(posts);
//            }
//        });
//        postViewModel.getLoadState().observe(this, integer -> {
//            postListPageAdapter.setLoadState(integer);
//        });

    }
}