package com.example.android_project;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Chronometer;
import android.widget.GridView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;

// Activité qui gère le jeu
public class Memory extends AppCompatActivity {

    private GridView gridView;
    private MemoryAdapter adapter;
    private ArrayList<Integer> cards;

    private int firstCard, secondCard;
    private boolean isFirst = true;
    private  int nbCards = 0;

    private int nbCoups = 0;

    private CountDownTimer timer;
    private long timeElapsed;
    private boolean isFirstCardFlipped = false;

    private boolean isBusy = false;

    private Chronometer chronometer;

    private MediaPlayer mediaPlayer;

    public Memory() {
    }

    // Méthode qui gère la création de l'activité
    @Override 
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);
        gridView = findViewById(R.id.gridView);
        chronometer = findViewById(R.id.chronometer);

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

    // Méthode qui gère la reprise de l'activité
    @Override
    protected void onResume() {
        super.onResume();

        //Click listener pour le gridView
        gridView.setOnItemClickListener((parent, view, position, id) -> {
            if (isBusy) {
                // Ne faites rien si le jeu est occupé à traiter une paire de cartes
                return;
            }
            if (!isFirstCardFlipped) {
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
                isFirstCardFlipped = true;
            }
            if (isFirst && adapter.getCardVisibility().get(position) == 0){
                firstCard = position;
                adapter.showCard(firstCard,1);
                isFirst = false;
            } else if (firstCard != position && adapter.getCardVisibility().get(position) == 0){ // Check if the same card is clicked
                secondCard = position;
                adapter.showCard(secondCard,1);
                checkMatch();
                isFirst = true;
            }
        });

        //Click listener pour le bouton de réinitialisation
        findViewById(R.id.resetButton).setOnClickListener(v -> resetGame());
    }

    // Méthode qui gère le Match des cartes
    private void checkMatch() {
        nbCoups++;
        // Matched
        if ((cards.get(firstCard).equals(cards.get(secondCard)))) { // Check if the cards match
            adapter.showCard(firstCard, 2);
            adapter.showCard(secondCard, 2);
            mediaPlayer = MediaPlayer.create(this, R.raw.pair_found);
            mediaPlayer.start();
            if (allCardsVisible()) {
                endOfGame();
            }
        } else {
            // Pas une paire, retournez les cartes
            isBusy = true;
            mediaPlayer = MediaPlayer.create(this, R.raw.not_matched);
            mediaPlayer.start();
            gridView.postDelayed(() -> {
                adapter.hideCard(firstCard);
                adapter.hideCard(secondCard);
                isBusy = false;
            }, 500); // Delay to allow the user to see the cards
        }
    }


    // Méthode qui réinitialise le jeu
    private void resetGame() {
        nbCoups = 0;
        chronometer.stop(); // Arrête le chronomètre
        chronometer.setBase(SystemClock.elapsedRealtime());
        isFirstCardFlipped = false; // Réinitialise l'état du premier flip de carte
        timeElapsed = 0; // Réinitialise le temps écoulé
        firstCard = -1;
        secondCard = -1;
        isFirst = true;
        Collections.shuffle(cards);
        adapter = new MemoryAdapter(this, cards);
        gridView.setAdapter(adapter);
    }

    // Méthode qui gère la fin du jeu
    public void endOfGame() {
        chronometer.stop();
        timeElapsed = SystemClock.elapsedRealtime() - chronometer.getBase();
        Intent intent = new Intent(this, Results.class);
        intent.putExtra("SCORE", nbCoups);
        intent.putExtra("TIMER", timeElapsed);
        intent.putExtra("level", nbCards);
        startActivity(intent);
        finish();
    }

    // Méthode qui vérifie si toutes les cartes sont visibles
    private boolean allCardsVisible() {
        for (Integer isVisible : adapter.getCardVisibility()) {
            if (isVisible==0) {
                return false;
            }
        }
        return true;
    }

    // Méthode qui gère le bouton retour
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.EXIT))
                .setMessage(getString(R.string.really_quit))
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        Memory.super.onBackPressed();
                    }
                }).create().show();
    }

    // Méthode qui libère les ressources MediaPlayer
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}






