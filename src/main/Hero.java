package main;

import java.util.ArrayList;

public class Hero {
    private int mana;
    private String description;
    private String Name;
    private int health = 30;
    private ArrayList<String> colors;

    boolean hasAttacked = false;

    public void attacked(int counter) {
        int life = getHealth() - counter;
        setHealth(life);
    }

    // getters and setters


    public boolean isHasAttacked() {
        return hasAttacked;
    }

    public void setHasAttacked(boolean hasAttacked) {
        this.hasAttacked = hasAttacked;
    }

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

    public void setHealth(int health) {
        this.health = health;
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
