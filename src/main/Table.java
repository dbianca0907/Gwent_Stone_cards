package main;

import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.ArrayList;

public class Table {
    ArrayList<ArrayList<Card>> cardsOnTable = null;
    ArrayList<Card> zeroRow = null;
    ArrayList<Card> firstRow = null;
    ArrayList<Card> secondRow = null;
    ArrayList<Card> thirdRow = null;

    public void addCardsOnTableForOne(Card card) {
        if (card.getSpecificRow() == 2)
            getSecondRow().add(card);
        else if (card.getSpecificRow() == 3)
            getThirdRow().add(card);
    }
    public void addCardsOnTableForTwo(Card card) {
        if (card.getSpecificRow() == 1)
            getFirstRow().add(card);
        else if (card.getSpecificRow() == 0)
            getZeroRow().add(card);
    }

    public void placeCard(Card card, int indxPlayer, Player players, int indx){
        if (indxPlayer == 1) {
            if (players.playerOne.getMana() >= card.getMana()) {
                addCardsOnTableForOne(card);
                int counter = (-1) * card.getMana();
                players.playerOne.increaseMana(counter);
                players.playerOne.getCardsInHand().getHandCards().getCards().remove(indx);
            }
        } else {
            if (players.playerTwo.getMana() >= card.getMana()) {
                int counter = (-1) * card.getMana();
                players.playerTwo.increaseMana(counter);
                addCardsOnTableForTwo(card);
                //players.playerTwo.getCardsInHand().getHandCards().getCards().remove(indx);
            }
        }
    }

    // constructors

    public ArrayList<ArrayList<Card>> cardsOnTable() {
        if (cardsOnTable == null) {
            cardsOnTable = new ArrayList<ArrayList<Card>>(4);
            return cardsOnTable;
        }
        return cardsOnTable;
    }

    public ArrayList<Card> getZeroRow() {
        if (zeroRow == null) {
            zeroRow = new ArrayList<Card>();
            return zeroRow;
        }
        return zeroRow;
    }

    public ArrayList<Card> getFirstRow() {
        if (firstRow == null) {
            firstRow = new ArrayList<Card>();
            return firstRow;
        }
        return firstRow;
    }

    public ArrayList<Card> getSecondRow() {
        if (secondRow == null) {
            secondRow = new ArrayList<Card>();
            return secondRow;
        }
        return secondRow;
    }

    public ArrayList<Card> getThirdRow() {
        if (thirdRow == null) {
            thirdRow = new ArrayList<Card>();
            return thirdRow;
        }
        return thirdRow;
    }
}
