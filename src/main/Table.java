package main;

import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.ArrayList;
import java.util.*;

public class Table {
    ArrayList<ArrayList<Card>> cardsOnTable = null;

    public void addCardsOnTableForOne(Card card) {
        card.setSpecificRow(card.getName(), 1);
        if (card.getSpecificRow() == 2)
            getCardsOnTable().get(2).add(card);
        else if (card.getSpecificRow() == 3)
            getCardsOnTable().get(3).add(card);
    }
    public void addCardsOnTableForTwo(Card card) {
        card.setSpecificRow(card.getName(), 2);
        if (card.getSpecificRow() == 1)
            getCardsOnTable().get(1).add(card);
        else if (card.getSpecificRow() == 0)
            getCardsOnTable().get(0).add(card);
    }

    public void placeCard(Card card, int indxPlayer, Player players, int indx){
        if (indxPlayer == 1) {
            if (players.playerOne.getMana() >= card.getMana()) {
                addCardsOnTableForOne(card);
                int counter = (-1) * card.getMana();
                players.playerOne.increaseMana(counter);
                players.playerOne.getCardsInHand().getHandCards().remove(indx);
            }
        } else if (indxPlayer == 2) {
            if (players.playerTwo.getMana() >= card.getMana()) {
                int counter = (-1) * card.getMana();
                players.playerTwo.increaseMana(counter);
                addCardsOnTableForTwo(card);
                players.playerTwo.getCardsInHand().getHandCards().remove(indx);
            }
        }
    }

    public boolean isFuLL(ArrayList<Card> arrayRow) {
        if (arrayRow.size() == 5)
            return true;
        return false;
    }

    // constructors

    public ArrayList<ArrayList<Card>> getCardsOnTable() {
        if (cardsOnTable == null) {
            cardsOnTable = new ArrayList<ArrayList<Card>>(4);
            cardsOnTable.add(new ArrayList<Card>());
            cardsOnTable.add(new ArrayList<Card>());
            cardsOnTable.add(new ArrayList<Card>());
            cardsOnTable.add(new ArrayList<Card>());
            return cardsOnTable;
        }
        return cardsOnTable;
    }
}
