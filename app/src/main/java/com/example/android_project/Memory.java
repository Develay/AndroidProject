package com.example.android_project;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
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
    private ArrayList<Integer> cards;

    private int firstCard, secondCard;
    private boolean isFirst = true;
    private  float nbCards = 0;

    private int nbCoups = 0;

    private CountDownTimer timer;
    private long timeElapsed;
    private boolean isFirstCardFlipped = false;

    public Memory() {
    }

    @Override 
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);

        gridView = findViewById(R.id.gridView);

        // Récupérer les données envoyées via l'intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            // Extraire la valeur de "level" de l'intent
            int level = extras.getInt("level", 20); // La valeur par défaut est 20 si "level" n'est pas trouvé
            nbCards = level;
        }

        cards = new ArrayList<>(); // List of cards with the pair values
        for (int i = 0; i < nbCards/2; i++) {
            cards.add(i);
            cards.add(i);
        }

        Collections.shuffle(cards);

        adapter = new MemoryAdapter(this, cards); // Adapter for the grid view

        if (gridView != null) { // Set the adapter for the grid view
            gridView.setAdapter(adapter);
        } else {
            Log.e("Memory", "GridView is null");
        }
    }

    private void checkMatch() {
        nbCoups++;
        // Matched
        if ((cards.get(firstCard).equals(cards.get(secondCard)))) { // Check if the cards match
            if (allCardsVisible()) {
                endOfGame();
            }
        } else {
            // Not matched, hide the cards
            gridView.postDelayed(() -> {
                adapter.hideCard(firstCard);
                adapter.hideCard(secondCard);
            }, 500); // Delay to allow the user to see the cards
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Click listener for grid view
        gridView.setOnItemClickListener((parent, view, position, id) -> {
            if (!isFirstCardFlipped) {
                startTimer();
                isFirstCardFlipped = true;
            }
            if (isFirst) {
                firstCard = position;
                adapter.showCard(firstCard);
                isFirst = false;
            } else if (firstCard != position){ // Check if the same card is clicked
                secondCard = position;
                adapter.showCard(secondCard);
                checkMatch();
                isFirst = true;
            }
        });

        //Click listener for reset button
        findViewById(R.id.resetButton).setOnClickListener(v -> resetGame());
    }

    private void resetGame() {
        nbCoups = 0;
        Collections.shuffle(cards);
        adapter = new MemoryAdapter(this, cards);
        gridView.setAdapter(adapter);
        finish();
    }

    public void endOfGame() {
        stopTimer();
        Intent intent = new Intent(this, Results.class);
        intent.putExtra("SCORE", nbCoups);
        intent.putExtra("TIMER", timeElapsed);
        new ScoreManager(this).saveHighScore(nbCoups, timeElapsed);
        startActivity(intent);
        finish();
    }

    private void startTimer() {
        timer = new CountDownTimer(3600000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeElapsed = 3600000 - millisUntilFinished;
            }

            @Override
            public void onFinish() {
                finish();
            }
        }.start();
    }

    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
        }
    }

    private boolean allCardsVisible() {
        for (Boolean isVisible : adapter.getCardVisibility()) {
            if (!isVisible) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}






