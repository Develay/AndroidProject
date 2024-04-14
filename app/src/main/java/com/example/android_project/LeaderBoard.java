package com.example.android_project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android_project.databinding.ActivityLeaderBoardBinding;

import java.util.ArrayList;
import java.util.List;

public class LeaderBoard extends AppCompatActivity {

    private ActivityLeaderBoardBinding binding;
    private ScoreManager scoreManager;
    private ListView listView;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLeaderBoardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        scoreManager = new ScoreManager(this);
        listView = findViewById(R.id.listView);
        List<Resultat> highScores = scoreManager.getHighScores();

        List<String> displayScores = new ArrayList<>();
        for (Resultat resultat : highScores) {
            displayScores.add(resultat.toString());
        }

        adapter = new ArrayAdapter<>(this, R.layout.list_item, displayScores);
        listView.setAdapter(adapter);

        findViewById(R.id.ReplayButton).setOnClickListener(v -> restartGame());
    }

    private void restartGame() {
        // Redémarrez le jeu en créant une nouvelle instance de l'activité Memory
        // et en démarrant l'activité
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}