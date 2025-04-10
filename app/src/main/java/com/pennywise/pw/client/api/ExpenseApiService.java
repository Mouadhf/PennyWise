package com.pennywise.pw.client.api;

import com.pennywise.pw.client.model.Expense;
import com.pennywise.pw.client.model.Category;

import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface ExpenseApiService {
    @POST("expenses/add")
    Call<String> addExpense(
            @Query("title") String title,
            @Query("amount") double amount,
            @Query("category") Category category,
            @Query("description") String description,
            @Query("limit") double limit
    );

    @GET("expenses/{id}")
    Call<Expense> getExpense(@Path("id") int id);

    @GET("expenses/all")
    Call<List<Expense>> getAllExpenses();

    @PUT("expenses/{id}")
    Call<String> updateExpense(
            @Path("id") int id,
            @Query("title") String title,
            @Query("amount") double amount,
            @Query("category") Category category,
            @Query("description") String description,
            @Query("limit") double limit
    );

    @DELETE("expenses/{id}")
    Call<String> deleteExpense(@Path("id") int id);
} 