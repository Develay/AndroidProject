package com.example.android_project;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android_project.databinding.ActivityMemoryBinding;

import java.util.ArrayList;
import java.util.Collections;



public class Memory extends AppCompatActivity {

    private ActivityMemoryBinding binding;
    private GridView gridView;
    private MemoryAdapter adapter;
    private final ArrayList<Integer> cards;
    private int firstCard, secondCard;
    private boolean isFirst = true;

    public Memory() {
        cards = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            cards.add(i);
        }
        adapter = new MemoryAdapter(this, cards);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);

        gridView = findViewById(R.id.gridView);

        if (gridView != null) {
            gridView.setAdapter(adapter);
        } else {
            Log.e("Memory", "GridView is null");
        }

        //Click listener for grid view
        gridView.setOnItemClickListener((parent, view, position, id) -> {
            if (isFirst) {
                firstCard = position;
                adapter.showCard(firstCard);
                isFirst = false;
            } else {
                secondCard = position;
                adapter.showCard(secondCard);
                checkMatch();
                isFirst = true;
            }
        });

        //Click listener for reset button
        findViewById(R.id.resetButton).setOnClickListener(v -> resetGame());
    }

    private void checkMatch() {
        if ((cards.get(firstCard).equals(cards.get(secondCard)))) {
            // Matched

        } else {
            // Not matched, hide the cards
            gridView.postDelayed(() -> {
                adapter.hideCard(firstCard);
                adapter.hideCard(secondCard);
            }, 1000); // Delay to allow the user to see the cards
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void resetGame() {
        Collections.shuffle(cards);
        adapter = new MemoryAdapter(this, cards);
        gridView.setAdapter(adapter);
    }
}






