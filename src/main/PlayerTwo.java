package main;

import java.util.ArrayList;

public class PlayerTwo {
    private Hand cardsInHand = null;
    private int mana = 1;
    private DecksForPlayer decks = null;
    private ChosenDeck chosenDeck = null;
    private Hero hero;
    private int indxOfDeck;
    private boolean isTurn = false;

    /**
     * set the turn of a player
     * @param number index of the player
     */
    public void verifIsTurn(final int number) {
        if (number == 2) {
            isTurn = true;
        }
    }
    /**
     * increases the mana after each round
     * @param counter
     */
    public void increaseMana(final int counter) {
        mana = mana + counter;
    }

    /**
     * adding the cards int player one's hand after every turn
     */
    public void addCardsInHand() {
        if (!getPlayerChosenDeck().getChosenDeck().getCards().isEmpty()) {
            getCardsInHand().getHandCards().add(getPlayerChosenDeck().getChosenDeck().getCards().get(0));
            getPlayerChosenDeck().getChosenDeck().getCards().remove(0);
        }
    }

    /**
     * setting the first card in hand at the beginning of a game
     * @param cardsInHand
     */
    public void setCardsInHand(final Hand cardsInHand) {

        Card card = cardsInHand.getHandCards().get(0);
        getCardsInHand().getHandCards().add(card);
    }

    /**
     * setting the cards unfreeze after a turn
     * @param table
     */
    public void unfreezeCards(final Table table) {
        for (int j = 0; j < table.getCardsOnTable().get(0).size(); j++) {
            table.getCardsOnTable().get(0).get(j).setFrozen(false);
        }
        for (int j = 0; j < table.getCardsOnTable().get(1).size(); j++) {
            table.getCardsOnTable().get(1).get(j).setFrozen(false);
        }
    }

    /**
     * marking that the plyaer's cards can attack after his turn
     * @param table
     */
    public void setAttackers(final Table table) {
        for (int j = 0; j < table.getCardsOnTable().get(0).size(); j++) {
            table.getCardsOnTable().get(0).get(j).setHasAttacked(false);
        }
        for (int j = 0; j < table.getCardsOnTable().get(1).size(); j++) {
            table.getCardsOnTable().get(1).get(j).setHasAttacked(false);
        }
    }

    // constructors
    /**
     * constructor
     * @return decks
     */
    public DecksForPlayer getDecks() {
        if (decks == null) {
            decks = new DecksForPlayer();
            return decks;
        }
        return decks;
    }

    /**
     * constructor
     * @return
     */
    public Hand getCardsInHand() {
        if (cardsInHand == null) {
            cardsInHand = new Hand();
            return cardsInHand;
        }
        return cardsInHand;
    }
    /**
     * constructor
     * @return
     */
    public ChosenDeck getPlayerChosenDeck() {
        if (chosenDeck == null) {
            chosenDeck = new ChosenDeck();
            return chosenDeck;
        }
        return chosenDeck;
    }


    // setters and getters

    /**
     * setter
     * @param indx
     */
    public void setIndxOfDeck(final int indx) {
        this.indxOfDeck = indx;
    }

    /**
     * setter
     * @param decks
     */
    public void setDecks(final DecksForPlayer decks) {
        this.decks = decks;
    }

    /**
     * setter
     * @param hero
     */
    public void setHero(final Hero hero) {
        this.hero = hero;
    }

    /**
     * setter
     * @param plyrDeck
     */
    public void setPlayerChosenDeck(final ChosenDeck plyrDeck) {
        ArrayList<Card> cards = getPlayerChosenDeck().getChosenDeck().getCards();
        for (int i = 0; i < plyrDeck.getChosenDeck().getCards().size(); i++) {
            cards.add(plyrDeck.getChosenDeck().getCards().get(i));
        }
    }
    /**
     * setter
     * @param turn
     */
    public void setTurn(final boolean turn) {
        isTurn = turn;
    }

    /**
     * setter
     * @param mana
     */
    public void setMana(final int mana) {
        this.mana = mana;
    }

    /**
     * getter
     * @return
     */
    public int getMana() {
        return mana;
    }

    /**
     * getter
     * @return
     */
    public int getIndxOfDeck() {
        return indxOfDeck;
    }

    /**
     * getter
     * @return
     */
    public Hero getHero() {
        return hero;
    }

    /**
     * getter
     * @return
     */
    public  boolean getTurn() {
        return isTurn;
    }
}
