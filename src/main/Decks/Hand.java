package main.Decks;

import main.Cards.Card;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> handCards = null;;

    /**
     * constructor
     * @return array of cards
     */
    public ArrayList<Card> getHandCards() {
        if (handCards == null) {
            handCards = new ArrayList<Card>();
            return handCards;
        }
        return handCards;
    }
}
