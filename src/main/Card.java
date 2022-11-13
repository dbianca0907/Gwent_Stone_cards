package main;
import java.util.*;


public class Card {
    static int mana = 0;
    static int health;
    static int attackDamage;
    static String description;
    static ArrayList<String> colors;
    static String name;
    boolean Tank = false;
    boolean isFrozen = false;
    boolean hasAbilities = false;
    static int type = 0;


    public void nextRound(int counter) {
        mana = mana + counter;
    }

    public void getType(String name) {
        if (name.equals("The Ripper") || name.equals("Miraj") ||
                name.equals("The cursed one") || name.equals("Disciple")) {
            type = 1;
            hasAbilities = true;
        } else if (name.equals("Goliath") || name.equals("Warden")) {
            type = 1;
            Tank = true;
        } else if (name.equals("Firestorm") || name.equals ("Winterfell") || name.equals("Heart Hound")) {
            type = 2;
        } else {
            type = 3;
        }
    }

    public static void setDescription(String description) {
        Card.description = description;
    }

    public static void setColors(ArrayList<String> colors) {
        Card.colors = colors;
    }
}