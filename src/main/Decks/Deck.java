package main.Decks;
import main.Cards.Card;

import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> cards = null;
    private int nrCards;

    /**
     *
     * @return the cards
     */
    public ArrayList<Card> getCards() {
        if (cards == null) {
            cards = new ArrayList<Card>();
            return cards;
        }
        return cards;
    }

    /**
     * setter
     * @param nrCards
     */
    public void setNrCards(final int nrCards) {
        this.nrCards = nrCards;
    }

}
