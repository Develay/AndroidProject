package com.example.android_project;

import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android_project.databinding.ActivityMemoryBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class Memory extends AppCompatActivity {

    private GridView gridView;
    private MemoryAdapter adapter;
    private final ArrayList<Integer> cards;
    private int firstCard, secondCard;
    private boolean isFirst = true;
    private  int nbCards = 30;

    public Memory() {
        cards = new ArrayList<>(); // List of cards with the pair values
        for (int i = 0; i < nbCards/2; i++) {
            cards.add(i);
            cards.add(i);
        }

        Collections.shuffle(cards);

        adapter = new MemoryAdapter(this, cards); // Adapter for the grid view
    }

    @Override 
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);

        gridView = findViewById(R.id.gridView);

        if (gridView != null) { // Set the adapter for the grid view
            gridView.setAdapter(adapter);
        } else {
            Log.e("Memory", "GridView is null");
        }
    }

    private void checkMatch() {
        int v1 = cards.get(firstCard);
        int v2 = cards.get(secondCard);
        // Matched
        if ((cards.get(firstCard).equals(cards.get(secondCard)))) { // Check if the cards match
            Log.e("Memory", "matched");
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
        //Click listener for grid view
        gridView.setOnItemClickListener((parent, view, position, id) -> {
            if (isFirst) {
                firstCard = position;
                adapter.showCard(firstCard);
                isFirst = false;
                position = -1;
            } else if (firstCard != position){ // Check if the same card is clicked

                secondCard = position;
                adapter.showCard(secondCard);
                checkMatch();
                isFirst = true;
                position = -1;
            }
        });

        //Click listener for reset button
        findViewById(R.id.resetButton).setOnClickListener(v -> resetGame());
    }

    private void resetGame() {
        Collections.shuffle(cards);
        adapter = new MemoryAdapter(this, cards);
        gridView.setAdapter(adapter);
    }
}






