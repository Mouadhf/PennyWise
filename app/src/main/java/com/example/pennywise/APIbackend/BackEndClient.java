package com.example.pennywise.APIbackend;

// Remove unused OkHttp imports if any remain after changes
import android.util.Log;
import okhttp3.OkHttpClient; // Keep this one
// import okhttp3.logging.HttpLoggingInterceptor; // Remove this

// Add import for RetrofitClient to get the shared client
import com.pennywise.pw.client.api.RetrofitClient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BackEndClient {
    private static final String TAG = "BackEndClient";
    private static Retrofit retrofit;

    public static Retrofit getClient() {
        if (retrofit == null) {
            // --- Remove local OkHttpClient creation --- 
            /*
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(message -> Log.d(TAG, message));
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    // Add timeouts if desired, like in RetrofitClient
                    // .connectTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
                    // .readTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
                    // .writeTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
                    .build();
            */
            // --- Get the shared OkHttpClient --- 
            OkHttpClient sharedOkHttpClient = RetrofitClient.getSharedOkHttpClient();

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:8080")
                    .client(sharedOkHttpClient) // Use the SHARED client
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            Log.d(TAG, "BackEndClient Retrofit instance created using SHARED OkHttpClient.");
        }
        return retrofit;
    }
}
