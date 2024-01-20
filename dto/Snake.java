package dto;

import exceptions.BadInputException;

public class Snake {
    private int start;
    private int end;

    public Snake(int start, int end) throws BadInputException {
        this.validateSnake(start, end);
        this.start = start;
        this.end = end;
    }

    private void validateSnake(int start, int end) throws BadInputException {
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
