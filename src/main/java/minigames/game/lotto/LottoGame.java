package minigames.game.lotto;

import minigames.Game;
import minigames.game.lotto.controller.LottoController;

import java.io.IOException;

public class LottoGame implements Game {
    private final LottoController lottoController;

    public LottoGame(LottoController lottoController) {
        this.lottoController = lottoController;
    }

    @Override
    public void play() throws IOException {
        lottoController.run();
    }

    @Override
    public String getName() {
        return "Lotto";
    }
}
