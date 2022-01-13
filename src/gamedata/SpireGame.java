package gamedata;

import creatures.Cultist;
import creatures.Player;

public class SpireGame {
    public static GameLibrary gameCardData = new GameLibrary();
    public static Player mainPlayer;
    public static Encounter currentEncounter;

    // initializes data values and prints intro text
    public static void startGame() {
        gameCardData.initializeCards();
        gameCardData.initializeEnemies();

        mainPlayer = new Player(80, 3);
        System.out.println(
                """
                        |==============================Game Start==============================|
                        | You are an adventurer climbing the Spire.
                        | Play the cards you are given each turn to defeat enemies.
                        """);
    }

    public static void winGame() {
        System.out.println("| You have won the demo. Thank you for playing!");
    }

    public static void encounterLoop() {
        Encounter[] encounters = new Encounter[5];
        encounters[0] = new Encounter(1, new Cultist(), mainPlayer);
        encounters[1] = new Encounter(2, new Cultist(), mainPlayer);

        for (Encounter e : encounters) {
            currentEncounter = e;
            if (e != null) {
                e.startEncounter();
                if (e.getEncounterStatus() == Encounter.EncounterStatus.PLAYER_LOST)
                    break;
            }
        }
        winGame();
    }

    public static void main(String[] args) {
        startGame();
        encounterLoop();
    }
}
