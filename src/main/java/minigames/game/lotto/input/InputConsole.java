package minigames.game.lotto.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class InputConsole implements InputData {

    private static final int LIMIT_NUMBERS = 6;

    @Override
    public Set<Integer> input() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Set<Integer> playerNumber = new HashSet<>();
        for (int i = 0; i < LIMIT_NUMBERS; i++) {
            playerNumber.add(Integer.valueOf(bufferedReader.readLine()));
        }
        return playerNumber;
    }

}
