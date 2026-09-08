package model;

import java.io.Serializable;

public class ProgrammingPlatform implements Serializable {
    private static final long serialVersionUID = 1L;

    private String platformName;
    private int rank;
    private double score;
    private int problemsSolved;
    private double calculatedPlatformScore;

    public ProgrammingPlatform(String platformName, int rank, double score, int problemsSolved) {
        this.platformName = platformName;
        this.rank = rank;
        this.score = score;
        this.problemsSolved = problemsSolved;
    }

    public String getPlatformName() { return platformName; }
    public void setPlatformName(String platformName) { this.platformName = platformName; }

    public int getRank() { return rank; }
    public void setRank(int rank) { this.rank = rank; }

    public double getScore() { return score; }
    public void setScore(double score) { this.score = score; }

    public int getProblemsSolved() { return problemsSolved; }
    public void setProblemsSolved(int problemsSolved) { this.problemsSolved = problemsSolved; }

    public double getCalculatedPlatformScore() { return calculatedPlatformScore; }
    public void setCalculatedPlatformScore(double calculatedPlatformScore) { 
        this.calculatedPlatformScore = calculatedPlatformScore; 
    }

    @Override
    public String toString() {
        return String.format("[%s | Rank: %d | Score: %.2f | Solved: %d | Score: %.2f]",
                platformName, rank, score, problemsSolved, calculatedPlatformScore);
    }
}