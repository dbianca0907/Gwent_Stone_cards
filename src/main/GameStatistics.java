package main;
public class GameStatistics {
    private int numberOfTurns = 0;
    private int numberOfWonGamesOne = 0;
    private int numberOfWonGamesTwo = 0;
    private int numberOfRounds = 1;
    private boolean hasFinished = false;

    /**
     * total number of games
     * @return
     */
    public int getTotal() {
        return getNumberOfWonGamesOne() + getNumberOfWonGamesTwo();
    }

    /**
     * increment nr of turns
     */
    public void incrNumberOfTurns() {
        numberOfTurns++;
    }

    /**
     * increment nr of games won by player one
     */
    public void incrNumberOfWonGamesOne() {
        numberOfWonGamesOne++;
    }

    /**
     * increment nr of games won by player two
     */
    public void incrNumberOfWonGamesTwo() {

        numberOfWonGamesTwo++;
    }

    /**
     * increment number of rounds
     */
    public void incrNumberOfRounds() {
        numberOfRounds++;
    }

    //getters and setters

    /**
     * getter
     * @return boolean to see that the  game has finished
     */
    public boolean isHasFinished() {
        return hasFinished;
    }

    /**
     * getter
     * @return number of turns
     */
    public int getNumberOfTurns() {
        return numberOfTurns;
    }

    /**
     * getter
     * @return number of games won by one
     */
    public  int getNumberOfWonGamesOne() {
        return numberOfWonGamesOne;
    }

    /**
     * getter
     * @return number of games won by two
     */
    public  int getNumberOfWonGamesTwo() {
        return numberOfWonGamesTwo;
    }

    /**
     * getter
     * @return number of rounds
     */
    public int getNumberOfRounds() {
        return numberOfRounds;
    }
    /**
     * setter
     * @param hasFinished
     */
    public void setHasFinished(final boolean hasFinished) {
        this.hasFinished = hasFinished;
    }

    /**
     * setter
     * @param numberOfTurns number of turns
     */
    public void setNumberOfTurns(final int numberOfTurns) {
        this.numberOfTurns = numberOfTurns;
    }

    /**
     * setter
     * @param numberOfWonGamesOne nr games won by one
     */
    public  void setNumberOfWonGamesOne(final int numberOfWonGamesOne) {
        this.numberOfWonGamesOne = numberOfWonGamesOne;
    }

    /**
     * setter
     * @param numberOfRounds nr of rounds
     */
    public void setNumberOfRounds(final int numberOfRounds) {
        this.numberOfRounds = numberOfRounds;
    }
}
