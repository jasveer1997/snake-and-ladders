package dto;

import java.util.UUID;
public class Player {
    private String name;
    private String id;

    private int blockedChances;

    private int pos;

    public Player(String name, int pos) {
        this.name = name;
        this.pos = pos;
        this.id = UUID.randomUUID().toString();
        this.blockedChances = 0;
    }

    public String getName() {
        return name;
    }

    public int getPos() {
        return pos;
    }

    public int setPos(int pos) {
        return this.pos = pos;
    }

    public String getId() {
        return id;
    }

    public int getBlockedChances() {
        return blockedChances;
    }
}
