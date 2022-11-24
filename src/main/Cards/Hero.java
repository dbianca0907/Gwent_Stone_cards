package main.Cards;

import java.util.ArrayList;

public class Hero {
    private int mana;
    private String description;
    private String name;
    private int health = 30;
    private ArrayList<String> colors;

    private boolean hasAttacked = false;

    /**
     * decrease health
     * @param counter
     */

    public void attacked(final int counter) {
        int life = getHealth() - counter;
        setHealth(life);
    }

    /**
     * setter
     * @param hasAttacked
     */
    public void setHasAttacked(final boolean hasAttacked) {
        this.hasAttacked = hasAttacked;
    }

    /**
     * setter
     * @param mana
     */
    public void setMana(final int mana) {
        this.mana = mana;
    }

    /**
     * setter
     * @param description
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * setter
     * @param colors
     */
    public void setColors(final ArrayList<String> colors) {
        this.colors = colors;
    }

    /**
     * setter
     * @param health
     */
    public void setHealth(final int health) {
        this.health = health;
    }

    /**
     * setter
     * @param name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * getter
     * @return
     */
    public int getHealth() {
        return health;
    }

    /**
     * getter
     * @return
     */
    public boolean isHasAttacked() {
        return hasAttacked;
    }

    /**
     * getter
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * getter
     * @return
     */
    public int getMana() {
        return mana;
    }

    /**
     * getter
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * getter
     * @return
     */
    public ArrayList<String> getColors() {
        return colors;
    }
}
