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

    public ObjectNode getPlayerDeck (int indx, Player players, String command) {
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

    public ObjectNode getCardsInHand (int indx, Player players, String command) {
        PlayerOne plyrOne = new PlayerOne();
        plyrOne = players.getPlayerOne();
        PlayerTwo plyrTwo = new PlayerTwo();
        plyrTwo = players.getPlayerTwo();
        ObjectNode node = JsonNodeFactory.instance.objectNode();
        ArrayNode deckArr = JsonNodeFactory.instance.arrayNode();

        node.put("command", command);
        node.put("playerIdx", indx);

        if (indx == 1) {
            deckArr = makeArrayFromDeck(plyrOne.getCardsInHand().getHandCards());
            node.set("output", deckArr);
        } else {
            deckArr = makeArrayFromDeck(plyrTwo.getCardsInHand().getHandCards());
            node.set("output", deckArr);
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
            if(card.getMana() > plyrOne.getMana()) {
                node.put("error", "Not enough mana to place card on table.");
                node.put("nume carte", card.getName());
                node.put("MANA CARTE", card.getMana());
                node.put("mana player", plyrOne.getMana());
                output.add(node);
                return 0;
            }
            if (table.getSecondRow().size() == 5 || table.getThirdRow().size() == 5) {
                node.put("error", "Cannot place card on table since row is full.");
                output.add(node);
                return 0;
            }
        } else if (indxPlyr == 2) {
            if (card.getMana() > plyrTwo.getMana()) {
                node.put("error", "Not enough mana to place card on table.");
                node.put("nume carte", card.getName());
                node.put("MANA CARTE", card.getMana());
                node.put("mana player", plyrTwo.getMana());
                output.add(node);
                return 0;
            }
            if (table.getZeroRow().size() == 5 || table.getFirstRow().size() == 5) {
                node.put("error", "Cannot place card on table since row is full.");
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
        node.put("handIdx", indx);

        if (indx == 1)
            node.put("output", plyrOne.getMana());
        else
            node.put("output", plyrTwo.getMana());

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
}
