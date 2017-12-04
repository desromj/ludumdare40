package com.leggodt.screen;

public class Score {

    int score, topScore;

    public Score() {
        score = 0;
        topScore = 0;
    }

    // Sets the record low time for a level. If there is no previous time, save the passed
    // time. Otherwise, compare to the last time and save the lowest
    public void setScore(int score) {
        this.score = score;
        if (score > this.topScore) {
            this.topScore = score;
        }
    }

    public int score() { return score; }
    public int topScore() { return topScore; }
}