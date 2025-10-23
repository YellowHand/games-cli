package minigames.game.lotto.input;

import java.io.IOException;
import java.util.Set;

public interface InputData {
    Set<Integer> input() throws IOException;
}
