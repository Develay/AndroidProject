package com.example.android_project;

public class Resultat {
    private String pseudo;
    private int score;
    private long time;

    public Resultat(int score, long time, String pseudo) {
        this.score = score;
        this.time = time;
        this.pseudo = pseudo;
    }

    public int getScore() {
        return score;
    }

    public long getTime() {
        return time;
    }

    public String toString() {
        if(getTime() == 3600000 && getScore() == 200) {
            return " ";
        }
        long minutes = (getTime() / 1000) / 60;
        long seconds = (getTime() / 1000) % 60;
        long milliseconds = getTime() % 1000;
        return getPseudo() + " | Score: " + getScore() + ", Time: " + minutes + ":" + String.format("%02d", seconds) + ":" + milliseconds;
    }

    public String getPseudo() {
        return pseudo;
    }
}