import minigames.game.lotto.config.LottoConfig;

import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {
        LottoConfig lottoConfig = new LottoConfig();
        lottoConfig.start();
    }
}
