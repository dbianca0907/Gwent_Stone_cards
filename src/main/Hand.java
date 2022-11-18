package main;

import java.util.ArrayList;

public class Hand {
    Deck handCards = null;

    public Deck getHandCards() {
        if (handCards == null) {
            handCards = new Deck();
            return handCards;
        }
        return handCards;
    }

    //generate getters and setters

    public void setHandCards(Deck handCards) {
        this.handCards = handCards;
    }
}
