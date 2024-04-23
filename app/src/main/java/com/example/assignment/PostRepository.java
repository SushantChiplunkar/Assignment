package com.example.assignment;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonObject;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostRepository {
    private static PostRepository instance;

    private ApiService apiService;

    public static PostRepository getInstance() {
        if(instance == null)
            instance = new PostRepository();

        return instance;
    }

    public PostRepository(){
        apiService = RetrofitClient.getServices().create(ApiService.class);
    }

    public LiveData<List<Post>> getAllPosts(Context context){
        final MutableLiveData<List<Post>> responseJson = new MutableLiveData<List<Post>>();
//        apiService.getAllPosts().enqueue(new Callback<JsonObject>() {
//            @Override
//            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
//                if (response.isSuccessful()){
//                    Log.d("","");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<JsonObject> call, Throwable t) {
//                Log.d("","");
//            }
//        });
//        apiService.getAllPosts().enqueue(new Callback<Post>() {
//            @Override
//            public void onResponse(Call<Post> call, Response<Post> response) {
//                if(response.isSuccessful()){
//                    Log.d("","");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Post> call, Throwable t) {
//                Log.d("","");
//            }
//        });
        apiService.getAllPosts().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(@NonNull Call<List<Post>> call, @NonNull Response<List<Post>> response) {
                if (response.isSuccessful()){
                    responseJson.postValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Post>> call, @NonNull Throwable t) {
                Log.d("Error", Objects.requireNonNull(t.getMessage()));
            }
        });
        return responseJson;
    }
}
