package com.example.assignment.screen;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.assignment.model.Post;
import com.example.assignment.R;
import com.example.assignment.databinding.ActivityDetailScreenBinding;

public class DetailScreen extends AppCompatActivity {
    private ActivityDetailScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_detail_screen);
        binding.setLifecycleOwner(this);

        if (getIntent().hasExtra("data")){
            Post post = getIntent().getParcelableExtra("data");
            String id,title,desc,userId;
            id = String.valueOf(post.getId());
            title = post.getTitle();
            desc = post.getBody();
            userId = String.valueOf(post.getUserId());
            binding.idTxt.setText(String.valueOf(post.getId()));
            binding.id2InputTxt.setText("Id: " + id);
            binding.titleTxt.setText("Title: "+title);
            binding.descTxt.setText("Description: "+desc);
            binding.userIdTxt.setText("User Id: "+userId);
        }

    }
}