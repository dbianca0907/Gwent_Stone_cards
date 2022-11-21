package main;
//DACA DAI COPY PASTE SE MODIFICA ISTURN
public class PlayerTwo {
    Hand cardsInHand = null;
    public int mana = 1;
    DecksForPlayer decks = null;
    ChosenDeck chosenDeck = null;
    Hero hero;
    private int indxOfDeck;
    private boolean isTurn = false;

    public void verif_isTurn(int number) {
        if (number == 2)
            isTurn = true;
    }

    public void increaseMana(int counter) {
        mana = mana + counter;
    }

    public void addCardsInHand() {
        if (getPlayerChosenDeck().getChosenDeck().getCards().isEmpty() == false) {
            getCardsInHand().getHandCards().add(getPlayerChosenDeck().getChosenDeck().getCards().get(0));
            getPlayerChosenDeck().getChosenDeck().getCards().remove(0);
        }
    }

    public void unfreezeCards (Table table) {
        for (int j = 0; j < table.getCardsOnTable().get(0).size(); j++)
            table.getCardsOnTable().get(0).get(j).setFrozen(false);
        for (int j = 0; j < table.getCardsOnTable().get(1).size(); j++)
            table.getCardsOnTable().get(1).get(j).setFrozen(false);
    }

    public void setAttackers (Table table) {
        for (int j = 0; j < table.getCardsOnTable().get(0).size(); j++)
            table.getCardsOnTable().get(0).get(j).setHasAttacked(false);
        for (int j = 0; j < table.getCardsOnTable().get(1).size(); j++)
            table.getCardsOnTable().get(1).get(j).setHasAttacked(false);
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

    public boolean getTurn() {
        return isTurn;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }


    public int getIndxOfDeck() {
        return indxOfDeck;
    }

    public void setPlayerChosenDeck(ChosenDeck chosenDeck) {
        for (int i = 0; i < chosenDeck.getChosenDeck().getCards().size(); i++)
            getPlayerChosenDeck().getChosenDeck().getCards().add(chosenDeck.getChosenDeck().getCards().get(i));
    }

    public void setTurn(boolean turn) {
        isTurn = turn;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    // se seteaza doar la inceput
    public void setCardsInHand(Hand cardsInHand) {

        Card card = cardsInHand.getHandCards().get(0);
        getCardsInHand().getHandCards().add(card);
    }

    public  int getMana() {
        return mana;
    }
}
