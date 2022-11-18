package main;
import java.util.*;

public class FrozenCards {
    ArrayList<Integer> frozenCardsOne = null;
    ArrayList<Integer> frozenCardsTwo = null;


    public void addFrozenCardsOne(int indx) {
        getFrozenCardsOne().add(indx);
    }
    public void addFrozenCardsTwo(int indx) {
        getFrozenCardsTwo().add(indx);
    }

    public void defrozenCards(ChosenDeck chosenDeck, int indx) {
        if (indx == 1) {
            for (int i = 0; i < chosenDeck.getChosenDeck().getCards().size(); i++)
                for (int j = 0; j < getFrozenCardsOne().size(); j++)
                    if (getFrozenCardsOne().get(j) == i)
                        chosenDeck.getChosenDeck().getCards().get(i).setFrozen(false);
        } else {
            for (int i = 0; i < chosenDeck.getChosenDeck().getCards().size(); i++)
                for (int j = 0; j < getFrozenCardsTwo().size(); j++)
                    if (getFrozenCardsTwo().get(j) == i)
                        chosenDeck.getChosenDeck().getCards().get(i).setFrozen(false);
        }
    }

    // constructors

    public ArrayList<Integer> getFrozenCardsOne() {
        if (frozenCardsOne == null) {
            frozenCardsOne = new ArrayList<Integer>();
            return frozenCardsOne;
        }
        return frozenCardsOne;
    }

    public ArrayList<Integer> getFrozenCardsTwo() {
        if (frozenCardsTwo == null) {
            frozenCardsTwo = new ArrayList<Integer>();
            return frozenCardsTwo;
        }
        return frozenCardsTwo;
    }

}
