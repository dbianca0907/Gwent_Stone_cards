package main;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.*;

import java.util.ArrayList;
import java.util.*;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.util.Random;

public class MyMain {
    public static void main(Input input, ArrayNode output, ObjectMapper objectMapper) {


        // preparing for the game

        DecksForPlayer groupDecksPlyrOne = new DecksForPlayer();
        DecksForPlayer groupDecksPlyrTwo = new DecksForPlayer();
        PlayerOne plyrOne = new PlayerOne();
        PlayerTwo plyrTwo = new PlayerTwo();
        Player players = new Player();

        groupDecksPlyrOne.setNrDecks(input.getPlayerOneDecks().getNrDecks());
        groupDecksPlyrTwo.setNrDecks(input.getPlayerTwoDecks().getNrDecks());
        groupDecksPlyrOne.setNrCardsInDecks(input.getPlayerOneDecks().getNrCardsInDeck());
        groupDecksPlyrTwo.setNrCardsInDecks(input.getPlayerTwoDecks().getNrCardsInDeck());

        ArrayList<ArrayList<CardInput>> decksForOne = new ArrayList<ArrayList<CardInput>>();
        ArrayList<ArrayList<CardInput>> decksForTwo = new ArrayList<ArrayList<CardInput>>();
        decksForOne = input.getPlayerOneDecks().getDecks();
        decksForTwo = input.getPlayerTwoDecks().getDecks();

        // for player one
        for (int i = 0; i < input.getPlayerOneDecks().getNrDecks(); i++) {
            Deck deck = new Deck();
            ArrayList<CardInput> cardsForOne = new ArrayList<CardInput>();
            cardsForOne = decksForOne.get(i);
            for (int j = 0; j < input.getPlayerOneDecks().getNrCardsInDeck(); j++) {
                Card card = new Card();
                card.setMana(cardsForOne.get(j).getMana());
                card.setAttackDamage(cardsForOne.get(j).getAttackDamage());
                card.setName(cardsForOne.get(j).getName());
                card.setHealth(cardsForOne.get(j).getHealth());
                card.setDescription(cardsForOne.get(j).getDescription());
                card.setColors(cardsForOne.get(j).getColors());
                card.getTheType(cardsForOne.get(j).getName());
                deck.getCards().add(card);
            }
            groupDecksPlyrOne.getDecksForPlayer().add(deck);
        }
        //for player two

        for (int i = 0; i < input.getPlayerTwoDecks().getNrDecks(); i++) {
            Deck deck = new Deck();
            ArrayList<CardInput> cardsForTwo =  new ArrayList<CardInput>();
            cardsForTwo = decksForTwo.get(i);

            for (int j = 0; j < input.getPlayerTwoDecks().getNrCardsInDeck(); j++) {
                Card card = new Card();
                card.setMana(cardsForTwo.get(j).getMana());
                card.setAttackDamage(cardsForTwo.get(j).getAttackDamage());
                card.setName(cardsForTwo.get(j).getName());
                card.setHealth(cardsForTwo.get(j).getHealth());
                card.setDescription(cardsForTwo.get(j).getDescription());
                card.setColors(cardsForTwo.get(j).getColors());
                card.getTheType(cardsForTwo.get(j).getName());

                deck.getCards().add(card);
            }
            groupDecksPlyrTwo.getDecksForPlayer().add(deck);
        }
        // de verificat
        plyrOne.getDecks();
        plyrTwo.getDecks();

        plyrOne.setDecks(groupDecksPlyrOne);
        plyrTwo.setDecks(groupDecksPlyrTwo);


        // starting game
        GameStatistics statistics = new GameStatistics();

        ArrayList<GameInput> games = new ArrayList<GameInput>();
        games = input.getGames();

        for (GameInput game: games) {
            statistics.incrNumberOfGames();
            FrozenCards frozenCards = new FrozenCards();
            Table table = new Table();

            Hand cardsInHandOne = new Hand();
            cardsInHandOne.getHandCards();
            Hand cardsInHandTwo = new Hand();
            cardsInHandTwo.getHandCards();

            StartGameInput startGame = game.getStartGame();
            plyrOne.setIndxOfDeck(startGame.getPlayerOneDeckIdx());
            plyrTwo.setIndxOfDeck(startGame.getPlayerTwoDeckIdx());
            int shuffleseed = startGame.getShuffleSeed();
            plyrOne.verif_isTurn(startGame.getStartingPlayer());
            plyrTwo.verif_isTurn(startGame.getStartingPlayer());


            // setting the heros
            Hero heroForOne = new Hero();
            Hero heroForTwo = new Hero();

            heroForOne.setMana(startGame.getPlayerOneHero().getMana());
            heroForOne.setDescription(startGame.getPlayerOneHero().getDescription());
            heroForOne.setName(startGame.getPlayerOneHero().getName());
            heroForOne.setColors(startGame.getPlayerOneHero().getColors());

            heroForTwo.setMana(startGame.getPlayerTwoHero().getMana());
            heroForTwo.setDescription(startGame.getPlayerTwoHero().getDescription());
            heroForTwo.setName(startGame.getPlayerTwoHero().getName());
            heroForTwo.setColors(startGame.getPlayerTwoHero().getColors());

            // setting the players
            plyrOne.setHero(heroForOne);
            plyrTwo.setHero(heroForTwo);
            players.getPlayerOne();
            players.getPlayerTwo();
            players.setPlayerOne(plyrOne);
            players.setPlayerTwo(plyrTwo);

            // setting the chosen decks

            ChosenDeck chosenDeckForOne = new ChosenDeck();
            ChosenDeck chosenDeckForTwo = new ChosenDeck();
            chosenDeckForOne.createChosenDeck(plyrOne.getIndxOfDeck(), plyrOne.getDecks());
            chosenDeckForTwo.createChosenDeck(plyrTwo.getIndxOfDeck(), plyrTwo.getDecks());

            // randomizing the decks and getting the first card
            // NRCARDS ESTE DE LA INCEPUT
            Random rnd1 = new Random(shuffleseed);
            Collections.shuffle(chosenDeckForOne.getChosenDeck().getCards(), rnd1);
            cardsInHandOne.getHandCards().getCards().add(chosenDeckForOne.getChosenDeck().getCards().get(0));
            chosenDeckForOne.getChosenDeck().getCards().remove(0);


            Random rnd2 = new Random(shuffleseed);
            Collections.shuffle(chosenDeckForTwo.getChosenDeck().getCards(), rnd2);
            cardsInHandTwo.getHandCards().getCards().add(chosenDeckForTwo.getChosenDeck().getCards().get(0));
            chosenDeckForTwo.getChosenDeck().getCards().remove(0);

            //pointing out the used decks and adding them to players
            plyrOne.getDecks().getDecksForPlayer().get(plyrOne.getIndxOfDeck()).setWasUsed(true);
            plyrTwo.getDecks().getDecksForPlayer().get(plyrOne.getIndxOfDeck()).setWasUsed(true);

            plyrOne.getPlayerChosenDeck();
            plyrTwo.getPlayerChosenDeck();
            plyrOne.setPlayerChosenDeck(chosenDeckForOne);
            plyrTwo.setPlayerChosenDeck(chosenDeckForTwo);
            plyrOne.setCardsInHand(cardsInHandOne);
            plyrTwo.setCardsInHand(cardsInHandTwo);
            //MyPrinter printer2 = new MyPrinter();
            //output.add(printer2.getPlayerDeck(2, players, "getPlayerDeck"));

            // TO DO: de refacut turn-ul, de verificat cartile din hand!

            ArrayList<ActionsInput> actions = new ArrayList<ActionsInput>();
            actions = game.getActions();
            MyPrinter printer = new MyPrinter();
            for (ActionsInput action : actions) {
               switch(action.getCommand()) {
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
                       statistics.incrNumberOfTurns();
                       // la sfarsitul rundei cresc mana
                       if (statistics.getNumberOfTurns() == 2) {
                           if (plyrOne.getTurn() == true) {
                               plyrOne.setTurn(false);
                               plyrTwo.setTurn(true);
                               frozenCards.defrozenCards(chosenDeckForOne, 1);
                           } else {
                               plyrOne.setTurn(true);
                               plyrTwo.setTurn(false);
                               frozenCards.defrozenCards(chosenDeckForTwo, 2);
                           }
                           statistics.incrNumberOfRounds();
                           plyrOne.increaseMana(statistics.getNumberOfRounds());
                           plyrTwo.increaseMana(statistics.getNumberOfRounds());
                           plyrOne.addCardsInHand();
                           plyrTwo.addCardsInHand();
                           statistics.setNumberOfTurns(0);
                       } else {
                           // verific a cui a fost tura, dezghet cartile
                           if (plyrOne.getTurn() == true) {
                               plyrOne.setTurn(false);
                               plyrTwo.setTurn(true);
                               frozenCards.defrozenCards(chosenDeckForOne, 1);

                           }
                           if (plyrTwo.getTurn() == true) {
                               plyrOne.setTurn(true);
                               plyrTwo.setTurn(false);
                               frozenCards.defrozenCards(chosenDeckForTwo, 2);
                           }
                       }
                        break;
                   case "getCardsInHand":
                        indx = action.getPlayerIdx();
                        output.add(printer.getCardsInHand(indx, players, "getCardsInHand"));
                        break;
                   case "getPlayerMana":
                        indx = action.getPlayerIdx();
                        output.add(printer.getPlayerMana(indx, players, "getPlayerMana"));
                        break;
                   case "placeCard":
                        indx = action.getHandIdx();
                        if (plyrOne.getTurn() == true) {
                            Card card = plyrOne.getCardsInHand().getHandCards().getCards().get(indx);
                            int verif = printer.verifErorrsPlaceCard(card, indx, "placeCard", players, 1, output, table);
                            if (verif == 1)
                                table.placeCard(card, 1, players, indx);
                        } else {
                            Card card = plyrTwo.getCardsInHand().getHandCards().getCards().get(indx);
                            int verif = printer.verifErorrsPlaceCard(card, indx, "placeCard", players, 2, output, table);
                            if (verif == 1)
                                table.placeCard(card, 2, players, indx);
                        }
                        break;
                }
            }
        }
    }
}
