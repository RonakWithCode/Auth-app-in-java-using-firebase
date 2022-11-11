package com.crazyostudio.authappinjavausingfireba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.crazyostudio.authappinjavausingfireba.databinding.ActivityLoginBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    ActivityLoginBinding binding;
    FirebaseAuth auth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        setContentView(binding.getRoot());
        binding.SignUp.setOnClickListener(view -> {
            startActivity(new Intent(Login.this, SignUp.class));
            finish();
        });

        binding.Login.setOnClickListener(view -> {
            if (!binding.Mail.getText().toString().isEmpty() && !binding.Password.getText().toString().isEmpty()) {
                LoginAccount();
            }
        });
    }

    private void LoginAccount() {
        auth.signInWithEmailAndPassword(binding.Mail.getText().toString(),binding.Password.getText().toString()).addOnCompleteListener(task -> {
            if (task.isSuccessful())
            {
                startActivity(new Intent(Login.this,MainActivity.class));
                finish();
            }
            else {
                binding.Mail.setError("Check...");
                binding.Password.setError("Check...");
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (user != null) {
            startActivity(new Intent(Login.this, MainActivity.class));
            finish();
        }
    }
}