package minigames.game.lotto.config;

import minigames.game.lotto.controller.LottoController;
import minigames.game.lotto.input.InputConsole;
import minigames.game.lotto.messaging.ConsoleMessage;
import minigames.game.lotto.service.LottoService;

import java.io.IOException;

public class LottoConfig {

    public void lottoGame() throws IOException {
        ConsoleMessage consoleMessage = new ConsoleMessage();
        InputConsole inputConsole = new InputConsole();
        LottoService lottoService = new LottoService();
        LottoController lottoController = new LottoController(lottoService, inputConsole, consoleMessage);
        lottoController.run();
    }
}
