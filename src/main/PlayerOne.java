package main;
// de vazut cum se creste mana si de setat la inceputul jocului in functie de mana primita
public class PlayerOne {
    Hand cardsInHand = null;
    public int mana = 1;
    DecksForPlayer decks = null;
    ChosenDeck chosenDeck = null;
    Hero hero;
    private int indxOfDeck;
    private boolean isTurn = false;

    public void verif_isTurn(int number) {
        if (number == 1)
            isTurn = true;
    }

    public void increaseMana(int counter) {
        mana = mana + counter;
    }

    public void addCardsInHand() {
        if ( getPlayerChosenDeck().getChosenDeck().getCards().isEmpty() == false) {
            getCardsInHand().getHandCards().getCards().add(getPlayerChosenDeck().getChosenDeck().getCards().get(0));
            getPlayerChosenDeck().getChosenDeck().getCards().remove(0);
        }
    }


    // constructors

    public DecksForPlayer getDecks() {
        if (decks == null) {
            decks = new DecksForPlayer();
            return decks;
        }
        return decks;
    }

    public Hand getCardsInHand() {
        if (cardsInHand == null) {
            cardsInHand = new Hand();
            return cardsInHand;
        }
        return cardsInHand;
    }

    public ChosenDeck getPlayerChosenDeck() {
        if (chosenDeck == null) {
            chosenDeck = new ChosenDeck();
            return chosenDeck;
        }
        return chosenDeck;
    }


    // setters and getters


    public void setIndxOfDeck(int indx) {
        this.indxOfDeck = indx;
    }

    public void setDecks(DecksForPlayer decks) {
        this.decks = decks;
    }

    public Hero getHero() {
        return hero;
    }

    public  boolean getTurn() {
        return isTurn;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public int getIndxOfDeck() {
        return indxOfDeck;
    }

    public void setPlayerChosenDeck(ChosenDeck chosenDeck) {
        this.chosenDeck = chosenDeck;
    }

    public void setTurn(boolean turn) {
        isTurn = turn;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public void setCardsInHand(Hand cardsInHand) {
        Card card = cardsInHand.getHandCards().getCards().get(0);
        getCardsInHand().getHandCards().getCards().add(card);
    }

    public int getMana() {
        return mana;
    }

}
