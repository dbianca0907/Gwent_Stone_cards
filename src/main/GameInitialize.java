package main;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.*;

import java.util.ArrayList;
import java.util.*;

import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.Random;

public class GameInitialize {
    public  DecksForPlayer initDecksForPLayer(DecksInput deckInit) {
        DecksForPlayer groupDecksPlyrOne = new DecksForPlayer();
        groupDecksPlyrOne.setNrDecks(deckInit.getNrDecks());
        groupDecksPlyrOne.setNrCardsInDecks(deckInit.getNrCardsInDeck());
        ArrayList<ArrayList<CardInput>> decksForOne = new ArrayList<ArrayList<CardInput>>();
        decksForOne = deckInit.getDecks();

        for (int i = 0; i < deckInit.getNrDecks(); i++) {
            Deck deck = new Deck();
            ArrayList<CardInput> cardsForOne = new ArrayList<CardInput>();
            cardsForOne = decksForOne.get(i);
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

    public void initHero (CardInput hero, Hero myHero) {
        myHero.setMana(hero.getMana());
        myHero.setDescription(hero.getDescription());
        myHero.setName(hero.getName());
        myHero.setColors(hero.getColors());
        myHero.setHasAttacked(false);
    }

    public void initPlayerDeck (ChosenDeck deck, Hand hand, int seed) {
        Random rnd = new Random(seed);
        Collections.shuffle(deck.getChosenDeck().getCards(), rnd);
        hand.getHandCards().add(deck.getChosenDeck().getCards().get(0));
        deck.getChosenDeck().getCards().remove(0);
    }
}
