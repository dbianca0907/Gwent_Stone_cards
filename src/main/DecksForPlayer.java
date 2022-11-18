package main;
import java.util.*;

public class DecksForPlayer {

    private int nrDecks;
    private int nrCardsInDecks;
    ArrayList<Deck> decks = null;

    public ArrayList<Deck> getDecksForPlayer() {
        if (decks == null) {
            decks = new ArrayList<Deck>();
            return decks;
        }
        return decks;
    }

    public int getNrDecks() {
        return nrDecks;
    }

    public int getNrCardsInDecks() {
        return nrCardsInDecks;
    }

    public void setNrDecks(int nrDecks) {this.nrDecks = nrDecks;
    }

    public void setNrCardsInDecks(int nrCardsInDecks) {
        this.nrCardsInDecks = nrCardsInDecks;
    }
}
