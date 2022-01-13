package gamedata;

import cards.AbstractCard;
import creatures.*;
import effects.AbstractEffect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Encounter {
    private final int floorCount;
    private final AbstractEnemy floorEnemy;
    private final Player currentPlayer;
    private final CardPiles cardPiles;

    private int turnCount = 1;
    private EncounterStatus encounterStatus = EncounterStatus.ONGOING;

    public Encounter(int floor, AbstractEnemy enemy, Player player) {
        this.floorCount = floor;
        this.floorEnemy = enemy;
        this.currentPlayer = player;
        this.cardPiles = new CardPiles(currentPlayer.getPlayerDeck());
    }

    public EncounterStatus getEncounterStatus() {
        return encounterStatus;
    }

    public CardPiles getCardPiles() {
        return cardPiles;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public AbstractEnemy getFloorEnemy() {
        return floorEnemy;
    }

    public void startEncounter() {
        currentPlayer.resetEnergy();
        currentPlayer.resetAllEffects();
        currentPlayer.resetBlock();
        cardPiles.startTurn();
        introString();
        menuPrint();
    }

    public void updateTurn() {
        int originalLength1 = currentPlayer.getEffects().size();
        List<AbstractEffect> toRemove1 = new ArrayList<>();

        for (int i = 0; i < originalLength1; i++) {
            if (currentPlayer.getEffects().get(i).checkEffectDuration())
                toRemove1.add(currentPlayer.getEffects().get(i));
        }

        for (AbstractEffect a : toRemove1) {
            currentPlayer.removeEffect(a);
        }

        List<AbstractEffect> toRemove2 = new ArrayList<>();

        int originalLength2 = floorEnemy.getEffects().size();
        for (int i = 0; i < originalLength2; i++) {
            if (floorEnemy.getEffects().get(i).checkEffectDuration())
                toRemove2.add(floorEnemy.getEffects().get(i));
        }

        for (AbstractEffect a : toRemove2) {
            floorEnemy.removeEffect(a);
        }

        cardPiles.endTurn();
        currentPlayer.resetEnergy();

        floorEnemy.turnAction();
        currentPlayer.resetBlock();

        turnCount++;
        turnPrint();
        cardPiles.startTurn();
    }

    // checks encounter status, updates accordingly. player game over will take precedence over defeating enemy
    public boolean encounterOngoing() {
        if (currentPlayer.getCurrentHP() <= 0) {
            encounterStatus = EncounterStatus.PLAYER_LOST;
            System.out.println("| Your HP is 0. You have lost.\n");
            return true;
        } else if (floorEnemy.getCurrentHP() <= 0) {
            encounterStatus = EncounterStatus.PLAYER_WON;
            System.out.println("| " + floorEnemy.getName() + "'s HP is 0. You win.\n");
            return true;
        }

        return false;
    }

    public void menuPrint() {
        Scanner readOption = new Scanner(System.in);

        while (encounterStatus == EncounterStatus.ONGOING) {
            if (encounterOngoing())
                break;

            System.out.print(
                    """
                                                        
                            | [C]ards
                            | [S]tatus
                            | [E]nd Turn                     
                            """
            );
            System.out.print("> ");
            String option = readOption.next().toUpperCase();

            switch (option) {
                case "C" -> cardMenu();
                case "S" -> {
                    System.out.println(floorEnemy.toString());
                    System.out.println(currentPlayer.toString());
                }
                case "E" -> updateTurn();
                default -> System.out.println("| Please input a valid key.");
            }
        }
    }

    public void turnPrint() {
        System.out.println("| Turn " + turnCount + " |");
        System.out.println(floorEnemy.toString());
        System.out.println(currentPlayer.toString());
    }

    public void introString() {
        System.out.println("|===============Floor " + floorCount + "===============|");
        System.out.println("| You encounter a " + floorEnemy.getName() + "!");
        turnPrint();
    }

    // returns list of cards and makes user select one
    public void handSelection() {
        Scanner readOption = new Scanner(System.in);

        while (true) {
            System.out.println("  | Current Energy: <" + currentPlayer.getCurrentEnergy() + "/" + currentPlayer.getMaxEnergy() + ">");
            System.out.println("  | Current Hand");

            // catch empty hand
            if (cardPiles.getHandPile().size() == 0) {
                System.out.println("| No cards in the hand!");
                return;
            }

            // print options
            for (int i = 0; i < cardPiles.getHandPile().size(); i++) {
                System.out.println("    | [" + (i + 1) + "] " + cardPiles.getHandPile().get(i));
            }
            System.out.println("    | [B]ack");

            // handle option selection
            String[] optionNums = new String[cardPiles.getHandPile().size()];
            for (int i = 0; i < cardPiles.getHandPile().size(); i++) {
                optionNums[i] = String.valueOf(i + 1);
            }

            System.out.print("Select a card to play. > ");
            String option = readOption.next().toUpperCase();

            if (option.equals("B")) {
                return;
            } else if (Arrays.asList(optionNums).contains(option)) {
                AbstractCard cardToUse = cardPiles.getHandPile().get(Integer.parseInt(option) - 1);

                if (currentPlayer.canUseEnergy(cardToUse.getCardCost())) {
                    System.out.println("  | Using " + cardToUse.getCardName() + "!\n");
                    cardToUse.useCard();
                    cardPiles.cardUsed(cardToUse);
                    System.out.println(floorEnemy.toString());
                    System.out.println(currentPlayer.toString());
                    if (encounterOngoing())
                        break;
                } else
                    System.out.println("  | Not enough energy!\n");
            } else {
                System.out.println("| Please input a valid key.\n");
            }
        }
    }

    public void cardMenu() {
        Scanner readOption = new Scanner(System.in);

        menuSelect:
        while (encounterStatus == EncounterStatus.ONGOING) {
            System.out.print(
                    """                   
                                                        
                            | Select an option.
                            | [C]urrent Hand
                            | dr[A]w Pile
                            | [D]iscard Pile
                            | [B]ack             
                            """
            );
            System.out.print("> ");
            String option = readOption.next().toUpperCase();

            switch (option) {
                case "C":
                    handSelection();
                    break;
                case "A":
                    System.out.println("  | Draw Pile (cards are not shown in order)");
                    cardPiles.pilePrint(cardPiles.drawPile, true);  // draw pile must be printed in random order to user
                    break;
                case "D":
                    System.out.println("  | Discard Pile");
                    cardPiles.pilePrint(cardPiles.discardPile, false);
                    break;
                case "B":
                    break menuSelect;
                default:
                    System.out.println("| Please input a valid key.");
                    break;
            }
        }
    }

    public enum EncounterStatus {
        PLAYER_WON, PLAYER_LOST, ONGOING
    }
}
