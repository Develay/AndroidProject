package com.example.android_project;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android_project.databinding.ActivityMainBinding;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.level1.setOnClickListener(v -> {
            mediaPlayer = MediaPlayer.create(this, R.raw.start);
            mediaPlayer.start();
            Intent intent = new Intent(this, Memory.class);
            intent.putExtra("level", 20);
            startActivity(intent);
        });
        binding.level2.setOnClickListener(v -> {
            mediaPlayer = MediaPlayer.create(this, R.raw.start);
            mediaPlayer.start();
            Intent intent = new Intent(this, Memory.class);
            intent.putExtra("level", 30);
            startActivity(intent);
        });
        binding.level3.setOnClickListener(v -> {
            mediaPlayer = MediaPlayer.create(this, R.raw.start);
            mediaPlayer.start();
            Intent intent = new Intent(this, Memory.class);
            intent.putExtra("level", 40);
            startActivity(intent);
        });
        binding.leaderboard.setOnClickListener(v -> {
            mediaPlayer = MediaPlayer.create(this, R.raw.pit_stop);
            mediaPlayer.start();
            Intent intent = new Intent(this, LeaderBoard.class);
            startActivity(intent);
        });
        binding.switchLanguage.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                setLocale("fr"); // Change to English
                mediaPlayer = MediaPlayer.create(this, R.raw.baguette);
                mediaPlayer.start();
            } else {
                setLocale("en"); // Change to default language
                mediaPlayer = MediaPlayer.create(this, R.raw.slurp);
                mediaPlayer.start();
            }
        });
    }

    public void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        recreate();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.EXIT))
                .setMessage(getString(R.string.really_quit))
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        MainActivity.super.onBackPressed();
                    }
                }).create().show();
    }
}