package main;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import main.Cards.Card;
import main.Cards.Hero;
import main.Decks.Table;
import main.GamePreps.GameStatistics;
import main.Players.Player;
import main.Players.PlayerOne;
import main.Players.PlayerTwo;

import java.util.ArrayList;

public class MyPrinter {

    /**
     * method for making an ObjectNode from a card
     * @param card to print
     * @return ObjectNode with all the information
     */
    public ObjectNode makeObjectFromCard(final Card card) {
        ObjectNode cardNode = JsonNodeFactory.instance.objectNode();
        ArrayNode nodeForColors = JsonNodeFactory.instance.arrayNode();

        if (card.getType() == 1) {
            cardNode.put("mana", card.getMana());
            cardNode.put("attackDamage", card.getAttackDamage());
            cardNode.put("health", card.getHealth());
            cardNode.put("description", card.getDescription());

            for (String color : card.getColors()) {
                nodeForColors.add(color);
            }
            cardNode.set("colors", nodeForColors);
            cardNode.put("name", card.getName());

        }
        if (card.getType() == 2) {
            cardNode.put("mana", card.getMana());
            cardNode.put("description", card.getDescription());
            for (String color : card.getColors()) {
                nodeForColors.add(color);
            }
            cardNode.set("colors", nodeForColors);
            cardNode.put("name", card.getName());
        }
        return cardNode;
    }

    /**
     * make an arrayNode with all the ObjectNodes cards from a deck
     * @param deck to print
     * @return arrayNode (deck)
     */
    public ArrayNode makeArrayFromDeck(final ArrayList<Card> deck) {
        ArrayNode deckNode = JsonNodeFactory.instance.arrayNode();

        for (int i = 0; i < deck.size(); i++) {
            deckNode.add(makeObjectFromCard(deck.get(i)));
        }
        return deckNode;
    }

    /**
     * printing all the characteristics of a Hero
     * @param hero to print
     * @return ObjectNode (hero)
     */
    public ObjectNode makeObjectFromHero(final Hero hero) {
        ObjectNode heroNode = JsonNodeFactory.instance.objectNode();
        ArrayNode nodeForColors = JsonNodeFactory.instance.arrayNode();

        heroNode.put("mana", hero.getMana());
        heroNode.put("description", hero.getDescription());
        for (String color : hero.getColors()) {
            nodeForColors.add(color);
        }
        heroNode.set("colors", nodeForColors);
        heroNode.put("name", hero.getName());
        heroNode.put("health", hero.getHealth());
        return heroNode;
    }

    /**
     * printing the deck of a player
     * @param indx player
     * @param players all the players
     * @return ObjectNode(chosen deck of the player)
     */
    public ObjectNode getPlayerDeck(final int indx, final Player players) {
        PlayerOne plyrOne = players.getPlayerOne();
        PlayerTwo plyrTwo  = players.getPlayerTwo();
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        ArrayNode deckArr;

        node.put("command", "getPlayerDeck");
        node.put("playerIdx", indx);

        if (indx == 1) {
            deckArr = makeArrayFromDeck(plyrOne.getPlayerChosenDeck().getChosenDeck().getCards());
            node.set("output", deckArr);
        } else {
            deckArr = makeArrayFromDeck(plyrTwo.getPlayerChosenDeck().getChosenDeck().getCards());
            node.set("output", deckArr);
        }
        return node;
    }

    /**
     * method for printing the cards in the player's hand
     * @param indx player
     * @param players al players
     * @return ObjectNode
     */
    public ObjectNode getCardsInHand(final int indx, final Player players) {
        PlayerOne plyrOne =  players.getPlayerOne();
        PlayerTwo plyrTwo = players.getPlayerTwo();
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        ArrayNode cardsArr;

        node.put("command", "getCardsInHand");
        node.put("playerIdx", indx);

        if (indx == 1) {
            cardsArr = makeArrayFromDeck(plyrOne.getCardsInHand().getHandCards());
            node.set("output", cardsArr);
        } else {
            cardsArr = makeArrayFromDeck(plyrTwo.getCardsInHand().getHandCards());
            node.set("output", cardsArr);
        }
        return node;
    }

    /**
     * method for printing the errors for "placeCard" command
     * @param card from the hand's player
     * @param handIndx the position of the card
     * @param players all players
     * @param indxPlyr index of the player
     * @param output the arrayNode from MAin
     * @param table all the cards on the Table
     * @return 0, if there aren't errors, 1 otherwise
     */
    public int verifErorrsPlaceCard(final Card card, final int handIndx, final Player players,
                                    final int indxPlyr, final ArrayNode output, final Table table) {
        PlayerOne plyrOne = players.getPlayerOne();
        PlayerTwo plyrTwo = players.getPlayerTwo();
        final int rowFull = 5;
        final int row = 3;
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.put("command", "placeCard");
        node.put("handIdx", handIndx);

        if (card.getType() == 2) {
            node.put("error", "Cannot place environment card on table.");
            output.add(node);
            return 0;
        }
        if (indxPlyr == 1) {
            if (card.getMana() > plyrOne.getMana()) {
                node.put("error", "Not enough mana to place card on table.");
                output.add(node);
                return 0;
            }
            if (table.getCardsOnTable().get(2).size() == rowFull
                    || table.getCardsOnTable().get(row).size() == rowFull) {
                node.put("error", "Cannot place card on table since row is full.");
                output.add(node);
                return 0;
            }
        } else if (indxPlyr == 2) {
            if (card.getMana() > plyrTwo.getMana()) {
                node.put("error", "Not enough mana to place card on table.");
                output.add(node);
                return 0;
            }
            if (table.getCardsOnTable().get(0).size() == rowFull
                    || table.getCardsOnTable().get(1).size() == rowFull) {
                node.put("error", "Cannot place card on table since row is full.");
                output.add(node);
                return 0;
            }
        }
        return 1;
    }

    /**
     * verifying and printing the errors for "useEnvironmentCard"
     * @param handIndx the position of the card in the hand
     * @param affectedRow the row that is attacked
     * @param players all the players
     * @param table all the cards on the table
     * @param indxPlyr index of the player
     * @param output from Main
     * @return 1 if there aren't errors, 0 otherwise
     */

    public int verifErrorsUseEnvCard(final int handIndx, final int affectedRow,
                                     final Player players, final Table table,
                                     final int indxPlyr, final ArrayNode output) {
        PlayerOne plyrOne =  players.getPlayerOne();
        PlayerTwo plyrTwo = players.getPlayerTwo();
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        int verif = 1;
        String error = "nothing";
        node.put("command", "useEnvironmentCard");
        node.put("handIdx", handIndx);
        node.put("affectedRow", affectedRow);


        if (indxPlyr == 1) {
            Card card = plyrOne.getCardsInHand().getHandCards().get(handIndx);

            if (card.getType() != 2) {
                verif = 0;
                error = "Chosen card is not of type environment.";
            } else if (plyrOne.getMana() < card.getMana()) {
                verif = 0;
                error = "Not enough mana to use environment card.";
            } else if (affectedRow >= 2) {
                verif = 0;
                error = "Chosen row does not belong to the enemy.";
            } else if (card.getName().equals("Heart Hound")) {
                final int rowFulll = 5;
                final int row = 3;
                if ((affectedRow == 0 && table.getCardsOnTable().get(row).size() == rowFulll)
                       || (affectedRow == 1 && table.getCardsOnTable().get(2).size() == rowFulll)) {
                    verif = 0;
                    error = "Cannot steal enemy card since the player's row is full.";
                }
            }
        } else {
            Card card = plyrTwo.getCardsInHand().getHandCards().get(handIndx);
            if (card.getType() != 2) {
               verif = 0;
               error = "Chosen card is not of type environment.";
            } else if (plyrTwo.getMana() < card.getMana()) {
               verif = 0;
               error = "Not enough mana to use environment card.";
            } else if (affectedRow <= 1) {
               verif = 0;
               error = "Chosen row does not belong to the enemy.";
            } else if (card.getName().equals("Heart Hound")) {
                final int rowFull = 5;
                final int row = 3;
                if ((affectedRow == 2 && table.getCardsOnTable().get(1).size() == rowFull)
                     || (affectedRow == row && table.getCardsOnTable().get(0).size() == rowFull)) {
                    verif = 0;
                    error = "Cannot steal enemy card since the player's row is full.";
                }
            }
        }
        if (verif == 0) {
            node.put("error", error);
            output.add(node);
            return 0;
        }
        return 1;
    }

    /**
     * method for printing the player's mana
     * @param indx of the player
     * @param players all the players
     * @return ObjectNode for output
     */
    public ObjectNode getPlayerMana(final int indx, final Player players) {
        PlayerOne plyrOne = players.getPlayerOne();
        PlayerTwo plyrTwo = players.getPlayerTwo();
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.put("command", "getPlayerMana");
        if (indx == 1) {
            node.put("output", plyrOne.getMana());
        } else {
            node.put("output", plyrTwo.getMana());
        }

        node.put("playerIdx", indx);

        return node;
    }

    /**
     * method for printing the player's hero
     * @param indx of the player
     * @param players all the players
     * @return ObjectNode for the output
     */
    public ObjectNode getPlayerHero(final int indx, final  Player players) {
        PlayerOne plyrOne = players.getPlayerOne();
        PlayerTwo plyrTwo = players.getPlayerTwo();
        ObjectNode node = JsonNodeFactory.instance.objectNode();

        node.put("command", "getPlayerHero");
        node.put("playerIdx", indx);
        if (indx == 1) {
            node.set("output", makeObjectFromHero(plyrOne.getHero()));
        } else {
            node.set("output", makeObjectFromHero(plyrTwo.getHero()));
        }
        return node;
    }

    /**
     * method for printing the player's turn
     * @param players all the players
     * @return ObjectNode for ouput
     */
    public ObjectNode getPlayerTurn(final Player players) {
        PlayerOne plyrOne = players.getPlayerOne();
        PlayerTwo plyrTwo = players.getPlayerTwo();
        ObjectNode node = JsonNodeFactory.instance.objectNode();

        node.put("command", "getPlayerTurn");
        if (plyrOne.getTurn()) {
            node.put("output", 1);
        } else if (plyrTwo.getTurn()) {
            node.put("output", 2);
        }
        return node;
    }

    /**
     * method for printing all the cards on the table
     * @param table the cards on the table
     * @return ObjectNode for output
     */
    public ObjectNode getcardsOnTable(final Table table) {
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.put("command", "getCardsOnTable");
        final int thirdIndx = 3;
        ArrayNode cardsOnTable = JsonNodeFactory.instance.arrayNode();
        ArrayNode zeroRow = makeArrayFromDeck(table.getCardsOnTable().get(0));
        ArrayNode firstRow = makeArrayFromDeck(table.getCardsOnTable().get(1));
        ArrayNode secondRow = makeArrayFromDeck(table.getCardsOnTable().get(2));
        ArrayNode thirdRow = makeArrayFromDeck(table.getCardsOnTable().get(thirdIndx));

        cardsOnTable.add(zeroRow);
        cardsOnTable.add(firstRow);
        cardsOnTable.add(secondRow);
        cardsOnTable.add(thirdRow);

        node.set("output", cardsOnTable);
        return node;
    }

    /**
     * printing the environment cards in the player's hand
     * @param indx of the player
     * @param players all the players
     * @return ObjectNode for output
     */
    public ObjectNode getEnvCardsInHand(final int indx, final  Player players) {
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        ArrayNode cardsArr;
        ArrayList<Card> cardsEnv = new ArrayList<Card>();

        node.put("command", "getEnvironmentCardsInHand");
        node.put("playerIdx", indx);

        if (indx == 1) {
            for (Card card : players.getPlayerOne().getCardsInHand().getHandCards()) {
                if (card.getType() == 2) {
                    cardsEnv.add(card);
                }
            }
            cardsArr = makeArrayFromDeck(cardsEnv);
        } else {
            for (Card card : players.getPlayerTwo().getCardsInHand().getHandCards()) {
                if (card.getType() == 2) {
                    cardsEnv.add(card);
                }
            }
            cardsArr = makeArrayFromDeck(cardsEnv);
        }
        node.set("output", cardsArr);
        return node;
    }

    /**
     * printing the errors for "getCardPosition" command
     * @param table all the cards on the table
     * @param x coordinate of the card
     * @param y coordinate of the card
     * @return ObjectNode for output
     */
    public ObjectNode getCardPosition(final Table table, final int x, final int y) {
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        ArrayNode card = JsonNodeFactory.instance.arrayNode();

        node.put("command", "getCardAtPosition");
        node.put("x", x);
        node.put("y", y);

        if (x >= table.getCardsOnTable().size()) {
            node.put("output", "No card available at that position.");
            return node;
        } else if (y >= table.getCardsOnTable().get(x).size()) {
            node.put("output", "No card available at that position.");
            return node;
        } else {
            card.add(makeObjectFromCard(table.getCardsOnTable().get(x).get(y)));
            node.set("output", makeObjectFromCard(table.getCardsOnTable().get(x).get(y)));
        }
        return node;
    }

    /**
     * printing the frozen cards
     * @param table all the cards on the table
     * @return ObjectNode for output
     */
    public ObjectNode getFrozenCards(final Table table) {
        ArrayNode frozencards = JsonNodeFactory.instance.arrayNode();
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.put("command", "getFrozenCardsOnTable");

        for (int i = 0; i < table.getCardsOnTable().size(); i++) {
            for (int j = 0; j < table.getCardsOnTable().get(i).size(); j++) {
                if (table.getCardsOnTable().get(i).get(j).getFrozen()) {
                    frozencards.add(makeObjectFromCard(table.getCardsOnTable().get(i).get(j)));
                }
            }
        }
        node.set("output", frozencards);
        return node;
    }

    /**
     * method for printing the beginning of the output
     * for "verifCardUsesAttack" and "verifCardUsesAbility"
     * @param myX coordinate of the current player's card
     * @param myY  coordinate of the current player's card
     * @param x coordinate of the attacked card
     * @param y coordinate of the attacked card
     * @param command the name of the command
     * @return ObjecteNode for output
     */

    public ObjectNode verifCardAttackPrint(final int myX, final int myY,
                                           final int x, final int y, final String command) {
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        ObjectNode coord = JsonNodeFactory.instance.objectNode();
        ObjectNode coordAt = JsonNodeFactory.instance.objectNode();


        node.put("command", command);
        coord.put("x", myX);
        coord.put("y", myY);
        node.set("cardAttacker", coord);
        coordAt.put("x", x);
        coordAt.put("y", y);
        node.set("cardAttacked", coordAt);

        return node;
    }

    /**
     * Verifying and printing the errors in the attack command
     * @param myX coordinate of the current player's card
     * @param myY  coordinate of the current player's card
     * @param x coordinate of the attacked card
     * @param y coordinate of the attacked card
     * @param table all the cards on the table
     * @param output from Main
     * @return 1 if there aren't errors, 0 otherwise
     */
    public int verifCardUsesAttack(final int myX, final int myY, final int x, final int y,
                                   final Table table, final ArrayNode output) {
        Card myCard = table.getCardsOnTable().get(myX).get(myY);
        Card attackedCard = table.getCardsOnTable().get(x).get(y);
        int verif = 1;
        String error = "nothing";
        ObjectNode node;

        if ((myX < 2 && x < 2) || (myX >= 2 && x >= 2)) {
            verif = 0;
            error = "Attacked card does not belong to the enemy.";
        } else if (myCard.getHasAttacked()) {
            verif = 0;
            error = "Attacker card has already attacked this turn.";
        } else if (myCard.getFrozen()) {
            verif = 0;
            error = "Attacker card is frozen.";
        } else {
            if (myX < 2) {
                if (!attackedCard.isTank()) {
                    for (Card card : table.getCardsOnTable().get(2)) {
                        if (card.isTank()) {
                            verif = 0;
                            error = "Attacked card is not of type 'Tank'.";
                            break;
                        }
                    }
                }
            } else {
                if (!attackedCard.isTank()) {
                    for (Card card : table.getCardsOnTable().get(1)) {
                        if (card.isTank()) {
                            verif = 0;
                            error = "Attacked card is not of type 'Tank'.";
                            break;
                        }
                    }
                }
            }
        }
        if (verif == 0) {
            node = verifCardAttackPrint(myX, myY, x, y, "cardUsesAttack");
            node.put("error", error);
            output.add(node);
            return 0;
        }
        return 1;
    }

    /**
     * verifying and printing the errors in the "using ability of a card" command
     * @param myX coordinate of the current player's card
     * @param myY  coordinate of the current player's card
     * @param x coordinate of the attacked card
     * @param y coordinate of the attacked card
     * @param table all the cards on the table
     * @param output from Main
     * @return 1 if there aren't errors, 0 otherwise
     */
    public int verifCardUsesAbility(final int myX, final int myY, final int x, final int y,
                                     final Table table, final ArrayNode output) {
        Card myCard = table.getCardsOnTable().get(myX).get(myY);
        Card attackedCard = table.getCardsOnTable().get(x).get(y);
        ObjectNode node;
        int verif = 1;
        String error = "nothing";

        if (myCard.getFrozen()) {
            verif = 0;
            error = "Attacker card is frozen.";
        } else if (myCard.getHasAttacked()) {
            verif = 0;
            error = "Attacker card has already attacked this turn.";
        } else if (myCard.getName().equals("Disciple")) {
            if ((x < 2 && myX >= 2) || (x >= 2 && myX < 2)) {
                error = "Attacked card does not belong to the current player.";
                verif = 0;
            }
        } else if ((x < 2 && myX < 2) || (x >= 2 && myX >= 2)) {
            error = "Attacked card does not belong to the enemy.";
            verif = 0;
        } else {
            if (myX < 2) {
                if (!attackedCard.isTank()) {
                    for (Card card : table.getCardsOnTable().get(2)) {
                        if (card.isTank()) {
                            error = "Attacked card is not of type 'Tank'.";
                            verif = 0;
                            break;
                        }
                    }
                }
            } else {
                if (!attackedCard.isTank()) {
                    for (Card card : table.getCardsOnTable().get(1)) {
                        if (card.isTank()) {
                            error = "Attacked card is not of type 'Tank'.";
                            verif = 0;
                            break;
                        }
                    }
                }
            }
        }
        if (verif == 0) {
            node = verifCardAttackPrint(myX, myY, x, y, "cardUsesAbility");
            node.put("error", error);
            output.add(node);
            return 0;
        }
        return 1;
    }

    /**
     * printing the errors for "useAttackHero" command
     * @param x coordinate of the card that is attacking the hero
     * @param y coordinate of the card that is attacking the hero
     * @param table all the cards on the table
     * @param output arrayNode from Main
     * @return 1 if there aren't errors, 0 otherwise
     */
    public int verifUseAttackHero(final int x, final int y,
                                   final Table table, final ArrayNode output) {
        Card myCard = table.getCardsOnTable().get(x).get(y);

        int verif = 1;
        String error = "nothing";
        if (myCard.getHasAttacked()) {
            verif = 0;
            error = "Attacker card has already attacked this turn.";
        } else if (myCard.getFrozen()) {
            verif = 0;
            error = "Attacker card is frozen.";
        } else {
            if (x < 2) {
                for (Card card : table.getCardsOnTable().get(2)) {
                    if (card.isTank()) {
                        verif = 0;
                        error = "Attacked card is not of type 'Tank'.";
                        break;
                    }
                }
            } else {
                for (Card card : table.getCardsOnTable().get(1)) {
                    if (card.isTank()) {
                        verif = 0;
                        error = "Attacked card is not of type 'Tank'.";
                        break;
                    }
                }
            }
        }
        if (verif == 0) {
            ObjectNode node = JsonNodeFactory.instance.objectNode();
            ObjectNode coord = JsonNodeFactory.instance.objectNode();
            node.put("command", "useAttackHero");
            coord.put("x", x);
            coord.put("y", y);
            node.set("cardAttacker", coord);
            node.put("error", error);
            output.add(node);
            return 0;
        }
        return 1;
    }

    /**
     * printing the messages if the hero was killed
     * @param x coordinate of the card that is attacking the hero
     * @param death contains 1, if the hero is dead, 0, otherwise
     * @param statistics the statistics of the games
     * @param output arrayNode from Main
     */
    public void verifDeathHero(final int x, final int death,
                               final GameStatistics statistics, final ArrayNode output) {
        if (death == 1) {
            ObjectNode node = JsonNodeFactory.instance.objectNode();
            if (x < 2) {
                node.put("gameEnded", "Player two killed the enemy hero.");
                output.add(node);
                statistics.incrNumberOfWonGamesTwo();
            } else {
                node.put("gameEnded", "Player one killed the enemy hero.");
                output.add(node);
                statistics.incrNumberOfWonGamesOne();
            }
        }
    }

    /**
     * verifying the "useHeroAbility" command and printing the errors
     * @param indx of the player
     * @param players all the players
     * @param affectedRow the row that was attacked
     * @param output arrayNode from Main
     * @return 1 if there aren't errors, 0 otherwise
     */
    public int verifUseHeroAbility(final int indx, final Player players,
                                    final int affectedRow, final ArrayNode output) {
        PlayerOne plyrOne = players.getPlayerOne();
        PlayerTwo plyrTwo = players.getPlayerTwo();

        int verif = 1;
        String error = "nothing";
        ObjectNode node = JsonNodeFactory.instance.objectNode();

        node.put("command", "useHeroAbility");
        node.put("affectedRow", affectedRow);
        if (indx == 1) {
            if (plyrOne.getMana() < plyrOne.getHero().getMana()) {
                verif = 0;
                error = "Not enough mana to use hero's ability.";
            } else if (plyrOne.getHero().isHasAttacked()) {
                verif = 0;
                error = "Hero has already attacked this turn.";
            } else if (plyrOne.getHero().getName().equals("Lord Royce")
                    || plyrOne.getHero().getName().equals("Empress Thorina")) {
                if (affectedRow >= 2) {
                    verif = 0;
                    error = "Selected row does not belong to the enemy.";
                }
            } else {
                if (affectedRow < 2) {
                    verif = 0;
                    error = "Selected row does not belong to the current player.";
                }
            }
        } else {
            if (plyrTwo.getMana() < plyrTwo.getHero().getMana()) {
                verif = 0;
                error = "Not enough mana to use hero's ability.";
            } else if (plyrTwo.getHero().isHasAttacked()) {
                verif = 0;
                error = "Hero has already attacked this turn.";
            } else if (plyrTwo.getHero().getName().equals("Lord Royce")
                    || plyrTwo.getHero().getName().equals("Empress Thorina")) {
                if (affectedRow < 2) {
                    verif = 0;
                    error = "Selected row does not belong to the enemy.";
                }
            } else {
                if (affectedRow >= 2) {
                    verif = 0;
                    error = "Selected row does not belong to the current player.";
                }
            }
        }
        if (verif == 0) {
            node.put("error", error);
            output.add(node);
            return 0;
        }
        return 1;
    }

    /**
     * printing the games won by the players
     * @param statistics game statistics
     * @param indx of the player
     * @return objectNode for output
     */
    public ObjectNode printWonGames(final GameStatistics statistics, final int indx) {
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        if (indx == 1) {
            node.put("command", "getPlayerOneWins");
            node.put("output", statistics.getNumberOfWonGamesOne());
        } else {
            node.put("command",  "getPlayerTwoWins");
            node.put("output", statistics.getNumberOfWonGamesTwo());
        }
        return node;
    }
}
