package main.Players;

public class Player {
    private PlayerOne playerOne = null;
    private PlayerTwo playerTwo = null;

    /**
     * constructor for player one
     * @return player one
     */
    public PlayerOne getPlayerOne() {
        if (playerOne == null) {
            playerOne = new PlayerOne();
            return playerOne;
        }
        return playerOne;
    }

    /**
     * constructor for player two
     * @return player two
     */
    public PlayerTwo getPlayerTwo() {
        if (playerTwo == null) {
            playerTwo = new PlayerTwo();
            return playerTwo;
        }
        return playerTwo;
    }

    /**
     * setter
     * @param playerOne player one
     */
    public void setPlayerOne(final PlayerOne playerOne) {

        this.playerOne = playerOne;
    }

    /**
     * setter
     * @param playerTwo player two
     */
    public void setPlayerTwo(final PlayerTwo playerTwo) {

        this.playerTwo = playerTwo;
    }
}
