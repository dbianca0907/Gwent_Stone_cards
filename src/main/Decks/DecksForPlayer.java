package main.Decks;
import java.util.ArrayList;

public class DecksForPlayer {

    private int nrDecks;
    private int nrCardsInDecks;
    private ArrayList<Deck> decks = null;

    /**
     *
     * @return decks
     */
    public ArrayList<Deck> getDecksForPlayer() {
        if (decks == null) {
            decks = new ArrayList<Deck>();
            return decks;
        }
        return decks;
    }

    /**
     * getter
     * @return number of decksa
     */
    public int getNrDecks() {
        return nrDecks;
    }

    /**
     * getter
     * @return number of cards
     */
    public int getNrCardsInDecks() {
        return nrCardsInDecks;
    }

    /**
     * setter
     * @param nrDecks number of Decks
     */
    public void setNrDecks(final int nrDecks) {
        this.nrDecks = nrDecks;
    }

    /**
     * setter
     * @param nrCardsInDecks number of cards
     */
    public void setNrCardsInDecks(final int nrCardsInDecks) {
        this.nrCardsInDecks = nrCardsInDecks;
    }
}
