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

public class PennyWiseActivity extends AppCompatActivity {

    // Declare the views from XML
    private MaterialToolbar toolbar;
    private CardView accountInfoCard;
    private ImageView profileImage;
    private TextView username, userEmail, currentBalance, monthlyBudget, remainingBudget, listTitle;
    private ProgressBar budgetProgress;
    private Spinner filterSpinner;
    private RadioGroup filterRadioGroup;
    private RecyclerView overviewList;
    private FloatingActionButton fabAddExpense; // Added FAB

    // Mock Categories
    private final String[] categories = {"Food", "Entertainment", "Bills", "Transport", "Shopping", "Other"};

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
        listTitle = findViewById(R.id.list_title);
        budgetProgress = findViewById(R.id.budget_progress);
        filterSpinner = findViewById(R.id.filter_spinner);
        filterRadioGroup = findViewById(R.id.filter_radio_group);
        overviewList = findViewById(R.id.overview_list);
        fabAddExpense = findViewById(R.id.fab_add_expense); // Initialize FAB

        // Set username (e.g., based on login - simplified here)
        // In a real app, you'd get this from the Intent or shared preferences
        username.setText("Pennywise User"); // Changed from John Doe
        userEmail.setText("email@example.com"); // Keep or fetch actual email
        currentBalance.setText("$2000.00");
        monthlyBudget.setText("$5000.00");
        remainingBudget.setText("65% of budget used");

        // Set listener for FAB
        fabAddExpense.setOnClickListener(v -> showAddExpenseDialog());

        // Optionally, set other listeners or behaviors if necessary
        filterRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton selectedRadioButton = findViewById(checkedId);
            // Handle the change
        });
    }

    // Method to show the Add Expense Dialog
    private void showAddExpenseDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_expense, null); // We need to create this layout
        builder.setView(dialogView);

        final Spinner categorySpinner = dialogView.findViewById(R.id.spinner_category);
        final EditText dateEditText = dialogView.findViewById(R.id.edittext_date);
        final EditText descriptionEditText = dialogView.findViewById(R.id.edittext_description);
        final EditText amountEditText = dialogView.findViewById(R.id.edittext_amount);

        // Setup Category Spinner
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categoryAdapter);

        // Setup Date Picker for Date EditText
        dateEditText.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(PennyWiseActivity.this,
                (view, year1, monthOfYear, dayOfMonth) -> {
                    // Month is 0-based, add 1 for display
                    String selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year1;
                    dateEditText.setText(selectedDate);
                }, year, month, day);
            datePickerDialog.show();
        });

        builder.setTitle("Add New Expense")
               .setPositiveButton("Add", (dialog, id) -> {
                   // Handle adding the expense
                   String selectedCategory = categorySpinner.getSelectedItem().toString();
                   String date = dateEditText.getText().toString();
                   String description = descriptionEditText.getText().toString();
                   String amountStr = amountEditText.getText().toString();

                   if (date.isEmpty() || description.isEmpty() || amountStr.isEmpty()) {
                       Toast.makeText(PennyWiseActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                       return;
                   }
                   try {
                       double amount = Double.parseDouble(amountStr);
                       // TODO: Process the new expense data (e.g., save to database, update UI)
                       Toast.makeText(PennyWiseActivity.this, "Expense Added: " + description + " ($"+amount+")", Toast.LENGTH_LONG).show();
                   } catch (NumberFormatException e) {
                       Toast.makeText(PennyWiseActivity.this, "Invalid amount entered", Toast.LENGTH_SHORT).show();
                   }
               })
               .setNegativeButton("Cancel", (dialog, id) -> dialog.cancel());

        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
