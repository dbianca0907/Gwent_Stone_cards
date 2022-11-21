package main;

import java.util.ArrayList;

public class Hand {
    ArrayList<Card> handCards = null;;

    public ArrayList<Card> getHandCards() {
        if (handCards == null) {
            handCards = new ArrayList<Card>();
            return handCards;
        }
        return handCards;
    }

    //generate getters and setters

    public void setHandCards(ArrayList<Card> handCards) {
        this.handCards = handCards;
    }
}
