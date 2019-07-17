package com.example.databindingdemo;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.example.databindingdemo.databinding.ActivityMainBinding;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;

    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.setLoginVM(loginViewModel);
        binding.setLifecycleOwner(this);


        loginViewModel.getUser().observe(this, new Observer<User>() {
            @SuppressLint("SetTextI18n")
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onChanged(@Nullable User user) {

                if (TextUtils.isEmpty(Objects.requireNonNull(user).getEmail())) {
                    binding.etEmail.setError("Please enter email");
                    binding.etEmail.requestFocus();
                } else if (!user.isEmailValid()) {
                    binding.etEmail.setError("Enter a Valid E-mail Address");
                    binding.etEmail.requestFocus();
                } else if (TextUtils.isEmpty(Objects.requireNonNull(user).getPassword())) {
                    binding.etPassword.setError("Please enter password");
                    binding.etPassword.requestFocus();
                } else if (!user.isPasswordLengthGreaterThan5()) {
                    binding.etPassword.setError("Enter at least 6 Digit password");
                    binding.etPassword.requestFocus();
                } else {

                    binding.tvMessage.setText("You are Logined as" + user.getEmail().split("@")[0]);

                }
            }
        });


    }
}
