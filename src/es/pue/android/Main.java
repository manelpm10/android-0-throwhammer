package es.pue.android;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Main {
    private static final int MARK_THREASHOLD = 120;
    private static final int NUM_PLAYERS = 6;
    private static final int MAX_DISTANCE = 200;
    private static ArrayList<Player> players = new ArrayList<>();

    private static void init() {
        for (int i=1; i <= Main.NUM_PLAYERS; i++) {
            players.add(new Player("Player "+i));
        }
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        Player winner;
        ArrayList<Player> finalists;

        init();

        try {
            // Get mark for each players.
            players = executeRound(players);
            showMarks(players);

            finalists = executeRound(extractFinalists(players));
            showMarks(finalists);

            winner = getWinner(finalists);
            System.out.println("\nWinner: " + winner.getName());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     *
     * @param player1
     * @param player2
     * @return String
     * /
    private static String undoTie(String player1, String player2) {
        int mark1 = throwHammer();
        int mark2 = throwHammer();

        if (mark1 == mark2) {
            return undoTie(player1, player2);
        }

        System.out.println("New "+player1+": "+mark1);
        System.out.println("New "+player2+": "+mark2);
        return (mark1 > mark2)? player1 : player2;
    }

    /**
     *
     * @param finalists
     * @return
     */
    private static Player getWinner(ArrayList<Player> finalists) {
        Collections.sort(finalists, (Player player1, Player player2) -> {
                if (player1.getMark() > player2.getMark()) {
                    return -1;
                } else if (player1.getMark() == player2.getMark()) {
                    return 0;
                } else {
                    return 1;
                }
        });

        return finalists.get(0);
    }

    private static ArrayList<Player> extractFinalists(ArrayList<Player> players) {
        ArrayList<Player> finalists = new ArrayList<>();

        for (Player player: players) {
            if (player.getMark() >= Main.MARK_THREASHOLD) {
                finalists.add(player);
            }
        }

        return finalists;
    }

    /**
     *
     * @param players
     * @return ArrayList<Integer>
     */
    private static ArrayList<Player> executeRound(ArrayList<Player> players) {
        int mark;

        System.out.println("Execute Round:");
        for (Player player: players) {
            mark = throwHammer();
            player.setMark(mark);
        }

        return players;
    }

    /**
     *
     * @param players
     */
    private static void showMarks(ArrayList<Player> players) {
        for (Player player: players) {
            System.out.println(player.getName()+": "+player.getMark());
        }
    }

    /**
     *
     * @return int
     */
    private static int throwHammer() {
        Random random = new Random();
        return random.nextInt(Main.MAX_DISTANCE);
    }
}
