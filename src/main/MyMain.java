package main;
import com.fasterxml.jackson.databind.ObjectMapper;
import fileio.*;

import java.util.ArrayList;

public class MyMain {
    public static void main(Input input, ObjectMapper objectMapper) {

        // preparing for the game

        DecksForPlayer groupDecksPlyrOne = new DecksForPlayer();
        DecksForPlayer groupDecksPlyrTwo = new DecksForPlayer();
        PlayerOne plyrOne = new PlayerOne();
        PlayerTwo plyrTwo = new PlayerTwo();

        groupDecksPlyrOne.setNrDecks(input.getPlayerOneDecks().getNrDecks());
        groupDecksPlyrTwo.setNrDecks(input.getPlayerTwoDecks().getNrDecks());
        groupDecksPlyrOne.setNrCardsInDecks(input.getPlayerOneDecks().getNrCardsInDeck());
        groupDecksPlyrTwo.setNrCardsInDecks(input.getPlayerTwoDecks().getNrCardsInDeck());

        ArrayList<ArrayList<CardInput>> decksForOne = new ArrayList<ArrayList<CardInput>>();
        ArrayList<ArrayList<CardInput>> decksForTwo = new ArrayList<ArrayList<CardInput>>();
        decksForOne = input.getPlayerOneDecks().getDecks();
        decksForTwo = input.getPlayerTwoDecks().getDecks();


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
                card.getType(cardsForOne.get(j).getName());
                deck.getCards().add(card);
            }
            groupDecksPlyrOne.getDecksForPlayer().add(deck);
        }

        for (int i = 0; i < input.getPlayerTwoDecks().getNrDecks(); i++) {
            Deck deck = new Deck();
            ArrayList<CardInput> cardsForTwo =  new ArrayList<CardInput>();
            cardsForTwo = decksForTwo.get(i);
            for (int j = 0; j < input.getPlayerOneDecks().getNrCardsInDeck(); j++) {
                Card card = new Card();
                card.setMana(cardsForTwo.get(j).getMana());
                card.setAttackDamage(cardsForTwo.get(j).getAttackDamage());
                card.setName(cardsForTwo.get(j).getName());
                card.setHealth(cardsForTwo.get(j).getHealth());
                card.setDescription(cardsForTwo.get(j).getDescription());
                card.setColors(cardsForTwo.get(j).getColors());
                card.getType(cardsForTwo.get(j).getName());
                deck.getCards().add(card);
            }
            groupDecksPlyrTwo.getDecksForPlayer().add(deck);
        }
        plyrOne.getDecks();
        plyrTwo.getDecks();

        plyrOne.setDecks(groupDecksPlyrOne);
        plyrTwo.setDecks(groupDecksPlyrTwo);

        // starting game

        ArrayList<GameInput> games = new ArrayList<GameInput>();
        games = input.getGames();

        for (GameInput game: games) {
            StartGameInput startGame = game.getStartGame();
            plyrOne.setIndx(startGame.getPlayerOneDeckIdx());
            plyrTwo.setIndx(startGame.getPlayerTwoDeckIdx());
            int shuffleseed = startGame.getShuffleSeed();
            plyrOne.verif_isTurn(startGame.getStartingPlayer());
            plyrTwo.verif_isTurn(startGame.getStartingPlayer());
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

            plyrOne.setHero(heroForOne);
            plyrTwo.setHero(heroForTwo);

            ArrayList<ActionsInput> actions = new ArrayList<ActionsInput>();
            actions = game.getActions();

            for (ActionsInput action : actions)
                switch(action.getCommand()) {
                    case "getPlayerDeck":
                        int indx = action.getPlayerIdx();
                        if (indx == 1) {

                        }
                }
        }
    }
}
