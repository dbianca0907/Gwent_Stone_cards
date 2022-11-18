package main;
import java.util.*;

public class Deck {
    ArrayList<Card> cards = null;
    private int nrCards;
    private boolean wasUsed = false;

    public ArrayList<Card> getCards() {
        if (cards == null) {
            cards = new ArrayList<Card>();
            return cards;
        }
        return cards;
    }


    // getters and setters


    public int getNrCards() {
        return nrCards;
    }

    public boolean isWasUsed() {
        return wasUsed;
    }

    public void setNrCards(int nrCards) {
        this.nrCards = nrCards;
    }

    public void setWasUsed(boolean wasUsed) {
        this.wasUsed = wasUsed;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

}
