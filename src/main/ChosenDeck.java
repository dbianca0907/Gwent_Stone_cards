package main;

import java.util.ArrayList;

public class ChosenDeck {
    private Deck chosenDeck = null;

    /**
     * creates the chosen deck by the player
     * @param indx of the player
     * @param allDecks all decks
     */
    public void createChosenDeck(final int indx, final DecksForPlayer allDecks) {
        ArrayList<Deck> varDeck = new ArrayList<Deck>();
        varDeck = allDecks.getDecksForPlayer();
        ArrayList<Card> varCards = new ArrayList<Card>();
        varCards = varDeck.get(indx).getCards();
        for (int i = 0; i < allDecks.getNrCardsInDecks(); i++) {
            Card card = new Card();
            card.setMana(varCards.get(i).getMana());
            card.setDescription(varCards.get(i).getDescription());
            card.setName(varCards.get(i).getName());
            card.getTheType(card.getName());
            card.setColors(varCards.get(i).getColors());
            card.setAttackDamage(varCards.get(i).getAttackDamage());
            card.setHealth(varCards.get(i).getHealth());
            getChosenDeck().getCards().add(card);
        }
        getChosenDeck().setNrCards(allDecks.getNrCardsInDecks());
    }

    /**
     * constructor for chosenDeck
     * @return chosenDeck
     */
    public Deck getChosenDeck() {
        if (chosenDeck == null) {
            chosenDeck = new Deck();
            return chosenDeck;
        }
        return chosenDeck;
    }
}
