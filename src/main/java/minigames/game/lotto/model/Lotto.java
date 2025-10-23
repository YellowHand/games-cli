package minigames.game.lotto.model;

import lombok.Data;

import java.util.Set;

@Data
public class Lotto {
    private final Set<Integer> drawNumbers;
    private final Set<Integer> playerNumbers;
    private final int matchedCount;


    @Override
    public String toString() {
        return String.format("Draw numbers: %s%nYour numbers: %s%nMatched: %s%n", drawNumbers, playerNumbers, matchedCount);
    }
}
