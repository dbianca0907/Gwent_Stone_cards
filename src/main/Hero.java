package main;

import java.util.ArrayList;

public class Hero {
    private int mana;
    private String description;
    private String Name;
    private int health = 30;
    private ArrayList<String> colors;

    // getters and setters

    public void setMana(int mana) {
        this.mana = mana;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setColors(ArrayList<String> colors) {
        this.colors = colors;
    }

    public int getHealth() {
        return health;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getName() {
        return Name;
    }

    public int getMana() {
        return mana;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<String> getColors() {
        return colors;
    }
}
