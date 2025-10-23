package minigames.game.lotto.input;

import minigames.game.lotto.input.exception.DuplicateNumberException;
import minigames.game.lotto.input.exception.InvalidNumberException;
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

        while (playerNumber.size() < LIMIT_NUMBERS) {
                try {
                    message.showMessage("Insert number: ");
                    String s = bufferedReader.readLine();
                    int number = Integer.parseInt(s);
                    validatorNumbers(number, playerNumber);
                    playerNumber.add(number);
                } catch (NumberFormatException e) {
                    message.showMessage("Insert number!");
                } catch (InvalidNumberException | DuplicateNumberException e) {
                    message.showMessage(e.getMessage());
                } catch (IOException e) {
                    message.showMessage("Error reading input!");
                }
            }

        return playerNumber;
    }

    private void validatorNumbers(Integer number, Set<Integer> numbers) {
        if (number >= 100 || number <= 0) {
            throw new InvalidNumberException("Input number must be 1 to 99!");
        }

        if (numbers.contains(number)) {
            throw new DuplicateNumberException("Number must be unique!");
        }
    }

}
