package com.example.android_project;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.android_project.databinding.ActivityMainBinding;
import com.example.android_project.databinding.ActivityMemoryBinding;

public class Memory extends AppCompatActivity {

    private ActivityMemoryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMemoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}