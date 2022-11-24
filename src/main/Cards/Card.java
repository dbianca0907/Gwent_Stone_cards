package main.Cards;

import java.util.ArrayList;

public class Card {
    private int mana;
    private int health;
    private int attackDamage;
    private String description;
    private ArrayList<String> colors;
    private String name;
    private boolean isTank = false;
    private boolean isFrozen = false;
    private boolean hasAttacked = false;
    private int type = 0;
    private int specificRow;

    /**
     * decrease mana after the card was attacked
     * @param counter
     */
    public void attacked(final int counter) {

        setHealth(getHealth() - counter);
    }

    /**
     * increases health
     */
    public void incrHealth() {
        setHealth(getHealth() + 1);
    }

    /**
     * increases attack damage
     */
    public void incrAttack() {
        setAttackDamage(getAttackDamage() + 1);
    }


    /**
     * set the Type of the card and verify if it is Tank type
     * 1 = minion Card
     * 2 = environment Card
     * 3 = Hero
     * @param nameCard of the Card
     */
    public void getTheType(final String nameCard) {
        final int typeHero = 3;
        if (nameCard.equals("The Ripper")
                || nameCard.equals("Miraj")
                || nameCard.equals("The Cursed One")
                || nameCard.equals("Disciple")) {
            type = 1;
        } else if (nameCard.equals("Goliath")
                || nameCard.equals("Warden")) {
            type = 1;
            isTank = true;
        } else if (nameCard.equals("Sentinel")
                || nameCard.equals("Berserker")) {
            type = 1;
        } else if (nameCard.equals("Firestorm")
                || nameCard.equals("Winterfell")
                || nameCard.equals("Heart Hound")) {
            type = 2;
        } else {
            type = typeHero;
        }
    }

    /**
     * setting the specific rows for cards
     * @param nameCard of card
     * @param indx of player
     */
    public void setSpecificRow(final String nameCard, final int indx) {
        final int lastRow = 3;
        if (nameCard.equals("The Ripper")
                || nameCard.equals("Miraj")
                || nameCard.equals("Goliath")
                || nameCard.equals("Warden")) {
            if (indx == 1) {
                specificRow = 2;
            } else {
                specificRow = 1;
            }
        } else {
            if (indx == 1) {
                specificRow = lastRow;
            } else {
                specificRow = 0;
            }
        }

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
     * @param mana for card
     */
    public void setMana(final int mana) {
        this.mana = mana;
    }

    /**
     * setter
     * @param health of card
     */
    public void setHealth(final int health) {
        this.health = health;
    }

    /**
     * setter
     * @param attackDamage of card
     */
    public void setAttackDamage(final int attackDamage) {
        this.attackDamage = attackDamage;
    }

    /**
     * setter
     * @param name of card
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * setter
     * @param frozen
     */
    public void setFrozen(final boolean frozen) {
        isFrozen = frozen;
    }

    /**
     * setter
     * @param hasAttacked
     */
    public void setHasAttacked(final boolean hasAttacked) {
        this.hasAttacked = hasAttacked;
    }

    /**
     * getter
     * @return mana
     */
    public  int getMana() {
        return mana;
    }

    /**
     * getter
     * @return health
     */
    public  int getHealth() {
        return health;
    }

    /**
     * getter
     * @return attack
     */
    public  int getAttackDamage() {
        return attackDamage;
    }

    /**
     * getter
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * getter
     * @return colors
     */
    public ArrayList<String> getColors() {
        return colors;
    }

    /**
     * getter
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * getter
     * @return type
     */
    public int getType() {
        return type;
    }

    /**
     * getter
     * @return Tank
     */
    public boolean isTank() {
        return isTank;
    }

    /**
     * getter
     * @return frozen
     */
    public boolean getFrozen() {
        return isFrozen;
    }

    /**
     * getter
     * @return specific Row for each card
     */
    public int getSpecificRow() {
        return specificRow;
    }

    /**
     * getter
     * @return
     */
    public boolean getHasAttacked() {
        return hasAttacked;
    }

}
