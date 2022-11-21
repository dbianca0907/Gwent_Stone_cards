package main;

import java.util.ArrayList;

public class Abilities {

    public void useAbilityHero(int affectedRow, int indx, Player players, Table table) {
        ArrayList<Card> card = table.getCardsOnTable().get(affectedRow);
        Hero hero;
        if (indx == 1) {
            hero = players.playerOne.getHero();
        } else {
            hero = players.playerTwo.getHero();
        }
        hero.setHasAttacked(true);

        switch(hero.getName()) {
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
        }
    }

    public int useAttackHero(int x, int y, Table table, Player players) {
        Card myCard = findCard(x, y, table);
        myCard.setHasAttacked(true);
        Hero hero;
        if (x < 2) {
            hero = players.playerOne.getHero();
        } else {
            hero = players.playerTwo.getHero();
        }
        hero.attacked(myCard.getAttackDamage());
        if (hero.getHealth() <= 0)
            return 0;
        return 1;
    }

    public void useAttack(int xMyCard, int yMyCard, int x, int y, Table table) {
        Card myCard = findCard(xMyCard, yMyCard, table);
        Card attackedCard = findCard(x, y, table);
        attackedCard.attacked(myCard.getAttackDamage());
        if (attackedCard.getHealth() <= 0)
            table.getCardsOnTable().get(x).remove(y);
        myCard.setHasAttacked(true);
    }

    public void useAbilitiesForMinionCard(int xMyCard, int yMyCard, int x, int y, Table table) {
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
        }
    }
    public void useAbilitiesForEnvCard (int indx, int indxCard, Player player, Card card,
                                            Table table, int affectedRow) {
        switch(card.getName()) {
            case "Firestorm":
                useFirestorm(indx, indxCard, card, player, table, affectedRow);
                break;
            case "Winterfell":
                useWinterFell(indx, indxCard, card, player, table, affectedRow);
                break;
            case "Heart Hound":
                useHeartHound(indx, indxCard, card, player, table, affectedRow);
                break;
            }
        }

        public Card findCard(int x, int y, Table table) {
            Card card = table.getCardsOnTable().get(x).get(y);
            return card;
        }

        public void useLordRoyce(Table table, int affectedRow, int indx, Player players) {
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

            if (indx == 1) {
                int cost = (-1) * players.playerOne.getHero().getMana();
                players.playerOne.increaseMana(cost);
            } else {
                int cost = (-1) * players.playerTwo.getHero().getMana();
                players.playerTwo.increaseMana(cost);
            }
        }

        public void useThorina(Table table, int affectedRow, int indx, Player players) {
            int maxHealth = 0;
            int maxIndx = 0;
            for (int i = 0; i < table.getCardsOnTable().get(affectedRow).size(); i++){
                Card card = table.getCardsOnTable().get(affectedRow).get(i);
                if (maxHealth < card.getHealth()) {
                    maxHealth = card.getHealth();
                    maxIndx = i;
                }
            }
            table.getCardsOnTable().get(affectedRow).remove(maxIndx);

            if (indx == 1) {
                int cost = (-1) * players.playerOne.getHero().getMana();
                players.playerOne.increaseMana(cost);
            } else {
                int cost = (-1) * players.playerTwo.getHero().getMana();
                players.playerTwo.increaseMana(cost);
            }

        }

        public void useMudface(Table table, int affectedRow, int indx, Player players) {
            for (Card card : table.getCardsOnTable().get(affectedRow))
                card.incrHealth();

            if (indx == 1) {
                int cost = (-1) * players.playerOne.getHero().getMana();
                players.playerOne.increaseMana(cost);
            } else {
                int cost = (-1) * players.playerTwo.getHero().getMana();
                players.playerTwo.increaseMana(cost);
            }
        }

        public void useGeneral(Table table, int affectedRow, int indx, Player players) {
            for (Card card : table.getCardsOnTable().get(affectedRow))
                card.incrAttack();

            if (indx == 1) {
                int cost = (-1) * players.playerOne.getHero().getMana();
                players.playerOne.increaseMana(cost);
            } else {
                int cost = (-1) * players.playerTwo.getHero().getMana();
                players.playerTwo.increaseMana(cost);
            }
        }

        public void useTheRipper(Card card) {
            card.setAttackDamage(card.getAttackDamage() - 2);
            if (card.getAttackDamage() < 0)
                card.setAttackDamage(0);
        }

        public void useMiraj (Card myCard, Card attackedCard) {
            int aux = myCard.getHealth();
            myCard.setHealth(attackedCard.getHealth());
            attackedCard.setHealth(aux);
        }

        public void useTheCursedOne(Card card, Table table, int x, int y) {
            int aux = card.getHealth();
            card.setHealth(card.getAttackDamage());
            card.setAttackDamage(aux);

            if (card.getHealth() <= 0)
                table.getCardsOnTable().get(x).remove(y);
        }

        public void useDisciple(Card card) {
            card.setHealth(card.getHealth() + 2);
        }

        public void useFirestorm(int indx, int indxCard, Card card, Player player,
                                 Table table, int affectedRow) {
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

            if (indx == 1) {
                player.playerOne.getCardsInHand().getHandCards().remove(indxCard);
                player.playerOne.increaseMana((-1) * card.getMana());
            } else {
                player.playerTwo.getCardsInHand().getHandCards().remove(indxCard);
                player.playerTwo.increaseMana((-1) * card.getMana());
            }
        }

        public void useWinterFell(int indx, int indxCard, Card card, Player player,
                                  Table table, int affectedRow) {
            for (Card cards : table.getCardsOnTable().get(affectedRow))
                cards.setFrozen(true);

            if (indx == 1) {
                player.playerOne.getCardsInHand().getHandCards().remove(indxCard);
                player.playerOne.increaseMana((-1) * card.getMana());
            } else {
                player.playerTwo.getCardsInHand().getHandCards().remove(indxCard);
                player.playerTwo.increaseMana((-1) * card.getMana());
            }
        }

        public void useHeartHound(int indx, int indxCard, Card card, Player player,
                                  Table table, int affectedRow) {
            int max = 0, maxIndx = -1;
            for (int i = 0; i < table.getCardsOnTable().get(affectedRow).size(); i++)
                if (max <= table.getCardsOnTable().get(affectedRow).get(i).getHealth()) {
                    max = table.getCardsOnTable().get(affectedRow).get(i).getHealth();
                    maxIndx = i;
                }
            int myIndx = -1;
            switch(affectedRow) {
                case(0):
                    myIndx = 3;
                    break;
                case(1):
                    myIndx = 2;
                    break;
                case(2):
                    myIndx = 1;
                    break;
                case(3):
                    myIndx = 0;
                    break;
            }
            // de verificat daca fac modificarile
            Card cardOnTable = table.getCardsOnTable().get(affectedRow).get(maxIndx);
            table.getCardsOnTable().get(myIndx).add(cardOnTable);
            table.getCardsOnTable().get(affectedRow).remove(maxIndx);

            if (indx == 1) {
                player.playerOne.getCardsInHand().getHandCards().remove(indxCard);
                player.playerOne.increaseMana((-1) * card.getMana());
            } else {
                player.playerTwo.getCardsInHand().getHandCards().remove(indxCard);
                player.playerTwo.increaseMana((-1) * card.getMana());
            }
        }
}
