package com.example.assignment.data;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.assignment.model.Post;
import com.example.assignment.network.ApiService;
import com.example.assignment.network.RetrofitClient;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostRepository {
    private static PostRepository instance;

    private ApiService apiService;
    private final PostsSourceFactory postsSourceFactory;

    private LiveData<Integer> loadState;

    public static PostRepository getInstance() {
        if (instance == null)
            instance = new PostRepository();

        return instance;
    }

    public PostRepository() {
        apiService = RetrofitClient.getServices().create(ApiService.class);
        postsSourceFactory = new PostsSourceFactory(apiService);
        loadState = Transformations.switchMap(postsSourceFactory.getPostsSourceMutableLiveData()
                , PostSource::getLoadState);
    }

    public LiveData<List<Post>> getAllPosts(Context context) {
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
                if (response.isSuccessful()) {
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

    public LiveData<PagedList<Post>> getPagePostList(){
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(5) //Configures how many data items in one page to be supplied to recycler view
                .setPrefetchDistance(1) // in first call how many pages to load
                .setMaxSize(10) //Maximum PagedList size must be at least pageSize + 2*prefetchDist
                .build();

        return new LivePagedListBuilder<>(postsSourceFactory, config)
                .setFetchExecutor(Executors.newFixedThreadPool(5)) // Use five threads to do the fetching operations
                .build();
    }

    public LiveData<Integer> getLoadState() {
        return loadState;
    }
}
