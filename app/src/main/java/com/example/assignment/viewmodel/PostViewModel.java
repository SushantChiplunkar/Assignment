package com.example.assignment.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.example.assignment.data.PostRepository;
import com.example.assignment.model.Post;

import java.util.List;

public class PostViewModel extends AndroidViewModel {
    private PostRepository repository;
    private final LiveData<PagedList<Post>> pagedList;
    public PostViewModel(@NonNull Application application) {
        super(application);
        this.repository = PostRepository.getInstance();
        pagedList = repository.getPagePostList();
    }

    public LiveData<List<Post>> getAllPost(){
        return repository.getAllPosts(getApplication().getApplicationContext());
    }

    public LiveData<PagedList<Post>> getPagedList() {
        return pagedList;
    }

    public LiveData<Integer> getLoadState(){
        return repository.getLoadState();
    }


}
