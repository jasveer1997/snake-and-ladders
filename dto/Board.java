package dto;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private int size;
    private List<Snake> snakes;
    private List<Ladder> ladders;

    private List<Crocodile> crocodiles;
    private Map<String, Player> playerPieces;



    public Board(int size) {
        this.size = size;
        this.snakes = new ArrayList<>();
        this.ladders = new ArrayList<>();
        this.crocodiles = new ArrayList<>();
        this.playerPieces = new HashMap<>();
    }

    public int getSize() {
        return size;
    }

    public List<Snake> getSnakes() {
        return snakes;
    }

    public void setSnakes(List<Snake> snakes) {
        this.snakes = snakes;
    }

    public List<Crocodile> getCrocodiles() {
        return crocodiles;
    }

    public void setCrocodiles(List<Crocodile> crocodiles) {
        this.crocodiles = crocodiles;
    }

    public List<Ladder> getLadders() {
        return ladders;
    }

    public void setLadders(List<Ladder> ladders) {
        this.ladders = ladders;
    }

    public Map<String, Player> getPlayerPieces() {
        return playerPieces;
    }

    public void setPlayerPieces(Map<String, Player> playerPieces) {
        this.playerPieces = playerPieces;
    }
}