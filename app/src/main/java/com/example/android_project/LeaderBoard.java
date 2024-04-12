package com.example.android_project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android_project.MainActivity;
import com.example.android_project.R;
import com.example.android_project.ScoreManager;
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

        List<Integer> highScores = scoreManager.getHighScores();
        List<Long> highScoreTimes = scoreManager.getHighScoreTimes();

        List<String> displayScores = new ArrayList<>();
        for (int i = 0; i < highScores.size(); i++) {
            displayScores.add(i+1 + "   Score: " + highScores.get(i) + ", Time: " + highScoreTimes.get(i));
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, displayScores);
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