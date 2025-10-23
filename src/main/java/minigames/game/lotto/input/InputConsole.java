package minigames.game.lotto.input;

import minigames.game.lotto.messaging.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class InputConsole implements InputData {
    private final Message message;
    private static final int LIMIT_NUMBERS = 6;

    public InputConsole(Message message) {
        this.message = message;
    }

    @Override
    public Set<Integer> getNumbersFromPlayer() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Set<Integer> playerNumber = new HashSet<>();
        for (int i = 0; i < LIMIT_NUMBERS; i++) {
            try {
                playerNumber.add(Integer.valueOf(bufferedReader.readLine()));
            } catch (IOException e) {
                message.showMessage("Error reading input");
            }
        }
        return playerNumber;
    }

}
