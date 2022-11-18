package main;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ChosenDeck {
    private int indx;
    Deck chosenDeck = null;

    public Deck getChosenDeck() {
        if (chosenDeck == null) {
           chosenDeck = new Deck();
           return chosenDeck;
        }
        return chosenDeck;
    }

    public void createChosenDeck(int indx, DecksForPlayer allDecks) {
        this.indx = indx;
        ArrayList<Deck> varDeck = new ArrayList<Deck>();
        varDeck = allDecks.getDecksForPlayer();
        ArrayList<Card> varCards = new ArrayList<Card>();
        varCards = varDeck.get(indx).getCards();
        for (int i = 0; i < allDecks.getNrCardsInDecks(); i++) {
            Card card = new Card();
            card.setMana(varCards.get(i).getMana());
            card.setDescription(varCards.get(i).getDescription());
            card.setName(varCards.get(i).getName());
            // se stabileste type ul
            card.getTheType(card.getName());
            card.setColors(varCards.get(i).getColors());
            card.setAttackDamage(varCards.get(i).getAttackDamage());
            card.setHealth(varCards.get(i).getHealth());
            getChosenDeck().getCards().add(card);
        }
        getChosenDeck().setNrCards(allDecks.getNrCardsInDecks());
    }

}
