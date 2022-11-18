package main;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;

// de verificat daca au ceva metodele fara static de la mana
public class Card {
    public int mana;
    private int health;
    private int attackDamage;
    private String description;
    private ArrayList<String> colors;
    private String name;

    // daca nu mai sunt final, de adaugat in ChosenDeck si la parsare
    @JsonIgnore
    boolean Tank = false;
    @JsonIgnore
    boolean isFrozen = false;
    @JsonIgnore
    boolean hasAbilities = false;
    @JsonIgnore
    int type = 0;
    int specificRow;

    public void attacked(int counter) {
        mana = mana - counter;
    }

    // imi verifica daca o carte este Tank, are abilitati, a fost sau nu inghetata

    public void getTheType(String name) {
        if (name.equals("The Ripper") || name.equals("Miraj") ||
                name.equals("The Cursed One") || name.equals("Disciple")) {
            type = 1;
            hasAbilities = true;
        } else if (name.equals("Goliath") || name.equals("Warden")) {
            type = 1;
            Tank = true;
        }else if (name.equals("Sentinel") || name.equals("Berserker")) {
            type = 1;
        } else if (name.equals("Firestorm") || name.equals ("Winterfell") || name.equals("Heart Hound")) {
            type = 2;
        } else {
            type = 3;
        }
    }


    // getters and setters
    public void setDescription(String description) {
        this.description = description;
    }

    public void setColors(ArrayList<String> colors) {
        this.colors = colors;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public void setName(String name) {
        this.name = name;
    }

    public  int getMana() {
        return mana;
    }

    public  int getHealth() {
        return health;
    }

    public  int getAttackDamage() {
        return attackDamage;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<String> getColors() {
        return colors;
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    public boolean isTank() {
        return Tank;
    }

    public boolean isFrozen() {
        return isFrozen;
    }

    public void setFrozen(boolean frozen) {
        isFrozen = frozen;
    }

    public int getSpecificRow() {
        return specificRow;
    }

    public void setSpecificRow (String name, int indx) {
        if (getType() != 1)
            specificRow = -1;
        if (name.equals("The Ripper") || name.equals("Miraj") ||
                name.equals("Goliath") || name.equals("Warden")) {
            if (indx == 1)
                specificRow = 2;
            else
                specificRow = 1;
        } else {
            if (indx == 1)
                specificRow = 3;
            else
                specificRow = 0;
        }

    }
}
