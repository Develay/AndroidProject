package com.example.android_project;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScoreManager {
    private static final String PREFS_NAME = "com.example.android_project";
    private static final String KEY_HIGH_SCORES = "high_scores";
    private static final String KEY_HIGH_SCORE_TIMES = "high_score_times";
    private static final int MAX_HIGH_SCORES = 5;

    private SharedPreferences preferences;

    public ScoreManager(Context context) {
        preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void saveHighScore(int score, long time) {
        List<Integer> highScores = getHighScores();
        List<Long> highScoreTimes = getHighScoreTimes();
        if (highScores.size() < MAX_HIGH_SCORES) {
            highScores.add(score);
            highScoreTimes.add(time);
        } else {
            // Replace the highest score (which is the last one after sorting) if the new score is lower
            if (score > highScores.get(highScores.size() - 1)) {
                highScores.remove(highScores.size() - 1);
                highScoreTimes.remove(highScoreTimes.size() - 1);
                highScores.add(score);
                highScoreTimes.add(time);
            }
        }
        // Sort the scores in ascending order
        Collections.sort(highScores);
        // Save the scores and times
        SharedPreferences.Editor editor = preferences.edit();
        for (int i = 0; i < highScores.size(); i++) {
            editor.putInt(KEY_HIGH_SCORES + i, highScores.get(i));
            editor.putLong(KEY_HIGH_SCORE_TIMES + i, highScoreTimes.get(i));
        }
        editor.apply();
    }

    public List<Integer> getHighScores() {
        List<Integer> highScores = new ArrayList<>();
        for (int i = 0; i < MAX_HIGH_SCORES; i++) {
            highScores.add(preferences.getInt(KEY_HIGH_SCORES + i, Integer.MAX_VALUE));
        }
        return highScores;
    }

    public List<Long> getHighScoreTimes() {
        List<Long> highScoreTimes = new ArrayList<>();
        for (int i = 0; i < MAX_HIGH_SCORES; i++) {
            highScoreTimes.add(preferences.getLong(KEY_HIGH_SCORE_TIMES + i, Long.MAX_VALUE));
        }
        return highScoreTimes;
    }
}
