package main;

public class Player {
    PlayerOne playerOne = null;
    PlayerTwo playerTwo = null;

    public PlayerOne getPlayerOne() {
        if (playerOne == null) {
            playerOne = new PlayerOne();
            return playerOne;
        }
        return playerOne;
    }

    public PlayerTwo getPlayerTwo() {
        if (playerTwo == null) {
            playerTwo = new PlayerTwo();
            return playerTwo;
        }
        return playerTwo;
    }

    public void setPlayerOne(PlayerOne playerOne) {
        this.playerOne = playerOne;
    }

    public void setPlayerTwo(PlayerTwo playerTwo) {
        this.playerTwo = playerTwo;
    }
}
