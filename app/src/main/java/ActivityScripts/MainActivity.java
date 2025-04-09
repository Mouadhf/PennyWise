package ActivityScripts;
import com.example.myapplication.R;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText emailInput;
    private TextInputEditText passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        emailInput = findViewById(R.id.email_input);
        passwordInput = findViewById(R.id.password_input);
        MaterialButton loginButton = findViewById(R.id.btn_login);
        MaterialButton signupButton = findViewById(R.id.btn_signup);
        TextView forgotPasswordText = findViewById(R.id.forgot_password);

        // Login button listener
        loginButton.setOnClickListener(view -> {
            String email = emailInput.getText() != null ? emailInput.getText().toString().trim() : "";
            String password = passwordInput.getText() != null ? passwordInput.getText().toString() : "";

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(MainActivity.this, "Please enter email and password", Toast.LENGTH_SHORT).show();
            } else {
                // Dummy login logic - allow original user OR the mock signed-up user
                boolean originalUser = email.equals("user@example.com") && password.equals("123456");
                boolean signedUpUser = email.equals("signup@example.com") && password.equals("signup");

                if (originalUser || signedUpUser) {
                    Toast.makeText(MainActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
                    // Redirect to the main app activity
                    Intent intent = new Intent(MainActivity.this, PennyWiseActivity.class);
                    startActivity(intent);
                    finish(); // Close MainActivity after successful login
                } else {
                    Toast.makeText(MainActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Sign-up button listener
        signupButton.setOnClickListener(view -> {
            // Navigate to SignUpActivity
            Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
            startActivity(intent);
        });

        // Forgot password listener
        forgotPasswordText.setOnClickListener(view -> {
            Toast.makeText(MainActivity.this, "Forgot password clicked", Toast.LENGTH_SHORT).show();
            // You can navigate to a ForgotPasswordActivity here
        });
    }
}
