package com.example.assignment.network;

import com.example.assignment.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("/posts")
    Call<List<Post>> getAllPosts();

    @GET("/posts/")
    Call<List<Post>> getPagePosts(@Query("page") int pageNumber);

}
