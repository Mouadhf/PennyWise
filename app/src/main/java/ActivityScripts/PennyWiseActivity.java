package ActivityScripts;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;
import com.google.android.material.appbar.MaterialToolbar;

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
        // Set some initial values for demonstration (you can remove or modify this based on your needs)
        username.setText("John Doe");
        userEmail.setText("john.doe@example.com");
        currentBalance.setText("$2000.00");
        monthlyBudget.setText("$5000.00");
        remainingBudget.setText("65% of budget used");

        // Optionally, set other listeners or behaviors if necessary (for example, for the radio buttons)
        filterRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton selectedRadioButton = findViewById(checkedId);
            // Handle the change (for example, to filter content based on the selected radio button)
        });
    }
}
