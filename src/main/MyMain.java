package main;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.GameInput;
import fileio.Input;
import fileio.StartGameInput;
import fileio.ActionsInput;

import java.util.ArrayList;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class MyMain {
    public static void main(final Input input, final ArrayNode output) {
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

            statistics.setNumberOfRounds(1);
            statistics.setNumberOfTurns(0);
            statistics.setHasFinished(false);

            Table table = new Table();
            table.getCardsOnTable();

            Abilities abilities = new Abilities();
            StartGameInput startGame = game.getStartGame();

            initGame.settingPlayers(plyrOne, plyrTwo, players, startGame, input);
            ArrayList<ActionsInput> actions = new ArrayList<ActionsInput>();
            actions = game.getActions();
            MyPrinter printer = new MyPrinter();
            for (ActionsInput action : actions) {
                switch (action.getCommand()) {
                    case "getPlayerDeck":
                        int indx = action.getPlayerIdx();
                        output.add(printer.getPlayerDeck(indx, players));
                        break;

                    case "getPlayerHero":
                        indx = action.getPlayerIdx();
                        output.add(printer.getPlayerHero(indx, players));
                        break;
                    case "getPlayerTurn":
                        output.add(printer.getPlayerTurn(players));
                        break;
                    case "endPlayerTurn":
                        if (!statistics.isHasFinished()) {
                            statistics.incrNumberOfTurns();
                            // final of the round
                            if (statistics.getNumberOfTurns() == 2) {
                                if (plyrOne.getTurn()) {
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
                                final int maxRoundOfMana = 10;
                                if (statistics.getNumberOfRounds() <= maxRoundOfMana) {
                                    plyrOne.increaseMana(statistics.getNumberOfRounds());
                                    plyrTwo.increaseMana(statistics.getNumberOfRounds());
                                }
                                plyrOne.addCardsInHand();
                                plyrTwo.addCardsInHand();
                                statistics.setNumberOfTurns(0);
                            } else {
                                // final of each player's turn
                                if (plyrOne.getTurn()) {
                                    plyrOne.setTurn(false);
                                    plyrTwo.setTurn(true);
                                    plyrOne.unfreezeCards(table);
                                    plyrOne.setAttackers(table);
                                    plyrOne.getHero().setHasAttacked(false);

                                } else if (plyrTwo.getTurn()) {
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
                        output.add(printer.getCardsInHand(indx, players));
                        break;
                    case "getPlayerMana":
                        indx = action.getPlayerIdx();
                        output.add(printer.getPlayerMana(indx, players));
                        break;
                    case "placeCard":
                        indx = action.getHandIdx();
                        if (plyrOne.getTurn()) {
                            ArrayList<Card> hand = plyrOne.getCardsInHand().getHandCards();
                            Card card = hand.get(indx);
                            int verif = printer.verifErorrsPlaceCard(card, indx,
                                    players, 1, output, table);
                            if (verif == 1) {
                                table.placeCard(card, 1, players, indx);
                            }
                        } else if (plyrTwo.getTurn()) {
                            ArrayList<Card> hand = plyrTwo.getCardsInHand().getHandCards();
                            Card card = plyrTwo.getCardsInHand().getHandCards().get(indx);
                            int verif = printer.verifErorrsPlaceCard(card, indx,
                                     players, 2, output, table);
                            if (verif == 1) {
                                table.placeCard(card, 2, players, indx);
                            }
                        }
                        break;
                    case "getCardsOnTable":
                        output.add(printer.getcardsOnTable(table));
                        break;
                    case "getEnvironmentCardsInHand":
                        indx = action.getPlayerIdx();
                        output.add(printer.getEnvCardsInHand(indx, players));
                        break;
                    case "useEnvironmentCard":
                        int indxCard = action.getHandIdx();
                        int affectedRow = action.getAffectedRow();
                        if (plyrOne.getTurn()) {
                            Card card = plyrOne.getCardsInHand().getHandCards().get(indxCard);
                            int verif = printer.verifErrorsUseEnvCard(indxCard, affectedRow,
                                    players, table, 1, output);
                            if (verif == 1) {
                                abilities.useAbilitiesForEnvCard(1, indxCard, players, card,
                                        table, affectedRow);
                            }
                        } else {
                            Card card = plyrTwo.getCardsInHand().getHandCards().get(indxCard);
                            int verif = printer.verifErrorsUseEnvCard(indxCard, affectedRow,
                                    players, table, 2, output);
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
                        if (verif == 1) {
                            abilities.useAttack(myX, myY, x, y, table);
                        }
                        break;
                    case "cardUsesAbility":
                        myX = action.getCardAttacker().getX();
                        myY = action.getCardAttacker().getY();
                        x = action.getCardAttacked().getX();
                        y = action.getCardAttacked().getY();
                        verif = printer.verifCardUsesAbility(myX, myY, x, y, table, output);
                        if (verif == 1) {
                            abilities.useAbilitiesForMinionCard(myX, myY, x, y, table);
                        }
                        break;
                    case "useAttackHero":
                        x = action.getCardAttacker().getX();
                        y = action.getCardAttacker().getY();
                        verif = printer.verifUseAttackHero(x, y, table, output);
                        if (verif == 1) {
                            int death = abilities.useAttackHero(x, y, table, players);
                            printer.verifDeathHero(x, death, statistics, output);
                            if (death == 1) {
                                statistics.setHasFinished(true);
                            }
                        }
                        break;
                    case "useHeroAbility":
                        affectedRow = action.getAffectedRow();
                        if (plyrOne.getTurn()) {
                            verif = printer.verifUseHeroAbility(1, players,
                                    affectedRow, output);
                            if (verif == 1) {
                                abilities.useAbilityHero(affectedRow, 1, players, table);
                            }
                        } else {
                            verif = printer.verifUseHeroAbility(2, players,
                                    affectedRow, output);
                            if (verif == 1) {
                                abilities.useAbilityHero(affectedRow, 2, players, table);
                            }
                        }
                        break;
                    case "getPlayerOneWins":
                        output.add(printer.printWonGames(statistics, 1));
                        break;
                    case "getPlayerTwoWins":
                        output.add(printer.printWonGames(statistics, 2));
                        break;
                    case "getTotalGamesPlayed":
                        ObjectNode node = JsonNodeFactory.instance.objectNode();
                        node.put("command", "getTotalGamesPlayed");
                        node.put("output", statistics.getTotal());
                        output.add(node);
                        break;
                    default:
                }
            }
        }
    }
}
