package main;

import java.util.ArrayList;

public class Abilities {

    /**
     * method for using the ability of a hero
     * @param affectedRow from input
     * @param indx of the player
     * @param players contains both of my players
     * @param table contains of the cards on the table
     */

    public void useAbilityHero(final int affectedRow, final int indx, final Player players,
                               final Table table) {
        Hero hero;
        if (indx == 1) {
            hero = players.getPlayerOne().getHero();
        } else {
            hero = players.getPlayerTwo().getHero();
        }
        hero.setHasAttacked(true);

        switch (hero.getName()) {
            case "Lord Royce":
                useLordRoyce(table, affectedRow, indx, players);
                break;
            case "Empress Thorina":
                useThorina(table, affectedRow, indx, players);
                break;
            case "King Mudface":
                useMudface(table, affectedRow, indx, players);
                break;
            case "General Kocioraw":
                useGeneral(table, affectedRow, indx, players);
                break;
            default:
        }
    }

    /**
     * the attack of a Hero by a Minion Card
     * @param x coordinate of my card from the table
     * @param y coordinate of the card from the table
     * @param table all the card from the table
     * @param players all my players
     * @return 1, if the hero didn't die
     */

    public int useAttackHero(final int x, final int y, final Table table, final Player players) {
        Card myCard = findCard(x, y, table);
        myCard.setHasAttacked(true);
        Hero hero;
        if (x < 2) {
            hero = players.getPlayerOne().getHero();
        } else {
            hero = players.getPlayerTwo().getHero();
        }
        hero.attacked(myCard.getAttackDamage());
        if (hero.getHealth() <= 0) {
            return 1;
        }
        return 0;
    }

    /**
     * the attack of a minion
     * @param xMyCard coordinate of my card
     * @param yMyCard coordinate of my card
     * @param x coordinate of the card that is attacked
     * @param y coordinate of the card that is attacked
     * @param table all the cards from the table
     */
    public void useAttack(final int xMyCard, final int yMyCard, final int x,
                          final int y, final  Table table) {
        Card myCard = findCard(xMyCard, yMyCard, table);
        Card attackedCard = findCard(x, y, table);
        attackedCard.attacked(myCard.getAttackDamage());
        if (attackedCard.getHealth() <= 0) {
            table.getCardsOnTable().get(x).remove(y);
        }
        myCard.setHasAttacked(true);
    }

    /**
     *
     * @param xMyCard coordinate of my card
     * @param yMyCard coordinate of my card
     * @param x coordinate of the attacked card
     * @param y coordinate of the attacked card
     * @param table all the cards on the table
     */
    public void useAbilitiesForMinionCard(final int xMyCard, final int yMyCard,
                                          final int x, final int y, final Table table) {
        Card myCard = findCard(xMyCard, yMyCard, table);
        Card attackedCard = findCard(x, y, table);
        myCard.setHasAttacked(true);

        switch (myCard.getName()) {
            case "The Ripper":
                useTheRipper(attackedCard);
                break;
            case "Miraj":
                useMiraj(myCard, attackedCard);
                break;
            case "The Cursed One":
                useTheCursedOne(attackedCard, table, x, y);
                break;
            case "Disciple":
                useDisciple(attackedCard);
                break;
            default:
        }
    }

    /**
     *
     * @param indx of the player, for decreasing the mana of the player
     * @param indxCard the position of the card in hand
     * @param player all players
     * @param card the environment card
     * @param table  all the cards from the table
     * @param affectedRow from input
     */
    public void useAbilitiesForEnvCard(final int indx, final int indxCard, final Player player,
                                        final Card card, final Table table, final int affectedRow) {
        switch (card.getName()) {
            case "Firestorm":
                useFirestorm(indx, indxCard, card, player, table, affectedRow);
                break;
            case "Winterfell":
                useWinterFell(indx, indxCard, card, player, table, affectedRow);
                break;
            case "Heart Hound":
                useHeartHound(indx, indxCard, card, player, table, affectedRow);
                break;
            default:
        }
    }

    /**
     * finds the cards from a table based on the coordinates
     * @param x coordinate of the card
     * @param y coordinate of the card
     * @param table all the cards on the table
     * @return the needed card
     */

    public Card findCard(final int x, final int y, final Table table) {
        return table.getCardsOnTable().get(x).get(y);
        }

    /**
     * method for decreasing mana for the Hero
     * @param indx
     * @param players
     */
    private void decreaseManaPlayer(final int indx, final Player players) {
        if (indx == 1) {
            int cost = (-1) * players.getPlayerOne().getHero().getMana();
            players.getPlayerOne().increaseMana(cost);
        } else {
            int cost = (-1) * players.getPlayerTwo().getHero().getMana();
            players.getPlayerTwo().increaseMana(cost);
        }
    }

    /**
     * using the ability of Lord Royce hero
     * @param table all the cards ont the table
     * @param affectedRow from input
     * @param indx the index of the player for decreasing mana
     * @param players all players
     */
    public void useLordRoyce(final Table table, final int affectedRow,
                             final int indx, final Player players) {
        int maxDamage = 0;
        int maxIndx = 0;
        for (int i = 0; i < table.getCardsOnTable().get(affectedRow).size(); i++) {
            Card card = table.getCardsOnTable().get(affectedRow).get(i);
            if (maxDamage < card.getAttackDamage()) {
                maxDamage = card.getAttackDamage();
                maxIndx = i;
            }
        }
        table.getCardsOnTable().get(affectedRow).get(maxIndx).setFrozen(true);

        decreaseManaPlayer(indx, players);
    }

    /**
     * using the ability of UseThorina hero
     * @param table all the cards ont the table
     * @param affectedRow from input
     * @param indx the index of the player for decreasing mana
     * @param players all players
     */
    public void useThorina(final Table table, final int affectedRow,
                           final int indx, final Player players) {
        int maxHealth = 0;
        int maxIndx = 0;
        for (int i = 0; i < table.getCardsOnTable().get(affectedRow).size(); i++) {
            Card card = table.getCardsOnTable().get(affectedRow).get(i);
            if (maxHealth < card.getHealth()) {
                maxHealth = card.getHealth();
                maxIndx = i;
            }
        }
        table.getCardsOnTable().get(affectedRow).remove(maxIndx);

        decreaseManaPlayer(indx, players);
    }

    /**
     * using the ability of King Mudface hero
     * @param table all the cards ont the table
     * @param affectedRow from input
     * @param indx the index of the player for decreasing mana
     * @param players all players
     */

    public void useMudface(final Table table, final int affectedRow,
                           final int indx, final Player players) {
        for (Card card : table.getCardsOnTable().get(affectedRow)) {
            card.incrHealth();
        }

        decreaseManaPlayer(indx, players);
    }


    /**
     * using the ability of General hero
     * @param table all the cards ont the table
     * @param affectedRow from input
     * @param indx the index of the player for decreasing mana
     * @param players all players
     */
    public void useGeneral(final Table table, final int affectedRow,
                           final int indx, final Player players) {
        for (Card card : table.getCardsOnTable().get(affectedRow)) {
            card.incrAttack();
        }

        decreaseManaPlayer(indx, players);
    }

    /**
     * using the ability of the Ripper
     * @param card attacked
     */
    public void useTheRipper(final Card card) {
        card.setAttackDamage(card.getAttackDamage() - 2);
        if (card.getAttackDamage() < 0) {
            card.setAttackDamage(0);
        }
    }

    /**
     * using the ability of Miraj
     * @param myCard Miraj card
     * @param attackedCard the attacked card
     */
    public void useMiraj(final Card myCard, final Card attackedCard) {
        int aux = myCard.getHealth();
        myCard.setHealth(attackedCard.getHealth());
        attackedCard.setHealth(aux);
    }

    /**
     * using the ability of The Cursed One card
     * @param card The Cursed One card
     * @param table all the cards on the table
     * @param x the coordinate of the attacked card
     * @param y the coordinate of the attacked card
     */
    public void useTheCursedOne(final Card card, final Table table, final int x, final int y) {
        int aux = card.getHealth();
        card.setHealth(card.getAttackDamage());
        card.setAttackDamage(aux);

        if (card.getHealth() <= 0) {
            table.getCardsOnTable().get(x).remove(y);
        }
    }

    /**
     *
     * @param card the attacked card
     */
    public void useDisciple(final Card card) {
        card.setHealth(card.getHealth() + 2);
    }

    /**
     * decrease mana for environment card
     * @param indx of the player
     * @param indxCard ok the env card in the hand
     * @param card the environment Card
     * @param player all players
     */

    private void decreaseManaEnvCard(final int indx, final int indxCard,
                                     final Card card, final Player player) {
        if (indx == 1) {
            player.getPlayerOne().getCardsInHand().getHandCards().remove(indxCard);
            player.getPlayerOne().increaseMana((-1) * card.getMana());
        } else {
            player.getPlayerTwo().getCardsInHand().getHandCards().remove(indxCard);
            player.getPlayerTwo().increaseMana((-1) * card.getMana());
        }
    }

    /**
     *
     * @param indx player for decreasing mana
     * @param indxCard in the player's hand
     * @param card Firestorm card for decreasing mana
     * @param player all the players
     * @param table all the cards on the table
     * @param affectedRow from input
     */
    public void useFirestorm(final int indx, final int indxCard, final Card card,
                             final Player player, final Table table, final int affectedRow) {
        ArrayList<Integer> removedIndx = new ArrayList<Integer>();
        for (int i = 0; i < table.getCardsOnTable().get(affectedRow).size(); i++) {
            Card cards = table.getCardsOnTable().get(affectedRow).get(i);
            cards.attacked(1);

            if (cards.getHealth() <= 0) {
                removedIndx.add(i);
            }
        }
        for (int i = removedIndx.size() - 1; i >= 0; i--) {
            table.getCardsOnTable().get(affectedRow).remove(removedIndx.get(i).intValue());
        }

        decreaseManaEnvCard(indx, indxCard, card, player);
    }

    /**
     *
     * @param indx player for decreasing mana
     * @param indxCard in the player's hand
     * @param card UseWinterfell card for decreasing mana
     * @param player all the players
     * @param table all the cards on the table
     * @param affectedRow from input
     */
    public void useWinterFell(final int indx, final int indxCard, final Card card,
                              final Player player, final Table table, final int affectedRow) {
        for (Card cards : table.getCardsOnTable().get(affectedRow)) {
            cards.setFrozen(true);
        }

        decreaseManaEnvCard(indx, indxCard, card, player);
    }

    /**
     *
     * @param indx player for decreasing mana
     * @param indxCard in the player's hand
     * @param card Firestorm card for decreasing mana
     * @param player all the players
     * @param table all the cards on the table
     * @param affectedRow from input
     */
    public void useHeartHound(final int indx, final int indxCard, final Card card,
                              final Player player, final Table table, final int affectedRow) {
        int max = 0, maxIndx = -1;
        final int position = 3;
        for (int i = 0; i < table.getCardsOnTable().get(affectedRow).size(); i++) {
            if (max <= table.getCardsOnTable().get(affectedRow).get(i).getHealth()) {
                max = table.getCardsOnTable().get(affectedRow).get(i).getHealth();
                maxIndx = i;
            }
        }
        int myIndx = -1;
        switch (affectedRow) {
            case(0):
                myIndx = position;
                break;
            case(1):
                myIndx = 2;
                break;
            case(2):
                myIndx = 1;
                break;
            case(position):
                myIndx = 0;
                break;
            default:
        }
        Card cardOnTable = table.getCardsOnTable().get(affectedRow).get(maxIndx);
        table.getCardsOnTable().get(myIndx).add(cardOnTable);
        table.getCardsOnTable().get(affectedRow).remove(maxIndx);

        decreaseManaEnvCard(indx, indxCard, card, player);
    }
}
