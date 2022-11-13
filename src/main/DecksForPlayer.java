package main;
import java.util.*;

public class DecksForPlayer {

    static int nrDecks;
    static int nrCardsInDecks;
    ArrayList<Deck> decks = null;

    public ArrayList getDecksForPlayer() {
        if (decks == null) {
            decks = new ArrayList<Deck>(nrDecks);
            return decks;
        }
        return decks;
    }

    public static int getNrDecks() {
        return nrDecks;
    }

    public static int getNrCardsInDecks() {
        return nrCardsInDecks;
    }

    public static void setNrDecks(int nrDecks) {
        DecksForPlayer.nrDecks = nrDecks;
    }

    public static void setNrCardsInDecks(int nrCardsInDecks) {
        DecksForPlayer.nrCardsInDecks = nrCardsInDecks;
    }
}
