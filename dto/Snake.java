package dto;

import exceptions.BadInputException;

public class Snake {
    private int start;
    private int end;

    private int bSize;

    public Snake(int bSize, int start, int end) throws BadInputException {
        this.bSize = bSize;
        this.start = start;
        this.end = end;
        this.validateSnake(start, end);

    }

    private void validateSnake(int start, int end) throws BadInputException {
        if (start <= 0 || start > this.bSize*this.bSize || end <= 0 || end > this.bSize*this.bSize) {
            throw new BadInputException("ladder start/end should be inside board posn");
        }
        if (start <= end) {
            throw new BadInputException("snake head should be at strictly greater position than tail");
        }
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}
