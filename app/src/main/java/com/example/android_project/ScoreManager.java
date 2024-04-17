package com.example.android_project;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ScoreManager {
    private static final String PREFS_NAME = "com.example.android_project";
    private static final String KEY_HIGH_SCORES = "high_scores";
    private static final String KEY_HIGH_SCORE_TIMES = "high_score_times";
    private static final String KEY_HIGH_SCORE_PSEUDOS = "high_score_pseudos";

    private static final int MAX_HIGH_SCORES = 10;

    private SharedPreferences preferences;

    public ScoreManager(Context context) {
        preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    //Méthode de sauvegarde du score
    public void saveHighScore(Resultat resultat, int level) {
        List<Resultat> highScores = getHighScores(level);

        if (highScores.size() < MAX_HIGH_SCORES) {
            highScores.add(resultat);
        }
        else if (resultat.getScore() < highScores.get(highScores.size() - 1).getScore()) {
            highScores.remove(highScores.size() - 1);
            highScores.add(resultat);
        }

        //Tri des résultats
        Collections.sort(highScores, Comparator.comparing(Resultat::getScore));
        //Enregistrement des résultats
        SharedPreferences.Editor editor = preferences.edit();
        for (int i = 0; i < highScores.size(); i++) {
            editor.putInt(KEY_HIGH_SCORES + i, highScores.get(i).getScore());
            editor.putLong(KEY_HIGH_SCORE_TIMES + i, highScores.get(i).getTime());
            editor.putString(KEY_HIGH_SCORE_PSEUDOS + i, highScores.get(i).getPseudo());
        }
        editor.apply();
    }

    //Méthode de récupération des résultats
    public List<Resultat> getHighScores(int level) {
        List<Resultat> highScores = new ArrayList<>();
        for (int i = 0; i < MAX_HIGH_SCORES; i++) {
            int score = preferences.getInt(KEY_HIGH_SCORES + i, 200);
            long temps = preferences.getLong(KEY_HIGH_SCORE_TIMES + i, 3600000);
            String pseudo = preferences.getString(KEY_HIGH_SCORE_PSEUDOS + i, "");
            highScores.add(new Resultat(score, temps, pseudo));
        }
        return highScores;
    }
}
