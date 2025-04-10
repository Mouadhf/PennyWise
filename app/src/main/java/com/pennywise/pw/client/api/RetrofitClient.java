package com.pennywise.pw.client.api;

import android.util.Log;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.concurrent.TimeUnit;

public class RetrofitClient {
    private static final String TAG = "RetrofitClient";
    private static final String BASE_URL = "http://10.0.2.2:8080/"; // Use 10.0.2.2 for Android emulator to access localhost
    private static RetrofitClient instance;
    private final ExpenseApiService apiService;

    // Make OkHttpClient static and initialize it here or in a static block
    private static final OkHttpClient okHttpClient;

    // Static initializer block for OkHttpClient
    static {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(message -> Log.d(TAG, message));
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
        Log.d(TAG, "Shared OkHttpClient created.");
    }

    private RetrofitClient() {
        // Create Retrofit instance using the shared OkHttpClient
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient) // Use shared client
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ExpenseApiService.class);
        Log.d(TAG, "RetrofitClient initialized with base URL: " + BASE_URL);
    }

    // Public static method to get the shared OkHttpClient
    public static OkHttpClient getSharedOkHttpClient() {
        return okHttpClient;
    }

    public static synchronized RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    public ExpenseApiService getApiService() {
        return apiService;
    }
} 