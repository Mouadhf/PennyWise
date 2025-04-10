package ActivityScripts;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.Toast;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.Calendar;
import com.pennywise.pw.client.api.ExpenseApiService;
import com.pennywise.pw.client.api.RetrofitClient;
import com.pennywise.pw.client.model.Category;
import com.pennywise.pw.client.model.Expense;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.util.Log;
import androidx.recyclerview.widget.LinearLayoutManager;
import Adapters.ExpenseAdapter;
import java.util.ArrayList;
import java.util.List;

// Implement the listener interface
public class PennyWiseActivity extends AppCompatActivity implements ExpenseAdapter.OnExpenseDeleteListener {

    private static final String TAG = "PennyWiseActivity";

    // Declare the views from XML
    private MaterialToolbar toolbar;
    private CardView accountInfoCard;
    private ImageView profileImage;
    private TextView username, userEmail, currentBalance, monthlyBudget, remainingBudget;
    private ProgressBar budgetProgress;
    private Spinner filterSpinner;
    private RadioGroup filterRadioGroup;
    private RecyclerView overviewList;
    private FloatingActionButton fabAddExpense; // Added FAB

    // Mock Categories (Consider using the enum defined in com.pennywise.pw.client.model.Category)
    // private final String[] categories = {"Food", "Entertainment", "Bills", "Transport", "Shopping", "Other"};
    private final Category[] categories = Category.values(); // Use the Enum

    // Add API service instance variable
    private ExpenseApiService apiService;

    // Add adapter and list variables
    private ExpenseAdapter expenseAdapter;
    private List<Expense> expenses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expense);
        toolbar = findViewById(R.id.toolbar);
        accountInfoCard = findViewById(R.id.account_info_card);
        profileImage = findViewById(R.id.profile_image);
        username = findViewById(R.id.username);
        userEmail = findViewById(R.id.user_email);
        currentBalance = findViewById(R.id.current_balance);
        monthlyBudget = findViewById(R.id.monthly_budget);
        remainingBudget = findViewById(R.id.remaining_budget);
        budgetProgress = findViewById(R.id.budget_progress);
        filterSpinner = findViewById(R.id.filter_spinner);
        filterRadioGroup = findViewById(R.id.filter_radio_group);
        overviewList = findViewById(R.id.overview_list);
        fabAddExpense = findViewById(R.id.fab_add_expense); // Initialize FAB

        // Initialize the API Service
        apiService = RetrofitClient.getInstance().getApiService();

        // Setup RecyclerView (passing the listener)
        setupRecyclerView();

        // Set username (e.g., based on login - simplified here)
        // In a real app, you'd get this from the Intent or shared preferences
        username.setText(getIntent().getStringExtra("username")); // Changed from John Doe
        currentBalance.setText("$2000.00");
        monthlyBudget.setText("$5000.00");
        remainingBudget.setText("65% of budget used");

        // Fetch expenses when activity starts
        fetchExpenses();

        // Set listener for FAB
        fabAddExpense.setOnClickListener(v -> showAddExpenseDialog());

        // Optionally, set other listeners or behaviors if necessary
        filterRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton selectedRadioButton = findViewById(checkedId);
            // Handle the change
        });
    }

    private void setupRecyclerView() {
        // Pass 'this' as the listener to the adapter constructor
        expenseAdapter = new ExpenseAdapter(expenses, this);
        overviewList.setLayoutManager(new LinearLayoutManager(this));
        overviewList.setAdapter(expenseAdapter);
        Log.d(TAG, "RecyclerView setup complete.");
    }

    private void fetchExpenses() {
        Log.d(TAG, "Fetching expenses...");
        Call<List<Expense>> call = apiService.getAllExpenses();
        call.enqueue(new Callback<List<Expense>>() {
            @Override
            public void onResponse(Call<List<Expense>> call, Response<List<Expense>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d(TAG, "Expenses fetched successfully: " + response.body().size() + " items.");
                    expenses = response.body(); 
                    // Update the adapter on the main UI thread by resetting it
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // Create a new adapter instance with the updated list
                            // Pass 'PennyWiseActivity.this' as the listener
                            expenseAdapter = new ExpenseAdapter(expenses, PennyWiseActivity.this);
                            // Set the new adapter on the RecyclerView
                            overviewList.setAdapter(expenseAdapter);
                            Log.d(TAG, "Adapter RESET on UI thread.");
                        }
                    });
                } else {
                    Log.e(TAG, "Failed to fetch expenses. Code: " + response.code());
                    Toast.makeText(PennyWiseActivity.this, "Failed to load expenses", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Expense>> call, Throwable t) {
                Log.e(TAG, "Error fetching expenses", t);
                Toast.makeText(PennyWiseActivity.this, "Network Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // --- Implementation of OnExpenseDeleteListener --- 
    @Override
    public void onExpenseDelete(Expense expense) {
        Log.d(TAG, "Attempting to delete expense ID: " + expense.getId());
        Call<String> call = apiService.deleteExpense(expense.getId());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(PennyWiseActivity.this, "Expense deleted successfully!", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Expense deleted successfully, refreshing list.");
                    fetchExpenses(); // Refresh the list
                } else {
                    String errorMessage = "Failed to delete expense.";
                    try {
                        if (response.errorBody() != null) {
                            errorMessage += " Error: " + response.errorBody().string();
                        }
                    } catch (Exception e) { Log.e(TAG, "Error reading error body", e); }
                    Log.e(TAG, "Failed to delete expense. Code: " + response.code() + " Body: " + errorMessage);
                    Toast.makeText(PennyWiseActivity.this, errorMessage, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e(TAG, "Network error deleting expense", t);
                Toast.makeText(PennyWiseActivity.this, "Network Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    // --- End of OnExpenseDeleteListener implementation ---

    // Method to show the Add Expense Dialog
    private void showAddExpenseDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_expense, null); // We need to create this layout
        builder.setView(dialogView);

        final Spinner categorySpinner = dialogView.findViewById(R.id.spinner_category);
        final EditText titleEditText = dialogView.findViewById(R.id.edittext_title);
        final EditText descriptionEditText = dialogView.findViewById(R.id.edittext_description);
        final EditText amountEditText = dialogView.findViewById(R.id.edittext_amount);
        final EditText limitEditText = dialogView.findViewById(R.id.edittext_limit);

        // Setup Category Spinner using the Enum
        ArrayAdapter<Category> categoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categoryAdapter);

        builder.setTitle("Add New Expense")
               .setPositiveButton("Add", (dialog, id) -> {
                   // Handle adding the expense
                   Category selectedCategory = (Category) categorySpinner.getSelectedItem(); // Get Enum Category
                   String title = titleEditText.getText().toString();
                   String description = descriptionEditText.getText().toString();
                   String amountStr = amountEditText.getText().toString();
                   String limitStr = limitEditText.getText().toString();

                   if (title.isEmpty() || description.isEmpty() || amountStr.isEmpty() || limitStr.isEmpty()) {
                       Toast.makeText(PennyWiseActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                       return;
                   }
                   try {
                       double amount = Double.parseDouble(amountStr);
                       double limit = Double.parseDouble(limitStr);

                       // ---- Make the API Call ----
                       Call<String> call = apiService.addExpense(title, amount, selectedCategory, description, limit);
                       call.enqueue(new Callback<String>() {
                           @Override
                           public void onResponse(Call<String> call, Response<String> response) {
                               if (response.isSuccessful()) {
                                   Toast.makeText(PennyWiseActivity.this, "Expense added successfully!", Toast.LENGTH_LONG).show();
                                   dialog.dismiss();
                                   fetchExpenses(); // Refresh the list after adding
                               } else {
                                   // Handle API errors (e.g., 400 Bad Request)
                                   String errorMessage = "Failed to add expense.";
                                   if (response.errorBody() != null) {
                                       try {
                                           errorMessage += " Error: " + response.errorBody().string();
                                       } catch (Exception e) {
                                           // Log error reading error body
                                       }
                                   }
                                   Toast.makeText(PennyWiseActivity.this, errorMessage, Toast.LENGTH_LONG).show();
                               }
                           }

                           @Override
                           public void onFailure(Call<String> call, Throwable t) {
                               // Handle network errors
                               Toast.makeText(PennyWiseActivity.this, "Network Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
                           }
                       });
                       // ---- End API Call ----

                   } catch (NumberFormatException e) {
                       Toast.makeText(PennyWiseActivity.this, "Invalid amount or limit entered", Toast.LENGTH_SHORT).show();
                   }
               })
               .setNegativeButton("Cancel", (dialog, id) -> dialog.cancel());

        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
