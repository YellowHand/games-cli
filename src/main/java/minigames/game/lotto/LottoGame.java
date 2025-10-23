package minigames.game.lotto;

import minigames.Game;
import minigames.game.lotto.controller.LottoController;


public class LottoGame implements Game {
    private final LottoController lottoController;

    public LottoGame(LottoController lottoController) {
        this.lottoController = lottoController;
    }

    @Override
    public void play() {
        lottoController.run();
    }

    @Override
    public String getName() {
        return "Lotto";
    }
}
