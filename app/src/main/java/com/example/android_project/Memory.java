package com.example.android_project;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.android_project.databinding.ActivityMemoryBinding;

import java.util.ArrayList;
import java.util.List;

public class Memory extends AppCompatActivity {

    private ActivityMemoryBinding binding;
    private List<Card> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMemoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        fragments = new ArrayList<>();
        fragments.add(Card.newInstance("2+6=", "8"));
        fragments.add(Card.newInstance("6+0=", "6"));
        fragments.add(Card.newInstance("5+6=", "11"));
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        for (Card frag : fragments) {
            ft.add(R.id.fragment_container1, frag);
        }
        fragments = new ArrayList<>();
        fragments.add(Card.newInstance("2+6=", "8"));
        fragments.add(Card.newInstance("6+0=", "6"));
        fragments.add(Card.newInstance("5+6=", "11"));

        for (Card frag : fragments) {
            ft.add(R.id.fragment_container2, frag);
        }
        ft.commit();
    }
}