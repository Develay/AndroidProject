package com.example.android_project;

//Classe Resultat
public class Resultat {
    private String pseudo;
    private int score;
    private long time;

    public Resultat(int score, long time, String pseudo) {
        this.score = score;
        this.time = time;
        this.pseudo = pseudo;
    }

    //Méthode de récupération du score
    public int getScore() {
        return score;
    }

    //Méthode de récupération du temps
    public long getTime() {
        return time;
    }

    //Méthode ToString
    public String toString() {
        if(getTime() == Long.MAX_VALUE && getScore() == Integer.MAX_VALUE) {
            return " ";
        }
        long minutes = (getTime() / 1000) / 60;
        long seconds = (getTime() / 1000) % 60;
        long milliseconds = getTime() % 1000;
        return getPseudo() + " \n| Score: " + getScore() + ", Time: " + minutes + ":" + String.format("%02d", seconds) + ":" + milliseconds;
    }

    //Méthode de récupération du pseudo
    public String getPseudo() {
        return pseudo;
    }
}