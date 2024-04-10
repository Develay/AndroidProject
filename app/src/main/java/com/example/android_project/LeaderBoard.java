package com.example.android_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.android_project.databinding.ActivityLeaderBoardBinding;

import java.util.List;

public class LeaderBoard extends AppCompatActivity {

    private ActivityLeaderBoardBinding binding;
    private ScoreManager scoreManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLeaderBoardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        scoreManager = new ScoreManager(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Integer> highScores = scoreManager.getHighScores();
        List<Long> highScoreTimes = scoreManager.getHighScoreTimes();
        for (int i = 0; i < highScores.size(); i++) {
            // Get the TextView for the score
            TextView scoreView = findViewById(getResources().getIdentifier("score" + i, "id", getPackageName()));
            // Get the TextView for the time
            TextView timeView = findViewById(getResources().getIdentifier("time" + i, "id", getPackageName()));
            // Check if the TextViews are not null before setting the text
            if (scoreView != null && timeView != null) {
                scoreView.setText(String.valueOf(highScores.get(i)));
                timeView.setText(String.valueOf(highScoreTimes.get(i)));
            }
        }
    }

    private void restartGame() {
        // Redémarrez le jeu en créant une nouvelle instance de l'activité Memory
        // et en démarrant l'activité
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}