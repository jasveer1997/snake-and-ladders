import dto.*;
import helper.Parser;
import processor.Simulator;

import java.util.*;

import static java.lang.Integer.parseInt;

public class GameHandler {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        // parse & get input values from json
        HashMap<String, String> config = Parser.parseJSONFields("./config.json");
        if (null == config) {
            System.out.println("invalid json, exiting!");
            return;
        }
        int numPlayers = parseInt(config.get("num_players"));
        int boardSize = parseInt(config.get("board_size"));
        int numSnakes = parseInt(config.get("num_snakes"));
        int numLadders = parseInt(config.get("num_ladders"));
        int numCrocodiles = parseInt(config.get("num_crocodiles"));
        int numDices = parseInt(config.get("num_dices"));
        Strategy strategy = Strategy.valueOf(config.get("movement_strategy"));

        // input
        System.out.println("Input snakes, in format x y for below " + numSnakes + " lines -> x denotes head, y denotes tail!");
        List<Snake> snakes = new ArrayList<>();
        for (int i = 0; i < numSnakes; i++) {
            snakes.add(new Snake(boardSize, scanner.nextInt(), scanner.nextInt()));
        }

        System.out.println("Input ladders, in format x y for below " + numLadders + " lines -> x denotes start, y denotes end!");
        List<Ladder> ladders = new ArrayList<>();
        for (int i = 0; i < numLadders; i++) {
            ladders.add(new Ladder(boardSize, scanner.nextInt(), scanner.nextInt()));
        }
        // Todo: Make sure we are not creating cycle with snakes & ladders

        List<Crocodile> crocodiles = new ArrayList<>();
        if (numCrocodiles > 0) {
            System.out.println("Input crocodiles, in format `x` for below " + numCrocodiles + " lines -> x denotes position!");
            for (int i = 0; i < numCrocodiles; i++) {
                crocodiles.add(new Crocodile(boardSize, scanner.nextInt()));
            }
        }

        System.out.println("Input players, in format x y for below " + numPlayers + " lines -> x denotes name, y denotes start position!");
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            players.add(new Player(scanner.next(), scanner.nextInt()));
        }

        // initialize game
        Simulator gameSimulatorObj = new Simulator(boardSize, numDices, strategy);
        gameSimulatorObj.setPlayers(players);
        gameSimulatorObj.setSnakes(snakes);
        gameSimulatorObj.setLadders(ladders);
        gameSimulatorObj.setCrocodiles(crocodiles);

        // simulate
        gameSimulatorObj.startGame();

    }

}