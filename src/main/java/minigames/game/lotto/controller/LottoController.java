package minigames.game.lotto.controller;

import minigames.game.lotto.input.InputData;
import minigames.game.lotto.messaging.Message;
import minigames.game.lotto.model.Lotto;
import minigames.game.lotto.service.LottoService;

import java.util.Set;


public class LottoController {
    private final LottoService lottoService;
    private final InputData inputData;
    private final Message message;

    public LottoController(LottoService lottoService, InputData inputData, Message message) {
        this.lottoService = lottoService;
        this.inputData = inputData;
        this.message = message;
    }

    public void run() {
        message.showMessage("Welcome to Lotto!");
        Set<Integer> numberFromPlayer = inputData.input();
        Lotto lotto = lottoService.playRound(numberFromPlayer);
        message.showMessage(lotto.toString());
    }

    
}
