package dto;

import exceptions.BadInputException;

public class Ladder {
    private int start;
    private int end;
    private int bSize;

    public Ladder(int bSize, int start, int end) throws BadInputException {
        this.bSize = bSize;
        this.validateLadder(start, end);
        this.start = start;
        this.end = end;
    }

    private void validateLadder(int start, int end) throws BadInputException {
        int startRow = (start - 1)/this.bSize;
        int endRow = (end - 1)/this.bSize;
        if (startRow >= endRow) {
            throw new BadInputException("ladder start should be at strictly lower position than end");
        }
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}