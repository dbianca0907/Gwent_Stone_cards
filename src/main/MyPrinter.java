package main;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.*;

public class MyPrinter {

    public ObjectNode makeObjectFromCard(Card card) {
        ObjectNode cardNode = JsonNodeFactory.instance.objectNode();
        ArrayNode nodeForColors = JsonNodeFactory.instance.arrayNode();

        if (card.getType() == 1) {
            cardNode.put("mana", card.getMana());
            cardNode.put("attackDamage", card.getAttackDamage());
            cardNode.put("health", card.getHealth());
            cardNode.put("description", card.getDescription());

            for (String color : card.getColors())
                nodeForColors.add(color);
            cardNode.set("colors", nodeForColors);
            cardNode.put("name", card.getName());

        }
        if (card.getType() == 2) {
            cardNode.put("mana", card.getMana());
            cardNode.put("description", card.getDescription());
            for (String color : card.getColors())
                nodeForColors.add(color);
            cardNode.set("colors", nodeForColors);
            cardNode.put("name", card.getName());
        }
        return cardNode;
    }

    public ArrayNode makeArrayFromDeck(Deck deck) {
        ArrayNode deckNode = JsonNodeFactory.instance.arrayNode();

        for (int i = 0; i < deck.getCards().size(); i++) {
            deckNode.add(makeObjectFromCard(deck.getCards().get(i)));
        }
        return deckNode;
    }

    public ArrayNode makeArrayFromArrayOfCards(ArrayList<Card> cards) {
        ArrayNode cardsNode = JsonNodeFactory.instance.arrayNode();

        for (int i = 0; i < cards.size(); i++) {
            cardsNode.add(makeObjectFromCard(cards.get(i)));
        }
        return cardsNode;
    }

    public ObjectNode makeObjectFromHero(Hero hero) {
        ObjectNode heroNode = JsonNodeFactory.instance.objectNode();
        ArrayNode nodeForColors = JsonNodeFactory.instance.arrayNode();

        heroNode.put("mana", hero.getMana());
        heroNode.put("description", hero.getDescription());
        for (String color : hero.getColors())
            nodeForColors.add(color);
        heroNode.set("colors", nodeForColors);
        heroNode.put("name", hero.getName());
        heroNode.put("health", hero.getHealth());
        return heroNode;
    }

    public ObjectNode getPlayerDeck(int indx, Player players, String command) {
        PlayerOne plyrOne = new PlayerOne();
        plyrOne = players.getPlayerOne();
        PlayerTwo plyrTwo = new PlayerTwo();
        plyrTwo = players.getPlayerTwo();
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        ArrayNode deckArr = JsonNodeFactory.instance.arrayNode();

        node.put("command", command);
        node.put("playerIdx", indx);

        if (indx == 1) {
            deckArr = makeArrayFromDeck(plyrOne.getPlayerChosenDeck().getChosenDeck());
            node.set("output", deckArr);
        } else {
            deckArr = makeArrayFromDeck(plyrTwo.getPlayerChosenDeck().getChosenDeck());
            node.set("output", deckArr);
        }
        return node;
    }

    public ObjectNode getCardsInHand(int indx, Player players, String command) {
        PlayerOne plyrOne = new PlayerOne();
        plyrOne = players.getPlayerOne();
        PlayerTwo plyrTwo = new PlayerTwo();
        plyrTwo = players.getPlayerTwo();
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        ArrayNode cardsArr = JsonNodeFactory.instance.arrayNode();

        node.put("command", command);
        node.put("playerIdx", indx);

        if (indx == 1) {
            cardsArr = makeArrayFromArrayOfCards(plyrOne.getCardsInHand().getHandCards());
            node.set("output", cardsArr);
        } else {
            cardsArr = makeArrayFromArrayOfCards(plyrTwo.getCardsInHand().getHandCards());
            node.set("output", cardsArr);
        }
        return node;
    }

    // TASK: de lasat doar plyrOne / plyrTwo, nu cred ca va fi nevoie de players
    public int verifErorrsPlaceCard(Card card, int handIndx, String command, Player players,
                                    int indxPlyr, ArrayNode output, Table table) {
        PlayerOne plyrOne = new PlayerOne();
        plyrOne = players.getPlayerOne();
        PlayerTwo plyrTwo = new PlayerTwo();
        plyrTwo = players.getPlayerTwo();
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.put("command", command);
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
            if (table.getCardsOnTable().get(2).size() == 5 || table.getCardsOnTable().get(3).size() == 5) {
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
            if (table.getCardsOnTable().get(0).size() == 5 || table.getCardsOnTable().get(1).size() == 5) {
                node.put("error", "Cannot place card on table since row is full.");
                output.add(node);
                return 0;
            }
        }
        return 1;
    }

    public int verifErrorsUseEnvCard(int handIndx, int affectedRow, Player players,
                                     Table table, int indxPlyr, ArrayNode output, String command) {
        PlayerOne plyrOne = new PlayerOne();
        plyrOne = players.getPlayerOne();
        PlayerTwo plyrTwo = new PlayerTwo();
        plyrTwo = players.getPlayerTwo();
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.put("command", command);
        node.put("handIdx", handIndx);
        node.put("affectedRow", affectedRow);

        if (indxPlyr == 1) {
            Card card = plyrOne.getCardsInHand().getHandCards().get(handIndx);

            if (card.getType() != 2) {
                node.put("error", "Chosen card is not of type environment.");
                output.add(node);
                return 0;
            } else if (plyrOne.getMana() < card.getMana()) {
                node.put("error", "Not enough mana to use environment card.");
                output.add(node);
                return 0;
            } else if (affectedRow >= 2) {
                node.put("error", "Chosen row does not belong to the enemy.");
                output.add(node);
                return 0;
            } else if (card.getName().equals("Heart Hound"))
                if ((affectedRow == 0 && table.getCardsOnTable().get(3).size() == 5) ||
                        (affectedRow == 1 && table.getCardsOnTable().get(2).size() == 5)) {
                    node.put("error", "Cannot steal enemy card since the player's row is full.");
                    output.add(node);
                    return 0;
                }
        } else {
            Card card = plyrTwo.getCardsInHand().getHandCards().get(handIndx);
            if (card.getType() != 2) {
                node.put("error", "Chosen card is not of type environment.");
                output.add(node);
                return 0;
            } else if (plyrTwo.getMana() < card.getMana()) {
                node.put("error", "Not enough mana to use environment card.");
                output.add(node);
                return 0;
            } else if (affectedRow <= 1) {
                node.put("error", "Chosen row does not belong to the enemy.");
                output.add(node);
                return 0;
            } else if (card.getName().equals("Heart Hound"))
                if ((affectedRow == 2 && table.getCardsOnTable().get(1).size() == 5) ||
                        (affectedRow == 3 && table.getCardsOnTable().get(0).size() == 5)) {
                    node.put("error", "Cannot steal enemy card since the player's row is full.");
                    output.add(node);
                    return 0;
                }
        }
        return 1;
    }


    public ObjectNode getPlayerMana(int indx, Player players, String command) {
        PlayerOne plyrOne = new PlayerOne();
        plyrOne = players.getPlayerOne();
        PlayerTwo plyrTwo = new PlayerTwo();
        plyrTwo = players.getPlayerTwo();
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.put("command", command);
        if (indx == 1)
            node.put("output", plyrOne.getMana());
        else
            node.put("output", plyrTwo.getMana());

        node.put("playerIdx", indx);

        return node;
    }

    public ObjectNode getPlayerHero(int indx, Player players, String command) {
        PlayerOne plyrOne = new PlayerOne();
        plyrOne = players.getPlayerOne();
        PlayerTwo plyrTwo = new PlayerTwo();
        plyrTwo = players.getPlayerTwo();
        ObjectNode node = JsonNodeFactory.instance.objectNode();

        node.put("command", command);
        node.put("playerIdx", indx);
        if (indx == 1) {
            node.set("output", makeObjectFromHero(plyrOne.getHero()));
        } else {
            node.set("output", makeObjectFromHero(plyrTwo.getHero()));
        }
        return node;
    }

    public ObjectNode getPlayerTurn(Player players, String command) {
        PlayerOne plyrOne = new PlayerOne();
        plyrOne = players.getPlayerOne();
        PlayerTwo plyrTwo = new PlayerTwo();
        plyrTwo = players.getPlayerTwo();
        ObjectNode node = JsonNodeFactory.instance.objectNode();

        node.put("command", command);
        if (plyrOne.getTurn() == true)
            node.put("output", 1);
        else if (plyrTwo.getTurn() == true)
            node.put("output", 2);
        return node;
    }

    public ObjectNode getcardsOnTable(Table table, String command) {
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.put("command", command);
        ArrayNode Table = JsonNodeFactory.instance.arrayNode();
        ArrayNode Zero = JsonNodeFactory.instance.arrayNode();
        ArrayNode First = JsonNodeFactory.instance.arrayNode();
        ArrayNode Second = JsonNodeFactory.instance.arrayNode();
        ArrayNode Third = JsonNodeFactory.instance.arrayNode();

        Zero = makeArrayFromArrayOfCards(table.getCardsOnTable().get(0));
        First = makeArrayFromArrayOfCards(table.getCardsOnTable().get(1));
        Second = makeArrayFromArrayOfCards(table.getCardsOnTable().get(2));
        Third = makeArrayFromArrayOfCards(table.getCardsOnTable().get(3));

        Table.add(Zero);
        Table.add(First);
        Table.add(Second);
        Table.add(Third);

        node.set("output", Table);
        return node;
    }

    public ObjectNode getEnvCardsInHand(int indx, Player players, String command) {
        PlayerOne plyrOne = new PlayerOne();
        plyrOne = players.getPlayerOne();
        PlayerTwo plyrTwo = new PlayerTwo();
        plyrTwo = players.getPlayerTwo();
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        ArrayNode cardsArr = JsonNodeFactory.instance.arrayNode();
        ArrayList<Card> cardsEnv = new ArrayList<Card>();

        node.put("command", command);
        node.put("playerIdx", indx);

        if (indx == 1) {
            for (Card card : players.playerOne.getCardsInHand().getHandCards())
                if (card.type == 2)
                    cardsEnv.add(card);
            cardsArr = makeArrayFromArrayOfCards(cardsEnv);
        } else {
            for (Card card : players.playerTwo.getCardsInHand().getHandCards())
                if (card.type == 2)
                    cardsEnv.add(card);
            cardsArr = makeArrayFromArrayOfCards(cardsEnv);
        }
        node.set("output", cardsArr);
        return node;
    }

    public ObjectNode getCardPosition(Table table, int x, int y) {
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        ArrayNode card = JsonNodeFactory.instance.arrayNode();

        node.put("command", "getCardAtPosition");
        node.put("x", x);
        node.put("y", y);

        // de modificat
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

    public ObjectNode getFrozenCards(Table table) {
        ArrayNode frozencards = JsonNodeFactory.instance.arrayNode();
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.put("command", "getFrozenCardsOnTable");

        for (int i = 0; i < table.getCardsOnTable().size(); i++)
            for (int j = 0; j < table.getCardsOnTable().get(i).size(); j++) {
                if (table.getCardsOnTable().get(i).get(j).getFrozen() == true)
                    frozencards.add(makeObjectFromCard(table.getCardsOnTable().get(i).get(j)));
            }
        node.set("output", frozencards);
        return node;
    }

    public ObjectNode verifCardAttackPrint(int myX, int myY, int x, int y, String command) {
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

    public int verifCardUsesAttack(int myX, int myY, int x, int y,
                                   Table table, ArrayNode output) {
        Card myCard = table.getCardsOnTable().get(myX).get(myY);
        Card attackedCard = table.getCardsOnTable().get(x).get(y);
        int verif = 1;
        String error = "nothing";
        ObjectNode node = JsonNodeFactory.instance.objectNode();

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
                    for (Card card : table.cardsOnTable.get(2))
                        if (card.isTank()) {
                            verif = 0;
                            error = "Attacked card is not of type 'Tank'.";
                            break;
                        }
                }
            } else {
                if (!attackedCard.isTank()) {
                    for (Card card : table.cardsOnTable.get(1))
                        if (card.isTank()) {
                            verif = 0;
                            error = "Attacked card is not of type 'Tank'.";
                            break;
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
    public int verifCardUsesAbility (int myX, int myY, int x, int y,
                                     Table table, ArrayNode output) {
        Card myCard = table.getCardsOnTable().get(myX).get(myY);
        Card attackedCard = table.getCardsOnTable().get(x).get(y);
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        int verif = 1;
        String error = "nothing";

        if (myCard.getFrozen()) {
            verif = 0;
            error = "Attacker card is frozen.";
        }else if(myCard.getHasAttacked()) {
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
                    for (Card card : table.cardsOnTable.get(2))
                        if (card.isTank()) {
                            error = "Attacked card is not of type 'Tank'.";
                            verif = 0;
                            break;
                        }
                }
            } else {
                if (!attackedCard.isTank()) {
                    for (Card card : table.cardsOnTable.get(1))
                        if (card.isTank()) {
                            error = "Attacked card is not of type 'Tank'.";
                            verif = 0;
                            break;
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

    public int verifUseAttackHero (int x, int y, Table table, ArrayNode output) {
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
                for (Card card : table.cardsOnTable.get(2))
                    if (card.isTank()) {
                        verif = 0;
                        error = "Attacked card is not of type 'Tank'.";
                        break;
                    }
            } else {
                for (Card card : table.cardsOnTable.get(1))
                    if (card.isTank()) {
                        verif = 0;
                        error = "Attacked card is not of type 'Tank'.";
                        break;
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

    public void verifDeathHero (int x, int death,GameStatistics statistics, ArrayNode output) {
        if (death == 0) {
            ObjectNode node = JsonNodeFactory.instance.objectNode();
            if (x < 2) {
                node.put("gameEnded","Player two killed the enemy hero.");
                output.add(node);
                statistics.incrNumberOfWonGamesTwo();
            } else {
                node.put("gameEnded","Player one killed the enemy hero.");
                output.add(node);
                statistics.incrNumberOfWonGamesOne();
            }
        }
    }

    public int verifUseHeroAbility (int indx, Player players, int affectedRow, ArrayNode output) {
        PlayerOne plyrOne = new PlayerOne();
        plyrOne = players.getPlayerOne();
        PlayerTwo plyrTwo = new PlayerTwo();
        plyrTwo = players.getPlayerTwo();

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
            } else if (plyrOne.getHero().getName().equals("Lord Royce") ||
                        plyrOne.getHero().getName().equals("Empress Thorina")) {
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
            } else if (plyrTwo.getHero().getName().equals("Lord Royce") ||
                    plyrTwo.getHero().getName().equals("Empress Thorina")) {
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

    public ObjectNode printWonGames(GameStatistics statistics, int indx, String command) {
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        node.put("command", command);
        if (indx == 1) {
            node.put("output", statistics.getNumberOfWonGamesOne());
        } else {
            node.put("output", statistics.getNumberOfWonGamesTwo());
        }
        return node;
    }
}
