package com.example.assignment.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.example.assignment.model.Post;
import com.example.assignment.network.ApiService;

public class PostsSourceFactory extends DataSource.Factory<Integer, Post>{

    private ApiService apiService;

    private MutableLiveData<PostSource> postsSourceMutableLiveData;

    public PostsSourceFactory(ApiService apiService) {
        this.apiService = apiService;
        postsSourceMutableLiveData = new MutableLiveData<>();
    }

    @NonNull
    @Override
    public DataSource<Integer, Post> create() {
        PostSource postSource = new PostSource(apiService);
        postsSourceMutableLiveData.postValue(postSource);
        return postSource;
    }

    public MutableLiveData<PostSource> getPostsSourceMutableLiveData() {
        return postsSourceMutableLiveData;
    }
}
