package minigames.game.lotto.messaging;

public class ConsoleMessage implements Message{
    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }
}
