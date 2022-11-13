package main;
import java.util.*;

public class Deck {
    ArrayList<Card> cards = null;
    static int nrCards = 0;
    static boolean wasUsed = false;

    public ArrayList<Card> getCards() {
        if (cards == null) {
            cards = new ArrayList<Card>(nrCards);
            return cards;
        }
        return cards;
    }


}
