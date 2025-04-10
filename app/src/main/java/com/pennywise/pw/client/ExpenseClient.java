package com.pennywise.pw.client;

import com.pennywise.pw.client.model.Expense;
import com.pennywise.pw.client.model.Category;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class ExpenseClient {
    private final String baseUrl = "http://localhost:8080/expenses";

    // Add a new expense
    public String addExpense(String title, double amount, String description, double limit, Category category) throws Exception {
        String url = baseUrl + "/add";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        
        // Set request method
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);

        // Create JSON request body
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("title", title);
        jsonBody.put("amount", amount);
        jsonBody.put("description", description);
        jsonBody.put("limit", limit);
        jsonBody.put("category", category.name());

        // Send request
        try (OutputStream os = con.getOutputStream()) {
            byte[] input = jsonBody.toString().getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        // Get response
        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            return response.toString();
        }
    }

    // Get an expense by ID
    public Expense getExpense(int id) throws Exception {
        String url = baseUrl + "/" + id;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        
        // Set request method
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");

        // Get response
        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            
            // Parse JSON response into Expense object
            JSONObject jsonResponse = new JSONObject(response.toString());
            Expense expense = new Expense();
            expense.setId(jsonResponse.getInt("id"));
            expense.setTitle(jsonResponse.getString("title"));
            expense.setAmount(jsonResponse.getDouble("amount"));
            expense.setDescription(jsonResponse.getString("description"));
            expense.setExpenseLimit(jsonResponse.getDouble("limit"));
            expense.setCategory(Category.valueOf(jsonResponse.getString("category")));
            return expense;
        }
    }

    // Update an existing expense
    public String updateExpense(int id, String title, double amount, String description, double limit, Category category) throws Exception {
        String url = baseUrl + "/" + id;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        
        // Set request method
        con.setRequestMethod("PUT");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);

        // Create JSON request body
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("title", title);
        jsonBody.put("amount", amount);
        jsonBody.put("description", description);
        jsonBody.put("limit", limit);
        jsonBody.put("category", category.name());

        // Send request
        try (OutputStream os = con.getOutputStream()) {
            byte[] input = jsonBody.toString().getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        // Get response
        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            return response.toString();
        }
    }

    // Delete an expense
    public String deleteExpense(int id) throws Exception {
        String url = baseUrl + "/" + id;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        
        // Set request method
        con.setRequestMethod("DELETE");
        con.setRequestProperty("Content-Type", "application/json");

        // Get response
        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            return response.toString();
        }
    }
} 