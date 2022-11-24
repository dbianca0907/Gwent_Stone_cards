package main;
import fileio.DecksInput;
import fileio.CardInput;
import fileio.StartGameInput;
import fileio.Input;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GameInitialize {

    /**
     * parsing the cards from input
     * @param deckInit input
     * @return array of decks
     */
    public  DecksForPlayer initDecksForPLayer(final DecksInput deckInit) {
        DecksForPlayer groupDecksPlyrOne = new DecksForPlayer();
        groupDecksPlyrOne.setNrDecks(deckInit.getNrDecks());
        groupDecksPlyrOne.setNrCardsInDecks(deckInit.getNrCardsInDeck());
        ArrayList<ArrayList<CardInput>> decksForOne = deckInit.getDecks();

        for (int i = 0; i < deckInit.getNrDecks(); i++) {
            Deck deck = new Deck();
            ArrayList<CardInput> cardsForOne = decksForOne.get(i);

            for (int j = 0; j < deckInit.getNrCardsInDeck(); j++) {
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
        return groupDecksPlyrOne;
    }

    /**
     *
     * @param hero input
     * @param myHero input
     */
    public void initHero(final CardInput hero, final Hero myHero) {
        myHero.setMana(hero.getMana());
        myHero.setDescription(hero.getDescription());
        myHero.setName(hero.getName());
        myHero.setColors(hero.getColors());
        myHero.setHasAttacked(false);
    }

    /**
     * setting the heroes for players
     * @param plyrOne player One
     * @param plyrTwo player Two
     * @param startGame input
     * @param players players
     */

    public void setHeroesForPLayers(final PlayerOne plyrOne, final PlayerTwo plyrTwo,
                                    final StartGameInput startGame, final Player players) {
        Hero heroForOne = new Hero();
        Hero heroForTwo = new Hero();
        initHero(startGame.getPlayerOneHero(), heroForOne);
        initHero(startGame.getPlayerTwoHero(), heroForTwo);
        plyrOne.setHero(heroForOne);
        plyrTwo.setHero(heroForTwo);
    }

    /**
     * randomize the chosen deck and prepare it for the game
     * @param deck chosen deck
     * @param hand array list of cards in my hands
     * @param seed shuffle seed
     */
    public void initPlayerDeck(final ChosenDeck deck, final Hand hand, final int seed) {
        Random rnd = new Random(seed);
        Collections.shuffle(deck.getChosenDeck().getCards(), rnd);
        hand.getHandCards().add(deck.getChosenDeck().getCards().get(0));
        deck.getChosenDeck().getCards().remove(0);
    }

    /**
     * preparing the chosen deck for the player
     * @param plyrOne player one
     * @param plyrTwo player two
     * @param startGame input
     */

    public void initCardsForPlayers(final PlayerOne plyrOne, final PlayerTwo plyrTwo,
                                    final StartGameInput startGame) {
        int shuffleseed = startGame.getShuffleSeed();

        Hand cardsInHandOne = new Hand();
        cardsInHandOne.getHandCards();
        Hand cardsInHandTwo = new Hand();
        cardsInHandTwo.getHandCards();

        ChosenDeck chosenDeckForOne = new ChosenDeck();
        ChosenDeck chosenDeckForTwo = new ChosenDeck();
        chosenDeckForOne.createChosenDeck(plyrOne.getIndxOfDeck(), plyrOne.getDecks());
        chosenDeckForTwo.createChosenDeck(plyrTwo.getIndxOfDeck(), plyrTwo.getDecks());
        initPlayerDeck(chosenDeckForOne, cardsInHandOne, shuffleseed);
        initPlayerDeck(chosenDeckForTwo, cardsInHandTwo, shuffleseed);

        plyrOne.getPlayerChosenDeck();
        plyrTwo.getPlayerChosenDeck();
        plyrOne.setPlayerChosenDeck(chosenDeckForOne);
        plyrTwo.setPlayerChosenDeck(chosenDeckForTwo);
        plyrOne.setCardsInHand(cardsInHandOne);
        plyrTwo.setCardsInHand(cardsInHandTwo);
    }

    /**
     * setting the players
     * @param plyrOne
     * @param plyrTwo
     * @param players
     * @param startGame
     * @param input
     */

    public void settingPlayers(final PlayerOne plyrOne, final PlayerTwo plyrTwo,
                               final Player players, final StartGameInput startGame,
                               final Input input) {
        // setting decks
        plyrOne.getDecks();
        plyrTwo.getDecks();
        plyrOne.setDecks(initDecksForPLayer(input.getPlayerOneDecks()));
        plyrTwo.setDecks(initDecksForPLayer(input.getPlayerTwoDecks()));
        // setting variables
        plyrOne.setIndxOfDeck(startGame.getPlayerOneDeckIdx());
        plyrTwo.setIndxOfDeck(startGame.getPlayerTwoDeckIdx());
        plyrOne.verifIsTurn(startGame.getStartingPlayer());
        plyrTwo.verifIsTurn(startGame.getStartingPlayer());
        //setting chosen Deck and the cards int the Hand
        initCardsForPlayers(plyrOne, plyrTwo, startGame);
        // setting Heroes
        setHeroesForPLayers(plyrOne, plyrTwo, startGame, players);
        // setting in the players
        players.getPlayerOne();
        players.getPlayerTwo();
        players.setPlayerOne(plyrOne);
        players.setPlayerTwo(plyrTwo);
    }
}
