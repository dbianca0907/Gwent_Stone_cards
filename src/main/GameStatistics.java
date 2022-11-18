package main;
import java.util.*;

public class GameStatistics {
    static int numberOfGames = 0;
    static int numberOfTurns = 0;
    static int numberOfWonGamesOne = 0;
    static int numberOfWonGamesTwo = 0;
    static int numberOfRounds = 1;

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

    public static int getNumberOfGames() {
        return numberOfGames;
    }

    public static int getNumberOfTurns() {
        return numberOfTurns;
    }

    public static int getNumberOfWonGamesOne() {
        return numberOfWonGamesOne;
    }

    public static int getNumberOfWonGamesTwo() {
        return numberOfWonGamesTwo;
    }

    public static int getNumberOfRounds() {
        return numberOfRounds;
    }

    public static void setNumberOfGames(int numberOfGames) {
        GameStatistics.numberOfGames = numberOfGames;
    }

    public static void setNumberOfTurns(int numberOfTurns) {
        GameStatistics.numberOfTurns = numberOfTurns;
    }

    public static void setNumberOfWonGamesOne(int numberOfWonGamesOne) {
        GameStatistics.numberOfWonGamesOne = numberOfWonGamesOne;
    }

    public static void setNumberOfWonGamesTwo(int numberOfWonGamesTwo) {
        GameStatistics.numberOfWonGamesTwo = numberOfWonGamesTwo;
    }

    public static void setNumberOfRounds(int numberOfRounds) {
        GameStatistics.numberOfRounds = numberOfRounds;
    }
}
