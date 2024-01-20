package processor;

import dto.*;

import java.util.*;

public class Simulator {
    private final Board snakeAndLadderBoard;
    private Queue<Player> players; // in case > 2 players
    private final Dice diceProcessor;

    public Simulator(int boardSize, int dices, Strategy strategy) {
        this.snakeAndLadderBoard = new Board(boardSize);
        this.players = new LinkedList<>();
        this.diceProcessor = new Dice(dices, strategy);
    }

    public void setPlayers(List<Player> players) {
        this.players = new LinkedList<>();
        Map<String, Player> playerPieces = new HashMap<>();
        for (Player player : players) {
            this.players.add(player);
            playerPieces.put(player.getId(), player);
        }
        snakeAndLadderBoard.setPlayerPieces(playerPieces);
    }

    public void setSnakes(List<Snake> snakes) {
        snakeAndLadderBoard.setSnakes(snakes);
    }

    public void setLadders(List<Ladder> ladders) {
        snakeAndLadderBoard.setLadders(ladders);
    }

    private void getNextSetOfMoves(int newPosition, List<ChanceDesc> moves) {
        int previousPosition;

        do {
            previousPosition = newPosition;
            for (Snake snake : snakeAndLadderBoard.getSnakes()) {
                if (snake.getStart() == newPosition) {
                    newPosition = snake.getEnd(); // snake bite
                    moves.add(new ChanceDesc(previousPosition, newPosition, ChanceDesc.SpecialObjects.SNAKE));
                }
            }

            for (Ladder ladder : snakeAndLadderBoard.getLadders()) {
                if (ladder.getStart() == newPosition) {
                    newPosition = ladder.getEnd(); // advance ladder
                    moves.add(new ChanceDesc(previousPosition, newPosition, ChanceDesc.SpecialObjects.LADDER));
                }
            }
            // Todo: Add crocodiles, mines
        } while (newPosition != previousPosition);
    }

    private void movePlayer(Player player, int positions) {
        int oldPosition = snakeAndLadderBoard.getPlayerPieces().get(player.getId()).getPos();
        int newPosition = oldPosition + positions;

        int boardSize = snakeAndLadderBoard.getSize();

        ArrayList<ChanceDesc> moves = new ArrayList<>();
        int finalPosition = newPosition;
        if (newPosition > boardSize*boardSize) {
            finalPosition = oldPosition;
            System.out.println(player.getName() + " rolled a " + positions + ", but it's outside board. Hence, ignoring the chance.");
        } else {
            moves.add(new ChanceDesc(oldPosition, newPosition, ChanceDesc.SpecialObjects.NORMAL));
            getNextSetOfMoves(newPosition, moves);
        }

        for (ChanceDesc move : moves) {
            move.logChance(player.getName(), positions);
            finalPosition = move.end;
        }

        // If another player exist at finalPosition -> put that at 0
        for(Map.Entry<String, Player> entrySet : snakeAndLadderBoard.getPlayerPieces().entrySet()) {
            if (entrySet.getValue().getPos() == newPosition) {
                System.out.println(player.getName() + " reached at " + newPosition + ", where " + entrySet.getValue().getName() + " is already was present, so resetting " + entrySet.getValue().getName() + " back to initial position!");
                Player newConfig = entrySet.getValue();
                newConfig.setPos(1);
                snakeAndLadderBoard.getPlayerPieces().put(entrySet.getKey(), newConfig);
            }
        }
        System.out.println("--"); // limiter to better see logs
        player.setPos(finalPosition);
        snakeAndLadderBoard.getPlayerPieces().put(player.getId(), player);
    }

    private boolean hasPlayerWon(Player player) {
        int playerPosition = snakeAndLadderBoard.getPlayerPieces().get(player.getId()).getPos();
        int boardSize = snakeAndLadderBoard.getSize();
        return playerPosition == boardSize*boardSize;
    }

    private boolean isGameCompleted() {
        int currentNumberOfPlayers = players.size();
        return currentNumberOfPlayers == 1; // change later, based on requirement
    }

    public void startGame() {
        while (!isGameCompleted()) {
            Player currentPlayer = players.poll();
            int totalDiceValue = this.diceProcessor.rollValue(currentPlayer);
            movePlayer(currentPlayer, totalDiceValue);
            if (hasPlayerWon(currentPlayer)) {
                System.out.println(currentPlayer.getName() + " wins the game");
                snakeAndLadderBoard.getPlayerPieces().remove(currentPlayer.getId());
            } else {
                players.add(currentPlayer);
            }
        }
    }
}