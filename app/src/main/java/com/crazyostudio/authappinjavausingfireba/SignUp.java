package com.crazyostudio.authappinjavausingfireba;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.crazyostudio.authappinjavausingfireba.databinding.ActivitySignUpBinding;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class SignUp extends AppCompatActivity {
    private FirebaseAuth Auth;
    private ActivitySignUpBinding binding;
    ProgressDialog bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        Auth = FirebaseAuth.getInstance();
        bar = new ProgressDialog(this);
        binding.signup.setOnClickListener(view -> {
            bar.setMessage("Wait Account is Creating");
            bar.show();
            CreateAccount();
        });

    }
    void CreateAccount() {
        Auth.createUserWithEmailAndPassword(binding.Mail.getText().toString(), binding.Password.getText().toString())
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        bar.dismiss();
                        startActivity(new Intent(this, SignUp.class));
                        finish();
                    }
                    else {
                        Toast.makeText(SignUp.this, Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        if (bar.isShowing()) {
                            bar.dismiss();
                        }
                    }
                });
    }

}