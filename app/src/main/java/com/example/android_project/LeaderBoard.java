package com.example.android_project;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android_project.databinding.ActivityLeaderBoardBinding;

import java.util.ArrayList;
import java.util.List;

public class LeaderBoard extends AppCompatActivity {

    private ActivityLeaderBoardBinding binding;
    private ScoreManager scoreManager;
    private ListView listView;
    private ListView positionsListView;
    private ArrayAdapter<String> adapter;

    private int currentLevel = 0;

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLeaderBoardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mediaPlayer = MediaPlayer.create(this, R.raw.restart);

        // Récupérer les données envoyées via l'intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            // Extraire la valeur de "level" de l'intent
            int level = extras.getInt("level", 20); // La valeur par défaut est 20 si "level" n'est pas trouvé
            currentLevel = level;
        }

        scoreManager = new ScoreManager(this);
        listView = findViewById(R.id.listView);
        positionsListView = findViewById(R.id.positionsListView);
        List<Resultat> highScores = scoreManager.getHighScores(currentLevel);

        List<String> displayScores = new ArrayList<>();
        List<String> displayPositions = new ArrayList<>();
        for (int i = 0; i < highScores.size(); i++) {
            displayScores.add(highScores.get(i).toString());
            displayPositions.add(String.valueOf(i + 1 + " :")); // Les positions commencent à 1
        }

        adapter = new ArrayAdapter<>(this, R.layout.list_item, displayScores);
        listView.setAdapter(adapter);

        ArrayAdapter<String> positionsAdapter = new ArrayAdapter<>(this, R.layout.position_list_item, displayPositions);
        positionsListView.setAdapter(positionsAdapter);


    }

    @Override
    protected void onResume() {
        super.onResume();

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // Pas besoin de faire quoi que ce soit ici
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                // Synchroniser le défilement de positionsListView avec listView
                positionsListView.setSelection(firstVisibleItem);
            }
        });

        positionsListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // Pas besoin de faire quoi que ce soit ici
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                // Synchroniser le défilement de listView avec positionsListView
                listView.setSelection(firstVisibleItem);
            }
        });

        findViewById(R.id.ReplayButton).setOnClickListener(v -> restartGame());
    }

    private void restartGame() {
        // Redémarrez le jeu en créant une nouvelle instance de l'activité Memory
        // et en démarrant l'activité
        mediaPlayer.start();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void updateLeaderboard() {
        List<Resultat> highScores = scoreManager.getHighScores(currentLevel);
        List<String> displayScores = new ArrayList<>();
        List<String> displayPositions = new ArrayList<>();
        for (int i = 0; i < highScores.size(); i++) {
            displayScores.add(highScores.get(i).toString());
            displayPositions.add(String.valueOf(i + 1 + " :")); // Les positions commencent à 1
        }

        adapter.clear();
        adapter.addAll(displayScores);
        adapter.notifyDataSetChanged();

        ArrayAdapter<String> positionsAdapter = new ArrayAdapter<>(this, R.layout.position_list_item, displayPositions);
        positionsListView.setAdapter(positionsAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}