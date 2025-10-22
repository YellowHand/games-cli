package minigames.game.lotto.model;

import lombok.Data;

import java.util.Set;

@Data
public class Lotto {
    private final Set<Integer> drawNumbers;
    private final Set<Integer> playerNumbers;
    private final int matchedCount;

}
