package com.example.android_project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android_project.databinding.ActivityResultsBinding;

public class Results extends AppCompatActivity {

    private ActivityResultsBinding binding;
    private String nbCoups;

    private ScoreManager scoreManager;
    private Resultat resultat;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        scoreManager = new ScoreManager(this);

        // Récupérer les données envoyées via l'intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            // Extraire la valeur de "SCORE" de l'intent
            int nbCoups = extras.getInt("SCORE", 0); // La valeur par défaut est 0 si "SCORE" n'est pas trouvé

            // Extraire la valeur de "TIMER" de l'intent
            long timeElapsed = extras.getLong("TIMER", 0); // La valeur par défaut est 0 si "TIMER" n'est pas trouvé

            // Convertir le temps écoulé en minutes, secondes et millisecondes
            long minutes = (timeElapsed / 1000) / 60;
            long seconds = (timeElapsed / 1000) % 60;
            long milliseconds = timeElapsed % 1000;

            // Afficher les valeurs de nbCoups et timeElapsed dans un TextView ou un autre composant d'interface utilisateur
            TextView scoreTextView = findViewById(R.id.score);
            TextView timeTextView = findViewById(R.id.time);

            if (scoreTextView != null) {
                scoreTextView.setText("Nombre de coups : " + nbCoups);
            }
            if (timeTextView != null) {
                timeTextView.setText("Temps écoulé : " + minutes + ":" + String.format("%02d", seconds) + ":" + milliseconds);

            }

            findViewById(R.id.saveButton).setOnClickListener(v -> {
                // Récupérer le pseudo de l'EditText
                EditText nickname = findViewById(R.id.nickname);
                String pseudo = nickname.getText().toString();

                // Créer une nouvelle instance de Resultat
                resultat = new Resultat(nbCoups, timeElapsed, pseudo);

                // Enregistrer le score
                scoreManager.saveHighScore(resultat);

                // Désactiver le bouton après le premier appui
                findViewById(R.id.saveButton).setEnabled(false);
                findViewById(R.id.saveButton).setAlpha(0.5f);
                nickname.setEnabled(false);
                nickname.setAlpha(0.5f);
            });

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        findViewById(R.id.replayButton).setOnClickListener(v -> restartGame());
        findViewById(R.id.leaderboardButton).setOnClickListener(v -> openLeaderBoard());
    }

    // Méthode pour redémarrer le jeu
    public void restartGame() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    // Méthode pour ouvrir le tableau des scores
    public void openLeaderBoard() {
        Intent intent = new Intent(this, LeaderBoard.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        restartGame();
    }
}