package main;
import java.util.*;

public class Deck {
    ArrayList<Card> cards = null;
    static int nrCards;
    static boolean wasUsed = false;

    public ArrayList<Card> getCards() {
        if (cards == null) {
            cards = new ArrayList<Card>(nrCards);
            return cards;
        }
        return cards;
    }


    // getters and setters


    public static int getNrCards() {
        return nrCards;
    }

    public static boolean isWasUsed() {
        return wasUsed;
    }

    public static void setNrCards(int nrCards) {
        Deck.nrCards = nrCards;
    }
}
