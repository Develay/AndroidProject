package com.example.android_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.android_project.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.level1.setOnClickListener(v -> {
            Intent intent = new Intent(this, Memory.class);
            intent.putExtra("level", 20);
            startActivity(intent);
        });
        binding.level2.setOnClickListener(v -> {
            Intent intent = new Intent(this, Memory.class);
            intent.putExtra("level", 30);
            startActivity(intent);
        });
        binding.level3.setOnClickListener(v -> {
            Intent intent = new Intent(this, Memory.class);
            intent.putExtra("level", 40);
            startActivity(intent);
        });
    }
}