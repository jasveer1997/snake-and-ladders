package dto;

import exceptions.BadInputException;

public class Crocodile {
    private int pos;

    public static final int BACK_POS = 5;

    private final int boardSize;
    public Crocodile(int boardSize, int pos) throws BadInputException {
        this.pos = pos;
        this.boardSize = boardSize;
        this.validateCrocodile(pos);
    }

    private void validateCrocodile(int pos) throws BadInputException {
        if (pos < 0 || pos >= this.boardSize*this.boardSize) {
            throw new BadInputException("crocodile posn is not valid - either it is invalid posn (outside board) or it's at the end");
        }
    }

    public int getPos() {
        return this.pos;
    }
}
