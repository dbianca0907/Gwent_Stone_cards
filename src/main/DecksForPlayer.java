package main;
import java.util.*;

public class DecksForPlayer {

    static int nrDecks;
    ArrayList<Deck> decks = null;

    public ArrayList getDecksForPlayer() {
        if (decks == null) {
            decks = new ArrayList<Deck>(nrDecks);
            return decks;
        }
        return decks;
    }
}
