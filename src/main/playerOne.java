package main;

import java.util.ArrayList;

public class playerOne {
    DecksForPlayer decks = null;
    Hero hero;
    static boolean isTurn = false;

    public DecksForPlayer getDecks() {
        if (decks == null) {
            decks = new DecksForPlayer();
            return decks;
        }
        return decks;
    }
}
