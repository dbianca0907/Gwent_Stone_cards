package main;

import java.util.ArrayList;
public class Table {
    private ArrayList<ArrayList<Card>> cardsOnTable = null;

    /**
     * adding cards on the rows of player one
     * @param card
     */
    public void addCardsOnTableForOne(final Card card) {
        final int row = 3;
        card.setSpecificRow(card.getName(), 1);
        if (card.getSpecificRow() == 2) {
            getCardsOnTable().get(2).add(card);
        } else if (card.getSpecificRow() == row) {
            getCardsOnTable().get(row).add(card);
        }
    }

    /**
     * adding cards on the rows of player two
     * @param card
     */
    public void addCardsOnTableForTwo(final Card card) {
        card.setSpecificRow(card.getName(), 2);
        if (card.getSpecificRow() == 1) {
            getCardsOnTable().get(1).add(card);
        } else if (card.getSpecificRow() == 0) {
            getCardsOnTable().get(0).add(card);
        }
    }

    /**
     * place card on the table
     * @param card that is placed
     * @param indxPlayer index of the player
     * @param players all the players
     * @param indx the position of the card in the Hand
     */
    public void placeCard(final Card card, final int indxPlayer,
                          final Player players, final int indx) {
        if (indxPlayer == 1) {
            if (players.getPlayerOne().getMana() >= card.getMana()) {
                addCardsOnTableForOne(card);
                int counter = (-1) * card.getMana();
                players.getPlayerOne().increaseMana(counter);
                players.getPlayerOne().getCardsInHand().getHandCards().remove(indx);
            }
        } else if (indxPlayer == 2) {
            if (players.getPlayerTwo().getMana() >= card.getMana()) {
                int counter = (-1) * card.getMana();
                players.getPlayerTwo().increaseMana(counter);
                addCardsOnTableForTwo(card);
                players.getPlayerTwo().getCardsInHand().getHandCards().remove(indx);
            }
        }
    }

    /**
     * constructor
     * @return the array with the cards from the table
     */
    public ArrayList<ArrayList<Card>> getCardsOnTable() {
        final int maxRows = 4;
        if (cardsOnTable == null) {
            cardsOnTable = new ArrayList<ArrayList<Card>>(maxRows);
            cardsOnTable.add(new ArrayList<Card>());
            cardsOnTable.add(new ArrayList<Card>());
            cardsOnTable.add(new ArrayList<Card>());
            cardsOnTable.add(new ArrayList<Card>());
            return cardsOnTable;
        }
        return cardsOnTable;
    }
}
