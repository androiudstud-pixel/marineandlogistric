package com.example.marineandlogistric;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> {

            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (username.isEmpty()) {
                etUsername.setError("Enter Username");
                return;
            }

            if (password.isEmpty()) {
                etPassword.setError("Enter Password");
                return;
            }

            if (username.equals("admin") && password.equals("1234")) {

                Toast.makeText(MainActivity.this,
                        "Login Successful",
                        Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                startActivity(intent);
                finish();

            } else {

                Toast.makeText(MainActivity.this,
                        "Invalid Username or Password",
                        Toast.LENGTH_SHORT).show();

            }

        });

    }
}