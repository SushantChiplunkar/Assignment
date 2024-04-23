package com.example.assignment;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @GET("/posts")
    Call<List<Post>> getAllPosts();

}
