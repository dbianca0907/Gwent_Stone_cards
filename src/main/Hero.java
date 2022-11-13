package main;

import java.util.ArrayList;

public class Hero {
    static int mana;
    static String description;
    static String Name;
    static int health = 30;
    static ArrayList<String> colors;

    // getters and setters

    public static void setMana(int mana) {
        Hero.mana = mana;
    }

    public static void setDescription(String description) {
        Hero.description = description;
    }

    public static void setColors(ArrayList<String> colors) {
        Hero.colors = colors;
    }

    public static int getHealth() {
        return health;
    }

    public static void setName(String name) {
        Name = name;
    }
}
