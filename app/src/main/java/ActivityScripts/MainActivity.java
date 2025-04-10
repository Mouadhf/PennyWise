package ActivityScripts;
import com.example.myapplication.R;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pennywise.APIbackend.BackEndClient;
import com.example.pennywise.APIbackend.BackEndClientService;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private BackEndClientService backEndClientService;
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
                backEndClientService = BackEndClient.getClient().create(BackEndClientService.class);

                login(email, password);
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
    private void login(String name, String password) {
        backEndClientService.login(name, password).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful() && response.body() != null) {
                    boolean loginSuccess = response.body();

                    if (loginSuccess) {
                        Toast.makeText(MainActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, PennyWiseActivity.class);
                        intent.putExtra("username", name);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(MainActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
