package main;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.*;

import java.util.ArrayList;
import java.util.*;

import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.Random;

public class MyMain {
    public static void main(Input input, ArrayNode output, ObjectMapper objectMapper) {


        // starting game
        GameStatistics statistics = new GameStatistics();

        ArrayList<GameInput> games = new ArrayList<GameInput>();
        games = input.getGames();
        statistics.setNumberOfWonGamesOne(0);
        statistics.setNumberOfWonGamesOne(0);

        for (GameInput game : games) {
            GameInitialize initGame = new GameInitialize();
            PlayerOne plyrOne = new PlayerOne();
            PlayerTwo plyrTwo = new PlayerTwo();
            Player players = new Player();

            // init Cards
            DecksForPlayer groupDecksPlyrOne = new DecksForPlayer();
            DecksForPlayer groupDecksPlyrTwo = new DecksForPlayer();
            groupDecksPlyrOne = initGame.initDecksForPLayer(input.getPlayerOneDecks());
            groupDecksPlyrTwo = initGame.initDecksForPLayer(input.getPlayerTwoDecks());
            plyrOne.getDecks();
            plyrTwo.getDecks();

            plyrOne.setDecks(groupDecksPlyrOne);
            plyrTwo.setDecks(groupDecksPlyrTwo);

            statistics.incrNumberOfGames();
            statistics.setNumberOfRounds(1);
            statistics.setNumberOfTurns(0);
            statistics.setHasFinished(false);

            FrozenCards frozenCards = new FrozenCards();
            Table table = new Table();
            table.getCardsOnTable();
            Abilities abilities = new Abilities();

            StartGameInput startGame = game.getStartGame();
            plyrOne.setIndxOfDeck(startGame.getPlayerOneDeckIdx());
            plyrTwo.setIndxOfDeck(startGame.getPlayerTwoDeckIdx());
            plyrOne.verif_isTurn(startGame.getStartingPlayer());
            plyrTwo.verif_isTurn(startGame.getStartingPlayer());

            // setting the heros
            Hero heroForOne = new Hero();
            Hero heroForTwo = new Hero();
            initGame.initHero(startGame.getPlayerOneHero(), heroForOne);
            initGame.initHero(startGame.getPlayerTwoHero(), heroForTwo);

            // setting the players
            plyrOne.setHero(heroForOne);
            plyrTwo.setHero(heroForTwo);
            players.getPlayerOne();
            players.getPlayerTwo();
            players.setPlayerOne(plyrOne);
            players.setPlayerTwo(plyrTwo);


            // setting the chosen decks
            int shuffleseed = startGame.getShuffleSeed();

            Hand cardsInHandOne = new Hand();
            cardsInHandOne.getHandCards();
            Hand cardsInHandTwo = new Hand();
            cardsInHandTwo.getHandCards();

            ChosenDeck chosenDeckForOne = new ChosenDeck();
            ChosenDeck chosenDeckForTwo = new ChosenDeck();
            chosenDeckForOne.createChosenDeck(plyrOne.getIndxOfDeck(), plyrOne.getDecks());
            chosenDeckForTwo.createChosenDeck(plyrTwo.getIndxOfDeck(), plyrTwo.getDecks());
            initGame.initPlayerDeck(chosenDeckForOne, cardsInHandOne, shuffleseed);
            initGame.initPlayerDeck(chosenDeckForTwo, cardsInHandTwo, shuffleseed);

            //pointing out the used decks and adding them to players
            plyrOne.getDecks().getDecksForPlayer().get(plyrOne.getIndxOfDeck()).setWasUsed(true);
            plyrTwo.getDecks().getDecksForPlayer().get(plyrOne.getIndxOfDeck()).setWasUsed(true);

            plyrOne.getPlayerChosenDeck();
            plyrTwo.getPlayerChosenDeck();
            plyrOne.setPlayerChosenDeck(chosenDeckForOne);
            plyrTwo.setPlayerChosenDeck(chosenDeckForTwo);
            plyrOne.setCardsInHand(cardsInHandOne);
            plyrTwo.setCardsInHand(cardsInHandTwo);

            ArrayList<ActionsInput> actions = new ArrayList<ActionsInput>();
            actions = game.getActions();
            MyPrinter printer = new MyPrinter();
            for (ActionsInput action : actions) {
                switch (action.getCommand()) {
                    case "getPlayerDeck":
                        int indx = action.getPlayerIdx();
                        output.add(printer.getPlayerDeck(indx, players, "getPlayerDeck"));
                        break;

                    case "getPlayerHero":
                        indx = action.getPlayerIdx();
                        output.add(printer.getPlayerHero(indx, players, "getPlayerHero"));
                        break;
                    case "getPlayerTurn":
                        output.add(printer.getPlayerTurn(players, "getPlayerTurn"));
                        break;
                    case "endPlayerTurn":
                        if (!statistics.isHasFinished()) {
                            statistics.incrNumberOfTurns();
                            // la sfarsitul rundei cresc mana
                            if (statistics.getNumberOfTurns() == 2) {
                                if (plyrOne.getTurn() == true) {
                                    plyrOne.setTurn(false);
                                    plyrTwo.setTurn(true);
                                    plyrOne.unfreezeCards(table);
                                    plyrOne.setAttackers(table);
                                    plyrOne.getHero().setHasAttacked(false);
                                } else {
                                    plyrOne.setTurn(true);
                                    plyrTwo.setTurn(false);
                                    plyrTwo.unfreezeCards(table);
                                    plyrTwo.setAttackers(table);
                                    plyrTwo.getHero().setHasAttacked(false);
                                }
                                statistics.incrNumberOfRounds();
                                if (statistics.getNumberOfRounds() <= 10) {
                                    plyrOne.increaseMana(statistics.getNumberOfRounds());
                                    plyrTwo.increaseMana(statistics.getNumberOfRounds());
                                }
                                plyrOne.addCardsInHand();
                                plyrTwo.addCardsInHand();
                                statistics.setNumberOfTurns(0);
                            } else {
                                // verific a cui a fost tura, dezghet cartile
                                if (plyrOne.getTurn() == true) {
                                    plyrOne.setTurn(false);
                                    plyrTwo.setTurn(true);
                                    plyrOne.unfreezeCards(table);
                                    plyrOne.setAttackers(table);
                                    plyrOne.getHero().setHasAttacked(false);

                                } else if (plyrTwo.getTurn() == true) {
                                    plyrOne.setTurn(true);
                                    plyrTwo.setTurn(false);
                                    plyrTwo.unfreezeCards(table);
                                    plyrTwo.setAttackers(table);
                                    plyrTwo.getHero().setHasAttacked(false);
                                }
                            }
                        }
                        break;
                    case "getCardsInHand":
                        indx = action.getPlayerIdx();
                        output.add(printer.getCardsInHand(indx, players,
                                "getCardsInHand"));
                        break;
                    case "getPlayerMana":
                        indx = action.getPlayerIdx();
                        output.add(printer.getPlayerMana(indx, players, "getPlayerMana"));
                        break;
                    case "placeCard":
                        indx = action.getHandIdx();
                        if (plyrOne.getTurn() == true) {
                            ArrayList<Card> hand = plyrOne.getCardsInHand().getHandCards();
                            Card card = hand.get(indx);
                            int verif = printer.verifErorrsPlaceCard(card, indx,
                                    "placeCard", players, 1, output, table);
                            if (verif == 1) {
                                table.placeCard(card, 1, players, indx);
                            }
                        } else if (plyrTwo.getTurn() == true) {
                            ArrayList<Card> hand = plyrTwo.getCardsInHand().getHandCards();
                            Card card = plyrTwo.getCardsInHand().getHandCards().get(indx);
                            int verif = printer.verifErorrsPlaceCard(card, indx,
                                    "placeCard", players, 2, output, table);
                            if (verif == 1) {
                                table.placeCard(card, 2, players, indx);
                            }
                        }
                        break;
                    case "getCardsOnTable":
                        output.add(printer.getcardsOnTable(table, "getCardsOnTable"));
                        break;
                    case "getEnvironmentCardsInHand":
                        indx = action.getPlayerIdx();
                        output.add(printer.getEnvCardsInHand(indx, players,
                                "getEnvironmentCardsInHand"));
                        break;
                    case "useEnvironmentCard":
                        int indxCard = action.getHandIdx();
                        int affectedRow = action.getAffectedRow();
                        if (plyrOne.getTurn() == true) {
                            Card card = plyrOne.getCardsInHand().getHandCards().get(indxCard);
                            int verif = printer.verifErrorsUseEnvCard(indxCard, affectedRow, players,
                                    table, 1, output, "useEnvironmentCard");
                            if (verif == 1) {
                                abilities.useAbilitiesForEnvCard(1, indxCard, players, card,
                                        table, affectedRow);
                            }
                        } else {
                            Card card = plyrTwo.getCardsInHand().getHandCards().get(indxCard);
                            int verif = printer.verifErrorsUseEnvCard(indxCard, affectedRow, players,
                                    table, 2, output, "useEnvironmentCard");
                            if (verif == 1) {
                                abilities.useAbilitiesForEnvCard(2, indxCard, players, card,
                                        table, affectedRow);
                            }
                        }
                        break;
                    case "getCardAtPosition":
                        int x = action.getX();
                        int y = action.getY();
                        output.add(printer.getCardPosition(table, x, y));
                        break;
                    case "getFrozenCardsOnTable":
                        output.add(printer.getFrozenCards(table));
                        break;
                    case "cardUsesAttack":
                        int myX = action.getCardAttacker().getX();
                        int myY = action.getCardAttacker().getY();
                        x = action.getCardAttacked().getX();
                        y = action.getCardAttacked().getY();
                        int verif = printer.verifCardUsesAttack(myX, myY, x, y, table, output);
                        if (verif == 1)
                            abilities.useAttack(myX, myY, x, y, table);
                        break;
                    case "cardUsesAbility":
                        myX = action.getCardAttacker().getX();
                        myY = action.getCardAttacker().getY();
                        x = action.getCardAttacked().getX();
                        y = action.getCardAttacked().getY();
                        verif = printer.verifCardUsesAbility(myX, myY, x, y, table, output);
                        if (verif == 1)
                            abilities.useAbilitiesForMinionCard(myX, myY, x, y, table);
                        break;
                    case "useAttackHero":
                        x = action.getCardAttacker().getX();
                        y = action.getCardAttacker().getY();
                        verif = printer.verifUseAttackHero(x, y, table, output);
                        if (verif == 1) {
                            int death = abilities.useAttackHero(x, y, table, players);
                            printer.verifDeathHero(x, death, statistics, output);
                            if (death == 0) {
                                statistics.setHasFinished(true);
                            }
                        }
                        break;
                    case "useHeroAbility":
                        affectedRow = action.getAffectedRow();
                        if (plyrOne.getTurn()) {
                            verif = printer.verifUseHeroAbility(1, players, affectedRow,output);
                            if (verif == 1)
                                abilities.useAbilityHero(affectedRow, 1, players, table);
                        } else {
                            verif = printer.verifUseHeroAbility(2, players, affectedRow,output);
                            if (verif == 1)
                                abilities.useAbilityHero(affectedRow, 2, players, table);
                        }
                        break;
                    case "getPlayerOneWins":
                        output.add(printer.printWonGames(statistics, 1, "getPlayerOneWins"));
                        break;
                    case "getPlayerTwoWins":
                        output.add(printer.printWonGames(statistics, 2, "getPlayerTwoWins"));
                        break;
                    case "getTotalGamesPlayed":
                        ObjectNode node = JsonNodeFactory.instance.objectNode();
                        node.put("command", "getTotalGamesPlayed");
                        node.put("output", statistics.getNumberOfWonGamesOne() + statistics.getNumberOfWonGamesTwo());
                        output.add(node);
                        break;
                }
            }
        }
    }
}
