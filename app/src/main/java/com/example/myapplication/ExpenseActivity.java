package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.pennywise.pw.client.api.RetrofitClient;
import com.pennywise.pw.client.model.Category;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.io.IOException;

public class ExpenseActivity extends AppCompatActivity {
    private static final String TAG = "ExpenseActivity";
    private EditText titleInput, amountInput, descriptionInput, limitInput;
    private Spinner categorySpinner;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);

        // Initialize views
        titleInput = findViewById(R.id.titleInput);
        amountInput = findViewById(R.id.amountInput);
        descriptionInput = findViewById(R.id.descriptionInput);
        limitInput = findViewById(R.id.limitInput);
        categorySpinner = findViewById(R.id.categorySpinner);
        addButton = findViewById(R.id.addButton);

        // Setup category spinner
        ArrayAdapter<Category> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Category.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addExpense();
            }
        });
    }

    private void addExpense() {
        try {
            // Validate inputs
            if (titleInput.getText().toString().isEmpty()) {
                Toast.makeText(this, "Please enter a title", Toast.LENGTH_SHORT).show();
                return;
            }
            if (amountInput.getText().toString().isEmpty()) {
                Toast.makeText(this, "Please enter an amount", Toast.LENGTH_SHORT).show();
                return;
            }
            if (limitInput.getText().toString().isEmpty()) {
                Toast.makeText(this, "Please enter a limit", Toast.LENGTH_SHORT).show();
                return;
            }

            String title = titleInput.getText().toString();
            double amount = Double.parseDouble(amountInput.getText().toString());
            String description = descriptionInput.getText().toString();
            double limit = Double.parseDouble(limitInput.getText().toString());
            Category category = (Category) categorySpinner.getSelectedItem();

            Log.d(TAG, "Attempting to add expense with parameters: " +
                    "TITLE=" + title + ", " +
                    "AMOUNT=" + amount + ", " +
                    "CATEGORY=" + category + ", " +
                    "DESCRIPTION=" + description + ", " +
                    "EXPENSE_LIMIT=" + limit);

            RetrofitClient.getInstance()
                    .getApiService()
                    .addExpense(title, amount, category, description, limit)
                    .enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            if (response.isSuccessful()) {
                                Log.d(TAG, "Expense added successfully");
                                Toast.makeText(ExpenseActivity.this, "Expense added successfully", Toast.LENGTH_SHORT).show();
                                clearInputs();
                            } else {
                                String errorBody = "";
                                try {
                                    errorBody = response.errorBody() != null ? response.errorBody().string() : "No error body";
                                } catch (IOException e) {
                                    errorBody = "Error reading error body";
                                }
                                Log.e(TAG, "Failed to add expense. Code: " + response.code() + ", Error: " + errorBody);
                                Toast.makeText(ExpenseActivity.this, "Failed to add expense: " + response.code() + " - " + errorBody, Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Log.e(TAG, "Error adding expense", t);
                            Toast.makeText(ExpenseActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
        } catch (NumberFormatException e) {
            Log.e(TAG, "Invalid number format", e);
            Toast.makeText(this, "Please enter valid numbers for amount and limit", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(TAG, "Unexpected error", e);
            Toast.makeText(this, "An unexpected error occurred", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearInputs() {
        titleInput.setText("");
        amountInput.setText("");
        descriptionInput.setText("");
        limitInput.setText("");
        categorySpinner.setSelection(0);
    }
} 