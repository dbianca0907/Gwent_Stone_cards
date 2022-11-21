package main;
import java.util.*;

public class GameStatistics {
    static int numberOfGames = 0;
    int numberOfTurns = 0;
     int numberOfWonGamesOne = 0;
     int numberOfWonGamesTwo = 0;
    int numberOfRounds = 1;
    boolean hasFinished = false;

    public void incrNumberOfGames() {
        numberOfGames++;
    }
    public void incrNumberOfTurns () {
        numberOfTurns++;
    }
    public void incrNumberOfWonGamesOne() {
        numberOfWonGamesOne++;
    }
    public void incrNumberOfWonGamesTwo() {

        numberOfWonGamesTwo++;
    }

    public void incrNumberOfRounds() {
        numberOfRounds++;
    }

    //generate getters and setters


    public void setHasFinished(boolean hasFinished) {
        this.hasFinished = hasFinished;
    }

    public boolean isHasFinished() {
        return hasFinished;
    }

    public static int getNumberOfGames() {
        return numberOfGames;
    }

    public int getNumberOfTurns() {
        return numberOfTurns;
    }

    public  int getNumberOfWonGamesOne() {
        return numberOfWonGamesOne;
    }

    public  int getNumberOfWonGamesTwo() {
        return numberOfWonGamesTwo;
    }

    public int getNumberOfRounds() {
        return numberOfRounds;
    }

    public static void setNumberOfGames(int numberOfGames) {
        GameStatistics.numberOfGames = numberOfGames;
    }

    public void setNumberOfTurns(int numberOfTurns) {
        this.numberOfTurns = numberOfTurns;
    }

    public  void setNumberOfWonGamesOne(int numberOfWonGamesOne) {
        this.numberOfWonGamesOne = numberOfWonGamesOne;
    }

    public  void setNumberOfWonGamesTwo(int numberOfWonGamesTwo) {
        this.numberOfWonGamesTwo = numberOfWonGamesTwo;
    }

    public void setNumberOfRounds(int numberOfRounds) {
        this.numberOfRounds = numberOfRounds;
    }
}
