package com.example.assignment;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static String APP_BASE_URL = "https://jsonplaceholder.typicode.com/";
    private static Retrofit retrofit = null;

    public static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(40, TimeUnit.SECONDS)
            .readTimeout(40, TimeUnit.SECONDS)
            .writeTimeout(40, TimeUnit.SECONDS)
            .build();

    public static Retrofit getServices() {

        retrofit = new Retrofit.Builder()
                .baseUrl(APP_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient )
                .build();
        return retrofit;
    }


}
