package main;

public class PlayerTwo {
    DecksForPlayer decks;
    Hero hero;
    static boolean isTurn = false;
    static int indx;


    public DecksForPlayer getDecks() {
        if (decks == null) {
            decks = new DecksForPlayer();
            return decks;
        }
        return decks;
    }
    public void verif_isTurn(int number) {
        if (number == 1)
            isTurn = true;
    }

    // setters and getters

    public static void setIndx(int indx) {
        PlayerOne.indx = indx;
    }
    public void setDecks(DecksForPlayer decks) {
        this.decks = decks;
    }

    public Hero getHero() {
        return hero;
    }

    public static boolean isIsTurn() {
        return isTurn;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public static void setIsTurn(boolean isTurn) {
        PlayerOne.isTurn = isTurn;
    }
}