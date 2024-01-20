package dto;

public class ChanceDesc {

    public enum SpecialObjects {
        LADDER,
        SNAKE,
        CROCODILE,
        MINE,
        NORMAL

    }
    public int start;
    public int end;
    public SpecialObjects specialObjects;

    public ChanceDesc(int start, int end, SpecialObjects type) {
        this.start = start;
        this.end = end;
        this.specialObjects = type;
    }

    public void logChance(String playerName, int rolledValue) {
        switch (this.specialObjects) {
            case LADDER:
                System.out.println("Due to rolled position advance to " + this.start + ", " + playerName + " climbed the ladder at " + this.start + " and moved from " + this.start + " to " + this.end);
                break;
            case SNAKE:
                System.out.println("Due to rolled position advance to " + this.start + ", " + playerName + " got bitten by snake at " + this.start + " and moved from " + this.start + " to " + this.end);
                break;
            case CROCODILE:
                System.out.println(playerName + " rolled a " + rolledValue + " and encounters a crocodile at " + this.start + " and moved from " + this.start + " to " + this.end);
                break;
            case MINE:
                System.out.println(playerName + " rolled a " + rolledValue + " and encounters a crocodile at " + this.start + " and moved from " + this.start + " to " + this.end);
                break;
            case NORMAL:
                System.out.println(playerName + " rolled a total of (including consecutive sixes) " + rolledValue + " and moved from " + this.start + " to " + this.end);
                break;
        }
    }
}
