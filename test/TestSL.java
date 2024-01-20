package test;

import dto.Ladder;
import dto.Player;
import dto.Snake;
import dto.Strategy;
import exceptions.BadInputException;
import helper.Parser;
import processor.Simulator;

import java.util.ArrayList;
import java.util.List;

public class TestSL {

    public static void main(String[] args) throws Exception {
        testCycle(); // - validation
        testPlayerMovement(); // - movements
    }

    private static void assertEquals(int expected, int actual, String testName) {
        if (expected == actual) {
            System.out.println(testName + " - Passed");
        } else {
            System.out.println(testName + " - Failed. Expected: " + expected + ", Actual: " + actual);
        }
    }

    private static void testCycle() throws BadInputException {
        Simulator gameSimulatorObj = new Simulator(10, 1, Strategy.SUM);
        List<Player> players = new ArrayList<>();
        players.add(new Player("sagar", 1));
        players.add(new Player("gaurav", 1));

        // Test 1
        List<Snake> snakes = new ArrayList<>();
        snakes.add(new Snake(10, 62, 5));
        snakes.add(new Snake(10, 33, 8));

        List<Ladder> ladders = new ArrayList<>();
        ladders.add(new Ladder(10, 5, 62));
        ladders.add(new Ladder(10, 6, 81));

        gameSimulatorObj.setPlayers(players);
        gameSimulatorObj.setSnakes(snakes);
        gameSimulatorObj.setLadders(ladders);

        Exception ex = null;
        try {
            gameSimulatorObj.validateBoard();
        } catch (BadInputException e) {
            ex = e;
        }
        if (ex != null) {
            System.out.println("cycle detection" + " - Passed");
        } else {
            System.out.println("cycle detection" + " - Failed");
        }
    }

    private static void testPlayerMovement() throws BadInputException {
        Simulator gameSimulatorObj = new Simulator(10, 1, Strategy.SUM);
        List<Player> players = new ArrayList<>();
        Player p1 = new Player("sagar", 1);
        Player p2 = new Player("gaurav", 1);
        players.add(p1);
        players.add(p2);

        // Test 1
        List<Snake> snakes = new ArrayList<>();
        snakes.add(new Snake(10, 62, 5));
        snakes.add(new Snake(10, 33, 6));
        snakes.add(new Snake(10, 49, 9));
        snakes.add(new Snake(10, 88, 16));
        snakes.add(new Snake(10, 41, 20));
        snakes.add(new Snake(10, 56, 53));
        snakes.add(new Snake(10, 98, 64));

        List<Ladder> ladders = new ArrayList<>();
        ladders.add(new Ladder(10, 2, 37));
        ladders.add(new Ladder(10, 27, 46));
        ladders.add(new Ladder(10, 10, 32));
        ladders.add(new Ladder(10, 51, 68));
        ladders.add(new Ladder(10, 61, 79));
        ladders.add(new Ladder(10, 65, 84));
        ladders.add(new Ladder(10, 90, 100));

        gameSimulatorObj.setPlayers(players);
        gameSimulatorObj.setSnakes(snakes);
        gameSimulatorObj.setLadders(ladders);

        // Test ladders
        gameSimulatorObj.movePlayer(p1, 1);
        assertEquals(37, p1.getPos(), "test ladder");

        // Test snake
        p1.setPos(61);
        gameSimulatorObj.movePlayer(p1, 1);
        assertEquals(5, p1.getPos(), "test snake");

        // Test win --- This can be classified into further categories of test - ladder/moves
        p1.setPos(89);
        gameSimulatorObj.movePlayer(p1, 1);
        assertEquals(100, p1.getPos(), "test win"); // 100 denotes bSize*bSize
    }
}
