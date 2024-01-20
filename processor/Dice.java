package processor;

import dto.Player;
import dto.Strategy;

import java.util.Random;

public class Dice {

    private final Strategy strategy;

    private static int DICE_FACE = 6;

    private final int numDices;

    private final Random random;

    public Dice(int numDices, Strategy strategy) {
        if (numDices == 0) {
            numDices = 1;
        }
        random = new Random();
        this.numDices = numDices;
        this.strategy = strategy;
    }

    private int roll() {
        int result = 0;
        for (int i = 0; i < this.numDices; i++) {
            int roll = this.random.nextInt(DICE_FACE) + 1;
            switch (this.strategy) {
                case MIN:
                    result = (i == 0) ? roll : Math.min(result, roll);
                    break;
                case MAX:
                    result = Math.max(result, roll);
                    break;
                case SUM:
                    result += roll;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid strategy: " + strategy);
            }
        }

        return result;
    }

    // Kept this layer over roll to implement any sort of extra dice roll rules - ex below has 3 consecutive 6 -> Cancels, and we return a single next roll value
    public int rollValue(Player p) {
        int consecutiveSixes = 0;
        int totalValue = 0;

        while (consecutiveSixes < 3) {
            int roll = this.roll();

            if (roll == DICE_FACE) {
                consecutiveSixes++;
                totalValue += DICE_FACE;
                System.out.println(p.getName() + " rolled a 6! Extra turn provided. Consecutive Sixes: " + consecutiveSixes);
            } else {
                totalValue += roll;
                return totalValue;
            }
        }

        System.out.println("Three consecutive sixes, all canceled, now returning a final value of new roll, whatever it will be");
        return this.roll();
    }

}
