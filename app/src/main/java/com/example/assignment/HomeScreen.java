package com.example.assignment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

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
        binding.postList.setAdapter(adapter);
        postViewModel = new ViewModelProvider(this).get(PostViewModel.class);
        postViewModel.getAllPost().observe(this, posts -> {
            if (posts != null && !posts.isEmpty()) {
                adapter.submitList(posts);
                //adapter.notifyDataSetChanged();
            }
        });


    }
}