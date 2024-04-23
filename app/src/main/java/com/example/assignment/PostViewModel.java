package com.example.assignment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class PostViewModel extends AndroidViewModel {
    private PostRepository repository;
    public PostViewModel(@NonNull Application application) {
        super(application);
        this.repository = PostRepository.getInstance();
    }

    public LiveData<List<Post>> getAllPost(){
        return repository.getAllPosts(getApplication().getApplicationContext());
    }
}
