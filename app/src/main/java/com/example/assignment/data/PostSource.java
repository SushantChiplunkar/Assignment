package com.example.assignment.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import com.example.assignment.utils.AppConstants;
import com.example.assignment.model.Post;
import com.example.assignment.network.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostSource extends PageKeyedDataSource<Integer, Post> {
    ApiService apiService;
    MutableLiveData<Integer> loadState;

    // private static PostSource instance;

    public PostSource(ApiService apiService) {
        this.apiService = apiService;
        this.loadState = new MutableLiveData<>();
    }

//    public static PostSource getInstance() {
//        if(instance ==null)
//            instance = new PostSource();
//        return instance;
//    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Post> callback) {
        final int currentPage = 1;
        loadState.postValue(AppConstants.ONGOING);
        apiService.getPagePosts(currentPage).enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    callback.onResult(response.body(), null, currentPage + 1);
                    loadState.postValue(AppConstants.SUCCESS);
                } else {
                    callback.onResult(new ArrayList<>(), null, currentPage);
                    loadState.postValue(AppConstants.FAILED);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                callback.onResult(new ArrayList<>(), null, currentPage);
                loadState.postValue(AppConstants.FAILED);
            }
        });

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Post> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Post> callback) {
        final int currentPage = params.key;

        loadState.postValue(AppConstants.ONGOING);

        apiService.getPagePosts(currentPage).enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful()) {
                    loadState.postValue(AppConstants.SUCCESS);
                    callback.onResult(response.body(), currentPage + 1);
                } else {
                    callback.onResult(new ArrayList<>(), currentPage);
                    loadState.postValue(AppConstants.FAILED);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                callback.onResult(new ArrayList<>(), currentPage);
                loadState.postValue(AppConstants.FAILED);
            }
        });
    }

    public MutableLiveData<Integer> getLoadState() {
        return loadState;
    }
}
