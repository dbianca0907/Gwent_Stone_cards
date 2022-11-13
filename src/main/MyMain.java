package main;
import com.fasterxml.jackson.databind.ObjectMapper;
import fileio.*;

import java.util.ArrayList;

public class MyMain {
    public static void main(Input input, ObjectMapper objectMapper) {

        // preparing for the game
        int shuffleseed;

        DecksInput decksPlayerOne = new DecksInput();
        DecksInput decksPlayerTwo = new DecksInput();
        ArrayList<CardInput> cardsforOne = new ArrayList<CardInput>();
        ArrayList<CardInput> cardsforTwo = new ArrayList<CardInput>();
        ArrayList<GameInput> games = new ArrayList<GameInput>();
        StartGameInput startGame = new StartGameInput;

        decksPlayerOne = input.getPlayerOneDecks();
        decksPlayerTwo = input.getPlayerTwoDecks();
        games = input.getGames();
        startGame = games.get(0).getStartGame();

        for (int i = 0; i < decksPlayerOne.getNrDecks(); i++) {
            for (int j = 0; j < decksPlayerOne.)
        }
    }
}
