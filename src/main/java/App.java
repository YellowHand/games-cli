import minigames.Game;
import minigames.game.lotto.config.LottoConfig;

public class App {

    public static void main(String[] args) {
        Game lotto = new LottoConfig().createGame();
        lotto.play();
    }
}
